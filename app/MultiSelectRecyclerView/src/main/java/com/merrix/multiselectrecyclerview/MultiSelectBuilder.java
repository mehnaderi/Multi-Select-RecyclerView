package com.merrix.multiselectrecyclerview;


import android.app.Activity;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class MultiSelectBuilder
{
    public static final int SELECT_VIEW_RTL = 0;
    public static final int SELECT_VIEW_LTR = 1;
    public static final int LIGHT_THEME = 0;
    public static final int DARK_THEME = 1;


    private MultiSelect multiSelect;
    private MultiSelectAdapter startAdapter, endAdapter;
    private int direction = SELECT_VIEW_LTR;
    private FragmentManager fragmentManager;
    private int layout;
    private int themeMode = LIGHT_THEME;
    private int backgroundColor;
    private int titleColor;
    private int splitterBackground;
    private int splitterCursor;
    private boolean useCustomTheme = false;

    private boolean hasCustomTitleStartPage = false;
    private boolean hasCustomTitleEndPage = false;
    private boolean hideTitleStartPage = false;
    private boolean hideTitleEndPage = false;
    private CharSequence titleStartPage = "";
    private CharSequence titleEndPage = "";
    private int widthInDp = 0;


    public MultiSelectBuilder(Activity activity, int layout)
    {
        fragmentManager = ((FragmentActivity) activity).getSupportFragmentManager();
        this.layout = layout;
    }

    public void setStartAdapter(MultiSelectAdapter adapter)
    {
        startAdapter = adapter;
    }
    public void setEndAdapter(MultiSelectAdapter adapter)
    {
        endAdapter = adapter;
    }
    public void setDirection(int direction)
    {
        this.direction = direction;
    }



    public void setThemeMode(int themeMode)
    {
        this.themeMode = themeMode;
        useCustomTheme = false;
    }
    public void setCustomTheme(   int backgroundColor,
                                  int titleColor,
                                  int splitterBackground,
                                  int splitterCursor)
    {

        this.backgroundColor = backgroundColor;
        this.titleColor = titleColor;
        this.splitterBackground = splitterBackground;
        this.splitterCursor = splitterCursor;
        useCustomTheme = true;
    }

    public void build()
    {
        if(useCustomTheme)
        {
            multiSelect = new MultiSelect(startAdapter, endAdapter, direction, backgroundColor,
                    titleColor, splitterBackground, splitterCursor, hideTitleStartPage, hasCustomTitleStartPage, titleStartPage,
                    hideTitleEndPage, hasCustomTitleEndPage, titleEndPage, widthInDp);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(layout, multiSelect);
            fragmentTransaction.commit();
        }else
        {
            multiSelect = new MultiSelect(startAdapter, endAdapter, direction, themeMode, hideTitleStartPage, hasCustomTitleStartPage, titleStartPage,
                    hideTitleEndPage, hasCustomTitleEndPage, titleEndPage, widthInDp);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(layout, multiSelect);
            fragmentTransaction.commit();
        }
    }


    public void hideStartPageTitle(boolean hiding)
    {
        hideTitleStartPage = hiding;
    }

    public void hideEndPageTitle(boolean hiding)
    {
        hideTitleEndPage = hiding;
    }
    public void setStartPageCustomTitle(CharSequence title)
    {
        titleStartPage = title;
        hasCustomTitleStartPage = true;
    }
    public void setEndPageCustomTitle(CharSequence title)
    {
        titleEndPage = title;
        hasCustomTitleEndPage = true;
    }
    public void setJointEndPageWidth(int widthInDp)
    {
        this.widthInDp = widthInDp;
    }




    //TODO : background Themes
    // 1. Add title for pages                                  //implemented
    // 2. Change background for start page and end page        //implemented
    // 3. Set Margin width for end page                        //implemented
    // 4. Add Searching and filtering to multi select
    // 5. Set good theme for splitter                          //implemented
    // 6. Improve performance
    // 7. Add some animations


}
