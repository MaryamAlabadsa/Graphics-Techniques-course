package com.app.graphicstechniques.simple.slider.secondOne;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.app.graphicstechniques.R;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ImageSliderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize ViewPager and ImageSliderAdapter
        viewPager = findViewById(R.id.viewPager);
        Integer[] images = {R.drawable.img1, R.drawable.img2, R.drawable.img1};
        adapter = new ImageSliderAdapter(this, images);

        // Set adapter for ViewPager
        viewPager.setAdapter(adapter);

        // Add page change listener to ViewPager to update point indicators
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                adapter.updatePointsIndicator(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }
}