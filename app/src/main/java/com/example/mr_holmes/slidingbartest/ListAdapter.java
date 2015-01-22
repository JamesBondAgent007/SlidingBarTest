package com.example.mr_holmes.slidingbartest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mr_holmes.slidingbartest.SampleListView;
import com.example.mr_holmes.slidingbartest.SampleListItem;
/**
 * Created by Mr_Holmes on 21/01/15.
 */
public class ListAdapter extends BaseAdapter {

    Context context;
    List<SampleListItem> SampleList;

    private class ViewHolder
    {
        ImageView _image;
        TextView _txt;
    }

    public ListAdapter(Context context, List<SampleListItem> sampleList) {
        this.context = context;
        SampleList = sampleList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.sample_list_item, null);
            holder = new ViewHolder();
            holder._txt = (TextView) convertView.findViewById(R.id.txt);
            holder._image = (ImageView) convertView.findViewById(R.id.listImage);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        SampleListItem sampleListItemItem = (SampleListItem) getItem(position);

        holder._txt.setText(sampleListItemItem.getDescription());
        holder._image.setImageResource(sampleListItemItem.getImageId());

        return convertView;
    }

    @Override
    public int getCount() {
        return SampleList.size();
    }

    @Override
    public long getItemId(int position) {
        return SampleList.indexOf(getItemId(position));
    }

    @Override
    public Object getItem(int position) {
        return SampleList.get(position);
    }

}
