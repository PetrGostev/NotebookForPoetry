package com.example.notebookforpoetry.Write.Interactor;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.notebookforpoetry.DataService.DataServiceInterface;
import com.example.notebookforpoetry.DataService.Object.Write;
import com.example.notebookforpoetry.DataService.WriteCallback;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class WriteViewModel extends ViewModel implements WriteViewModelInput {

    private DataServiceInterface dataService;

    private String id;

    private MutableLiveData<WriteViewItem> itemMutableLiveData;

    public void setDataService(DataServiceInterface dataService) {
        this.dataService = dataService;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public LiveData<WriteViewItem> getWriteViewItem() {
        if (itemMutableLiveData == null) {
            itemMutableLiveData = new MutableLiveData<>();
            loadData();
        }
        return itemMutableLiveData;
    }

    @Override
    public void saveWrite(String title, String write) {
        dataService.saveWrite(title, write, new WriteCallback() {
            @Override
            public void done(Error error, Write write) {
                if (error == null && write != null) {
                    loadViewItem(write);
                }
            }
        });
    }

    @Override
    public void updateWrite(String title, String write) {
        dataService.updateWrite(id, title, write, new WriteCallback() {
            @Override
            public void done(Error error, Write write) {
                if (error == null && write != null) {
                    loadViewItem(write);
                }
            }
        });
    }

    //Private

    private void loadData() {

        dataService.getWrite(id, new WriteCallback() {
            @Override
            public void done(Error error, Write write) {
                if (error == null && write != null) {
                    loadViewItem(write);
                }
            }
        });
    }

    private void loadViewItem(Write write) {
        WriteViewItem viewItem;

        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String dateWrite = dateFormat.format(write.getWriteDate());
        String dateEdit = dateFormat.format(write.getEditDate());

        viewItem = new WriteViewItem();
        viewItem.id = id;
        viewItem.title = write.getTitle();
        viewItem.text = write.getText();
        viewItem.dateWrite = dateWrite;
        viewItem.dateEdit = dateEdit;

        itemMutableLiveData.postValue(viewItem);
    }
}
