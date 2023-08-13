package com.messas.kikikikikikik;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.messas.starlifeuser_user_user.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class CartFragment extends Fragment {

  View view;
  private TextView namebutton;
  private CircleImageView primage;
  private TextView updateDetails;
  private LinearLayout wishlistView;
  private ImageSlider imageSlider;

  //to get user session data
  private UserSession session;
  private TextView tvemail, tvphone;
  private HashMap<String, String> user;
  private String name, email, photo, mobile;
  FirebaseFirestore firebaseFirestore;
  FirebaseAuth firebaseAuth;



  //
  LottieAnimationView empty_cart;
  DocumentReference documentReference;
  RecyclerView recyclerView;


  FirebaseUser firebaseUser;
  KProgressHUD progressHUD;
  String cus_name;
  public CartFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    view = inflater.inflate(R.layout.fragment_cart, container, false);
    firebaseFirestore = FirebaseFirestore.getInstance();

    initialize();
    firebaseFirestore = FirebaseFirestore.getInstance();
    firebaseAuth = FirebaseAuth.getInstance();
    TextView allcategores=view.findViewById(R.id.allcategores);
    allcategores.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent=new Intent(getContext(),CartActivity.class);
        startActivity(intent);
      }
    });
    TextView updatepin=view.findViewById(R.id.updatepin);
    updatepin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent=new Intent(getContext(),UpdatePinActivity.class);
        startActivity(intent);
      }
    });
    TextView tracking=view.findViewById(R.id.tracking);
    tracking.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        firebaseFirestore.collection("Tracking")
                .document("abc@gmail.com")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                  @Override
                  public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                      if (task.getResult().exists()) {
                        Intent intent=new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(task.getResult().getString("link")));
                        startActivity(intent);
                      }
                    }
                  }
                });
      }
    });

    //check Internet Connection


    //retrieve session values and display on listviews
    getValues();

    //ImageSLider
    inflateImageSlider();
    return view;
  }
  private void getValues() {

    //create new session object by passing application context
    session = new UserSession(view.getContext());

    //validating session
    session.isLoggedIn();

    //get User details if logged in
    user = session.getUserDetails();

    name = user.get(UserSession.KEY_NAME);
    email = user.get(UserSession.KEY_EMAIL);
    mobile = user.get(UserSession.KEY_MOBiLE);
    photo = user.get(UserSession.KEY_PHOTO);
    String upload="https://firebasestorage.googleapis.com/v0/b/cash-money-express-ltd.appspot.com/o/profile_images%2Fo8Dnqf5LFodKSwocGQ4nKB7ZEkW2.jpg?alt=media&token=c22700e2-67ca-4497-8bf1-204ac83b6749";
    if (photo.equals(upload)) {
      Picasso.get().load(R.drawable.profile_image).into(primage);
    }
    else {
      Picasso.get().load(photo).into(primage);
    }
    //setting values
    tvemail.setText(email);
    tvphone.setText(mobile);

    namebutton.setText(name);
  }
  private void inflateImageSlider() {
    ArrayList<SlideModel> slideModels = new ArrayList<>();
      /*
        slideModels.add(new SlideModel("https://m.media-amazon.com/images/G/31/img19/Wireless/Apple/iPhone11/RiverImages/11Pro/IN_iPhone11Pro_DESKTOP_01._CB437064827_.jpg"));
        slideModels.add(new SlideModel("https://piunikaweb.com/wp-content/uploads/2019/08/oneplus_7_pro_5g_experience_the_power_of_5g_banner-750x354.jpg"));
        slideModels.add(new SlideModel("https://lh3.googleusercontent.com/RSyeouwiFX4XVq6iw3H94al0VcXD693tBy2MxhBKCxAHCIfIpdt7wDV47_j2HanPSnTli7JgZ0fYHxESjz0uvVgeCBT3=w1000"));
        slideModels.add(new SlideModel("https://cdn.metrobrands.com/mochi/media/images/content/Homepage/HOTTMARZZ-BANNER-MOCHI.webp"));
        slideModels.add(new SlideModel("https://i.pinimg.com/originals/b2/78/7c/b2787cea792bff7d2c33e26ada6436bb.jpg"));
        slideModels.add(new SlideModel("https://cdnb.artstation.com/p/assets/images/images/016/802/459/large/shuja-shuaib-banner.jpg?1553535424"));
       */
    slideModels.add(new SlideModel(R.drawable.app_logo, ScaleTypes.FIT));
    //slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/cash-money-express-ltd.appspot.com/o/salary.png?alt=media&token=627d0a8e-2bf4-4e84-84e9-5574d9b8fbaa"));

    imageSlider.setImageList(slideModels, ScaleTypes.FIT);
  }
  private void initialize() {

    imageSlider = view.findViewById(R.id.slider);

    primage = view.findViewById(R.id.profilepic);
    tvemail =view. findViewById(R.id.emailview);
    tvphone = view.findViewById(R.id.mobileview);
    namebutton =view. findViewById(R.id.name_button);




  }
  //Other Variables
  private Animation topAnimation, bottomAnimation, startAnimation, endAnimation;
  private SharedPreferences onBoardingPreference;
}