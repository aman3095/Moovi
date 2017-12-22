package aman.com.moovi.ui.main;

import java.util.ArrayList;

import aman.com.moovi.model.Program;
import aman.com.moovi.ui.base.MvpView;

/**
 * Created by amanpreetsingh on 12/21/17.
 */

public interface ProgramListView extends MvpView {

    <T extends Program> void showProgramList(ArrayList<T> programs);

    void showError();

}
