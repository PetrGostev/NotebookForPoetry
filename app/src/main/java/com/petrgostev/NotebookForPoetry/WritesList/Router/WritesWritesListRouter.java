package com.petrgostev.NotebookForPoetry.WritesList.Router;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.petrgostev.NotebookForPoetry.DataService.EditCallback;
import com.petrgostev.NotebookForPoetry.R;
import com.petrgostev.NotebookForPoetry.Utils.DialogUtils;
import com.petrgostev.NotebookForPoetry.Write.Assembly.WriteAssembly;
import com.petrgostev.NotebookForPoetry.WritesList.View.StartActivity;

import java.util.ArrayList;

public class WritesWritesListRouter implements WritesListRouterInput {

    private StartActivity getActivity;

    public WritesWritesListRouter(StartActivity getActivity) {
        this.getActivity = getActivity;
    }

    @Override
    public void presentWriteForCreate() {
        FragmentManager fragmentManager = getActivity.getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment fragment = WriteAssembly.writeModule(null, true);

        fragmentTransaction.replace(R.id.frame, fragment, null);
        fragmentTransaction.addToBackStack(fragment.getTag());
        fragmentTransaction.commit();
    }

    @Override
    public void presentWriteFragment(String id) {
        FragmentManager fragmentManager = getActivity.getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment fragment = WriteAssembly.writeModule(id, false);

        fragmentTransaction.replace(R.id.frame, fragment, null);
        fragmentTransaction.addToBackStack(fragment.getTag());
        fragmentTransaction.commit();
    }

    public void presentEditAlert(ArrayList<String> ids, final EditCallback callback) {
        DialogUtils.showDeleteWritesAlertDialog(ids, getActivity, new EditCallback() {
            @Override
            public void done(boolean ok) {
                callback.done(ok);
            }
        });
    }

    @Override
    public void presentDeleteWriteAlert(final EditCallback callback) {
        DialogUtils.showDeleteWriteAlertDialog(getActivity, new EditCallback() {
            @Override
            public void done(boolean ok) {
                callback.done(ok);
            }
        });
    }
}
