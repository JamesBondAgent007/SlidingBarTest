package com.example.mr_holmes.slidingbartest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mr_holmes.slidingbartest.SampleListView;
import com.example.mr_holmes.slidingbartest.SampleListView.Description;
/**
 * Created by Mr_Holmes on 21/01/15.
 */
public class ListAdapter extends BaseAdapter {

    SampleListView listView = new SampleListView();
    List<Description> descriptionList = listView.getDataForListView();

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null)
        {
            LayoutInflater inflater = (LayoutInflater) listView.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.sample_list_item, parent, false);
        }

        TextView listElement = (TextView)convertView.findViewById(R.id.listElement);

        Description description = descriptionList.get(position);

        listElement.setText(description.description);

        return convertView;

    }

    public Description getDescription(int position)
    {
        return descriptionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

}
