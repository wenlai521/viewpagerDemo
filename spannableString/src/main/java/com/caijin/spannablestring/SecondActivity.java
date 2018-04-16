package com.caijin.spannablestring;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;

public class SecondActivity extends Activity {
private MyTextView textView;
    
    public String text = "Android是一种基于Linux的自由及开放源代码的操作系统，主要使用于移动设备，如智能手机和平板电脑，由Google公司和开放手机联盟领导及开发。尚未有统一中文名称，中国大陆地区较多人使用“安卓”或“安致”。Android操作系统最初由Andy Rubin开发，主要支持手机。2005年8月由Google收购注资。2007年11月，Google与84家硬件制造商、软件开发商及电信营运商组建开放手机联盟共同研发改良Android系统。随后Google以Apache开源许可证的授权方式，发布了Android的源代码。第一部Android智能手机发布于2008年10月。Android逐渐扩展到平板电脑及其他领域上，如电视、数码相机、游戏机等。2011年第一季度，Android在全球的市场份额首次超过塞班系统，跃居全球第一。 2013年的第四季度，Android平台手机的全球市场份额已经达到78.1%。[1] 2013年09月24日谷歌开发的操作系统Android在迎来了5岁生日，全世界采用这款系统的设备数量已经达到10亿台。";
     
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
      // 正则表达式。表示连续6位的数字,可以在这边修改成自己所要的格式  
      Pattern pattern = Pattern.compile("[1-9]\\d*[TBU][1-9]\\d*");  
      String code = "";
      // 匹配短信内容  
      Matcher matcher = pattern.matcher(text);  
      if (matcher.find()) {  
          code = matcher.group(0);  
      }  
      
        textView = (MyTextView)findViewById(R.id.tv);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setSpecifiedTextsColor(text, "Android", Color.parseColor("#FF0000"));
        textView.setHighlightColor(getResources().getColor(android.R.color.transparent));//设置点击后的颜色
    }
}