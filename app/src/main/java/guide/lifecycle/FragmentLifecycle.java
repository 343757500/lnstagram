package guide.lifecycle;

public interface FragmentLifecycle {
    void onStart();

    void onStop();

    void onDestroyView();

    void onDestroy();

    void onResume();

    void onPause();
}