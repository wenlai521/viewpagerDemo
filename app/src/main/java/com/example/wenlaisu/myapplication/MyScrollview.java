package com.example.wenlaisu.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;
/**
 * Created by wenlaisu on 2018/4/12.
 */
public class MyScrollview extends ScrollView {

    private ScrollViewListener scrollViewListener = null;

    public MyScrollview(Context context) {
        super(context);
    }

    public MyScrollview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, l, t, oldl, oldt);
        }
    }

    public interface ScrollViewListener {
        void onScrollChanged(MyScrollview scrollView, int l, int t, int oldl, int oldt);
    }
}