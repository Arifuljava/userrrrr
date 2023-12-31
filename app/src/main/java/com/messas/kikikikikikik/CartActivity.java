package com.messas.kikikikikikik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.messas.starlifeuser_user_user.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class CartActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ///cart Animation
        topAnimation = AnimationUtils.loadAnimation(CartActivity.this, R.anim.splash_top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(CartActivity.this, R.anim.splash_bottom_animation);
        startAnimation = AnimationUtils.loadAnimation(CartActivity.this, R.anim.splash_start_animation);
        endAnimation = AnimationUtils.loadAnimation(CartActivity.this, R.anim.splash_end_animation);
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
        mRecyclerView.setAnimation(topAnimation);

        populateRecyclerView();



    }
    double alldelivery=0;
    double allmain=0;
    int coount=0;

    private void populateRecyclerView() {

        Log.d(TAG,"Populate Recycler view called");


        /*
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

                    for (QueryDocumentSnapshot document : task.getResult()) {


                        Log.d(TAG, document.getId() + " => " + document.getData());
                    }
                } else {
                    Log.d(TAG, "Error getting documents.", task.getException());
                }

            }
        });
         */
        Query query = firebaseFirestore.collection("Cart").document(user.get(UserSession.KEY_EMAIL))
                .collection(user.get(UserSession.KEY_NAME) + " Cart");

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    cartcollect.clear();
                    totalcost = 0;
                    totalproducts = 0;
                    allmain = 0;
                    coount = 0;

                    if (task.getResult().size() == 0) {
                        tv_no_item.setVisibility(View.GONE);
                        activitycartlist.setVisibility(View.GONE);
                        emptycart.setVisibility(View.VISIBLE);
                        return;
                    }

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        SingleProductModel model = document.toObject(SingleProductModel.class);

                        // Check for duplicates before adding to the list
                        boolean isDuplicate = false;
                        for (SingleProductModel existingModel : cartcollect) {
                            if (existingModel.getPrimage().equals(model.getPrname())) {
                                isDuplicate = true;
                                break;
                            }
                        }

                        if (!isDuplicate) {
                            cartcollect.add(model);
                            Toast.makeText(CartActivity.this, ""+cartcollect, Toast.LENGTH_SHORT).show();
                            firebaseFirestore=FirebaseFirestore.getInstance();
                            int priddd=model.getPrid();
                            String ddd=""+priddd;

                            /////
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
                                                    totalcost += (model.getNo_of_items()*Float.parseFloat(model.getPrprice()));
                                                    if (coount==1) {
                                                        totalcost=(Float.parseFloat(String.valueOf(alldelivery)))+totalcost;
                                                    }
                                                    totalproducts += model.getNo_of_items();
                                                    allmain += (model.getNo_of_items()*Float.parseFloat(model.getPrprice()));
                                                    //cartcollect.add(model);

                                                    no_of_items_tv.setText("No. of Items- "+totalproducts);
                                                    total_amount_tv.setText("Delivery Charge : "+alldelivery+"\nProducts Price : "+allmain+"\nTotal Amount- ৳"+totalcost);

                                                    checkout.setVisibility(View.VISIBLE);
                                                    details_layout.setVisibility(View.VISIBLE);




                                                }
                                            }
                                        }
                                    });
                            /////

                        }
                    }

                } else {
                    Log.d(TAG, "Error getting documents.", task.getException());
                }
            }
        });


        Log.d(TAG,"Creating Response");

        final FirestoreRecyclerOptions<SingleProductModel> response = new FirestoreRecyclerOptions.Builder<SingleProductModel>()
                .setQuery(query, SingleProductModel.class)
                .build();

        Log.d(TAG,"Response Created");
        Log.d(TAG,"Getting Data");

        //Say Hello to our new FirebaseUI android Element, i.e., FirebaseRecyclerAdapter
        adapter =new FirestoreRecyclerAdapter<SingleProductModel, CartActivity.MovieViewHolder>(response) {

            @Override
            protected void onBindViewHolder(@NonNull CartActivity.MovieViewHolder viewHolder, final int position, @NonNull SingleProductModel model) {
                Log.d(TAG,"onBindViewHolder called for: "+position);


                if(tv_no_item.getVisibility()== View.VISIBLE){
                    tv_no_item.setVisibility(View.GONE);
                }

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
                                        viewHolder.deliverycharge.setText("Delivery Charge is : "+task.getResult().getString("charge")+" Taka");
                                        alldelivery=Double.parseDouble(task.getResult().getString("charge"));
                                        viewHolder.cardname.setText(model.getPrname());
                                        viewHolder.cardprice.setText("৳ "+model.getPrprice());
                                        viewHolder.cardcount.setText("Quantity : "+model.getNo_of_items());
                                        viewHolder.totalCardAmt.setText("৳ "+model.getPrprice()+" x "+model.getNo_of_items()+" = ৳ "+(Float.valueOf(model.getPrprice())*model.getNo_of_items()));
                                        Picasso.get().load(model.getPrimage()).into(viewHolder.cardimage);



                                        viewHolder.carddelete.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                getSnapshots().getSnapshot(position).getReference().delete();
                                                session.decreaseCartValue();
                                                startActivity(new Intent(CartActivity.this,CartActivity.class));
                                                finish();
                                            }
                                        });

                                    }
                                }
                            }
                        });



            }

            @NonNull
            @Override
            public CartActivity.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup group, int viewType) {
                Log.d(TAG,"Inflating Layout");
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.cart_item_layout, group, false);

                return new MovieViewHolder(view);

            }


        };

        Log.d("xlr8_cart", String.valueOf(cartcollect));


        adapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(adapter);





    }

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
        startActivity(new Intent(CartActivity.this,ProfileActivity.class));
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
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("xlr8","Adapter Not  Listening");
        adapter.stopListening();
    }

    //viewHolder for our Firebase UI
    public static class MovieViewHolder extends RecyclerView.ViewHolder{

        TextView cardname;
        ImageView cardimage;
        TextView cardprice;
        TextView cardcount;
        ImageView carddelete;
        TextView totalCardAmt;
        LinearLayout linearlayouttt;
        TextView deliverycharge;

        View mView;
        public MovieViewHolder(View v) {
            super(v);
            mView = v;
            cardname = v.findViewById(R.id.cart_prtitle);
            cardimage = v.findViewById(R.id.image_cartlist);
            cardprice = v.findViewById(R.id.cart_prprice);
            cardcount = v.findViewById(R.id.cart_prcount);
            carddelete = v.findViewById(R.id.deletecard);
            totalCardAmt = v.findViewById(R.id.total_card_amount);
            linearlayouttt=v.findViewById(R.id.linearlayouttt);
            deliverycharge=v.findViewById(R.id.deliverycharge);

        }
    }


    public void checkout(View view) {

        Intent intent = new Intent(CartActivity.this, CheckoutActivity.class);
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
