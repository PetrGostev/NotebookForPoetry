package com.example.notebookforpoetry.Write.Router;

import com.example.notebookforpoetry.DataService.EditCallback;


public interface WriteRouterInput {

    void presentEditAlert(String title, EditCallback callback);

    void closeModule();

    void presentInfoAlert(String title);
}
