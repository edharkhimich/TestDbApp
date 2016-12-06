package com.khimich.testdbapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.khimich.testdbapp.R;
import com.khimich.testdbapp.model.Contact;

import java.util.List;


public class MyCursorAdapter extends BaseAdapter {

    private List<Contact> list;
    private Context context;

    public MyCursorAdapter(List<Contact> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Contact getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_layout, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.name.setText(list.get(i).getName() + " " + list.get(i).getSurname());
        notifyDataSetChanged();
        return view;
    }

    public class ViewHolder {

        private TextView name;

        ViewHolder(View view) {
            name = (TextView) view.findViewById(R.id.itemTv);
        }
    }
}