package com.merrix.libs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class ViewHolder extends RecyclerView.ViewHolder
{


    private TextView title;
    private TextView artist;
    //    TextView duration;
    private ImageView albumArt;

    public ViewHolder(Context context, ViewGroup viewGroup) {
        super(LayoutInflater.from(context).inflate(R.layout.music_item_layout, viewGroup, false));
        View view = LayoutInflater.from(context).inflate(R.layout.music_item_layout, viewGroup, false);
        title =  view.findViewById(R.id.musicName);
        artist =  view.findViewById(R.id.artistName);
        albumArt = view.findViewById(R.id.musicImage);
//        duration =  view.findViewById(R.id.musicDuration);
    }

    public ViewHolder(View view) {
        super(view);
        title =  view.findViewById(R.id.musicName);
        artist =  view.findViewById(R.id.artistName);
        albumArt = view.findViewById(R.id.musicImage);
    }

    public static void bind(ViewHolder viewHolder, Music music, Context context) {
        viewHolder.title.setText(music.getMusicName());
        viewHolder.albumArt.setClipToOutline(true);
        Glide.with(context).load(music.getAlbumArt()).placeholder(R.drawable.music_proto_high).into(viewHolder.albumArt);
//        viewHolder.albumArt.setImageDrawable(R.drawable.music_proto_high);
        viewHolder.artist.setText(music.getMusicArtist());
        viewHolder.artist.setSelected(true);
        viewHolder.title.setSelected(true);
//        viewHolder.duration.setText(music.getMusicDuration());
    }

}
