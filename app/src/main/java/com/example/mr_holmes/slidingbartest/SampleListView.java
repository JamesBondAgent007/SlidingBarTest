package com.example.mr_holmes.slidingbartest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr_Holmes on 21/01/15.
 */
public class SampleListView extends Activity {


    //dato di prova
    public class Description
    {
       String description;
    }

    ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_list_view);

        listAdapter = new ListAdapter();
        ListView descriptionList = (ListView) findViewById(R.id.descriptionList);
        descriptionList.setAdapter(this.listAdapter);

        descriptionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Description description = listAdapter.getDescription(position);
                Toast.makeText(SampleListView.this, description.description, Toast.LENGTH_LONG).show();
            }
        });
    }

    public List<Description> getDataForListView(){

        List<Description> descriptionList = new ArrayList<Description>();

        for(int i = 0; i < 10; i++)
        {
            Description description = new Description();
            description.description = "Description number " + i;
            descriptionList.add(description);
        }

        return descriptionList;

    }


}
