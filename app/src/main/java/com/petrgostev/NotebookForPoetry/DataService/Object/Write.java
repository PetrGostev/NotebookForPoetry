package com.petrgostev.NotebookForPoetry.DataService.Object;

import java.util.Date;

import io.realm.RealmObject;


public class Write extends RealmObject {

    private String title;
    private String text;
    private Date writeDate;
    private Date editDate;
    private String id;

    public String getTitle() {
        return title != null ? title : "";
    }

    public String getText() {
        return text != null ? text : "";
    }

    public Date getWriteDate() {
        return writeDate;
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setWriteDate(Date writeDate) {
        this.writeDate = writeDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
