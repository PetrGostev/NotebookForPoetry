package com.petrgostev.NotebookForPoetry.DataService;

import android.annotation.SuppressLint;
import android.content.Context;

import com.petrgostev.NotebookForPoetry.DataService.Object.Write;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;


public class DataService implements DataServiceInterface {

    private static DataService ourInstance;

    private RealmList<Write> writes;

    private Realm realm;

    private Write write;

    public static DataService getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new DataService(context);
        }

        return ourInstance;
    }

    @SuppressLint("SimpleDateFormat")
    private DataService(Context context) {
        Context context1 = context.getApplicationContext();
        Realm.init(context);
        realm = Realm.getDefaultInstance();
        writes = new RealmList<>();
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public void saveWrite(String title, String text, WriteCallback callback) {
        if (write == null) {
            write = new Write();
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");

        write.setId(dateFormat.format(new Date()));
        write.setTitle(title);
        write.setText(text);
        write.setWriteDate(new Date());
        write.setEditDate(new Date());
        writes.add(write);
        addToDataBase(write);

        callback.done(null, write);
    }

    @Override
    public void updateWrite(String id, String title, String text, WriteCallback callback) {
        realm.beginTransaction();

        RealmList<Write> responseRealm = getWritesFromDB();

        for (Write write : responseRealm) {
            if (write.getId().equals(id)) {
                write.setTitle(title);
                write.setText(text);
                write.setEditDate(new Date());

                callback.done(null, write);
            }
        }

        writes = new RealmList<>();
        writes.addAll(responseRealm);

        realm.copyToRealmOrUpdate(responseRealm);

        realm.commitTransaction();
    }

    @Override
    public void getWrite(String id, WriteCallback callback) {

        if (writes != null) {

            for (Write write : writes) {

                if (write.getId().equals(id)) {
                    callback.done(null, write);

                } else {
                    callback.done(new Error("Write-null"), null);
                }

            }
        } else {

            for (Write write : getWritesFromDB()) {

                if (write.getId().equals(id)) {
                    callback.done(null, write);

                }else {
                    callback.done(new Error("Write-null"), null);
                }
            }
        }
    }

    @Override
    public void getWrites(WritesCallback callback) {
        if (!writes.isEmpty()) {
            callback.done(null, writes);
        } else {
            writes = new RealmList<>();

            callback.done(null, getWritesFromDB());
        }
    }

    @Override
    public void deleteWrite(String id, WritesCallback callback) {
        Iterator<Write> writeIterator = writes.iterator();
        while (writeIterator.hasNext()) {

            Write write = writeIterator.next();
            if (write.getId().equals(id)) {
                writeIterator.remove();
            }
        }
        realm.beginTransaction();

        RealmResults<Write> writeRealmResults = realm.where(Write.class)
                .equalTo("id", id)
                .findAll();

        writeRealmResults.deleteAllFromRealm();

        realm.commitTransaction();

        callback.done(null, writes);
    }

    //Private

    private RealmList<Write> getWritesFromDB() {
        RealmQuery<Write> realmQuery = realm.where(Write.class);
        RealmResults<Write> all = realmQuery.findAll();

        writes = new RealmList<>();
        writes.addAll(all);

        return writes;
    }

    private void addToDataBase(Write write) {
        realm.beginTransaction();

        writes.add(write);

        RealmList<Write> responseRealm = getWritesFromDB();

        if (responseRealm == null) {
            responseRealm = new RealmList<>();
        }

        responseRealm.add(write);

        realm.copyToRealmOrUpdate(responseRealm);

        realm.commitTransaction();
    }
}
