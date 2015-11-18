package com.sxc.cai.testapp;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ScrollView;

/**
 * Created by cai on 2015/10/30.
 */
public class ReFlashScroll extends ScrollView implements AbsListView.OnScrollListener{

    View header;    //顶部布局文件
    int headerHeight; //顶部布局文件的高度
    public ReFlashScroll(Context context) {
        super(context);
        initView(context);
    }

    public ReFlashScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ReFlashScroll(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    /**
     * 初始化界面，添加顶部布局文件到LinearLayout里面
     * @param context
     */
    private void initView(Context context){
        LayoutInflater inflater = LayoutInflater.from(context);
        header = inflater.inflate(R.layout.header_layout,null);
        measureView(header);
        headerHeight = header.getMeasuredHeight();  //getMeasuredHeight()是实际View的大小，与屏幕无关
        Log.i("header长度", "headerHeight" + headerHeight);
        topPadding(-headerHeight);
        this.addView(header);
    }

    /**
     * 设置header布局上边距 为负，使header布局隐藏
     * @param topPadding
     */
    private void topPadding(int topPadding){
        header.setPadding(header.getPaddingLeft(), topPadding, header.getPaddingRight(), header.getPaddingBottom());
        header.invalidate();
    }

    /**
     * 通知父布局，占用的宽高
     * @param view
     */
    private void measureView(View view){
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params == null){
            params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int width = ViewGroup.getChildMeasureSpec(0,0,params.width);
        int height;
        int tempHeight = params.height;
        if (tempHeight > 0){
            height = MeasureSpec.makeMeasureSpec(tempHeight,MeasureSpec.EXACTLY);
        }else {
            height = MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);
        }
        view.measure(width, height);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
