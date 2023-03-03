package com.app.graphicstechniques.simple.slider.firstone;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.graphicstechniques.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.core.ImageTranscoderType;
import com.facebook.imagepipeline.core.MemoryChunkType;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {

    private List<String> mImages;
    private LayoutInflater mInflater;
    Context context;

    public SliderAdapter(Context context, List<String> images) {
        this.mInflater = LayoutInflater.from(context);
        this.mImages = images;
        this.context = context;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.slider_item, parent, false);
        return new SliderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        String imageRes = mImages.get(position);
        // Create a ProgressbarDrawable instance and set it as the progress bar image
        ProgressBarDrawable progressBarDrawable = new ProgressBarDrawable();
        progressBarDrawable.setBarWidth(10); // Set the width of the progress bar
//        progressBarDrawable.setBarHeight(20); // Set the height of the progress bar
        progressBarDrawable.setPadding(20); // Set the padding of the progress bar
        progressBarDrawable.setRadius(50); // Set the radius of the corners of the progress bar
        progressBarDrawable.setColor(Color.BLUE); // Set the color of the progress bar
        progressBarDrawable.setBackgroundColor(Color.LTGRAY); // Set the background color of the progress bar
//        progressBarDrawable.setProgressBarWidth(40); // Set the width of the overall progress bar
        progressBarDrawable.setColor(Color.BLUE); // Customize the color of the progress bar

        holder.imageView.getHierarchy().setProgressBarImage(progressBarDrawable);
        // Set the placeholder image
        @SuppressLint("UseCompatLoadingForDrawables")
        Drawable placeholderDrawable = context.getResources().getDrawable(R.drawable.img1);
        holder.imageView.getHierarchy().setPlaceholderImage(placeholderDrawable);

        // Set the scaling type
        holder.imageView.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER);

        Fresco.initialize(
                context,
                ImagePipelineConfig.newBuilder(context)
                        .setMemoryChunkType(MemoryChunkType.BUFFER_MEMORY)
                        .setImageTranscoderType(ImageTranscoderType.JAVA_TRANSCODER)
                        .experiment().setNativeCodeDisabled(true)
                        .build());
        Uri uri = Uri.parse(imageRes);
        holder.imageView.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    static class SliderViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView imageView;

        SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }
}

