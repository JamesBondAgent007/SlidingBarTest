package com.example.mr_holmes.slidingbartest.listTest;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mr_holmes.slidingbartest.R;

/**
* Created by Mr_Holmes on 21/01/15.
*/
public class ListAdapter extends BaseAdapter {

    Context context;
    List<SampleListItem> SampleList; //lista di oggetti base della lista (sono questi che si possono modificare a piacimento)

    //questa classe andrà portata fuori e chiaramente cambia a seconda del layout
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
            holder._txt = (TextView) convertView.findViewById(R.id.txt);            //Questa parte di codice andrebbe resa più flessibile
            holder._image = (ImageView) convertView.findViewById(R.id.listImage);   //basterebbe fare un metodo che prende viewHolder
            convertView.setTag(holder);                                             //come parametro e setta la roba a seconda del tipo di holder
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        SampleListItem sampleListItemItem = (SampleListItem) getItem(position);

        holder._txt.setText(sampleListItemItem.getDescription());       //Anche questa parte deve diventare più flessibile
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
