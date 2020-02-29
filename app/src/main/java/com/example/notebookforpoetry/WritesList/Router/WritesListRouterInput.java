package com.example.notebookforpoetry.WritesList.Router;


import com.example.notebookforpoetry.DataService.EditCallback;

import java.util.ArrayList;

public interface WritesListRouterInput {
    void presentWriteForCreate();

    void presentWriteFragment(String id);

    void presentDeleteWriteAlert(EditCallback editCallback);
}
