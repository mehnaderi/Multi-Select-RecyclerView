package com.merrix.multiselectrecyclerview;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import static com.merrix.multiselectrecyclerview.MultiSelectBuilder.SELECT_VIEW_LTR;
import static com.merrix.multiselectrecyclerview.MultiSelectBuilder.SELECT_VIEW_RTL;

public class MultiSelect extends Fragment
{


    private final MultiSelectAdapter startAdapter;
    private final MultiSelectAdapter endAdapter;
    private final int direction;
    private int themeMode;
    private ViewPager viewPager;
    private SlidePager slidePager;
    private boolean useCustomTheme = false;
    private List<Fragment> fragmentList = new ArrayList<>();
    private int backgroundColor;
    private int titleColor;
    private int splitterBackground;
    private int splitterCursor;
    private final boolean hideStartTitle;
    private final boolean hasCustomStartTitle;
    private final CharSequence startTitle;
    private final boolean hideEndTitle;
    private final boolean hasCustomEndTitle;
    private final CharSequence endTitle;
    private int widthInDp;

    public MultiSelect(MultiSelectAdapter startAdapter, MultiSelectAdapter endAdapter, int direction, int themeMode,
                       boolean hideStartTitle,
                       boolean hasCustomStartTitle,
                       CharSequence startTitle,
                       boolean hideEndTitle,
                       boolean hasCustomEndTitle,
                       CharSequence endTitle,
                       int widthInDp)
    {

        this.startAdapter = startAdapter;
        this.endAdapter = endAdapter;
        this.direction = direction;
        this.themeMode = themeMode;
        this.hideStartTitle = hideStartTitle;
        this.hasCustomStartTitle = hasCustomStartTitle;
        this.startTitle = startTitle;
        this.hideEndTitle = hideEndTitle;
        this.hasCustomEndTitle = hasCustomEndTitle;
        this.endTitle = endTitle;
        this.widthInDp = widthInDp;
        this.useCustomTheme = false;
    }
    public MultiSelect(MultiSelectAdapter startAdapter,
                       MultiSelectAdapter endAdapter,
                       int direction,
                       int backgroundColor,
                       int titleColor,
                       int splitterBackground,
                       int splitterCursor,
                       boolean hideStartTitle,
                       boolean hasCustomTitle,
                       CharSequence startTitle,
                       boolean hideEndTitle,
                       boolean hasEndTitle,
                       CharSequence endTitle,
                       int widthInDp)
    {

        this.startAdapter = startAdapter;
        this.endAdapter = endAdapter;
        this.direction = direction;
        this.backgroundColor = backgroundColor;
        this.titleColor = titleColor;
        this.splitterBackground = splitterBackground;
        this.splitterCursor = splitterCursor;
        this.hideStartTitle = hideStartTitle;
        this.hasCustomStartTitle = hasCustomTitle;
        this.startTitle = startTitle;
        this.hideEndTitle = hideEndTitle;
        this.hasCustomEndTitle = hasEndTitle;
        this.endTitle = endTitle;
        this.widthInDp = widthInDp;
        this.useCustomTheme = true;
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_multi_select, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = getView().findViewById(R.id.main_pager);
        build();
    }

    public void build()
    {
        switch (direction)
        {
            case SELECT_VIEW_RTL:
            {
                if(useCustomTheme)
                {
                    fragmentList.add(new FragmentRightPage(endAdapter, backgroundColor, titleColor, splitterBackground, splitterCursor, hideEndTitle, hasCustomEndTitle, endTitle));
                    fragmentList.add(new FragmentLeftPage(startAdapter, direction, backgroundColor, titleColor, splitterBackground, splitterCursor, hideStartTitle, hasCustomStartTitle, startTitle));
                }else
                {
                    fragmentList.add(new FragmentRightPage(endAdapter, themeMode, hideEndTitle, hasCustomEndTitle, endTitle));
                    fragmentList.add(new FragmentLeftPage(startAdapter, direction, themeMode, hideStartTitle, hasCustomStartTitle, startTitle));
                }
                slidePager = new SlidePager(getFragmentManager(), fragmentList, getActivity(), widthInDp);
                viewPager.setAdapter(slidePager);
                viewPager.setCurrentItem(1);
                break;
            }
            case SELECT_VIEW_LTR:
            {
                if(useCustomTheme)
                {
                    fragmentList.add(new FragmentLeftPage(startAdapter, direction, backgroundColor, titleColor, splitterBackground, splitterCursor, hideStartTitle, hasCustomStartTitle, startTitle));
                    fragmentList.add(new FragmentRightPage(endAdapter, backgroundColor, titleColor, splitterBackground, splitterCursor, hideEndTitle, hasCustomEndTitle, endTitle));
                }else
                {
                    fragmentList.add(new FragmentLeftPage(startAdapter, direction, themeMode, hideStartTitle, hasCustomStartTitle, startTitle));
                    fragmentList.add(new FragmentRightPage(endAdapter, themeMode, hideEndTitle, hasCustomEndTitle, endTitle));
                }

                slidePager = new SlidePager(getFragmentManager(), fragmentList, getActivity(), widthInDp);
                viewPager.setAdapter(slidePager);
                break;
            }
        }
    }


}
