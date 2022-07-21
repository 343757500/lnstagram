package com.lhh.lnstagram.mvvm.base;

import android.os.AsyncTask;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.lhh.lnstagram.https.BaseResp;
import com.lhh.lnstagram.task.BitmapTaskExecutor;


/**
 * 获取数据源（从DB或API）
 * @param <ResultType>
 */
public abstract class AbsDataSource<ResultType> {

    private final MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();

    // 存储API返回的数据
    @WorkerThread
    protected abstract void saveApiRespResult(@NonNull BaseResp baseResp);

    // 根据DB获取的结果，判断是否需要从API获取数据
    @MainThread
    protected abstract boolean shouldReqApi(@Nullable ResultType data);

    // 从DB获取数据
    @NonNull
    @MainThread
    protected abstract LiveData<ResultType> loadFromDb();

    // 创建API请求
    @NonNull
    @MainThread
    protected abstract LiveData<BaseResp> createApiReq();


    @MainThread
    public AbsDataSource() {
        final LiveData<ResultType> dbLiveData = loadFromDb();
        result.addSource(dbLiveData, new Observer<ResultType>() {
            @Override
            public void onChanged(@Nullable ResultType resultType) {
                result.postValue(Resource.success(resultType, false));
                result.removeSource(dbLiveData);
                if (shouldReqApi(resultType)) {
                    reqFromNetwork(dbLiveData);
                } else {
                    result.addSource(dbLiveData, new Observer<ResultType>() {
                        @Override
                        public void onChanged(@Nullable ResultType resultType) {
                            result.postValue(Resource.success(resultType, false));
                        }
                    });
                }
            }
        });
    }


    /**
     * 监听API返回的LiveData（需要实现createApiReq()返回LiveData）
     * @param dbLiveData 数据库返回的LiveDta
     */
    private void reqFromNetwork(final LiveData<ResultType> dbLiveData) {
        final LiveData<BaseResp> baseRespLiveData = createApiReq();
        result.addSource(baseRespLiveData, new Observer<BaseResp>() {
            @Override
            public void onChanged(@Nullable final BaseResp baseResp) {
                result.removeSource(baseRespLiveData);
                //noinspection ConstantConditions
                if (baseResp.isOk()) {
                    saveResultAndReInit(baseResp);
                } else {
                    result.addSource(dbLiveData, new Observer<ResultType>() {
                        @Override
                        public void onChanged(@Nullable ResultType resultType) {
                            result.setValue(Resource.error(resultType, baseResp.getMsg()));
                        }
                    });
                }
            }
        });
    }

    /**
     * 保存数据（需要实现saveApiRespResult() ）
     * 并展示给UI（）
     * @param baseResp
     */
    @MainThread
    private void saveResultAndReInit(final BaseResp baseResp) {
        // LogUtil.e("ABS", "saveResultAndReInit---000000------------------------------");
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                // LogUtil.e("ABS", "saveResultAndReInit---1111111---saveApiRespResult");
                saveApiRespResult(baseResp);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                // we specially request a new live getDate,
                // otherwise we will get immediately last cached value,
                // which may not be updated with latest results received from network.
                // LogUtil.e("ABS", "saveResultAndReInit---222222222222---onPostExecute");
                LiveData<ResultType> dbLiveData = loadFromDb();
                result.addSource(dbLiveData, new Observer<ResultType>() {
                    @Override
                    public void onChanged(@Nullable ResultType resultType) {
                        // LogUtil.e("ABS", "saveResultAndReInit---333333333---onChanged");
                        result.postValue(Resource.success(resultType, true));
                    }
                });
            }
//        }.execute();
        }.executeOnExecutor(BitmapTaskExecutor.getInstance().getExecutor());
    }

    public final MediatorLiveData<Resource<ResultType>> getAsLiveData() {
        return result;
    }

}
