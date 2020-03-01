package com.petrgostev.NotebookForPoetry.Write.View;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.petrgostev.NotebookForPoetry.R;
import com.petrgostev.NotebookForPoetry.Write.Assembly.WriteAssembly;
import com.petrgostev.NotebookForPoetry.Write.Interactor.WriteViewItem;
import com.petrgostev.NotebookForPoetry.Write.Presenter.WritePresenter;
import com.petrgostev.NotebookForPoetry.Write.Interactor.WriteViewModelInput;

import java.util.Objects;


public class WriteFragment extends Fragment {
    private static String FOR_EDIT_KEY = "FOR_EDIT_KEY";

    private boolean isForEdit = false;
    private boolean isForCreate = false;

    private WriteViewOutput viewOutput;
    private WriteViewModelInput viewModel;

    private EditText titleET;
    private EditText writeET;
    private TextView dateWriteET;
    private TextView dateEditET;
    private LinearLayout dateWriteLayout;
    private LinearLayout dateEditLayout;

    private MenuItem editItem;
    private MenuItem saveItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();

        if (args != null) {
            String FOR_CREATE_KEY = "FOR_CREATE_KEY";
            isForCreate = args.getBoolean(FOR_CREATE_KEY);
        }

        setHasOptionsMenu(true);

        WriteAssembly.configureModule(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("RestrictedApi")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_write, container, false);

        titleET = view.findViewById(R.id.title);
        writeET = view.findViewById(R.id.write);
        dateWriteET = view.findViewById(R.id.date_write);
        dateEditET = view.findViewById(R.id.date_edit);
        dateWriteLayout = view.findViewById(R.id.date_write_layout);
        dateEditLayout = view.findViewById(R.id.date_edit_layout);

        FloatingActionButton fab = Objects.requireNonNull(getActivity()).findViewById(R.id.fab);
        fab.setVisibility(View.GONE);

        if (isForCreate) {
            isForEdit = true;
        }

        if (savedInstanceState != null) {
            isForEdit = savedInstanceState.getBoolean(FOR_EDIT_KEY);
        }

        LiveData<WriteViewItem> viewItemLiveData = viewModel.getWriteViewItem();
        viewItemLiveData.observe(this, new Observer<WriteViewItem>() {

            @Override
            public void onChanged(@Nullable WriteViewItem writeViewItem) {
                if (!isForEdit) {
                    titleET.setText(Objects.requireNonNull(writeViewItem).title);
                    writeET.setText(writeViewItem.text);
                }

                dateWriteET.setText(Objects.requireNonNull(writeViewItem).dateWrite);
                dateEditET.setText(writeViewItem.dateEdit);
            }
        });

        setEditMode(isForEdit);

        return view;
    }

    public void setViewOutput(WritePresenter viewOutput) {
        this.viewOutput = viewOutput;
    }

    public void setViewModel(WriteViewModelInput viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        savedInstanceState.putBoolean(FOR_EDIT_KEY, isForEdit);

        super.onSaveInstanceState(savedInstanceState);
    }

    public void setEditMode(boolean isForEdit) {
        this.isForEdit = isForEdit;

        titleET.setEnabled(isForEdit);
        writeET.setEnabled(isForEdit);
        dateWriteLayout.setVisibility(isForEdit ? View.GONE : View.VISIBLE);
        dateEditLayout.setVisibility(isForEdit ? View.GONE : View.VISIBLE);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();

        inflater.inflate(R.menu.menu_fragment, menu);
        editItem = menu.findItem(R.id.edit_fragment);
        saveItem = menu.findItem(R.id.save_fragment);

        editItem.setVisible(!isForEdit);
        saveItem.setVisible(isForEdit);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.save_fragment) {
            setEditMode(false);
            saveItem.setVisible(false);
            editItem.setVisible(true);

            viewOutput.vewDidTapSave(titleET.getText().toString(), writeET.getText().toString());
        }

        if (id == R.id.edit_fragment) {
            setEditMode(true);
            saveItem.setVisible(true);
            editItem.setVisible(false);
        }

        return super.onOptionsItemSelected(item);
    }
}
