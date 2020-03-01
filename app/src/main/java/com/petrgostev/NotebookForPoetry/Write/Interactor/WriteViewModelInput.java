package com.petrgostev.NotebookForPoetry.Write.Interactor;

import android.arch.lifecycle.LiveData;


public interface WriteViewModelInput {

    void saveWrite(String title, String write);

    void updateWrite(String title, String write);

    LiveData<WriteViewItem> getWriteViewItem();
}
