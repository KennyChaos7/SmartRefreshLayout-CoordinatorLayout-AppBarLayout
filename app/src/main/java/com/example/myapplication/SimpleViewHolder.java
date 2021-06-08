package com.example.myapplication;

import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author: ou
 * @Date:   2020-12-26 0026 9:31
 * @Description: 简单设置RecyclerView的控件属性
 */
public class SimpleViewHolder extends RecyclerView.ViewHolder {
    public SimpleViewHolder(View itemView) {
        super(itemView);
    }
    
    //  线程安全, 自动扩容
    private SparseArray<View> views = new SparseArray<>();

    public SimpleViewHolder setText(@IdRes int id, String string) {
        TextView textView = getTargetView(id);
        if (textView != null) textView.setText(string);
        return this;
    }

    public SimpleViewHolder setTextColor(@IdRes int id, @ColorInt int colorId) {
        TextView textView = getTargetView(id);
        if (textView != null) textView.setTextColor(colorId);
        return this;
    }

    public SimpleViewHolder setBackgroundColor(@IdRes int id, @ColorInt int colorId) {
        View view = getTargetView(id);
        if (view != null) view.setBackgroundColor(colorId);
        return this;
    }

    public SimpleViewHolder setBackgroundResource(@IdRes int id, @DrawableRes int drawableId) {
        View view = getTargetView(id);
        if (view != null) view.setBackgroundResource(drawableId);
        return this;
    }

    public SimpleViewHolder setVisible(@IdRes int id, boolean isVisible) {
        View view = getTargetView(id);
        if (view != null) view.setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
        return this;
    }

    public SimpleViewHolder setGone(@IdRes int id, boolean isHide) {
        View view = getTargetView(id);
        if (view != null) view.setVisibility(isHide ? View.GONE : View.VISIBLE);
        return this;
    }

    public SimpleViewHolder setImageResource(@IdRes int id, int resId) {
        ImageView imageView = getTargetView(id);
        if (imageView != null) imageView.setImageResource(resId);
        return this;
    }

    public SimpleViewHolder setBackgroundDrawable(@IdRes int id, Drawable drawable) {
        View view = this.itemView.findViewById(id);
        if (view != null && drawable != null)
            view.setBackground(drawable);
        return this;
    }

    public SimpleViewHolder bindOnClickListener(@IdRes int id, View.OnClickListener onClickListener) {
        View view = getTargetView(id);
        if (view != null) view.setOnClickListener(onClickListener);
        return this;
    }

    public View findView(@IdRes int id) {
        return this.itemView.findViewById(id);
    }

    /**
     * 不用每次都去findView
     * @param id
     * @param <T>
     * @return
     */
    private <T extends View> T getTargetView(@IdRes int id) {
        View view = views.get(id);
        if (view == null)
        {
            view = this.itemView.findViewById(id);
            if (view != null) {
                views.put(id, view);
            }
        }
        return (T) view;
    }
}
