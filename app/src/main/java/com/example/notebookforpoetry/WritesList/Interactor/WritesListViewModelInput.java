package com.example.notebookforpoetry.WritesList.Interactor;

import android.arch.lifecycle.LiveData;

import com.example.notebookforpoetry.Write.Interactor.WriteViewItem;

import java.util.ArrayList;


public interface WritesListViewModelInput {

    LiveData<ArrayList<WriteViewItem>> getWriteViewItems();

    void createViewItemsForDelete(String id);

    void updateViewItemsForDelete(boolean checked, String id);

    LiveData<ArrayList<String>> getIdsForDelete();

    void loadWrites();

    void deleteWrite(String id);

    void RevertView();
}
