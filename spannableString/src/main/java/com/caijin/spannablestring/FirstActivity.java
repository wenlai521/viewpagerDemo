package com.caijin.spannablestring;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class FirstActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        TextView textView = (TextView) findViewById(R.id.text1);
        SpannableStringBuilder spannable = new SpannableStringBuilder("可以点击的");
        //设置文字的前景色
        spannable.setSpan(new ForegroundColorSpan(Color.RED),2,4,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //这个一定要记得设置，不然点击不生效
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        spannable.setSpan(new TextClick(),2,4 , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannable);
    }
    private class TextClick extends ClickableSpan{
        @Override
        public void onClick(View widget) {
            //在此处理点击事件
            Log.e("------->", "点击了");
        }
        
        @Override
        public void updateDrawState(TextPaint ds) {
//            ds.setColor(ds.linkColor);        
//            ds.setUnderlineText(true);
        }
    }
}
