package com.messas.kikikikikikik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.messas.starlifeuser_user_user.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

public class Bags111 extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mLayoutManager;
    private LottieAnimationView tv_no_item;
    private String usermobile;
    private UserSession session;
    public static ArrayList<Long> wishlist;

    private final String TAG = this.getClass().getSimpleName() + "_xlr8";

    //Getting reference to Firebase Database
    private FirebaseFirestore firebaseFirestore;
    private FirestoreRecyclerAdapter adapter;
    private Animation topAnimation, bottomAnimation, startAnimation, endAnimation;
    private SharedPreferences onBoardingPreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bags111);
        FirebaseApp.initializeApp(this);
        topAnimation = AnimationUtils.loadAnimation(Bags111.this, R.anim.splash_top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(Bags111.this, R.anim.splash_bottom_animation);
        startAnimation = AnimationUtils.loadAnimation(Bags111.this, R.anim.splash_start_animation);
        endAnimation = AnimationUtils.loadAnimation(Bags111.this, R.anim.splash_end_animation);
        session = new UserSession(getApplicationContext());

        firebaseFirestore = FirebaseFirestore.getInstance();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //check Internet Connection
        // new CheckInternetConnection(this).checkConnection();

        //Initializing our Recyclerview
        mRecyclerView = findViewById(R.id.my_recycler_view);
        tv_no_item = findViewById(R.id.tv_no_cards);
        tv_no_item.setVisibility(View.VISIBLE);
        mRecyclerView.setAnimation(topAnimation);

        //  if (mRecyclerView != null) {
        //to enable optimization of recyclerview
        //    mRecyclerView.setHasFixedSize(true);
        // }

        //using staggered grid pattern in recyclerview
        mLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Query query = firebaseFirestore.collection("Products").document("101").collection("Kids");

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                    }
                } else {
                    Log.d(TAG, "Error getting documents.", task.getException());
                }

            }
        });


        Log.d(TAG, "Creating Response");

        final FirestoreRecyclerOptions<GenericProductModel> response = new FirestoreRecyclerOptions.Builder<GenericProductModel>()
                .setQuery(query, GenericProductModel.class)
                .build();

        Log.d(TAG, "Response Created");
        Log.d(TAG, "Getting Data");
        //Say Hello to our new FirebaseUI android Element, i.e., FirebaseRecyclerAdapter
        adapter = new FirestoreRecyclerAdapter<GenericProductModel, MovieViewHolder>(response) {
            @Override
            protected void onBindViewHolder(@NonNull MovieViewHolder viewHolder, final int position, @NonNull GenericProductModel model) {
                Log.d(TAG, "Populating Data");

                viewHolder.cardname.setText(model.getCardname());
                viewHolder.cardprice.setText("৳ " + model.getCardprice());
                Picasso.get().load(model.getCardimage()).into(viewHolder.cardimage);
                viewHolder.mView.setAnimation(topAnimation);
                Random rand = new Random();
                int value = rand.nextInt(50);
                firebaseFirestore=FirebaseFirestore.getInstance();
                firebaseFirestore.collection("Delivercharge")
                        .document("7")
                        .collection("Charge")
                        .document(""+model.getCardid())
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    if (task.getResult().exists())
                                    {
                                        viewHolder.soldproducts.setText(""+task.getResult().getString("deliver"));
                                    }
                                    else {
                                        viewHolder.soldproducts.setText(""+value);
                                    }
                                }

                            }
                        });


                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Bags111.this, IndividualProductActivity2.class);
                        intent.putExtra("product", getItem(position));
                        intent.putExtra("uuod", "" + getItem(position).getCardid());
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup group, int viewType) {
                Log.d(TAG, "Inflating Layout");
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.cards_cardview_layout, group, false);

                return new MovieViewHolder(view);
            }


        };

        adapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(adapter);

        wishlist = session.getWishlist(tv_no_item);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, String.valueOf(response.getSnapshots()));

            }
        }, 10000);


    }

    public void Notifications(View view) {
        //startActivity(new Intent(CardsActivity.this,NotificationActivity.class));
        //finish();
    }

    public void viewCart(View view) {
        startActivity(new Intent(Bags111.this, CartActivity.class));
        finish();
    }

    //viewHolder for our Firebase UI
    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        TextView cardname;
        ImageView cardimage;
        TextView cardprice;
        View mView;
        TextView soldproducts;

        public MovieViewHolder(View v) {
            super(v);
            mView = v;
            cardname = v.findViewById(R.id.cardcategory);
            cardimage = v.findViewById(R.id.cardimage);
            cardprice = v.findViewById(R.id.cardprice);
            soldproducts=v.findViewById(R.id.soldproducts);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "Adapter Listening");
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "Adapter Not  Listening");
        adapter.stopListening();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        //check Internet Connection
        //new CheckInternetConnection(this).checkConnection();
    }
}