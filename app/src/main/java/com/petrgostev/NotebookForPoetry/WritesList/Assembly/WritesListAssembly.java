package com.petrgostev.NotebookForPoetry.WritesList.Assembly;

import android.arch.lifecycle.ViewModelProviders;

import com.petrgostev.NotebookForPoetry.DataService.DataService;
import com.petrgostev.NotebookForPoetry.WritesList.Presenter.WritesListPresenter;
import com.petrgostev.NotebookForPoetry.WritesList.Router.WritesWritesListRouter;
import com.petrgostev.NotebookForPoetry.WritesList.View.StartActivity;
import com.petrgostev.NotebookForPoetry.WritesList.Interactor.WritesListViewModel;


public class WritesListAssembly {

    public static void configureModule(StartActivity view) {

        DataService dataService = DataService.getInstance(view);
        WritesListPresenter presenter = new WritesListPresenter();
        WritesWritesListRouter router = new WritesWritesListRouter(view);
        WritesListViewModel viewModel = ViewModelProviders.of(view).get(WritesListViewModel.class);

        view.setViewOutput(presenter);
        view.setViewModel(viewModel);

        viewModel.setDataService(dataService);

        presenter.setRouter(router);
        presenter.setViewModel(viewModel);
    }
}
