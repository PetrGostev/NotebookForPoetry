package com.example.notebookforpoetry.WritesList.View;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.notebookforpoetry.R;
import com.example.notebookforpoetry.Write.Interactor.WriteViewItem;

import java.util.ArrayList;

import ru.rambler.libs.swipe_layout.SwipeLayout;


public class WriteListAdapter extends RecyclerView.Adapter<WriteListAdapter.ViewHolder> {

    private ArrayList<WriteViewItem> viewItems;

    private WriteListAdapter.ClickListener adapterOnClickListener;

    public WriteListAdapter(ArrayList<WriteViewItem> viewItems) {
        this.viewItems = viewItems;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ViewGroup viewGroup;

        ViewHolder(ViewGroup viewGroup) {
            super(viewGroup);
            this.viewGroup = viewGroup;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewGroup viewGr = (ViewGroup) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.write_card, viewGroup, false);

        return new ViewHolder(viewGr);
    }

    @Override
    public void onBindViewHolder(@NonNull WriteListAdapter.ViewHolder viewHolder, int position) {
        ViewGroup viewGroup = viewHolder.viewGroup;

        configureView(viewGroup, position, viewHolder);
    }

    private void configureView(final ViewGroup viewGroup, final int position, final ViewHolder viewHolder) {
        final WriteViewItem item = viewItems.get(position);

        final CardView cardView = viewGroup.findViewById(R.id.card_view);
        final TextView titleTV = viewGroup.findViewById(R.id.title);
        final TextView dateWriteTV = viewGroup.findViewById(R.id.date_write);
        final TextView dateEditTV = viewGroup.findViewById(R.id.date_edit);
        final SwipeLayout swipeLayout = (SwipeLayout) viewGroup;

        titleTV.setText(item.title);
        dateWriteTV.setText(item.dateWrite);
        dateEditTV.setText(item.dateEdit);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapterOnClickListener != null) {
                    adapterOnClickListener.onClick(viewHolder.getAdapterPosition());
                }
            }
        });

        swipeLayout.setOnSwipeListener(new SwipeLayout.OnSwipeListener() {
            @Override
            public void onBeginSwipe(SwipeLayout swipeLayout, boolean moveToRight) {

            }

            @Override
            public void onSwipeClampReached(SwipeLayout swipeLayout, boolean moveToRight) {
                adapterOnClickListener.onRemove(item.id);
            }

            @Override
            public void onLeftStickyEdge(SwipeLayout swipeLayout, boolean moveToRight) {

            }

            @Override
            public void onRightStickyEdge(SwipeLayout swipeLayout, boolean moveToRight) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return viewItems.size();
    }

    interface ClickListener {

        void onClick(int position);

        void onRemove(String id);
    }

    public void setSelectListener(ClickListener selectListener) {
        this.adapterOnClickListener = selectListener;
    }
}
