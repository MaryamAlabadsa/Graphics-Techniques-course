package com.app.graphicstechniques.simple.slider;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.a.myapplication.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 mViewPager;
    private Handler swipeHandler = new Handler();
    ArrayList<String> list = new ArrayList();
    private Runnable swipeRunnable = new Runnable() {
        @Override
        public void run() {
            Toast.makeText(MainActivity.this, mViewPager.getCurrentItem() + "", Toast.LENGTH_SHORT).show();
            if (mViewPager.getCurrentItem() == list.size() - 1) {
                mViewPager.setCurrentItem(0);
            } else
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
            swipeHandler.postDelayed(this, 3000); // set the delay between swipes
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQaeU6EtR-fQUAlni6w0pgriAjQ_RKYbyOfs01JanaDUDR5t_AKqwCWZmL2Up9bFLAn11U&usqp=CAU");
        list.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQsy4T5sl6Hp3SadCxd5cpq8wnhMZsPNCJ09eAr51V3dN1XEkWwsnY6sLF38o3m2swJIEQ&usqp=CAU");
        list.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT9LbcPywfMxOmT5MiL9I600gdcI1T4PM_Yal1_jTXxJfNQEr4GLrdvsIgZoyyh093-Y5w&usqp=CAU");
        list.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTgrHXrHOUJSCnXhkSnV5jFG11p3pRCvDlXDXhrszqYAWQ8VUzpJSW-rQZI-kFYHxFg_XM&usqp=CAU");
        mViewPager = findViewById(R.id.view_pager);
        SliderAdapter adapter = new SliderAdapter(this, list);
        mViewPager.setAdapter(adapter);
        mViewPager.setPageTransformer(new ZoomOutPageTransformer());
        swipeHandler.postDelayed(swipeRunnable, 3000); // start the auto-swipe feature
        mViewPager.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(@NonNull View view) {

            }

            @Override
            public void onViewDetachedFromWindow(@NonNull View view) {

            }
        });
//        mViewPager.addOnPageChangeListener(new ViewPager2.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                // not needed
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                if (position == adapter.getItemCount() - 1) { // last page reached
//                    viewPager2.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            viewPager2.setCurrentItem(0, false); // go to the first page without animation
//                        }
//                    });
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//                // not needed
//            }
//        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        swipeHandler.removeCallbacks(swipeRunnable); // stop the auto-swipe feature

    }
}