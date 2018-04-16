package com.example.wenlaisu.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenlaisu on 2018/4/12.
 */
public class ListFragment2 extends Fragment {

    private final CustomViewPager vp;
    private final int fragment_ID;
    TextView text;
    private static final String KEY = "key";
    private String title = "测试";

    List<String> mDatas = new ArrayList<>();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private View mView;
    private Context mContext;
    private ItemAdapter mAdapter;
    private LinearLayout ll;


    public ListFragment2(CustomViewPager viewPager, int fragmentID){
        vp = viewPager;
        fragment_ID =fragmentID;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mContext = getActivity();
            mView = View.inflate(mContext, R.layout.fragment_list2, null);
            initView(mView);
        } else {
            // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，
            // 要不然会发生这个rootview已经有parent的错误。
            ViewGroup parent = (ViewGroup) mView.getParent();
            if (parent != null) {
                parent.removeView(mView);
            }
        }

        vp.setObjectForPosition(mView,fragment_ID);
        return mView;
    }


    @Override
    public void onResume() {
        super.onResume();
    }


    public LinearLayout getLl() {
        return ll;
    }

    protected void initView(View view) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            title = arguments.getString(KEY);
        }
        text = view.findViewById(R.id.text);
        ll = view.findViewById(R.id.ll);
        text.setText(" 最近在开发中遇到了这个问题  ScrollView在小屏手机里面填满了屏幕  但是在大屏里面没有填满 下部有留白" +
                " 打算让最后一个按钮停靠在屏幕最下端  里面包裹LinearLayout  然后设置权重的 方式  但是没有效果  卡了好久  各种找问题  \n" +
                "\n" +
                " 最后在网上查到  当ScrollView里的元素想填满ScrollView时，使用\"fill_parent\"是不管用的，必需为" +
                "ScrollView设置：android:fillViewport=\"true\"。\n" +
                "\n" +
                " 当ScrollView没有fillVeewport=“true”时, 里面的元素(比如LinearLayout)会按照wrap_content来计算" +
                "(不论它是否设了\"fill_parent\"),而如果LinearLayout的元素设置了fill_parent,那么也是不管用的，因为" +
                "LinearLayout依赖里面的元素，而" +
                "里面的元素又依赖LinearLayout,这样自相矛盾.所以里面元素设置了fill_parent，也会当做wrap_content来计算. 最近在" +
                "开发中遇到了这个问题  ScrollView在小屏手机里面填满了屏幕  但是在大屏里面没有填满 下部有留白 打算让最后一个按" +
                "钮停靠在屏幕最下端  里面包裹LinearLayout  然后设置权重的 方式  但是没有效果  卡了好久  各种找问题  \n" +
                "\n" +
                " 最后在网上查到  当ScrollView里的元素想填满ScrollView时，使用\"fill_parent\"是不管用的，必需为ScrollView设" +
                "置：android:fillViewport=\"true\"。\n" +
                "\n" +
                " 当ScrollView没有fillVeewport=“true”时, 里面的元素(比如LinearLayout)会按照wrap_content来计算(不论它是否" +
                "设了\"fill_parent\"),而如果LinearLayout的元素设置了fill_parent,那么也是不管用的，因为LinearLayout依赖里" +
                "面的元素，" +
                "而里面的元素又依赖LinearLayout,这样自相矛盾.所以里面元素设置了fill_parent，也会当做wrap_content来计算.");
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(),
//                LinearLayoutManager.VERTICAL);
//        mRecyclerView.addItemDecoration(itemDecoration);
//        mRecyclerView.setLayoutManager(layoutManager);
//
//        for (int i = 0; i < 15; i++) {
//            String s = String.format("我是第%d个" + title, i);
//            mDatas.add(s);
//        }
//
//        mAdapter = new ItemAdapter(mContext, mDatas);
//        mRecyclerView.setAdapter(mAdapter);



    }


}
