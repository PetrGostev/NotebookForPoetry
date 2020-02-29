package com.example.notebookforpoetry.DataService;

import com.example.notebookforpoetry.DataService.Object.Write;


public interface WriteCallback {
    void done(Error error, Write write);
}
