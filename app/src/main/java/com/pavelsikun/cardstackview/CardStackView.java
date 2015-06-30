package com.pavelsikun.cardstackview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RotateDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;

/**
 * Created by mrbimc on 25.06.15.
 */
public class CardStackView extends FrameLayout {

    public static final int DIRECTION_DOWN = 0;
    public static final int DIRECTION_UP = 1;

    private static final int DEFAULT_STACK_SIZE = 0;
    private static final int DEFAULT_STACK_DIRECTION = DIRECTION_DOWN;

    private int mDirection = DIRECTION_DOWN;
    private int mStackSize = 0;

    private LayerDrawable mBackground;

    public CardStackView(Context context) {
        super(context);
        init(null);
    }

    public CardStackView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CardStackView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        setValuesFromXml(attrs);
        setStackDirection(mDirection);
    }

    private void setValuesFromXml(AttributeSet attrs) {
        if(attrs == null) {
            mDirection = DIRECTION_DOWN;
            mStackSize = 0;
        } else {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.StackView);
            try {
                mDirection = a.getInt(R.styleable.StackView_stackDirection, DEFAULT_STACK_DIRECTION);
                mStackSize = a.getInt(R.styleable.StackView_stackSize, DEFAULT_STACK_SIZE);
            } finally {
                a.recycle();
            }
        }
    }

    public void setStackSize(int size) {
        mStackSize = size;
        if(size > 5) mStackSize = 5;
        else if(size < 0) mStackSize = 0;

        for(int i = 0; i < 5; i++) mBackground.getDrawable(i).setAlpha(255);
        for(int i = 4 - mStackSize; i != -1; i-- ) mBackground.getDrawable(i).setAlpha(0);
        invalidate();
    }

    public int getStackSize() {
        return mStackSize;
    }

    public int getStackDirection() {
        return mDirection;
    }

    public void setStackDirection(int direction) {
        mDirection = direction;
        if(mDirection == DIRECTION_UP) setBackgroundResource(R.drawable.stack_up);
        else if(mDirection == DIRECTION_DOWN) setBackgroundResource(R.drawable.stack);
        mBackground = (LayerDrawable) getBackground();
        setChildGravity();
        setStackSize(mStackSize);
    }

    private void setChildGravity() {
        if(getChildCount() > 0) {
            if(mDirection == DIRECTION_UP)
                ((FrameLayout.LayoutParams) getChildAt(0).getLayoutParams()).gravity = Gravity.BOTTOM;
            else if(mDirection == DIRECTION_DOWN)
                ((FrameLayout.LayoutParams) getChildAt(0).getLayoutParams()).gravity = Gravity.TOP;
        }
    }
}
