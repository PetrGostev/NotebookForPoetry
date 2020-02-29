package com.example.notebookforpoetry.Write.Assembly;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;

import com.example.notebookforpoetry.DataService.DataService;
import com.example.notebookforpoetry.DataService.DataServiceInterface;
import com.example.notebookforpoetry.Write.Presenter.WritePresenter;
import com.example.notebookforpoetry.Write.Router.WriteRouter;
import com.example.notebookforpoetry.Write.View.WriteFragment;
import com.example.notebookforpoetry.Write.Interactor.WriteViewModel;
import com.example.notebookforpoetry.WritesList.Interactor.WritesListViewModel;
import com.example.notebookforpoetry.WritesList.View.StartActivity;


public class WriteAssembly {

    private static String ID_KEY = "ID_KEY";
    private static String FOR_CREATE_KEY = "FOR_CREATE_KEY";

    public static WriteFragment writeModule(String id, boolean isForCreate) {
        WriteFragment fragment = new WriteFragment();

        Bundle args = new Bundle();

        if (id != null) {
            args.putString(ID_KEY, id);
        }

        args.putBoolean(FOR_CREATE_KEY, isForCreate);

        fragment.setArguments(args);

        return fragment;
    }

    public static void configureModule(WriteFragment fragment) {
        Bundle args = fragment.getArguments();

        String id = null;
        boolean isForCreate = false;

        if (args != null) {
            id = args.getString(ID_KEY);
            isForCreate = args.getBoolean(FOR_CREATE_KEY);
        }

        DataServiceInterface dataService = DataService.getInstance(fragment.getContext());
        WritePresenter presenter = new WritePresenter();
        WriteViewModel viewModel = ViewModelProviders.of(fragment).get(WriteViewModel.class);
        StartActivity startActivity = (StartActivity) fragment.getActivity();
        WriteRouter router = new WriteRouter(fragment.getActivity());

        fragment.setViewOutput(presenter);
        fragment.setViewModel(viewModel);

        viewModel.setId(id);
        viewModel.setDataService(dataService);

        presenter.setRouter(router);
        presenter.setViewModel(viewModel);

        if (isForCreate) {
            presenter.configureForCreate();
        }

        router.setStartActivity(startActivity);
    }
}
