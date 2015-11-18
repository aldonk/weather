package com.sxc.cai.testapp;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by cai on 2015/10/30.
 */
public class ReFlashLinearLayout extends LinearLayout implements View.OnTouchListener{
    View header;    //顶部布局文件
    int headerHeight; //顶部布局文件的高度
//    private ScrollView scrollView;

    int scrolly; //滚动条滑动距离
    int scrollState; //当前滚动状态
    boolean isRemark; //标记当前是在最顶端摁下的
    int startY; //摁下时的Y值

    int state; //当前的状态
    final int NONE = 0; //正常状态
    final int PULL = 1; //提示下拉可以刷新状态
    final int RELESE = 2; //提示松开释放状态
    final int REFLASHING = 3; //正在刷新状态
    IReFlashListener iReFlashListener; //刷新数据的接口

    public ReFlashLinearLayout(Context context) {
        super(context);
        initView(context);
    }

    public ReFlashLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ReFlashLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
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
//        scrollView = (ScrollView) header.findViewById(R.id.scroll);
        measureView(header);
        headerHeight = header.getMeasuredHeight();  //getMeasuredHeight()是实际View的大小，与屏幕无关
        Log.i("header长度","headerHeight"+headerHeight);
        topPadding(-headerHeight);
        this.addView(header);
        this.setOnTouchListener(this);
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
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
//                if (scrollView.getScrollY()==0){
////                    Log.i("滑动距离","getScrollY"+scrollView.getScrollY());
                    isRemark = true;
                    startY = (int)event.getY();
//                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                if (state == RELESE){
                    state = REFLASHING;
                    //加载最新数据
                    reflashViewByState();
                    iReFlashListener.onReFlash();
                }else if (state == PULL){
                    state = NONE;
                    isRemark = false;
                    reflashViewByState();
                }
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                onMove(event);
                break;
            }
            default:
                break;
        }
        return true;
    }
    /**
     * 判断移动过程中的操作
     */
    private void onMove(MotionEvent event){
        if (!isRemark){
            return;
        }
//        Log.i("滑动距离","headerHeight"+headerHeight);
        int tempY = (int) event.getY();
        int space = tempY - startY;
//        Log.i("tag","space"+space);
        int topPadding = space - headerHeight;
        if (topPadding > 350){
            topPadding = 350;
        }
//        Log.i("state状态","state"+state);
        switch (state){
            case NONE:{
                if (space > 0) {
                    state = PULL;
                    reflashViewByState();
                }
                break;
            }
            case PULL:{
                topPadding(topPadding);
                if (space>headerHeight+50){
                    state = RELESE;
                    reflashViewByState();
                }
                break;
            }
            case RELESE:{
                topPadding(topPadding);
                if (space<headerHeight+30){
                    state = PULL;
                    reflashViewByState();
                }else if (space <= 0){
                    state = NONE;
                    isRemark = false;
                    reflashViewByState();
                }
                break;
            }
            case REFLASHING:{
                reflashViewByState();
                break;
            }
            default:
                break;
        }
    }

    /**
     * 根据当前状态改变界面显示
     */
    private void reflashViewByState(){
        TextView tip = (TextView) header.findViewById(R.id.tip);
        ImageView down = (ImageView) header.findViewById(R.id.down);
        ProgressBar progress = (ProgressBar) header.findViewById(R.id.progress);
        RotateAnimation anim = new RotateAnimation(0,-180,RotateAnimation.RELATIVE_TO_SELF,0.5f,
                RotateAnimation.RELATIVE_TO_SELF,0.5f);
        anim.setDuration(500);
        anim.setFillAfter(true);
        RotateAnimation anim1 = new RotateAnimation(0,0,RotateAnimation.RELATIVE_TO_SELF,0.5f,
                RotateAnimation.RELATIVE_TO_SELF,0.5f);
        anim1.setDuration(500);
        anim1.setFillAfter(true);
        switch (state){
            case NONE:{
                topPadding(-headerHeight);
                down.clearAnimation();
                down.setVisibility(View.INVISIBLE);
                break;
            }
            case PULL:{
                down.setVisibility(View.VISIBLE);
                progress.setVisibility(View.INVISIBLE);
                tip.setText("下拉可以刷新");
                down.clearAnimation();
                down.setAnimation(anim1);
                break;
            }
            case RELESE:{
                down.setVisibility(View.VISIBLE);
                progress.setVisibility(View.INVISIBLE);
                tip.setText("松开可以刷新");
                down.clearAnimation();
                down.setAnimation(anim);
                break;
            }
            case REFLASHING:{
                topPadding(50);
                down.setVisibility(View.INVISIBLE);
                progress.setVisibility(View.VISIBLE);
                tip.setText("正在刷新...");
                down.clearAnimation();
                break;
            }
            default:
                break;
        }
    }

    /**
     * 获取完数据
     */
    public void reflashComplete(){
        state = NONE;
        isRemark = false;
        reflashViewByState();
    }

    public void setInterface(IReFlashListener iReFlashListener){
        this.iReFlashListener = iReFlashListener;
    }
    /**
     * 刷新数据接口；使用接口回调的方式调用到Activity类
     */
    public interface IReFlashListener{
        public void onReFlash();
    }
}
