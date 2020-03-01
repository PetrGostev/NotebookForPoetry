package com.petrgostev.NotebookForPoetry.DataService;

import com.petrgostev.NotebookForPoetry.DataService.Object.Write;

import io.realm.RealmList;

public interface WritesCallback {
    void done(Error error, RealmList<Write> writes);
}
