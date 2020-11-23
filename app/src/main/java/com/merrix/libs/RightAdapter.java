package com.merrix.libs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.merrix.multiselectrecyclerview.MultiSelectAdapter;

import java.util.List;

public class RightAdapter extends MultiSelectAdapter
{
    private List<Music> musicList;
    private MultiSelectAdapter leftAdapter;
    Context context;
    public RightAdapter(List<Music> musicList)
    {
        this.musicList = musicList;
        setItemList(musicList);
    }

    public void setLeftAdapter(MultiSelectAdapter leftAdapter) {
        this.leftAdapter = leftAdapter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.music_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        ViewHolder.bind((ViewHolder) holder, musicList.get(position), context);
//        prepareItem(holder, position, RIGHT_ADAPTER);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deselectItem(position, leftAdapter);
            }
        });
    }


    @Override
    public int getItemCount() {
        return musicList.size();
    }


}
