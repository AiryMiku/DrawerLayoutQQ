package com.airy.drawerlayoutqq;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ListView;

/**
 * Created by Airy on 2017/6/27.
 */

public class CustomListView extends ListView {

    private View inner;
    private float y;
    private Rect normal = new Rect();
    private static final int size = 3;

    public CustomListView(Context context){
        super(context);
    }

    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /*
    * get the first view
    * */
    @SuppressLint("MissingSuperCall")
    @Override
    protected void onFinishInflate() {
        if(getChildCount() > 0){
            inner = getChildAt(0);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(inner == null){
            return super.onTouchEvent(ev);
        }else{
            commOnTouchEvent(ev);
        }
        return super.onTouchEvent(ev);
    }

    /**
     *
     * gesture event
     *
     * @param ev
     * */

    public void commOnTouchEvent(MotionEvent ev){
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                y = ev.getY();
                break;
            case MotionEvent.ACTION_UP:
                if(isNeedAnimation()){
                    animation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                final float preY = y;
                float nowY = ev.getY();
                int deltaY = (int) (preY - nowY) / size;
                y=nowY;
                //滚动到最上或最下就不会再滚动，然后移动布局
                if (isNeedMove()){
                    //save normal position
                    if (normal.isEmpty()) {
                        normal.set(inner.getLeft(), inner.getTop(), inner.getRight(), inner.getBottom());
                        return;
                    }
                    //移动布局
                    inner.layout(inner.getLeft(),inner.getTop()-deltaY,inner.getRight(),inner.getBottom()-deltaY);
                }
                break;
        }
    }


    // 开启动画移动
    public void animation() {
        // 开启移动动画
        TranslateAnimation ta = new TranslateAnimation(0, 0, inner.getTop() - normal.top, 0);
        ta.setDuration(200);
        inner.startAnimation(ta);
        // 设置回到正常的布局位置
        inner.layout(normal.left, normal.top, normal.right, normal.bottom);
        normal.setEmpty();
    }

    // 是否需要开启动画
    public boolean isNeedAnimation() {
        return !normal.isEmpty();
    }

    // 是否需要移动布局
    public boolean isNeedMove() {
        int offset = inner.getMeasuredHeight() - getHeight();
        int scrollY = getScrollY();
        if (scrollY == 0 || scrollY == offset) {
            return true;
        }
        return false;
    }


}
