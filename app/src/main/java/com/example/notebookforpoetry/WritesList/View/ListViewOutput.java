package com.example.notebookforpoetry.WritesList.View;

import com.example.notebookforpoetry.Write.Interactor.WriteViewItem;

import java.util.ArrayList;


public interface ListViewOutput {

    void viewDidTapAddButton();

    void viewDidTapDeleteButton(String id);

    void viewDidLongClick(String id);

    void viewDidSelectItem(WriteViewItem viewItem);

    void viewDidTapRevertButton();

    void viewDidCheckedViewItem(boolean checked, String id);

    void viewDidBackPressed();
}
