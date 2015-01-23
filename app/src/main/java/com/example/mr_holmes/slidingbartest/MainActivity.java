package com.example.mr_holmes.slidingbartest;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.SearchView;

import com.example.mr_holmes.slidingbartest.animationHelper.BackgroundColorChangerHSV;
import com.example.mr_holmes.slidingbartest.animationHelper.DpHelper;
import com.example.mr_holmes.slidingbartest.animationHelper.LayoutDimensionChanger;
//import com.example.mr_holmes.slidingbartest.listTest.SampleListView;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelSlideListener;
import com.example.mr_holmes.slidingbartest.listTest.FragmentListView;

public class MainActivity extends ActionBarActivity {

    private static final String TAG = "MainActivity";
    private DpHelper dpHelper;
    private Context context;



// * * * * * * * * * * * * COSTANTI (evitiamo i numeri magici) * * * * * * * * * * * * * * * * * *
    final float ANCHOR_POINT = 0.5f;
    final int SLIDING_BAR_EXPANDED_HEIGHT_DP = 120;
    final int SLIDING_BAR_HEIGHT_DP = 48;
    final int SEARCH_BAR_PADDING_DP = 8;
    final  int SEARCH_BAR_HIDED_PADDING_DP = -60;


// * * * * * * * * * * * *  DEFINIZIONE E INIZIALIZZAZIONE LAYOUT * * * * * * * * * * * * * * * * *
    private RelativeLayout mSlidingBarBg;
    private LinearLayout mSlidingBar;
    private SlidingUpPanelLayout mSlidingUpPanelLayout;
    private TextView t;
    private ScrollView scrollView;
    private SearchView mSearch;
    private FloatingActionButton mButton;
    private boolean fadeOutAnimationStarted = false;
    private boolean colorAnimationStarted = false;
    private ListView museumList;
    private LinearLayout dragView;


    private void initActivityAndXML()
    {
    // FIND VIEW BY ID * * * * * * * * * * * * * * * * * * * * * * * *
        //setSupportActionBar((Toolbar)findViewById(R.id.main_toolbar)); setta la barra del titolo, per adesso non serve
        mSearch = (SearchView) findViewById(R.id.search_view);
        mSlidingUpPanelLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        //mSlidingUpPanelLayout.setEnableDragViewTouchEvents(true);


        mButton = (FloatingActionButton) findViewById(R.id.nav_button);
        mSlidingBarBg = (RelativeLayout) findViewById(R.id.slidingBarBg);
        mSlidingBar = (LinearLayout) findViewById(R.id.slidingBar);
        TextView t = (TextView) findViewById(R.id.main);

        final Animation fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        final Animation fadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);



