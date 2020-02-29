package com.example.notebookforpoetry.Write.Presenter;

import com.example.notebookforpoetry.DataService.EditCallback;
import com.example.notebookforpoetry.Write.Router.WriteRouterInput;
import com.example.notebookforpoetry.Write.View.WriteViewOutput;
import com.example.notebookforpoetry.Write.Interactor.WriteViewModelInput;


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
