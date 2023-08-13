package com.messas.kikikikikikik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.messas.starlifeuser_user_user.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;

public class Cart2Activity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName()+"_xlr8";
    //to get user session data
    private UserSession session;
    private HashMap<String,String> user;
    private String name,email,photo,mobile;
    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mLayoutManager;

    private LottieAnimationView tv_no_item;
    private LinearLayout activitycartlist;
    private LottieAnimationView emptycart;

    private ArrayList<SingleProductModel> cartcollect;
    private float totalcost=0;
    private int totalproducts=0;

    //Getting reference to Firebase Database
    private FirebaseFirestore firebaseFirestore;
    private TextView no_of_items_tv;
    private TextView total_amount_tv;
    private FirestoreRecyclerAdapter adapter;
    private TextView checkout;
    private LinearLayout details_layout;
    FirebaseAuth firebaseAuth;

    //Other Variables
    private Animation topAnimation, bottomAnimation, startAnimation, endAnimation;
    private SharedPreferences onBoardingPreference;
    CartAdapter getDataAdapter1;
    List<SingleProductModel> getList;
    DocumentReference documentReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart2);
        topAnimation = AnimationUtils.loadAnimation(Cart2Activity.this, R.anim.splash_top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(Cart2Activity.this, R.anim.splash_bottom_animation);
        startAnimation = AnimationUtils.loadAnimation(Cart2Activity.this, R.anim.splash_start_animation);
        endAnimation = AnimationUtils.loadAnimation(Cart2Activity.this, R.anim.splash_end_animation);
        firebaseAuth=FirebaseAuth.getInstance();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Cart");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FirebaseApp.initializeApp(this);
        firebaseFirestore = FirebaseFirestore.getInstance();

        no_of_items_tv = findViewById(R.id.total_items_tv);
        total_amount_tv = findViewById(R.id.total_amount_tv);
        details_layout = findViewById(R.id.details_layout);

        checkout = findViewById(R.id.text_action_bottom2);


        //check Internet Connection
        //   new CheckInternetConnection(this).checkConnection();

        //retrieve session values and display on listviews
        getValues();

        //SharedPreference for Cart Value
        session = new UserSession(getApplicationContext());

        //validating session
        session.isLoggedIn();

        mRecyclerView = findViewById(R.id.recyclerview);
        tv_no_item = findViewById(R.id.tv_no_cards);
        activitycartlist = findViewById(R.id.activity_cart_list);
        emptycart = findViewById(R.id.empty_cart);
        cartcollect = new ArrayList<>();

        if (mRecyclerView != null) {
            //to enable optimization of recyclerview
            mRecyclerView.setHasFixedSize(true);
        }

        //using staggered grid pattern in recyclerview
        mLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();


        getList = new ArrayList<>();
        getDataAdapter1 = new CartAdapter(getList);
        firebaseFirestore = FirebaseFirestore.getInstance();
        documentReference =   firebaseFirestore.collection("Cart").document(user.get(UserSession.KEY_EMAIL))
                .collection(user.get(UserSession.KEY_NAME)+" Cart")
                .document();

        if (mRecyclerView != null) {
            //to enable optimization of recyclerview
            mRecyclerView.setHasFixedSize(true);
        }

        //using staggered grid pattern in recyclerview
        mLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(getDataAdapter1);
        reciveData();
        //no data
        Query query = firebaseFirestore.collection("Cart").document(user.get(UserSession.KEY_EMAIL))
                .collection(user.get(UserSession.KEY_NAME)+" Cart");

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if(task.isSuccessful()){
                    //second loginc
                    cartcollect.clear();  // Clear previous data
                    totalcost = 0;
                    totalproducts = 0;
                    allmain = 0;
                    coount = 0;

                    for (QueryDocumentSnapshot document : task.getResult()) {

                    }
                    Log.d(TAG,"Task Success");
                    if(task.getResult().size()==0){

                        tv_no_item.setVisibility(View.GONE);
                        activitycartlist.setVisibility(View.GONE);
                        emptycart.setVisibility(View.VISIBLE);
                        return;
                    }
                    else
                    {
                        tv_no_item.setVisibility(View.GONE);
                        emptycart.setVisibility(View.GONE);
                        checkout.setVisibility(View.VISIBLE);
                        details_layout.setVisibility(View.VISIBLE);

                    }
                   // Toast.makeText(Cart2Activity.this, ""+task.getResult().size(), Toast.LENGTH_SHORT).show();

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        long  no_of_items1=document.getLong("no_of_items");
                        String dff = ""+no_of_items1;
                        int no_of_items = Integer.parseInt(dff);
                        String prprice=document.getString("prprice");

                        firebaseFirestore=FirebaseFirestore.getInstance();
                       
                        firebaseFirestore.collection("AllDelivery")
                                .document("abc@gmail.com")
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                        if (task.isSuccessful()) {
                                            if (task.getResult().exists()) {
                                                coount++;
                                                String indi=task.getResult().getString("charge");

                                                alldelivery=Double.parseDouble(task.getResult().getString("charge"));



                                                totalcost += (no_of_items*Float.parseFloat(prprice));
                                                if (coount==1) {
                                                    totalcost=(Float.parseFloat(String.valueOf(alldelivery)))+totalcost;
                                                }
                                                totalproducts += no_of_items;
                                                allmain += (no_of_items*Float.parseFloat(prprice));
                                                //cartcollect.add(model);

                                                no_of_items_tv.setText("No. of Items- "+totalproducts);
                                                total_amount_tv.setText("Delivery Charge : "+alldelivery+"\nProducts Price : "+allmain+"\nTotal Amount- ৳"+totalcost);

                                                checkout.setVisibility(View.VISIBLE);
                                                details_layout.setVisibility(View.VISIBLE);




                                            }
                                        }
                                    }
                                });

                        Log.d(TAG, document.getId() + " => " + document.getData());
                    }
                } else {
                    Log.d(TAG, "Error getting documents.", task.getException());
                }

            }
        });
    }
    private void reciveData() {

        firebaseFirestore.collection("Cart").document(user.get(UserSession.KEY_EMAIL))
                .collection(user.get(UserSession.KEY_NAME)+" Cart").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                for (DocumentChange ds : queryDocumentSnapshots.getDocumentChanges()) {
                    if (ds.getType() == DocumentChange.Type.ADDED) {

                 /*String first;
                 first = ds.getDocument().getString("name");
                 Toast.makeText(MainActivity2.this, "" + first, Toast.LENGTH_SHORT).show();*/
                        SingleProductModel get = ds.getDocument().toObject(SingleProductModel.class);
                        getList.add(get);
                        getDataAdapter1.notifyDataSetChanged();
                    }

                }
            }
        });

    }

    double alldelivery=0;
    double allmain=0;
    int coount=0;
    private void getValues() {

        //create new session object by passing application context
        session = new UserSession(getApplicationContext());

        //validating session
        session.isLoggedIn();

        //get User details if logged in
        user = session.getUserDetails();

        name = user.get(UserSession.KEY_NAME);
        email = user.get(UserSession.KEY_EMAIL);
        mobile = user.get(UserSession.KEY_MOBiLE);
        photo = user.get(UserSession.KEY_PHOTO);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),HomeACTIVITY.class));
    }

    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(getApplicationContext(),HomeACTIVITY.class));
        return true;
    }

    public void viewProfile(View view) {
        startActivity(new Intent(Cart2Activity.this,ProfileActivity.class));
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //check Internet Connection
        //  new CheckInternetConnection(this).checkConnection();

    }

    public void Notifications(View view) {

        //startActivity(new Intent(Cart.this,NotificationActivity.class));
        //finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("xlr8","Adapter Listening");
        //adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("xlr8","Adapter Not  Listening");
       // adapter.stopListening();
    }
    public void checkout(View view) {

        Intent intent = new Intent(Cart2Activity.this, CheckoutActivity.class);
        intent.putExtra("totalprice", Float.toString(totalcost));
        intent.putExtra("totalproducts", Integer.toString(totalproducts));
        intent.putExtra("cartproducts", cartcollect);
        intent.putExtra("alldelivery",""+alldelivery);
        intent.putExtra("allmain",""+allmain);

        // Toast.makeText(this, ""+cartcollect, Toast.LENGTH_SHORT).show();
        startActivity(intent);
        finish();


    }
}