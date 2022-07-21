package com.lhh.lnstagram.mvvm.base;

import android.app.Activity;
import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;



import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.callback.UpdateOrDeleteCallback;

import java.io.File;
import java.util.Calendar;

/**
 * @author sunling
 * @date 2019/6/18
 */
public class EmptyViewModel extends AbsViewModel<AbsRepository> {
    public EmptyViewModel(@NonNull Application application) {
        super(application);
    }


}
