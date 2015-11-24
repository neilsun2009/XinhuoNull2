package com.teamnull2.xinhuo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lenovo on 2015/11/23.
 */
public class HomeListItemAdapter extends ArrayAdapter<HomeListItem> {
    private int resourceId;
    public HomeListItemAdapter(Context context, int textViewResourceId, List<HomeListItem> objects) {
        super(context, textViewResourceId, objects);
        resourceId=textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HomeListItem homeListItem= getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView==null) {
            view= LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder= new ViewHolder();
            viewHolder.fl= (FrameLayout)view.findViewById(R.id.hli_special);
            viewHolder.iv= (ImageView)view.findViewById(R.id.hli_image);
            viewHolder.tv= (TextView)view.findViewById(R.id.hli_text);
            view.setTag(viewHolder);
        } else {
            view= convertView;
            viewHolder= (ViewHolder)view.getTag();
        }
        // ImageView hliImage= (ImageView)view.findViewById(R.id.hli_image);
        // FrameLayout hliSpecial=(FrameLayout)view.findViewById(R.id.hli_special);
        // TextView hliText= (TextView)view.findViewById(R.id.hli_text);
        viewHolder.iv.setImageResource(homeListItem.getImage());
        viewHolder.fl.setBackgroundResource(homeListItem.getSpecial());
        viewHolder.tv.setText(homeListItem.getTitle());
        return view;
    }
    class ViewHolder {
        ImageView iv;
        FrameLayout fl;
        TextView tv;
    }
}