    // INIT * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
        mSlidingUpPanelLayout.setAnchorPoint(ANCHOR_POINT);
        mSlidingUpPanelLayout.setDragView(mSlidingBarBg);
        // sliding avviene solo se si scrolla sulla slidingBar e non se si scrolla il contenuto


    }


    public void initEventListeners()
    {

         mSlidingUpPanelLayout.setPanelSlideListener(new PanelSlideListener() {


            SlidingBarExtensionAnimationManager slidingBarHeightAnimMan = new SlidingBarExtensionAnimationManager(mSlidingBar, mSlidingBarBg, context);
            ObjectAnimator slidingBarHeightAnimation;
            BackgroundColorChangerHSV slidingBarColorChanger = new BackgroundColorChangerHSV(mSlidingBarBg, 255, 152, 0);
            ObjectAnimator slidingBarColorAnimation;

//            MarginChanger marginChanger = new MarginChanger((RelativeLayout.MarginLayoutParams) mSearch.getLayoutParams());
//            ObjectAnimator searchBarAnimation;



            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.i(TAG, "onPanelSlide, offset " + slideOffset);
                //resizeScrollView(1-slideOffset); <-- Da richiamare da file esterno

                if (slideOffset >= 0.7) {
                    if (colorAnimationStarted != true) {
                        slidingBarHeightAnimation = ObjectAnimator.ofInt(slidingBarHeightAnimMan, "dpHeight", SLIDING_BAR_HEIGHT_DP, SLIDING_BAR_EXPANDED_HEIGHT_DP);
                        slidingBarHeightAnimation.setDuration(200);
                        slidingBarHeightAnimation.start();

                        slidingBarHeightAnimation = ObjectAnimator.ofFloat(slidingBarColorChanger, "saturation", 0, slidingBarColorChanger.getS());
                        slidingBarHeightAnimation.setDuration(500);
                        slidingBarHeightAnimation.start();
                        colorAnimationStarted = true;
                    }
                }

                if (slideOffset <= 0.67) {
                    if (colorAnimationStarted != false) {
                        slidingBarHeightAnimation = ObjectAnimator.ofFloat(slidingBarColorChanger, "saturation", slidingBarColorChanger.getS(), 0);
                        slidingBarHeightAnimation = ObjectAnimator.ofFloat(slidingBarColorChanger, "saturation", slidingBarColorChanger.getS(), 0);
                        slidingBarHeightAnimation.setDuration(200);
                        slidingBarHeightAnimation.start();

                        slidingBarHeightAnimation = ObjectAnimator.ofInt(slidingBarHeightAnimMan, "dpHeight", SLIDING_BAR_EXPANDED_HEIGHT_DP, SLIDING_BAR_HEIGHT_DP);
                        slidingBarHeightAnimation.setDuration(500);
                        slidingBarHeightAnimation.start();
                        colorAnimationStarted = false;
                    }
                }


                if (slideOffset >= 0.4) {

                    if (fadeOutAnimationStarted == false)
                    {

                        Animation a = new Animation() {
                            @Override
                            protected void applyTransformation(float interpolatedTime, Transformation t) {
                                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mSearch.getLayoutParams();
                                params.topMargin = DpHelper.dpToPx(SEARCH_BAR_PADDING_DP, context) + (int) (dpHelper.dpToPx(SEARCH_BAR_HIDED_PADDING_DP) * interpolatedTime);
                                mSearch.setLayoutParams(params);
                            }
                        };
                        a.setDuration(200);
                        mSearch.startAnimation(a);

                        fadeOutAnimationStarted = true;

                         /*
                            SOSTITUITO COL CODICE SOPRA, NON SO PERCHÈ QUESTO NON FUNZIONA BENE:
                             animMargin = ObjectAnimator.ofInt(marginChanger, "topMargin", DpHelper.dpToPx(8, context), DpHelper.dpToPx(60, context));
                             animMargin.setDuration(1000);
                             animMargin.start();
                         */


                    }
                }
                else if (slideOffset <= 0.3)
                {
                    if (fadeOutAnimationStarted == true)
                    {
                        Animation a = new Animation() {
                            @Override
                            protected void applyTransformation(float interpolatedTime, Transformation t) {
                                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mSearch.getLayoutParams();
                                params.topMargin = dpHelper.dpToPx(SEARCH_BAR_PADDING_DP) + (int) (dpHelper.dpToPx(SEARCH_BAR_HIDED_PADDING_DP) * (1 - interpolatedTime));
                                mSearch.setLayoutParams(params);
                            }
                        };
                        a.setDuration(200);
                        mSearch.startAnimation(a);
                        fadeOutAnimationStarted = false;


                        /*
                           SOSTITUITO COL CODICE SOPRA, NON SO PERCHÈ QUEST NON FUNZIONA BENE:
                            marginChanger = new MarginChanger((RelativeLayout.MarginLayoutParams)mSearch.getLayoutParams() );
                            animMargin = ObjectAnimator.ofInt(marginChanger, "topMargin", DpHelper.dpToPx(60, context), DpHelper.dpToPx(8, context));
                            animMargin.setDuration(1000);
                            animMargin.start();
                        */
                    }
                }

            }


            @Override
            public void onPanelExpanded(View panel) {
                Log.i(TAG, "onPanelExpanded");
                //resizeScrollView(0.0f); <-- da richiamare da altro file
            }

            @Override
            public void onPanelCollapsed(View panel) {
                Log.i(TAG, "onPanelCollapsed");

            }

            @Override
            public void onPanelAnchored(View panel) {
                Log.i(TAG, "onPanelAnchored");
                //resizeScrollView(ANCHOR_POINT);
            }

            @Override
            public void onPanelHidden(View panel) {
                Log.i(TAG, "onPanelHidden");
            }
        });


        mButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Intent to2ndActivity = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(to2ndActivity);
            }
        });

    }



