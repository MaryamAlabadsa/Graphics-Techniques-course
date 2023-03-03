package com.app.graphicstechniques.simple.slider.firstone;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;


import com.amrdeveloper.lottiedialog.LottieDialog;
import com.app.graphicstechniques.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Context context;
    private ViewPager2 mViewPager;
    private Handler swipeHandler = new Handler();
    ArrayList<String> list = new ArrayList();
    private Runnable swipeRunnable = new Runnable() {
        @Override
        public void run() {
            checkNetwork();
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
        context = MainActivity.this;
        mViewPager = findViewById(R.id.view_pager);
        firstSlider();
    }

    public void firstSlider() {
        list.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQaeU6EtR-fQUAlni6w0pgriAjQ_RKYbyOfs01JanaDUDR5t_AKqwCWZmL2Up9bFLAn11U&usqp=CAU");
        list.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQsy4T5sl6Hp3SadCxd5cpq8wnhMZsPNCJ09eAr51V3dN1XEkWwsnY6sLF38o3m2swJIEQ&usqp=CAU");
        list.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT9LbcPywfMxOmT5MiL9I600gdcI1T4PM_Yal1_jTXxJfNQEr4GLrdvsIgZoyyh093-Y5w&usqp=CAU");
        list.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTgrHXrHOUJSCnXhkSnV5jFG11p3pRCvDlXDXhrszqYAWQ8VUzpJSW-rQZI-kFYHxFg_XM&usqp=CAU");
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
    }

    public void checkNetwork() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
        } else {
            launchInternetLottieDialog();
        }
    }

    public void launchInternetLottieDialog() {
        Button button = new Button(context);
        button.setText("Retry");
        button.setTextColor(Color.WHITE);
        button.setAllCaps(false);
        int purpleColor = ContextCompat.getColor(context, R.color.green);
        button.setBackgroundTintList(ColorStateList.valueOf(purpleColor));

        LottieDialog dialog = new LottieDialog(context)
                .setAnimation(R.raw.no_internet)
                .setAutoPlayAnimation(true)
                .setAnimationRepeatCount(LottieDialog.INFINITE)
                .setMessage("You have no internet connection")
                .addActionButton(button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                checkNetwork();
            }
        });

        dialog.show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        swipeHandler.removeCallbacks(swipeRunnable); // stop the auto-swipe feature

    }
}