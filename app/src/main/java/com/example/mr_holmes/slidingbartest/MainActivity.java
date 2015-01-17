package com.example.mr_holmes.slidingbartest;

        import android.animation.ObjectAnimator;
        import android.os.Bundle;
        import android.support.v7.app.ActionBarActivity;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.view.animation.Animation;
        import android.view.animation.AnimationUtils;
        import android.widget.RelativeLayout;
        import android.widget.TextView;
        import android.widget.SearchView;

        import com.getbase.floatingactionbutton.FloatingActionButton;
        import com.sothree.slidinguppanel.SlidingUpPanelLayout;
        import com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelSlideListener;

public class MainActivity extends ActionBarActivity {

    private static final String TAG = "MainActivity";

    private RelativeLayout mSlidingBarBg;
    private SlidingUpPanelLayout mLayout;
    private SearchView mSearch;
    private FloatingActionButton mButton;
    private boolean fadeOutAnimationStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //setSupportActionBar((Toolbar)findViewById(R.id.main_toolbar)); setta la barra del titolo, per adesso non serve
        mSearch = (SearchView) findViewById(R.id.search_view);
        mLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        mLayout.setAnchorPoint(0.6f);
        mButton = (FloatingActionButton) findViewById(R.id.pink_icon);
        mSlidingBarBg = (RelativeLayout) findViewById(R.id.slidingBarBg);

        final Animation fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        final Animation fadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);

        mLayout.setPanelSlideListener(new PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.i(TAG, "onPanelSlide, offset " + slideOffset);

                if(slideOffset>=0.8) {

                    if (fadeOutAnimationStarted != true) {
                        mSearch.startAnimation(fadeOut);
                        mSearch.setVisibility(View.INVISIBLE);
                        mButton.startAnimation(fadeOut);
                        mButton.setVisibility(View.INVISIBLE);
                        fadeOutAnimationStarted = true;
                       // mDragView.setBackgroundColor(Color.RED);


                        BackgroundColorChangerHSV colorChanger = new BackgroundColorChangerHSV(mSlidingBarBg, 255, 152, 0 );
                        ObjectAnimator anim = ObjectAnimator.ofFloat(colorChanger, "saturation", 0, 1);
                        anim.setDuration(1000);
                        anim.start();
                    }
                }
                else if(slideOffset<=0.75) {
                    if(fadeOutAnimationStarted != false) {
                        if (mSearch.getVisibility() != View.VISIBLE) {
                            mSearch.startAnimation(fadeIn);
                            mSearch.setVisibility(View.VISIBLE);
                            mButton.startAnimation(fadeIn);
                            mButton.setVisibility(View.VISIBLE);
                            fadeOutAnimationStarted = false;

                            BackgroundColorChangerHSV colorChanger = new BackgroundColorChangerHSV(mSlidingBarBg, 255, 152, 0 );
                            ObjectAnimator anim = ObjectAnimator.ofFloat(colorChanger, "saturation", 1, 0);
                            anim.setDuration(1000);
                            anim.start();

                        }
                    }
                }

            }



            @Override
            public void onPanelExpanded(View panel) {
                Log.i(TAG, "onPanelExpanded");
                //inserire qui la funzione che cambia colore della barra
            }

            @Override
            public void onPanelCollapsed(View panel) {
                Log.i(TAG, "onPanelCollapsed");

            }
            @Override
            public void onPanelAnchored(View panel) {
                Log.i(TAG, "onPanelAnchored");
            }

            @Override
            public void onPanelHidden(View panel) {
                Log.i(TAG, "onPanelHidden");
            }
        });

        TextView t = (TextView) findViewById(R.id.main);
        t.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mLayout.collapsePanel();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_toggle);
        if (mLayout != null) {
            if (mLayout.isPanelHidden()) {
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
                if (mLayout != null) {
                    if (!mLayout.isPanelHidden()) {
                        mLayout.hidePanel();
                        item.setTitle(R.string.action_show);
                    } else {
                        mLayout.showPanel();
                        item.setTitle(R.string.action_hide);
                    }
                }
                return true;
            }
            case R.id.action_anchor: {
                if (mLayout != null) {
                    if (mLayout.getAnchorPoint() == 1.0f) {
                        mLayout.setAnchorPoint(0.7f);
                        mLayout.expandPanel(0.7f);
                        item.setTitle(R.string.action_anchor_enable);
                    } else {
                        mLayout.setAnchorPoint(1.0f);
                        mLayout.collapsePanel();
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
        if (mLayout != null && mLayout.isPanelExpanded() || mLayout.isPanelAnchored()) {
            mLayout.collapsePanel();
        } else {
            super.onBackPressed();
        }
    }
}