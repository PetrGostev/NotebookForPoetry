package com.example.notebookforpoetry.DataService;

import com.example.notebookforpoetry.DataService.Object.Write;

import io.realm.RealmList;


public interface WritesCallback {
    void done(Error error, RealmList<Write> writes);
}
