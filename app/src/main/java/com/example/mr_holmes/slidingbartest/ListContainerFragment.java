package com.example.mr_holmes.slidingbartest;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListContainerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListContainerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListContainerFragment extends Fragment {

    @Override
    public void onAttach(Activity mainActivity2) {
        super.onAttach(mainActivity2);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.list_container_fragment, container, false);
    }


}