// * * * * * * * * * * * * FUNZIONI PRIVATE (UTILITY / HELPER) * * * * * * * * * * * * * * * * * *

//    private void resizeScrollView(final float slideOffset) {
//        // The scrollViewHeight calculation would need to change based on what views are
//        // in the sliding panel. The calculation below works because the layout has
//        // 2 views. 1) The row with the drag view which is layout.getPanelHeight() high.
//        // 2) The ScrollView.
//
//        if( slideOffset == 0.0f)
//        {
//            ViewGroup.LayoutParams params = museumList.getLayoutParams();
//            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
//            museumList.setLayoutParams(params);
//        }
//        else
//        {
//            final int scrollViewHeight = (int) ((mSlidingUpPanelLayout.getHeight() - mSlidingUpPanelLayout.getPanelHeight()) * (1.0f - slideOffset));
//            final ViewGroup.LayoutParams currentLayoutParams = museumList.getLayoutParams();
//            currentLayoutParams.height = scrollViewHeight;
//            museumList.setLayoutParams(currentLayoutParams);
//        }
//
//            //final int scrollViewHeight = (int) ((mSlidingUpPanelLayout.getHeight() - mSlidingUpPanelLayout.getPanelHeight()) * (1.0f - slideOffset));
//
//    }



// * * * * * * * * * * * * * * *  EVENT MANAGER DELLA ACTIVITY * * * * * * * * * * * * * * *

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        this.context = this;
        dpHelper = new DpHelper(this);

        initActivityAndXML();
        initEventListeners();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_toggle);
        if (mSlidingUpPanelLayout != null) {
            if (mSlidingUpPanelLayout.isPanelHidden()) {
                item.setTitle(R.string.action_show);
            } else {
                item.setTitle(R.string.action_hide);
            }
        }
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_toggle: {
                if (mSlidingUpPanelLayout != null) {
                    if (!mSlidingUpPanelLayout.isPanelHidden()) {
                        mSlidingUpPanelLayout.hidePanel();
                        item.setTitle(R.string.action_show);
                    } else {
                        mSlidingUpPanelLayout.showPanel();
                        item.setTitle(R.string.action_hide);
                    }
                }
                return true;
            }
            case R.id.action_anchor: {
                if (mSlidingUpPanelLayout != null) {
                    if (mSlidingUpPanelLayout.getAnchorPoint() == 1.0f) {
                        mSlidingUpPanelLayout.setAnchorPoint(0.7f);
                        mSlidingUpPanelLayout.expandPanel(0.7f);
                        item.setTitle(R.string.action_anchor_enable);
                    } else {
                        mSlidingUpPanelLayout.setAnchorPoint(1.0f);
                        mSlidingUpPanelLayout.collapsePanel();
                        item.setTitle(R.string.action_anchor_disable);
                    }
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mSlidingUpPanelLayout != null && mSlidingUpPanelLayout.isPanelExpanded() || mSlidingUpPanelLayout.isPanelAnchored()) {
            mSlidingUpPanelLayout.collapsePanel();
        } else {
            super.onBackPressed();
        }
    }


}


// HELPER PER ANIMAZIONE
class SlidingBarExtensionAnimationManager {

    LayoutDimensionChanger c1;
    LayoutDimensionChanger c2;
    SlidingUpPanelLayout v3;
    Context context;

    SlidingBarExtensionAnimationManager(ViewGroup v1, ViewGroup v2, Context context)
    {
        this.c1 = new LayoutDimensionChanger(v1, context);
        this.c2 = new LayoutDimensionChanger(v2, context);
        this.context = context;
    }
    public void setDpHeight(int dpHeight)
    {
        c1.setDpHeight(dpHeight);
        c2.setDpHeight(dpHeight);
    }
}