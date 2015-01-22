package com.example.mr_holmes.slidingbartest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.mr_holmes.slidingbartest.ListAdapter;
import com.example.mr_holmes.slidingbartest.SampleListItem;

/**
 * Created by Mr_Holmes on 21/01/15.
 */
public class SampleListView extends Activity {

    ListView listView;
    List<SampleListItem> sampleListItems;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_list_view);

        sampleListItems = new ArrayList<SampleListItem>();
        for(int i = 0; i < 20; i++)
        {
            String str = new String();
            str = "Description number " + i;
            SampleListItem item = new SampleListItem(R.drawable.graphic, str);
            sampleListItems.add(item);
        }

        listView = (ListView) findViewById(R.id.descriptionList);
        ListAdapter adapter = new ListAdapter(this, sampleListItems);
        listView.setAdapter(adapter);
    }

}
