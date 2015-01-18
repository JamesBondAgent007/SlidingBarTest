package com.example.mr_holmes.slidingbartest.animationHelper;

import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.widget.AbsoluteLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by nagash on 17/01/15.
 */
public class MarginChanger
{
    ViewGroup.MarginLayoutParams params;
    ViewGroup view;

    public MarginChanger( ViewGroup.MarginLayoutParams marginLayoutParams)
    {
        this.params = marginLayoutParams;
    }




    public void setHeight(int height) {
        this.params.height = height;
    }
    public void setWidth(int width) {
        this.params.height = width;
    }

    public void setMargin(Integer left, Integer top, Integer right, Integer bottom)
    {
        if(left != null) setLeftMargin(left);
        if(top != null) setTopMargin(top);
        if(right != null) setRightMargin(right);
        if(bottom != null) setBottomMargin(bottom);
    }

    public void setLeftMargin(int margin){
        params.leftMargin = margin;
    }
    public void setTopMargin(int margin){
        params.topMargin = margin;
    }
    public void setRightMargin(int margin){
        params.rightMargin = margin;
    }
    public void setBottomMargin(int margin){
        params.bottomMargin = margin;
    }



}
