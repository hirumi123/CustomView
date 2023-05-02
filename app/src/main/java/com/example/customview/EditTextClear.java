package com.example.customview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.res.ResourcesCompat;

public class EditTextClear extends AppCompatEditText {

    Drawable mClearButtonImage;


    private void init() {
        mClearButtonImage = ResourcesCompat.getDrawable
                (getResources(), R.drawable.ic_clear_opaque, null);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                showClearButton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (getCompoundDrawablesRelative()[2] != null){
                    float clearButtonStartPosition =
                            (getWidth() - getPaddingEnd() - mClearButtonImage.getIntrinsicWidth());
                    boolean isButtonClicked = false;

//                    if (event.getX() > clearButtonStartPosition){
//                        isButtonClicked = true;
//                    }

                    if (getLayoutDirection() == LAYOUT_DIRECTION_RTL) {
                        if (event.getX() < clearButtonStartPosition) {
                            isButtonClicked = true;
                        }
                    }

                    else {
                        if (event.getX() > clearButtonStartPosition) {
                            isButtonClicked = true;
                        }
                    }


                    if (isButtonClicked){
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            mClearButtonImage = ResourcesCompat.getDrawable
                                    (getResources(), R.drawable.ic_clear, null);
                            showClearButton();
                        }
                        if (event.getAction() == MotionEvent.ACTION_UP){
                            mClearButtonImage = ResourcesCompat.getDrawable
                                    (getResources(), R.drawable.ic_clear_opaque, null);
                            getText().clear();
                            hideClearButton();
                            return true;
                        }
                    }
                    else {
                        return false;
                    }
                }
                return false;
            }
        });
    }
    public EditTextClear(@NonNull Context context) {
        super(context);
        init();
    }

    public EditTextClear(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditTextClear(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void showClearButton(){
        setCompoundDrawablesRelativeWithIntrinsicBounds
                (null, null, mClearButtonImage, null);
    }

    private void hideClearButton(){
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null);
    }
}

