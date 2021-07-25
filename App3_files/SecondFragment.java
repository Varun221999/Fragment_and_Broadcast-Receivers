package com.example.app3;

import androidx.fragment.app.Fragment;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/*SecondFragment = F2 = Cover/PosterFragment: The poster of TV show Titles. */
public class SecondFragment extends Fragment {

    private static final String TAG = "SecondFragment";
    //private static ArrayList<Integer> coverIds;
    private int mCurrIdx = -1;
    private int mQuoteArrLen;
    private ImageView mThumbnail = null;

    int getShownIndex() {
        return mCurrIdx;
    }

    // Show the Cover string at position newIndex
    void showCoverAtIndex(int newIndex, ArrayList<Integer> id) {
        if (newIndex < 0 || newIndex >= mQuoteArrLen)
            return;
        mCurrIdx = newIndex;
        //mThumbnail.setImageResource(MainActivity.mThumbIds.get(mCurrIdx));
        //coverIds.addAll(id);
        mThumbnail.setImageResource(id.get(mCurrIdx));
    }

    @Override
    public void onAttach(@NonNull Context activity) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onAttach()");
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
        super.onCreate(savedInstanceState);
        setRetainInstance(true); //TODO: Not Working?!
    }

    // Called to create the content view for this Fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreateView()");

        // Inflate the layout defined in fragment1Layout.xml
        // The last parameter is false because the returned view does not need to be attached to the container ViewGroup
        return inflater.inflate(R.layout.fragment2layout,
                container, false);
    }

    // Set up some information about the mThumbnail ImageView
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onActivityCreated()");
        super.onActivityCreated(savedInstanceState);

        mThumbnail = (ImageView) getActivity().findViewById(R.id.quoteView);
        mQuoteArrLen = MainActivity.mQuoteArray.length;
    }

    @Override
    public void onStart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStart()");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onPause()");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStop()");
        super.onStop();
    }

    @Override
    public void onDetach() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDetach()");
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDestroyView()");
        super.onDestroyView();
    }

}
