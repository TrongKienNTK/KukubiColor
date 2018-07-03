package com.leobi.trongkien.kukubicolor;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class txtView extends AppCompatTextView {

    public txtView(Context context) {
        super(context);
    }

    public txtView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public txtView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        setMeasuredDimension(width,width);
    }
}
