package com.merrix.multiselectrecyclerview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FragmentRightPage extends Fragment
{
    private final RecyclerView.Adapter adapter;
    private int themeMode;
    private boolean hideTitle;
    private boolean hasCustomTitle;
    private CharSequence customTitle;
    private int backgroundColor;
    private int titleColor;
    private int splitterBackground;
    private int splitterCursor;
    private RecyclerView rightRecyclerView;
    private boolean useCustomTheme = false;
    private ConstraintLayout mainView;
    private TextView pageTitle;

    public FragmentRightPage(MultiSelectAdapter adapter, int backgroundColor, int titleColor, int splitterBackground, int splitterCursor
            , boolean hideTitle, boolean hasCustomTitle, CharSequence customTitle)
    {
        this.adapter = adapter;
        this.backgroundColor = backgroundColor;
        this.titleColor = titleColor;
        this.splitterBackground = splitterBackground;
        this.splitterCursor = splitterCursor;
        this.hideTitle = hideTitle;
        this.hasCustomTitle = hasCustomTitle;
        this.customTitle = customTitle;
        useCustomTheme = true;
    }



    public FragmentRightPage(RecyclerView.Adapter adapter, int themeMode, boolean hideTitle, boolean hasCustomTitle, CharSequence customTitle)
    {
        this.adapter = adapter;

        this.themeMode = themeMode;
        this.hideTitle = hideTitle;
        this.hasCustomTitle = hasCustomTitle;
        this.customTitle = customTitle;
        useCustomTheme = false;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.right_page, container, false);
        return view;
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
        rightRecyclerView = getView().findViewById(R.id.rightRecyclerView);
        mainView = getView().findViewById(R.id.main_layout);
        pageTitle = getView().findViewById(R.id.pageTitle);

        if(hideTitle)
        {
            pageTitle.setVisibility(View.GONE);
        }else
        {
            pageTitle.setTextColor(titleColor);
            if(hasCustomTitle)
            {
                pageTitle.setText(customTitle);
            }
        }


//        pageTitle.setText("");
        mainView.setBackgroundColor(backgroundColor);

        rightRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        rightRecyclerView.setAdapter(adapter);
    }
}
