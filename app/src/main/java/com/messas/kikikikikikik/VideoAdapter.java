package com.messas.kikikikikikik;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.messas.starlifeuser_user_user.R;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.myview> {
    public List<filemodel> data;
    FirebaseFirestore firebaseFirestore;
    SimpleExoPlayer simpleExoPlayer;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestoreg;
    private Animation topAnimation, bottomAnimation, startAnimation, endAnimation;
    public VideoAdapter(List<filemodel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public VideoAdapter.myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sss, parent, false);
        return new VideoAdapter.myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.myview holder, final int position) {
        firebaseAuth= FirebaseAuth.getInstance();
        firebaseFirestore= FirebaseFirestore.getInstance();

       try {
           topAnimation = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.splash_top_animation);
           bottomAnimation = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.splash_bottom_animation);
           startAnimation = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.splash_start_animation);
           endAnimation = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.splash_end_animation);
           holder.card.setAnimation(topAnimation);


           BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
           TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
           simpleExoPlayer =(SimpleExoPlayer) ExoPlayerFactory.newSimpleInstance(holder.itemView.getContext(),trackSelector);
           Uri videoURI = Uri.parse(data.get(position).getVurl());

           DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_video");
           ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
           MediaSource mediaSource = new ExtractorMediaSource(videoURI, dataSourceFactory, extractorsFactory, null, null);

           holder.simpleExoPlayerView.setPlayer(simpleExoPlayer);
           simpleExoPlayer.prepare(mediaSource);
           simpleExoPlayer.setPlayWhenReady(false);
           holder.vtitleview.setText(data.get(position).getTitle());
       }catch (Exception e) {
           BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
           TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
           simpleExoPlayer =(SimpleExoPlayer) ExoPlayerFactory.newSimpleInstance(holder.itemView.getContext(),trackSelector);
           Uri videoURI = Uri.parse(data.get(position).getVurl());

           DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_video");
           ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
           MediaSource mediaSource = new ExtractorMediaSource(videoURI, dataSourceFactory, extractorsFactory, null, null);

           holder.simpleExoPlayerView.setPlayer(simpleExoPlayer);
           simpleExoPlayer.prepare(mediaSource);
           simpleExoPlayer.setPlayWhenReady(false);
           holder.vtitleview.setText(data.get(position).getTitle());
       }


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myview extends RecyclerView.ViewHolder {
        SimpleExoPlayerView simpleExoPlayerView;

        TextView vtitleview;
        ImageSlider slider;
        CardView card;

        public myview(@NonNull View itemView) {
            super(itemView);
            simpleExoPlayerView=itemView.findViewById(R.id.exoplayerview);
            vtitleview=itemView.findViewById(R.id.vtitle);
            slider=itemView.findViewById(R.id.slider);
            card=itemView.findViewById(R.id.card);

        }
    }
}