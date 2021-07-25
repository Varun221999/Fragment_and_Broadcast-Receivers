package com.example.app3;
/*
* Name: Varun Subramanian
* NetID: vsubra25
* CS 478
*
* References:
* https://developer.android.com/guide/topics/resources/runtime-changes
* */
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
PS: Comments are all over the place.
    These are some of the abbreviations for fragments I have used
TitleFragment = Fragment 1 = F1
Cover/Poster/Thumbnail Fragment = Fragment2 = F2
 */

/*
* TODO: setRetainInstance(true) is also not working
*  in both fragments. Return back to this issue after sorting out
*  communication.
* */

/*
* Show List and Index Num:
* The Mentalist - 0
* Breaking Bad - 1
* Suits - 2
* Dark - 3
* Mr.Robot - 4
* */
public class MainActivity extends AppCompatActivity
        implements FirstFragment.ListSelectionListener {

    /*
    * onConfigurationChanged gets called when configuration changes.
    * Setting a bool flag to true when orientation is landscape
    * later to be used in setLayout
    * */
    Boolean flag = false;
    public Configuration newConfig1;
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        newConfig1 = newConfig;
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            flag = true;
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            flag = false;
        }
    }

    public static String[] mQuoteArray;
    public static String[] mTitleArray;
    public static String[] mURLArray;
    public static int showSelected = -1;
    public static ArrayList<Integer> mThumbIds = new ArrayList<>(
            Arrays.asList(R.drawable.thementalist, R.drawable.breakingbad,
                    R.drawable.suits, R.drawable.dark, R.drawable.mrrobot));

    public Toolbar tb;
    public ActionBar actionBar;

    private final SecondFragment mSecondFragment = new SecondFragment();
    private static final String TOAST_INTENT  = "TV_SHOW";
    private static final String TOAST_INTENT_App2  = "A2";
    public BroadcastReceiver broadcastReceiver;
    FragmentManager mFragmentManager;
    private FrameLayout mFragment1Layout, mFragment2Layout;

    private static final String DANGEROUS_PERMISSION = "edu.uic.cs478.s19.kaboom";
    private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
    private static final String TAG = "FirstFragmentViewerActivity";

    /*
    * onCreate for MainActivity
    * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.i(TAG, getClass().getSimpleName() + ": entered onCreate()");
        super.onCreate(savedInstanceState);

        // Get the string arrays with the Show Titles
        mTitleArray = getResources().getStringArray(R.array.Titles);
        mQuoteArray = getResources().getStringArray(R.array.Quotes);
        mURLArray   = getResources().getStringArray(R.array.IMDB_URLs);
//        mURLArray   = getResources().getStringArray(R.array.Wiki_URLs);

        setContentView(R.layout.activity_main);

        // Get references to the TitlesFragment i.e. Fragment1's container
        // & to the Posters/CoversFragment i.e. fragment2's container
        mFragment1Layout = (FrameLayout) findViewById(R.id.fragment1_container);
        mFragment2Layout = (FrameLayout) findViewById(R.id.fragment2_container);

        //Toolbar/action bar
        tb = findViewById(R.id.my_toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.androidiconblacklarge);



        // Get a reference to the SupportFragmentManager instead of original FragmentManager
        mFragmentManager = getSupportFragmentManager();

        // Start a new FragmentTransaction with backward compatibility
        //android.support.v4.app.FragmentTransaction fragmentTransaction = mFragmentManager
        //		.beginTransaction();

        FragmentTransaction fragmentTransaction =mFragmentManager.beginTransaction();


        fragmentTransaction.replace(R.id.fragment1_container, new FirstFragment());

        // Commit the FragmentTransaction
        fragmentTransaction.commit();

        // Add a OnBackStackChangedListener to reset the layout when the back stack changes
        mFragmentManager.addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        setLayout();
                    }
                });
    }

    /*
    * setLayout():
    * This functions sets layout of the screen according the orientations
    * if portait, fragment 1 occupies whole screen,
    *   when an item on the list in F1 is clicked, F2 with the corresponding poster
    *   takes over the whole screen
    * else in landscape fragment 1 occupies whole screen
    *   upon listItemClicked, F1 shifts to 1/3rd and F2 occupies the remaining 2/3rd of the screen
    * */
    private void setLayout() {

        /* pseudo
        if(2nd Frag added)
        {
                set basic layout:
                F1 - Full Screen
                F2 - 0 Screen
        }
        else
        {
            if(orientation=LANDSCAPE)
            {
                F1 = 1/3rd Screen
                F2 = 2/3rd Screen
            }
            else when portrait
            {
                set basic layout:
                F1 - Full Screen
                F2 - 0 Screen
            }
        }
        * */

        // Determine whether the SecondFragment has been added
        if (!mSecondFragment.isAdded()) {

            // Make the TitleFragment occupy the entire layout
            mFragment1Layout.setLayoutParams(new LinearLayout.LayoutParams(
                    MATCH_PARENT, MATCH_PARENT));
            mFragment2Layout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT));
        }
        else {

//            if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE)
//            if(newConfig1.orientation== Configuration.ORIENTATION_LANDSCAPE)
            if(flag)
            {
                // the Fragment1Layout covers 1/3rd of the screen in landscape
                mFragment1Layout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 1f));

                // the Fragment2Layout covers 2/3rd of the screen in landscape
                mFragment2Layout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 2f));
            }

            //Portrait mode
            else{
                // "hide" the Fragment1Layout
                mFragment1Layout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 0));

                // Make the Fragment2Layout take full layout's width
                mFragment2Layout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 1f));
            }
        }
    }

    // Implement Java interface ListSelectionListener defined in TitlesFragment
    // Called by TitlesFragment when the user selects an item in the TitlesFragment
    @Override
    public void onListSelection(int index) {

        // If the QuoteFragment has not been added, add it now
        if (!mSecondFragment.isAdded()) {

            // Start a new FragmentTransaction
            FragmentTransaction fragmentTransaction = mFragmentManager
                    .beginTransaction();

            // Add the QuoteFragment to the layout
            fragmentTransaction.add(R.id.fragment2_container,
                    mSecondFragment);

            // Add this FragmentTransaction to the backstack
            fragmentTransaction.addToBackStack(null);

            // Commit the FragmentTransaction
            fragmentTransaction.commit();

            // Force Android to execute the committed FragmentTransaction
            mFragmentManager.executePendingTransactions();
        }

        if (mSecondFragment.getShownIndex() != index) {

            //Saving the index num of the show selected
            showSelected = index; //Will be used to send the apt URL to A1.

            // Tell the SecondFragment to show the Cover at position index
            mSecondFragment.showCoverAtIndex(index, mThumbIds);
        }
    }


    /*  Options Menu:
    *       onCreateOptionsMenu()
    *       onOptionsItemsSelected()
    * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
//        tb.inflateMenu(R.menu.options_menu);
        return true;
    }

    //TODO: MenuItem1: Launch App1 and 2. MenuItem2: Close App3
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch(item.getItemId()){
            case R.id.menuItem1:
                Log.i(TAG, "Launching App1 and App2");
                //TODO: sendOrderedBroadcast to start A1 and A2
                /*
                * if (a tv show is selected)
                * {
                *   create an intent, putExtra with the chosen tv show's URL
                *   send an ordered broadcast
                *   A2 should display toast, then A1 should show the webView
                * }
                * else if(no tv show is selected)
                * {
                *   no intent is created and sent.
                * display a toast message
                * }
                * */
                if(showSelected != -1){

                    intent = new Intent(TOAST_INTENT);
                    intent.putExtra("TV_SHOW", mURLArray[showSelected]);
                    Log.i("ShowURL: ", mURLArray[showSelected]); //Debugging-remove
                    sendOrderedBroadcast(intent, DANGEROUS_PERMISSION);
                }
                else{
                    Toast.makeText(getApplicationContext(),
                            "Select a TV Show first",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.menuItem2:
                Log.i(TAG, "Exiting App3");
                finishAndRemoveTask();
                System.exit(0);
                break;
        }
        return true;
    }


    /*Callbacks for debugging purposes*/
    @Override
    protected void onDestroy() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onPause()");
        super.onPause();
    }

    @Override
    protected void onRestart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onRestart()");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");
        super.onResume();
    }

    @Override
    protected void onStart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStart()");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStop()");
        super.onStop();
    }

}

