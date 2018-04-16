# viewpagerDemo
这几天 朋友问我了个问题  有个特别的需求  页面如下

![gif图片](https://github.com/wenlai521/viewpagerDemo/blob/master/123.gif)

就是当viewpager里面的当前页面内容足够多的时候  要去能滑上去 并且指示器吸顶  但是当内容不多的时候  能滑到哪就停在哪  当时第一反应是用coordinatelayout  结果不行 内容少的时候  也直接滑到顶部了 

activity的xml文件

<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/main_content"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">


    <com.example.wenlaisu.myapplication.MyScrollview
        android:id="@+id/scroll"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:fillViewport="true">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical">

            <ImageView
                android:layout_width="match_parent"
                android:layout_height="200dp"
                android:background="?attr/colorPrimary"
                android:scaleType="fitXY"
                android:id="@+id/iv"
                android:src="@drawable/tangyan" />

            <LinearLayout
                android:id="@+id/layout"
                android:layout_width="match_parent"
                android:layout_height="wrap_content">

                <android.support.design.widget.TabLayout
                    android:id="@+id/tabs"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:background="?attr/colorPrimary" />
            </LinearLayout>


            <com.example.wenlaisu.myapplication.CustomViewPager
                android:id="@+id/viewpager"
                android:layout_width="match_parent"
                android:layout_height="wrap_content" />
        </LinearLayout>
    </com.example.wenlaisu.myapplication.MyScrollview>

    <RelativeLayout
        android:id="@+id/rl_layout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" >

    </RelativeLayout>
</RelativeLayout>

后来就直接用scrollView包裹来实现了 自定义scrollView实现滚动监听 用来实现指示器吸顶效果  

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

在activity里面设置监听

scroll.setScrollViewListener(new MyScrollview.ScrollViewListener() {
    @Override
    public void onScrollChanged(MyScrollview scrollView, int l, int t, int oldl, int oldt) {
        if (t >iv.getHeight() && mTabLayout.getParent() == layout) {
            layout.removeView(mTabLayout);
            rl_layout.addView(mTabLayout);
        } else if (t < iv.getHeight() && mTabLayout.getParent() == rl_layout) {
            rl_layout.removeView(mTabLayout);
            layout.addView(mTabLayout);
        }
    }
});

然后就是实现viewpager的每个页面的内容高度适配了  从写viewpager

public class CustomViewPager extends ViewPager {
  
    private int current;  
    private int height = 0;  
    /**  
     * 保存position与对于的View  
     */  
    private HashMap<Integer, View> mChildrenViews = new LinkedHashMap<Integer, View>();
  
    private boolean scrollble = true;  
  
    public CustomViewPager(Context context) {
        super(context);  
    }  
  
    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);  
    }  
  
  
    @Override  
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {  
        if (mChildrenViews.size() > current) {  
            View child = mChildrenViews.get(current);  
            if (child != null) {  
                child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));  
                height = child.getMeasuredHeight();  
            }  
        }  
  
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);  
  
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);  
    }  
  
    public void resetHeight(int current) {  
        this.current = current;  
        if (mChildrenViews.size() > current) {  
  
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
            if (layoutParams == null) {  
                layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, height);
            } else {  
                layoutParams.height = height;  
            }  
            setLayoutParams(layoutParams);  
        }  
    }  
  
    /**  
     * 保存position与对于的View  
     */  
    public void setObjectForPosition(View view, int position) {  
        mChildrenViews.put(position, view);  
    }  
  
  
    @Override  
    public boolean onTouchEvent(MotionEvent ev) {
        if (!scrollble) {  
            return true;  
        }  
        return super.onTouchEvent(ev);  
    }  
  
  
    public boolean isScrollble() {  
        return scrollble;  
    }  
  
    public void setScrollble(boolean scrollble) {  
        this.scrollble = scrollble;  
    }  
  
  
}  
用法是在每个fragment 的onCreatView里面添加一行代码就行 
![image](https://github.com/wenlai521/viewpagerDemo/blob/master/1523862723(1).jpg)
然后在viewpager所在的activity里面设置监听  就可以了

mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        mViewPager.resetHeight(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
});

下面是整个activity的代码


package com.example.wenlaisu.myapplication;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by wenlaisu on 2018/4/12.
 */
public class ViewPagerSample extends AppCompatActivity {

    CustomViewPager mViewPager;
    List<Fragment> mFragments;

    String[] mTitles = new String[]{
            "主页", "微博", "相册"
    };
    private TabLayout mTabLayout;
    private MyScrollview scroll;
    private LinearLayout layout;
    private RelativeLayout rl_layout;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        // 第一步，初始化ViewPager和TabLayout
        mViewPager = (CustomViewPager) findViewById(R.id.viewpager);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        layout = (LinearLayout) findViewById(R.id.layout);
        rl_layout = (RelativeLayout) findViewById(R.id.rl_layout);
        scroll = (MyScrollview) findViewById(R.id.scroll);
        iv = (ImageView) findViewById(R.id.iv);
        setupViewPager();
    }

    private void setupViewPager() {
        mFragments = new ArrayList<>();
        ListFragment listFragment = new ListFragment(mViewPager, 0);
        ListFragment2 listFragment2 = new ListFragment2(mViewPager, 1);
        ListFragment3 listFragment3 = new ListFragment3(mViewPager, 2);
        mFragments.add(listFragment);
        mFragments.add(listFragment2);
        mFragments.add(listFragment3);

        // 第二步：为ViewPager设置适配器
        BaseFragmentAdapter adapter =
                new BaseFragmentAdapter(getSupportFragmentManager(), mFragments, mTitles);

        mViewPager.setAdapter(adapter);
        //  第三步：将ViewPager与TableLayout 绑定在一起
        mTabLayout.setupWithViewPager(mViewPager);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mViewPager.resetHeight(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        scroll.setScrollViewListener(new MyScrollview.ScrollViewListener() {
            @Override
            public void onScrollChanged(MyScrollview scrollView, int l, int t, int oldl, int oldt) {
                if (t >iv.getHeight() && mTabLayout.getParent() == layout) {
                    layout.removeView(mTabLayout);
                    rl_layout.addView(mTabLayout);
                } else if (t < iv.getHeight() && mTabLayout.getParent() == rl_layout) {
                    rl_layout.removeView(mTabLayout);
                    layout.addView(mTabLayout);
                }
            }
        });

    }
}

就可以了
