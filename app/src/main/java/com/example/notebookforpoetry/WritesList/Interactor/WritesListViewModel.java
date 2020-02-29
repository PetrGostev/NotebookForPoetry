package com.example.notebookforpoetry.WritesList.Interactor;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.notebookforpoetry.DataService.DataService;
import com.example.notebookforpoetry.DataService.DataServiceInterface;
import com.example.notebookforpoetry.DataService.Object.Write;
import com.example.notebookforpoetry.DataService.WritesCallback;
import com.example.notebookforpoetry.Write.Interactor.WriteViewItem;

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
    public LiveData<ArrayList<String>> getIdsForDelete() {
        if (idsForDelete == null) {
            idsForDelete = new MutableLiveData<>();
        }

        return idsForDelete;
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

    @Override
    public void createViewItemsForDelete(String id) {
        ids = new ArrayList<>();
        idsForDelete = new MutableLiveData<>();

        ids.add(id);

        updateViewItemsForSelectedCheckBow(true, id);
    }

    @Override
    public void updateViewItemsForDelete(boolean checked, String id) {
        if (checked) {
            ids.add(id);
            updateViewItemsForSelectedCheckBow(true, id);
        }
    }

    @Override
    public void RevertView() {
        ids = new ArrayList<>();

        for (WriteViewItem viewItem : viewItems) {
            viewItem.isSelectedCheckBox = false;
            viewItem.isSelectedForShowCheckBox = false;
        }

        idsForDelete.postValue(ids);
        itemsMutableLiveData.postValue(viewItems);
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

    private void updateViewItemsForSelectedCheckBow(boolean checked, String id) {

        for (WriteViewItem item : viewItems) {
            item.isSelectedForShowCheckBox = checked;

            if (item.id.equals(id)) {
                item.isSelectedCheckBox = checked;
            }
        }
        idsForDelete.setValue(ids);
        itemsMutableLiveData.postValue(viewItems);
    }
}
