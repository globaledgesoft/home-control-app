package com.app.globaledge_homecontrol_app.Util;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;


public class GETextView extends AppCompatTextView {
    public GETextView(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public GETextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public GETextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context);
    }

    private void applyCustomFont(Context context){

        Typeface customTypeFace = FontCache.getTypeFace(context , Constants.FONT);
        setTypeface(customTypeFace);
    }
}
