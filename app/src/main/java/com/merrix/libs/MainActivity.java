package com.merrix.libs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import android.widget.Toast;

import com.merrix.multiselectrecyclerview.MultiSelectBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_CODE_SDCARD = 1;
    private static final int PERMISSION_CODE_CALL = 2;
    private RightAdapter rightAdapter;
    private LeftAdapter leftAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rightAdapter = new RightAdapter(new ArrayList<Music>());
        leftAdapter = new LeftAdapter(getMusics(this), rightAdapter);
        rightAdapter.setLeftAdapter(leftAdapter);

        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE))
            {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_CODE_SDCARD);
            }else
            {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_CODE_SDCARD);
            }
        }else
        {


//            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            this.addView(inflater.inflate(R.layout.shownumberlayout, null));
            createSelector();





//            multiSelect = new MultiSelect(this, getMusics(this), (RecyclerView.Adapter) new LeftAdapter(getMusics(this), new CallBackOnClick() {
//                @Override
//                public void onClick(int itemPosition, RecyclerView.ViewHolder holder) {
//                    multiSelect.selectItem(itemPosition, holder);
//                }
//            }), (RecyclerView.Adapter) new RightAdapter(getMusics(this)));
        }

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, PERMISSION_CODE_CALL);
        }


    }



    private void createSelector()
    {
        MultiSelectBuilder builder = new MultiSelectBuilder(this, R.id.frame_layout_main);
        builder.setDirection(MultiSelectBuilder.SELECT_VIEW_RTL);
        builder.setStartAdapter(leftAdapter);
        builder.setEndAdapter(rightAdapter);
//        builder.setCustomTheme(getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorPrimaryDark), getResources().getColor(R.color.colorAccent), Color.RED);
        builder.setThemeMode(MultiSelectBuilder.LIGHT_THEME);
        builder.setJointEndPageWidth(60);
        builder.build();
    }




    /**
     * function to go back to previous fragment
     */
    private void oneStepBack() {
        FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() >= 2) {
            fragmentManager.popBackStackImmediate();
            fts.commit();
        } else {
            doubleClickToExit();
        }
    }


    // double back pressed function
    private static long back_pressed;

    private void doubleClickToExit() {
        if ((back_pressed + 2000) > System.currentTimeMillis())
            finish();
        else
            Toast.makeText(MainActivity.this, "Click again to exit", Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
    }

    @Override
    public void onBackPressed() {
        oneStepBack();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if( requestCode == PERMISSION_CODE_SDCARD)
        {
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                {
                    createSelector();
//                    multiSelect = new MultiSelect(this, getMusics(this), (RecyclerView.Adapter) new LeftAdapter(getMusics(this), new CallBackOnClick() {
//                        @Override
//                        public void onClick(int itemPosition, RecyclerView.ViewHolder holder) {
//                            multiSelect.selectItem(itemPosition, holder);
//                        }
//                    }), null);


                    createSelector();

                }
            }else
            {
                finish();
            }
        }
    }


    public static List<Music> getMusics(Activity activity)
    {
        if(activity == null)
        {
            return null;
        }
        List<Music> musicList = new ArrayList<>();
        ContentResolver contentResolver = activity.getContentResolver();
        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor musicCursor = contentResolver.query(musicUri,null, null, null, null);

        if(musicCursor != null && musicCursor.moveToFirst())
        {
            int musicTitle = musicCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int musicArtist = musicCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int musicLocation = musicCursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            // Next line has no warning or problem, this is android studio's bug!
            int musicTime = musicCursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
//            int musicB = musicCursor.getColumnIndex(MediaStore.Audio.Media.BOOKMARK);
            int musicOrNo = musicCursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC);
            int musicAlbumID = musicCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);
            int musicAlbum = musicCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);
            do {
                if(musicCursor.getLong(musicTime) >= 5 && musicCursor.getInt(musicOrNo) != 0) {
                    Music model = new Music();
                    model.setMusicName(musicCursor.getString(musicTitle));
                    model.setMusicArtist(musicCursor.getString(musicArtist));
//                    System.out.println("BOOOKMARRRK: "+musicCursor.getString(musicB));
                    model.setMusicLocation(musicCursor.getString(musicLocation));
                    model.setAlbumName(musicCursor.getString(musicAlbum));
                    model.setAlbumID(musicCursor.getInt(musicAlbumID));
                    String tempTime = formatTime(musicCursor.getLong(musicTime));
                    model.setMusicDuration(tempTime);

                    if (musicCursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC) > 0)
                        musicList.add(model);
                }
            }while (musicCursor.moveToNext());
            musicCursor.close();
        }
        return musicList;
    }
    private static String formatTime(long time){
        int seconds = (int) (time/1000);
        int minutes = seconds/60;
        seconds %= 60;
        return String.format(Locale.ENGLISH, "%02d",minutes)+":"+String.format(Locale.ENGLISH,"%02d",seconds);
    }













}