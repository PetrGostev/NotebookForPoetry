package com.petrgostev.NotebookForPoetry.WritesList.View;

import com.petrgostev.NotebookForPoetry.Write.Interactor.WriteViewItem;


public interface ListViewOutput {

    void viewDidTapAddButton();

    void viewDidTapDeleteButton(String id);

    void viewDidSelectItem(WriteViewItem viewItem);

    void viewDidBackPressed();
}
