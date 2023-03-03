package com.app.graphicstechniques.simple.slider.secondOne;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.app.graphicstechniques.R;

public class ImageSliderAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private Integer[] images;
    private ImageView[] points;

    public ImageSliderAdapter(Context context, Integer[] images) {
        this.context = context;
        this.images = images;
        points = new ImageView[images.length];
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_item, container, false);
        ImageView imageView = view.findViewById(R.id.image_view);
        imageView.setImageResource(images[position]);

        // Create point indicator for this image
        points[position] = new ImageView(context);
        points[position].setImageResource(R.drawable.default_point_indicator);

        // Set layout parameters for point indicator
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8, 0, 8, 0);
        ((LinearLayout) view.findViewById(R.id.pointsLayout)).addView(points[position], layoutParams);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public void updatePointsIndicator(int position) {
        for (int i = 0; i < points.length; i++) {
            if (i == position) {
                points[i].setImageResource(R.drawable.selected_point_indicator);
            } else {
                points[i].setImageResource(R.drawable.default_point_indicator);
            }
        }
    }
}

