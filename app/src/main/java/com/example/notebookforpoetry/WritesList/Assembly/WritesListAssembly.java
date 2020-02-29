package com.example.notebookforpoetry.WritesList.Assembly;

import android.arch.lifecycle.ViewModelProviders;

import com.example.notebookforpoetry.DataService.DataService;
import com.example.notebookforpoetry.WritesList.Presenter.WritesListPresenter;
import com.example.notebookforpoetry.WritesList.Router.WritesWritesListRouter;
import com.example.notebookforpoetry.WritesList.View.StartActivity;
import com.example.notebookforpoetry.WritesList.Interactor.WritesListViewModel;


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
