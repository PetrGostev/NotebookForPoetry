package com.example.notebookforpoetry.WritesList.View;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.notebookforpoetry.R;
import com.example.notebookforpoetry.Write.Interactor.WriteViewItem;
import com.example.notebookforpoetry.WritesList.Assembly.WritesListAssembly;
import com.example.notebookforpoetry.WritesList.Interactor.WritesListViewModelInput;

import java.util.ArrayList;
import java.util.Objects;


public class StartActivity extends AppCompatActivity implements StartActivityInput{

    private static String FOR_EDIT_KEY = "FOR_EDIT_KEY";
    private static String LONG_CLICK_KEY = "LONG_CLICK_KEY";

    private boolean isEdit = false;
    private boolean isRecyclerLongClick = false;

    private ListViewOutput viewOutput;
    private WritesListViewModelInput viewModel;

    private RecyclerView recycler;
    private TextView welcomeText;

    private FloatingActionButton fab;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        TextView title = findViewById(R.id.title_view);
        fab = findViewById(R.id.fab);
        recycler = findViewById(R.id.recycler);
        welcomeText = findViewById(R.id.welcome_text);

        WritesListAssembly.configureModule(this);
        LiveData<ArrayList<WriteViewItem>> viewItemsLiveData = viewModel.getWriteViewItems();
        viewItemsLiveData.observe(this, new Observer<ArrayList<WriteViewItem>>() {
            @Override
            public void onChanged(@Nullable ArrayList<WriteViewItem> writeViewItems) {
                assert writeViewItems != null;

                welcomeText.setVisibility(writeViewItems.size() > 0 ? View.GONE : View.VISIBLE);

                showViewItems(writeViewItems);
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {
                viewOutput.viewDidTapAddButton();
            }
        });

        title.setText("Мое творчество");
    }

    public void setViewOutput(ListViewOutput viewOutput) {
        this.viewOutput = viewOutput;
    }

    public void setViewModel(WritesListViewModelInput viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(FOR_EDIT_KEY, isEdit);
        outState.putBoolean(LONG_CLICK_KEY, isRecyclerLongClick);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        isEdit = savedInstanceState.getBoolean(FOR_EDIT_KEY);
        isRecyclerLongClick = savedInstanceState.getBoolean(LONG_CLICK_KEY);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        fab.setVisibility(isEdit ? View.GONE : View.VISIBLE);
        viewOutput.viewDidBackPressed();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void visibilityFab(boolean visible) {
        fab.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    //Private

    private void showViewItems(final ArrayList<WriteViewItem> viewItems) {
        WriteListAdapter adapter = new WriteListAdapter(viewItems);

        adapter.setSelectListener(new WriteListAdapter.ClickListener() {
            @Override
            public void onClick(int position) {
                WriteViewItem viewItem = viewItems.get(position);
                viewOutput.viewDidSelectItem(viewItem);
            }

            @Override
            public void onRemove(String id) {
                viewOutput.viewDidTapDeleteButton(id);
            }
        });

        recycler.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
    }

    private ArrayList<String> getIdsWritesForRemove() {

        final ArrayList<String> idsWritesForRemove = new ArrayList<>();

        LiveData<ArrayList<String>> idsForDeleteLiveData = viewModel.getIdsForDelete();
        idsForDeleteLiveData.observe(this, new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> strings) {
                idsWritesForRemove.addAll(strings);
            }
        });

        return idsWritesForRemove;
    }
}
