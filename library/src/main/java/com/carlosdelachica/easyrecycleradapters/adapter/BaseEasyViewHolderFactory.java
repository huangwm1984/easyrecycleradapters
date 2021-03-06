package com.carlosdelachica.easyrecycleradapters.adapter;

import android.content.Context;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class BaseEasyViewHolderFactory {

    protected Context context;

    private Map<Class, Class<? extends EasyViewHolder>> boundViewHolders = new HashMap<>();

    public BaseEasyViewHolderFactory(Context context) {
        this.context = context;
    }

    public EasyViewHolder create(Class valueClass, ViewGroup parent) {
        try {
            Class<? extends EasyViewHolder> easyViewHolderClass = boundViewHolders.get(valueClass);
            Constructor<? extends EasyViewHolder> constructor = easyViewHolderClass.getDeclaredConstructor(Context.class, ViewGroup.class);
            return constructor.newInstance(context, parent);
        } catch (Throwable e) {
            throw new RuntimeException("Unable to create ViewHolder for" + valueClass, e);
        }
    }

    void bind(Class valueClass, Class<? extends EasyViewHolder> viewHolder) {
        boundViewHolders.put(valueClass, viewHolder);
    }

}
