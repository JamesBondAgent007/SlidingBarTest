package com.example.mr_holmes.slidingbartest.CustomList;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mr_holmes.slidingbartest.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentListView2.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentListView2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentListView2 extends Fragment {

    public FragmentListView2() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View myFragmentView = inflater.inflate(R.layout.fragment_list_view_2, container, false);

        return myFragmentView;
    }

}
