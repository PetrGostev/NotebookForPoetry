package com.petrgostev.NotebookForPoetry.WritesList.Router;


import com.petrgostev.NotebookForPoetry.DataService.EditCallback;

public interface WritesListRouterInput {
    void presentWriteForCreate();

    void presentWriteFragment(String id);

    void presentDeleteWriteAlert(EditCallback editCallback);
}
