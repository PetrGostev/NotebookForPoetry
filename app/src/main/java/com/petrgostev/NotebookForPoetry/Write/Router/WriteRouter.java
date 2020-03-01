package com.petrgostev.NotebookForPoetry.Write.Router;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.petrgostev.NotebookForPoetry.DataService.EditCallback;
import com.petrgostev.NotebookForPoetry.Utils.DialogUtils;
import com.petrgostev.NotebookForPoetry.WritesList.View.StartActivityInput;


public class WriteRouter implements WriteRouterInput {

    private FragmentActivity getActivity;
    private StartActivityInput startActivityInput;

    public WriteRouter(FragmentActivity getActivity) {
        this.getActivity = getActivity;
    }

    public void setStartActivity(StartActivityInput startActivityInput) {
        this.startActivityInput = startActivityInput;
    }

    @Override
    public void presentEditAlert(final String title, final EditCallback callback) {
        DialogUtils.showEditAlertDialog(getActivity, title, new EditCallback() {
            @Override
            public void done(boolean ok) {
                callback.done(ok);
            }
        });
    }

    @Override
    public void presentInfoAlert(String title) {
        DialogUtils.showInfoAlertDialog(getActivity, title);
    }

    @Override
    public void closeModule() {
        FragmentManager fragmentManager = getActivity.getSupportFragmentManager();

        if (fragmentManager != null) {
            fragmentManager.popBackStack();
        }
        startActivityInput.visibilityFab(true);
    }
}
