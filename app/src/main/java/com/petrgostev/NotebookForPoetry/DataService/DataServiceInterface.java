package com.petrgostev.NotebookForPoetry.DataService;


public interface DataServiceInterface {
    void saveWrite(String title, String text, WriteCallback writeCallback);

    void getWrite(String id,WriteCallback writeCallback);

    void getWrites(WritesCallback writesCallback);

    void deleteWrite(String id, WritesCallback callback);

    void updateWrite(String id, String title, String text, WriteCallback writeCallback);

}
