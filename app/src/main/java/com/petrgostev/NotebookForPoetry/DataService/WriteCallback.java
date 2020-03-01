package com.petrgostev.NotebookForPoetry.DataService;

import com.petrgostev.NotebookForPoetry.DataService.Object.Write;


public interface WriteCallback {
    void done(Error error, Write write);
}
