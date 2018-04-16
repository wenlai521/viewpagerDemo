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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenlaisu on 2018/4/12.
 */
public class ListFragment extends Fragment {

    RecyclerView mRecyclerView;
    private static final String KEY = "key";
    private String title = "测试";

    List<String> mDatas = new ArrayList<>();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private View mView;
    private Context mContext;
    private ItemAdapter mAdapter;
    private CustomViewPager vp;
    private int fragment_ID;
    private LinearLayout ll;


    public ListFragment( CustomViewPager viewPager, int fragmentID){
        vp = viewPager;
        fragment_ID =fragmentID;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mContext = getActivity();
            mView = View.inflate(mContext, R.layout.fragment_list, null);
            initView(mView);
        } else {
            ViewGroup parent = (ViewGroup) mView.getParent();
            if (parent != null) {
                parent.removeView(mView);
            }
        }
        vp.setObjectForPosition(mView,fragment_ID);
        return mView;
    }

    protected void initView(View view) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            title = arguments.getString(KEY);
        }
        mRecyclerView = view.findViewById(R.id.recyclerView);
        ll = view.findViewById(R.id.ll);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(),
                LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setLayoutManager(layoutManager);

        for (int i = 0; i < 5; i++) {
            String s = String.format("我是第%d个" + title, i);
            mDatas.add(s);
        }

        mAdapter = new ItemAdapter(mContext, mDatas);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    public LinearLayout getLl() {
        return ll;
    }
}
