package com.merrix.libs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.merrix.multiselectrecyclerview.MultiSelectAdapter;

import java.util.List;

public class LeftAdapter extends MultiSelectAdapter
{

    private List<Music> musicList;
    Context context;
    private RecyclerView.Adapter rightAdapter;

    public LeftAdapter(List<Music> musicList, RightAdapter rightAdapter)
    {
        this.rightAdapter = rightAdapter;
        this.musicList = musicList;
        setItemList(musicList);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.music_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        ViewHolder.bind((ViewHolder) holder, musicList.get(position), context);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectItem(position, (MultiSelectAdapter) rightAdapter);
            }
        });
    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }

}
