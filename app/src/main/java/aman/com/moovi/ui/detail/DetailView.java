package aman.com.moovi.ui.detail;

import aman.com.moovi.model.Program;
import aman.com.moovi.ui.base.MvpView;

/**
 * Created by amanpreetsingh on 12/22/17.
 */

public interface DetailView extends MvpView {

    void showBackgroundImage(String posterPath);

    void showProgramDetails(Program program);

    void showError();

}
