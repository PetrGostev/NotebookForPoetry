package com.petrgostev.NotebookForPoetry.WritesList.Presenter;

import com.petrgostev.NotebookForPoetry.DataService.EditCallback;
import com.petrgostev.NotebookForPoetry.Write.Interactor.WriteViewItem;
import com.petrgostev.NotebookForPoetry.WritesList.Router.WritesListRouterInput;
import com.petrgostev.NotebookForPoetry.WritesList.View.ListViewOutput;
import com.petrgostev.NotebookForPoetry.WritesList.Interactor.WritesListViewModelInput;


public class WritesListPresenter implements ListViewOutput {

    private WritesListRouterInput router;
    private WritesListViewModelInput viewModel;

    public void setRouter(WritesListRouterInput router) {
        this.router = router;
    }

    public void setViewModel(WritesListViewModelInput viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void viewDidTapAddButton() {
        router.presentWriteForCreate();
    }

    @Override
    public void viewDidTapDeleteButton(final String id) {

        router.presentDeleteWriteAlert(new EditCallback() {
            @Override
            public void done(boolean ok) {
                if (ok) {
                    viewModel.deleteWrite(id);

                } else {
                    viewModel.loadWrites();
                }
            }
        });
    }

    @Override
    public void viewDidSelectItem(WriteViewItem viewItem) {
        router.presentWriteFragment(viewItem.id);
    }

    @Override
    public void viewDidBackPressed() {
        viewModel.loadWrites();
    }
}
