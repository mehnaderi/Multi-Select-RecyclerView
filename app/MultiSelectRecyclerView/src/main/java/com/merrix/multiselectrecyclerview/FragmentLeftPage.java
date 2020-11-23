package com.merrix.multiselectrecyclerview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentLeftPage extends Fragment
{
    private RecyclerView leftRecyclerView;
    private TextView titleView;
    private RelativeLayout splitterCursorViewLeft;
    private RelativeLayout splitterCursorViewRight;
    private ConstraintLayout splitterBackgroundViewLeft;
    private ConstraintLayout splitterBackgroundViewRight;
    private ConstraintLayout mainView;


    private MultiSelectAdapter adapter;


    private int direction;
    private int themeMode;
    private final boolean hideTitle;
    private final boolean hasCustomTitle;
    private final CharSequence customTitle;

    private int backgroundColor;
    private int titleColor;
    private int splitterBackground;
    private int splitterCursor;
    private boolean useCustomTheme = false;

    public FragmentLeftPage(MultiSelectAdapter adapter, int direction, int backgroundColor, int titleColor, int splitterBackground, int splitterCursor,
                            boolean hideTitle, boolean hasCustomTitle, CharSequence customTitle)
    {
        this.adapter = adapter;
        this.direction = direction;
        this.backgroundColor = backgroundColor;
        this.titleColor = titleColor;
        this.splitterBackground = splitterBackground;
        this.splitterCursor = splitterCursor;
        this.hideTitle = hideTitle;
        this.hasCustomTitle = hasCustomTitle;
        this.customTitle = customTitle;
        this.useCustomTheme = true;
    }
    public FragmentLeftPage(MultiSelectAdapter adapter, int direction, int themeMode,
                            boolean hideTitle, boolean hasCustomTitle, CharSequence customTitle)
    {
        this.adapter = adapter;
        this.direction = direction;
        this.themeMode = themeMode;
        this.hideTitle = hideTitle;
        this.hasCustomTitle = hasCustomTitle;
        this.customTitle = customTitle;
        this.useCustomTheme = false;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.left_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(!useCustomTheme)
        {
            setColors();
        }
        init();

    }
    private void setColors()
    {
        if (themeMode == MultiSelectBuilder.DARK_THEME)
        {
            this.backgroundColor = ResourcesCompat.getColor(getResources(), R.color.DarkModeBackground, null);
            this.titleColor = ResourcesCompat.getColor(getResources(), R.color.DarkModeTitleColor, null);
            this.splitterBackground = ResourcesCompat.getColor(getResources(), R.color.DarkModeSplitterBackground, null);
            this.splitterCursor = ResourcesCompat.getColor(getResources(), R.color.DarkModeSplitterCursor, null);
        }else
        {
            this.backgroundColor = ResourcesCompat.getColor(getResources(), R.color.LightModeBackground, null);
            this.titleColor = ResourcesCompat.getColor(getResources(), R.color.LightModeTitleColor, null);
            this.splitterBackground = ResourcesCompat.getColor(getResources(), R.color.LightModeSplitterBackground, null);
            this.splitterCursor = ResourcesCompat.getColor(getResources(), R.color.LightModeSplitterCursor, null);
        }
    }
    private void init() {
        leftRecyclerView = getView().findViewById(R.id.leftRecyclerView);
        mainView = getView().findViewById(R.id.main_layout);
        titleView = getView().findViewById(R.id.pageTitle);

        if(hideTitle)
        {
            titleView.setVisibility(View.GONE);
        }else
        {
            titleView.setTextColor(titleColor);
            if(hasCustomTitle)
            {
                titleView.setText(customTitle);
            }
        }

        switch (direction)
        {
            case MultiSelectBuilder.SELECT_VIEW_RTL:
                splitterBackgroundViewRight = getView().findViewById(R.id.splitterRight);
                splitterBackgroundViewRight.setVisibility(View.GONE);
                // Set colors
                splitterBackgroundViewLeft = getView().findViewById(R.id.splitterLeft);
                splitterBackgroundViewLeft.setVisibility(View.VISIBLE);
                splitterCursorViewLeft = getView().findViewById(R.id.cursorLeft);
                //TODO : title is remained!
//                mainView.setBackgroundColor(backgroundColor);
                splitterBackgroundViewLeft.setBackgroundColor(splitterBackground);
                splitterCursorViewLeft.setBackgroundColor(splitterCursor);
                mainView.setBackgroundColor(backgroundColor);
                break;
            case MultiSelectBuilder.SELECT_VIEW_LTR:
                splitterBackgroundViewLeft = getView().findViewById(R.id.splitterLeft);
                splitterBackgroundViewLeft.setVisibility(View.GONE);
                // Set colors
                splitterBackgroundViewRight = getView().findViewById(R.id.splitterRight);
                splitterBackgroundViewRight.setVisibility(View.VISIBLE);
                splitterCursorViewRight = getView().findViewById(R.id.cursorRight);
                //TODO : title is remained!
//                mainView.setBackgroundColor(backgroundColor);
                splitterBackgroundViewRight.setBackgroundColor(splitterBackground);
                splitterCursorViewRight.setBackgroundColor(splitterCursor);
                mainView.setBackgroundColor(backgroundColor);

                break;
        }

        leftRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        leftRecyclerView.setAdapter(adapter);

    }
}
