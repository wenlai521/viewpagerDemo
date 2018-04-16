package com.caijin.spannablestring;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

 
public class MyTextView extends TextView
{
 
    public MyTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }
 
    public void setSpecifiedTextsColor(String text, String specifiedTexts, int color)
    {   
        List<Integer> sTextsStartList = new ArrayList<>();
         
        int sTextLength = specifiedTexts.length();
        String temp = text;
        int lengthFront = 0;//记录被找出后前面的字段的长度
        int start = -1;
        do
        {
            start = temp.indexOf(specifiedTexts);
             
            if(start != -1)
            {
                start = start + lengthFront;
                sTextsStartList.add(start);
                lengthFront = start + sTextLength;
                temp = text.substring(lengthFront);
            }
             
        }while(start != -1);
         
        SpannableStringBuilder styledText = new SpannableStringBuilder(text);
        for(Integer i : sTextsStartList)
        {
            styledText.setSpan(
                    new ForegroundColorSpan(color), 
                    i, 
                    i + sTextLength, 
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            //这个一定要记得设置，不然点击不生效
            setMovementMethod(LinkMovementMethod.getInstance());
            styledText.setSpan(new TextClick(),i,i + sTextLength , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        setText(styledText);
    }
    private class TextClick extends ClickableSpan{
        @Override
        public void onClick(View widget) {
            //在此处理点击事件
            Log.e("------->", "点击了");
//            widget.seth
        }
        
        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(ds.linkColor);        
            ds.setUnderlineText(true);
        }
    }
    
}
