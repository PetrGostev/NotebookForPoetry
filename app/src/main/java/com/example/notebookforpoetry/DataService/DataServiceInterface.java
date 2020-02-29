package com.example.notebookforpoetry.DataService;

import java.util.ArrayList;


public interface DataServiceInterface {
    void saveWrite(String title, String text, WriteCallback writeCallback);

    void getWrite(String id,WriteCallback writeCallback);

    void getWrites(WritesCallback writesCallback);

    void deleteWrites(ArrayList<String> ids, WritesCallback callback);

    void deleteWrite(String id, WritesCallback callback);

    void updateWrite(String id, String title, String text, WriteCallback writeCallback);
}
