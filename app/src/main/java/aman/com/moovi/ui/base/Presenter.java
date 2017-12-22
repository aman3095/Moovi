package aman.com.moovi.ui.base;

/**
 * Created by amanpreetsingh on 12/21/17.
 */

public interface Presenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();
}
