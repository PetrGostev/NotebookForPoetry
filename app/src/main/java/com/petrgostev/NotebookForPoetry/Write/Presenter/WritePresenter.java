package com.petrgostev.NotebookForPoetry.Write.Presenter;

import com.petrgostev.NotebookForPoetry.DataService.EditCallback;
import com.petrgostev.NotebookForPoetry.Write.Router.WriteRouterInput;
import com.petrgostev.NotebookForPoetry.Write.View.WriteViewOutput;
import com.petrgostev.NotebookForPoetry.Write.Interactor.WriteViewModelInput;


public class WritePresenter implements WriteViewOutput {

    private WriteViewModelInput viewModel;
    private WriteRouterInput router;

    private boolean isForCreate = false;

    public void setViewModel(WriteViewModelInput viewModel) {
        this.viewModel = viewModel;
    }

    public void setRouter(WriteRouterInput router) {
        this.router = router;
    }

    public void configureForCreate() {
        isForCreate = true;
    }

    @Override
    public void vewDidTapSave(final String title, final String write) {
        if (title.isEmpty()) {
            String titleAlert = "Напишите заголовок!";
            router.presentInfoAlert(titleAlert);
            return;
        }

        if (isForCreate) {
            viewModel.saveWrite(title, write);

        } else {
            String titleAlert = "Сохранить изменения?";
            router.presentEditAlert(titleAlert, new EditCallback() {
                @Override
                public void done(boolean ok) {
                    if (ok) {
                        viewModel.updateWrite(title, write);

                    } else {
                        router.closeModule();
                    }
                }
            });
        }
    }
}
