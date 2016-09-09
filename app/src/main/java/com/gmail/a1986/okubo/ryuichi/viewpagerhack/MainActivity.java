package com.gmail.a1986.okubo.ryuichi.viewpagerhack;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter sectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager viewPager;

    private TextView debugView;
    private ScrollView debugContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        debugContainer = (ScrollView) findViewById(R.id.debug_container);
        debugView = (TextView) findViewById(R.id.debug);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(sectionsPagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                showLog("onPageScrolled position=" + position + " positionOffset=" + positionOffset + " positionOffsetPixels=" + positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                showLog("onPageSelected position=" + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                String stateString;
                switch (state) {
                    case 0:
                        stateString = "SCROLL_STATE_IDLE";
                        break;
                    case 1:
                        stateString = "SCROLL_STATE_DRAGGING";
                        break;
                    case 2:
                        stateString = "SCROLL_STATE_SETTLING";
                        break;
                    default:
                        stateString = "ERROR!!";
                }
                showLog("onPageScrollStateChanged state=" + stateString);
            }
        });

        viewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                showLog("transformPage page=" + page + " position=" + position);
            }
        });

        viewPager.addOnAdapterChangeListener(new ViewPager.OnAdapterChangeListener() {
            @Override
            public void onAdapterChanged(@NonNull ViewPager viewPager, @Nullable PagerAdapter oldAdapter, @Nullable PagerAdapter newAdapter) {

            }
        });
    }

    private void showLog(String log) {
        String currentText = debugView.getText().toString();
        debugView.setText(currentText + '\n' + log);
        debugContainer.fullScroll(View.FOCUS_DOWN);
    }

}
