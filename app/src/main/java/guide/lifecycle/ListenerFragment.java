package guide.lifecycle;

import android.app.Fragment;

import guide.util.GuideLogUtil;


public class ListenerFragment extends Fragment {

    FragmentLifecycle mFragmentLifecycle;

    public void setFragmentLifecycle(FragmentLifecycle lifecycle) {
        mFragmentLifecycle = lifecycle;
    }

    @Override
    public void onStart() {
        super.onStart();
        GuideLogUtil.d("onStart: ");
        if (mFragmentLifecycle != null) mFragmentLifecycle.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mFragmentLifecycle != null) mFragmentLifecycle.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mFragmentLifecycle != null) mFragmentLifecycle.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        GuideLogUtil.d("onDestroy: ");
        if (mFragmentLifecycle != null) mFragmentLifecycle.onDestroy();
    }
}
