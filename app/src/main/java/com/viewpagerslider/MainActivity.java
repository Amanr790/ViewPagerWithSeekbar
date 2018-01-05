package com.viewpagerslider;

import android.animation.Animator;


import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.AdapterView;

import android.widget.TextView;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager vpEmotions;
    private EmotionsAdapter mEmotionsAdapter;
    private ComboSeekBar sbSeekbar;
    private TextView tvPagerIndicator;
    private Animator pagerAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    /**
     * Method to Initialize Views
     */
    private void initViews() {
        vpEmotions = findViewById(R.id.vp_emotions);
        sbSeekbar = findViewById(R.id.seekbar);
        tvPagerIndicator = findViewById(R.id.tv_text);
        final List<String> points = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            points.add("Happy");
        sbSeekbar.setAdapter(points);
        changePagerScroller();
        mEmotionsAdapter = new EmotionsAdapter(getSupportFragmentManager());
        vpEmotions.setAdapter(mEmotionsAdapter);
        vpEmotions.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffset > 0 && positionOffset < 1) {
                    sbSeekbar.setSelection((int) ((position + positionOffset) * 10));
                } else
                    sbSeekbar.setSelection((position) * 10);
            }


            @Override
            public void onPageSelected(int position) {
                tvPagerIndicator.setText(String.valueOf(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vpEmotions.setOffscreenPageLimit(0);

        sbSeekbar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Click", "" + position);
                if (position % 10 == 0) {
                    vpEmotions.setCurrentItem(position / 10, true);
                    //animatePagerTransition(true);
                }

            }
        });
    }

    private void changePagerScroller() {
        try {
            Field mScroller = null;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            ViewPagerScroller scroller = new ViewPagerScroller(vpEmotions.getContext());
            mScroller.set(vpEmotions, scroller);
        } catch (Exception e) {
            Log.e("Error", "error of change scroller ", e);
        }
    }


}
