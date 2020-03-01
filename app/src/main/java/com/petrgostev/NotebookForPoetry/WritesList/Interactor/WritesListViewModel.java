package com.petrgostev.NotebookForPoetry.WritesList.Interactor;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.petrgostev.NotebookForPoetry.DataService.DataService;
import com.petrgostev.NotebookForPoetry.DataService.DataServiceInterface;
import com.petrgostev.NotebookForPoetry.DataService.Object.Write;
import com.petrgostev.NotebookForPoetry.DataService.WritesCallback;
import com.petrgostev.NotebookForPoetry.Write.Interactor.WriteViewItem;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import io.realm.RealmList;


public class WritesListViewModel extends ViewModel implements WritesListViewModelInput {

    private DataServiceInterface dataService;

    private MutableLiveData<ArrayList<WriteViewItem>> itemsMutableLiveData;
    private MutableLiveData<ArrayList<String>> idsForDelete;

    private ArrayList<WriteViewItem> viewItems;
    private ArrayList<String> ids;

    public void setDataService(DataService dataService) {
        this.dataService = dataService;
    }

    @Override
    public LiveData<ArrayList<WriteViewItem>> getWriteViewItems() {
        if (itemsMutableLiveData == null) {
            itemsMutableLiveData = new MutableLiveData<>();
            loadWrites();
        }

        return itemsMutableLiveData;
    }

    @Override
    public void loadWrites() {
        dataService.getWrites(new WritesCallback() {
            @Override
            public void done(Error error, RealmList<Write> writes) {
                if (error == null && writes != null) {
                    loadViewItems(writes);
                }
            }
        });
    }

    @Override
    public void deleteWrite(String id) {
        dataService.deleteWrite(id, new WritesCallback() {
            @Override
            public void done(Error error, RealmList<Write> writes) {
                if (error == null && writes != null) {
                    loadViewItems(writes);
                }
            }
        });
    }

    //Private

    private void loadViewItems(RealmList<Write> writes) {
        viewItems = new ArrayList<>();

        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        for (Write write : writes) {

            WriteViewItem writeViewItem = new WriteViewItem();
            String dateWrite = dateFormat.format(write.getWriteDate());
            String dateEdit = dateFormat.format(write.getEditDate());

            writeViewItem.id = write.getId();
            writeViewItem.title = write.getTitle();
            writeViewItem.text = write.getText();
            writeViewItem.dateWrite = dateWrite;
            writeViewItem.dateEdit = dateEdit;

            viewItems.add(writeViewItem);
        }

        itemsMutableLiveData.postValue(viewItems);
    }
}
