package com.leohan.refresh.multistyle;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.leohan.refresh.entites.Ad;
import com.leohan.refresh.entites.Person;
import com.leohan.refresh.viewholder.AdViewHolder;
import com.leohan.refresh.viewholder.PersonViewHolder;

import java.security.InvalidParameterException;

/**
 * Created by Mr.Jude on 2015/7/18.
 */
public class PersonWithAdAdapter extends RecyclerArrayAdapter<Object> {
    public static final int TYPE_INVALID = 0;
    public static final int TYPE_AD = 1;
    public static final int TYPE_PERSON = 2;
    public PersonWithAdAdapter(Context context) {
        super(context);
    }

    @Override
    public int getViewType(int position) {
        if(getItem(position) instanceof Ad){
            return TYPE_AD;
        }else if (getItem(position) instanceof Person){
            return TYPE_PERSON;
        }
        return TYPE_INVALID;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_PERSON:
                return new PersonViewHolder(parent);
            case TYPE_AD:
                return new AdViewHolder(parent);
            default:
                throw new InvalidParameterException();
        }
    }
}
