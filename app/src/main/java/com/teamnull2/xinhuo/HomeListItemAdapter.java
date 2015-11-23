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
        View view= LayoutInflater.from(getContext()).inflate(resourceId, null);
        ImageView hliImage= (ImageView)view.findViewById(R.id.hli_image);
        FrameLayout hliSpecial=(FrameLayout)view.findViewById(R.id.hli_special);
        TextView hliText= (TextView)view.findViewById(R.id.hli_text);
        hliImage.setImageResource(homeListItem.getImage());
        hliSpecial.setBackgroundResource(homeListItem.getSpecial());
        hliText.setText(homeListItem.getTitle());
        return view;
    }
}
