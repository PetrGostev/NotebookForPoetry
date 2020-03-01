package com.petrgostev.NotebookForPoetry.WritesList.Interactor;

import android.arch.lifecycle.LiveData;

import com.petrgostev.NotebookForPoetry.Write.Interactor.WriteViewItem;

import java.util.ArrayList;


public interface WritesListViewModelInput {

    LiveData<ArrayList<WriteViewItem>> getWriteViewItems();

    void loadWrites();

    void deleteWrite(String id);
}
