package com.petrgostev.NotebookForPoetry.Write.Router;

import com.petrgostev.NotebookForPoetry.DataService.EditCallback;


public interface WriteRouterInput {

    void presentEditAlert(String title, EditCallback callback);

    void closeModule();

    void presentInfoAlert(String title);
}
