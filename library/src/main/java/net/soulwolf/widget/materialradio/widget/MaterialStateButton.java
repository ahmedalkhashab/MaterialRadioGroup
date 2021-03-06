/**
 * <pre>
 * Copyright 2015 Soulwolf Ching
 * Copyright 2015 The Android Open Source Project for MaterialRadioGroup
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </pre>
 */
package net.soulwolf.widget.materialradio.widget;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.support.v7.widget.AppCompatImageView;

import com.toaker.common.tlog.TLog;

import net.soulwolf.widget.materialradio.listener.OnStateButtonCheckedListener;

/**
 * author: Soulwolf Created on 2015/7/15 23:45.
 * email : Ching.Soulwolf@gmail.com
 */
public class MaterialStateButton extends AppCompatImageView implements Checkable {

    private static final int[] CHECKED_STATE_SET = {
            android.R.attr.state_checked
    };

    private static final boolean DEBUG = false;

    private static final String LOG_TAG = "MaterialStateButton:";

    private boolean mChecked;

    private OnStateButtonCheckedListener mOnStateButtonCheckedListener;

    public MaterialStateButton(Context context) {
        super(context);
    }

    public MaterialStateButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MaterialStateButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setChecked(boolean checked) {
        if(DEBUG){
            TLog.d(LOG_TAG,"setChecked :%s",checked);
        }
        if (mChecked != checked) {
            mChecked = checked;
            refreshDrawableState();

            if(mOnStateButtonCheckedListener != null){
                mOnStateButtonCheckedListener.onCheckedChanged(mChecked);
            }
        }
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void toggle() {
        setChecked(!mChecked);
        if(mOnStateButtonCheckedListener != null){
            mOnStateButtonCheckedListener.onCheckedToggle();
        }
    }

    @Override
    public boolean performClick() {
        if(DEBUG){
            TLog.d(LOG_TAG,"performClick");
        }
        if(!isChecked()){
            toggle();
        }
        return super.performClick();
    }

    @Override
    public int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }

    public void setOnStateButtonCheckedListener(OnStateButtonCheckedListener listener) {
        this.mOnStateButtonCheckedListener = listener;
    }
}
