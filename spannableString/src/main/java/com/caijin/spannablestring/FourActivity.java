package com.caijin.spannablestring;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class FourActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
         TextView tips = (TextView) findViewById(R.id.text);
//         https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white_fe6da1ec.png
          String str = "单击打开 <a href='http://www.baidu.com/'>百度首页</a>";
          tips.setText(Html.fromHtml(str));
          // Html.fromHtml(
          // "<b>text3:</b>  Text with a " +
          // "<a href=\"http://www.google.com\">link</a> " +
          // "created in the Java source code using HTML."))
          tips.setMovementMethod(LinkMovementMethod.getInstance());
         TextView protocalTv = (TextView) findViewById(R.id.text1);
         String str1 =
         "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white_fe6da1ec.png";
         SpannableString spannableString1 = new SpannableString(str1);
        
         spannableString1.setSpan(new ClickableSpan() {
         public void onClick(View widget) {
         Toast.makeText(getApplicationContext(), "点击了第一处", 0).show();
        
         }
         }, str1.length() - 5, str1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
         spannableString1.setSpan(new ForegroundColorSpan(getResources()
         .getColor(android.R.color.holo_blue_bright)), str1.length() - 5,
         str1.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
         spannableString1.setSpan(new ClickableSpan() {
         public void onClick(View widget) {
         Toast.makeText(getApplicationContext(), "点击了第二处", 0).show();
        
         }
         }, 7, 14, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
         spannableString1.setSpan(new ForegroundColorSpan(getResources()
         .getColor(android.R.color.holo_red_light)), 7, 14,
         Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
         protocalTv.setText(spannableString1);
         protocalTv.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
