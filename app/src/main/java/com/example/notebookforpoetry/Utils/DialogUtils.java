package com.example.notebookforpoetry.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.example.notebookforpoetry.DataService.EditCallback;

import java.util.ArrayList;

public class DialogUtils {

    public static void showEditAlertDialog(Activity getActivity, final String title, final EditCallback callback) {
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity).create();

        alertDialog.setTitle(title);
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        callback.done(false);
                    }
                });

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        callback.done(true);
                    }
                });
        alertDialog.show();
    }

    public static void showInfoAlertDialog(Activity getActivity, final String title) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity);

        builder.setTitle(title)
                .setCancelable(false)
                .setNegativeButton("ОК",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static void showDeleteWriteAlertDialog(Activity getActivity, final EditCallback callback) {
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity).create();

        alertDialog.setTitle("Удалить запись?");
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Отмена",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        callback.done(false);
                    }
                });

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Удалить",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        callback.done(true);
                    }
                });
        alertDialog.show();
    }

    public static void showDeleteWritesAlertDialog(ArrayList<String> ids, Activity getActivity, final EditCallback callback) {
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity).create();

        String title = ids.size() > 1 ? "Удалить выбранные записи?" : "Удалить запись?";

        alertDialog.setTitle(title);
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Отмена",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        callback.done(false);
                    }
                });

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Удалить",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        callback.done(true);
                    }
                });
        alertDialog.show();
    }
}
