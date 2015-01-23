package com.example.mr_holmes.slidingbartest.listTest;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import com.example.mr_holmes.slidingbartest.R;

/**
* Created by Mr_Holmes on 21/01/15.
*/
public class FragmentListView extends Fragment {

    ListView listView;
    List<SampleListItem> sampleListItems;

    public FragmentListView() {}

    @Override
    public void onAttach(Activity mainActivity2) {
        super.onAttach(mainActivity2);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        return inflater.inflate(R.layout.fragment_list_view, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        sampleListItems = new ArrayList<SampleListItem>();
        for(int i = 0; i < 20; i++)
        {
            String str = new String();
            str = "Description number " + i;
            SampleListItem item = new SampleListItem(R.drawable.graphic, str);
            sampleListItems.add(item);
        }

        listView = (ListView) getView().findViewById(R.id.descriptionList);
        ListAdapter adapter = new ListAdapter(getActivity(), sampleListItems);
        listView.setAdapter(adapter);

    }

}