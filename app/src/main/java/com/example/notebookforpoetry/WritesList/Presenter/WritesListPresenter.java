package com.example.notebookforpoetry.WritesList.Presenter;

import com.example.notebookforpoetry.DataService.EditCallback;
import com.example.notebookforpoetry.Write.Interactor.WriteViewItem;
import com.example.notebookforpoetry.WritesList.Router.WritesListRouterInput;
import com.example.notebookforpoetry.WritesList.View.ListViewOutput;
import com.example.notebookforpoetry.WritesList.Interactor.WritesListViewModelInput;

import java.util.ArrayList;


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
    public void viewDidTapRevertButton() {
        viewModel.RevertView();
    }

    @Override
    public void viewDidLongClick(String id) {
        viewModel.createViewItemsForDelete(id);
    }

    @Override
    public void viewDidCheckedViewItem(boolean checked, String id) {
        viewModel.updateViewItemsForDelete(checked, id);
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
