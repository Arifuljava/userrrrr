package com.messas.kikikikikikik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.flatdialoglibrary.dialog.FlatDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.messas.starlifeuser_user_user.R;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class IndividualProductActivity4 extends AppCompatActivity {

    @BindView(R.id.productimage)
    ImageView productimage;
    @BindView(R.id.productname)
    TextView productname;
    @BindView(R.id.productprice)
    TextView productprice;
    @BindView(R.id.add_to_cart)
    TextView addToCart;
    @BindView(R.id.buy_now)
    TextView buyNow;
    @BindView(R.id.productdesc)
    TextView productdesc;
    @BindView(R.id.quantityProductPage)
    EditText quantityProductPage;
    @BindView(R.id.add_to_wishlist)
    LottieAnimationView addToWishlist;
    @BindView(R.id.customheader)
    EditText customheader;
    @BindView(R.id.custommessage)
    EditText custommessage;


    private boolean alreadyInWishlist = false;

    private GenericProductModel model;
    private UserSession session;

    private String usermobile, useremail;
    private int quantity = 1;
    private String username;

    private FirebaseFirestore firestore;

    @BindView(R.id.wishlist_red)
    ImageView wishlist_red;
    String get;
    //imageslider
    private SliderView imageSlider;
    private SliderAdapter adapter;
    private ArrayList<SliderData> sliderDataArrayList;
    FirebaseFirestore db;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String user1;
    String cardiddd;
    FirebaseFirestore firebaseFirestore;
    String identifier;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_product4);
        ButterKnife.bind(this);
        firebaseFirestore=FirebaseFirestore.getInstance();


        //check Internet Connection
        // new CheckInternetConnection(this).checkConnection();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        TextView delikvery=findViewById(R.id.delikvery);


        initialize();
        customheader.setText("Hi");
        custommessage.setText("I want it");
        try {
            cardiddd=getIntent().getStringExtra("uuod");
            firebaseFirestore.collection("AllDelivery")

                    .document("abc@gmail.com").get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                if (task.getResult().exists()) {
                                    delikvery.setText("Delivery Charge is : "+task.getResult().getString("charge")+" Taka");
                                }
                            }
                        }
                    });
        }catch (Exception e) {
            cardiddd=getIntent().getStringExtra("uuod");
            firebaseFirestore.collection("AllDelivery")

                    .document("abc@gmail.com").get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                if (task.getResult().exists()) {
                                    delikvery.setText("Delivery Charge is : "+task.getResult().getString("charge")+" Taka");
                                }
                            }
                        }
                    });
        }
        //ImageSLider
        imageSlider = findViewById(R.id.slider);
        sliderDataArrayList = new ArrayList<>();
        customheader.setText("hi");
        custommessage.setText("I want it");
        // initializing our slider view and
        // firebase firestore instance.
        db = FirebaseFirestore.getInstance();

        // calling our method to load images.
        loadImages(cardiddd);
        wishlist_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final KProgressHUD progressDialog=  KProgressHUD.create(IndividualProductActivity4.this)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setLabel("Please wait")
                        .setCancellable(false)
                        .setAnimationSpeed(2)
                        .setDimAmount(0.5f)
                        .show();


                firestore.collection("Wishlist").document(usermobile).collection(username+" Wishlist")
                        .document(String.valueOf(model.getCardid())).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        progressDialog.dismiss();
                        Log.d("xlr8_wlv", String.valueOf(session.getWishlistValue()));
                        addToWishlist.setVisibility(View.VISIBLE);
                        addToWishlist.cancelAnimation();
                        addToWishlist.clearAnimation();
                        addToWishlist.setProgress(0);
                        addToWishlist = findViewById(R.id.add_to_wishlist);
                        wishlist_red.setVisibility(View.GONE);
                        session.decreaseWishlistValue();
                        Connect.wishlist.remove(Long.valueOf(model.getCardid()));

                        Toasty.success(IndividualProductActivity4.this,"Removed from Wishlist",2000).show();
                    }
                });
            }
        });

    }
    private void loadImages(String cardiddd) {
        // getting data from our collection and after
        // that calling a method for on success listener.

        db.collection("ImageSlider")
                .document("1")
                .collection(cardiddd).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                // inside the on success method we are running a for loop
                // and we are getting the data from Firebase Firestore
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {

                    // after we get the data we are passing inside our object class.
                    SliderData sliderData = documentSnapshot.toObject(SliderData.class);
                    SliderData model = new SliderData();

                    // below line is use for setting our
                    // image url for our modal class.
                    model.setImgUrl(sliderData.getImgUrl());

                    // after that we are adding that
                    // data inside our array list.
                    sliderDataArrayList.add(model);

                    // after adding data to our array list we are passing
                    // that array list inside our adapter class.
                    adapter = new SliderAdapter(getApplicationContext(), sliderDataArrayList);

                    // belows line is for setting adapter
                    // to our slider view
                    imageSlider.setSliderAdapter(adapter);

                    // below line is for setting animation to our slider.
                    imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);

                    // below line is for setting auto cycle duration.
                    imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);

                    // below line is for setting
                    // scroll time animation
                    imageSlider.setScrollTimeInSec(3);

                    // below line is for setting auto
                    // cycle animation to our slider
                    imageSlider.setAutoCycle(true);

                    // below line is use to start
                    // the animation of our slider view.
                    imageSlider.startAutoCycle();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // if we get any error from Firebase we are
                // displaying a toast message for failure
                Toast.makeText(getApplicationContext(), "Fail to load slider data..", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        //check Internet Connection
        //new CheckInternetConnection(this).checkConnection();
    }

    private void initialize() {

        firestore = FirebaseFirestore.getInstance();
        model = (GenericProductModel) getIntent().getSerializableExtra("product");

        productprice.setText("৳ " + model.getCardprice());
        productname.setText(model.getCardname());
        productdesc.setText(model.getCarddiscription());
        quantityProductPage.setText("1");
        Picasso.get().load(model.getCardimage()).into(productimage);

        //SharedPreference for Cart Value
        session = new UserSession(getApplicationContext());

        //validating session
        session.isLoggedIn();
        usermobile = session.getUserDetails().get(UserSession.KEY_MOBiLE);
        useremail = session.getUserDetails().get(UserSession.KEY_EMAIL);
        username = session.getUserDetails().get(UserSession.KEY_NAME);

        Log.d("xlr8", String.valueOf(Connect.wishlist));
        Log.d("xlr8_id", String.valueOf(model.getCardid()));

        if(Connect.wishlist.contains(Long.valueOf(model.getCardid()))){
            Log.d("xlr8","True");
            wishlist_red.setVisibility(View.VISIBLE);
            addToWishlist.setVisibility(View.GONE);
        } else {
            Log.d("xlr8","False");
            wishlist_red.setVisibility(View.GONE);
            addToWishlist.setVisibility(View.VISIBLE);
        }


        //setting textwatcher for no of items field
        quantityProductPage.addTextChangedListener(productcount);

        productname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FlatDialog flatDialog = new FlatDialog(IndividualProductActivity4.this);
                flatDialog.setTitle("Send a Review")
                        .setTitleColor(Color.parseColor("#000000"))
                        .setBackgroundColor(Color.parseColor("#f5f0e3"))
                        .setLargeTextFieldHint("write review here ...")
                        .setLargeTextFieldHintColor(Color.parseColor("#000000"))
                        .setLargeTextFieldBorderColor(Color.parseColor("#000000"))
                        .setLargeTextFieldTextColor(Color.parseColor("#000000"))
                        .setFirstButtonColor(Color.parseColor("#fda77f"))
                        .setFirstButtonTextColor(Color.parseColor("#000000"))
                        .setFirstButtonText("Done")
                        .withFirstButtonListner(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (TextUtils.isEmpty(flatDialog.getLargeTextField().toString())) {
                                    Toast.makeText(IndividualProductActivity4.this, "Enter review", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    flatDialog.dismiss();
                                    Toast.makeText(IndividualProductActivity4.this, "Done", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .show();
            }
        });


    }

    //check that product count must not exceed 500
    TextWatcher productcount = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //none
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (quantityProductPage.getText().toString().equals("")) {
                quantityProductPage.setText("0");
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            //none
            if (Integer.parseInt(quantityProductPage.getText().toString()) >= 500) {
                Toasty.error(IndividualProductActivity4.this, "Product Count Must be less than 500", Toast.LENGTH_LONG).show();
            }
        }

    };

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void Notifications(View view) {
        //startActivity(new Intent(IndividualProductActivity.this, NotificationActivity.class));
        //finish();
    }

    public void decrement(View view) {
        if (quantity > 1) {
            quantity--;
            quantityProductPage.setText(String.valueOf(quantity));
        }
    }

    public void increment(View view) {
        if (quantity < 500) {
            quantity++;
            quantityProductPage.setText(String.valueOf(quantity));
        } else {
            Toasty.error(IndividualProductActivity4.this, "Product Count Must be less than 500", Toast.LENGTH_LONG).show();
        }
    }

    public void shareProduct(View view) {

        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Found amazing " + productname.getText().toString() + "on Mini Bazaar App";
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    public void similarProduct(View view) {
        finish();
    }

    public void addToWishList(View view) {

        final KProgressHUD progressDialog=  KProgressHUD.create(IndividualProductActivity4.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();

        firestore.collection("Wishlist").document(usermobile).collection(username+" Wishlist")
                .document(String.valueOf(model.getCardid())).set(getProductObject()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                progressDialog.dismiss();
                addToWishlist.playAnimation();
                session.increaseWishlistValue();
                Connect.wishlist.add(Long.valueOf(model.getCardid()));
                Log.d("xlr8_wlv", String.valueOf(session.getWishlistValue()));
                Toasty.success(IndividualProductActivity4.this,"Added to your Wishlist",2000).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        addToWishlist.clearAnimation();
                        addToWishlist.setVisibility(View.GONE);
                        wishlist_red.setVisibility(View.VISIBLE);

                    }
                },500);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toasty.error(IndividualProductActivity4.this,"Failed to add.",2000).show();
            }
        });

        /*firestore.collection("Wishlist").document(usermobile).collection(username+" Wishlist")
                .add(getProductObject()).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {

                progressDialog.dismiss();
                addToWishlist.playAnimation();
                session.increaseWishlistValue();
                ShoesActivity.wishlistProductIds.add(Long.valueOf(model.getCardid()));
                Toasty.success(IndividualProductActivity.this,"Added to your Wishlist",2000).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        addToWishlist.setVisibility(View.GONE);
                        wishlist_red.setVisibility(View.VISIBLE);

                    }
                },1000);


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toasty.error(IndividualProductActivity.this,"Failed to add.",2000).show();
            }
        });*/
    }
    private SingleProductModel getProductObject() {

        return new SingleProductModel(model.getCardid(), Integer.parseInt(quantityProductPage.getText().toString()), useremail, usermobile, model.getCardname(), Float.toString(model.getCardprice()), model.getCardimage(), model.carddiscription,customheader.getText().toString(),custommessage.getText().toString(),identifier);

    }

    public void goToCart(View view) {

        if ( customheader.getText().toString().length() == 0 ||  custommessage.getText().toString().length() ==0 ){

            Snackbar.make(view, "Header or Message Empty", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }else {
            firebaseFirestore.collection("Products")
                    .document("6")
                    .collection("List")
                    .document(cardiddd)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                if (task.getResult().exists()) {
                                    String finding=task.getResult().getString("finding");
                                    if (finding.toLowerCase().equals("1")) {
                                        String[] ss ={"S","M","L","XL","XXL"};
                                        AlertDialog.Builder builder=new AlertDialog.Builder(IndividualProductActivity4.this);
                                        builder.setTitle("Size/Style")
                                                .setItems(ss, new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        if (which==0) {
                                                            identifier="S";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==1) {
                                                            identifier="M";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==2) {
                                                            identifier="L";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==3) {
                                                            identifier="XL";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==4) {
                                                            identifier="XXL";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                    }
                                                }).create().show();

                                    }
                                    else  if (finding.toLowerCase().equals("2")) {
                                        String[] ss ={"S","M","L","XL","XXL"};
                                        AlertDialog.Builder builder=new AlertDialog.Builder(IndividualProductActivity4.this);
                                        builder.setTitle("Size/Style")
                                                .setItems(ss, new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        if (which==0) {
                                                            identifier="S";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==1) {
                                                            identifier="M";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==2) {
                                                            identifier="L";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==3) {
                                                            identifier="XL";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==4) {
                                                            identifier="XXL";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                    }
                                                }).create().show();
                                    }
                                    else  if (finding.toLowerCase().equals("3")) {
                                        String[] quantifier={"20","21","22","23","24","25","26","27","28","29",
                                                "30","31","32","33","34","35","36","37","38","39","40","41","42","43"};
                                        AlertDialog.Builder builder=new AlertDialog.Builder(IndividualProductActivity4.this);
                                        builder.setTitle("Size/Style")
                                                .setItems(quantifier, new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        if (which==0) {
                                                            identifier="20";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==1) {
                                                            identifier="21";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==2) {
                                                            identifier="22";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==3) {
                                                            identifier="23";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==4) {
                                                            identifier="24";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        //6
                                                        if (which==5) {
                                                            identifier="25";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==6) {
                                                            identifier="26";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==7) {
                                                            identifier="27";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==8) {
                                                            identifier="28";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==9) {
                                                            identifier="29";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        //11
                                                        if (which==10) {
                                                            identifier="30";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==11) {
                                                            identifier="31";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==12) {
                                                            identifier="32";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==13) {
                                                            identifier="33";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==14) {
                                                            identifier="34";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        //16
                                                        if (which==15) {
                                                            identifier="35";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==16) {
                                                            identifier="36";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==17) {
                                                            identifier="37";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==18) {
                                                            identifier="38";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==19) {
                                                            identifier="39";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        //20
                                                        if (which==20) {
                                                            identifier="40";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==21) {
                                                            identifier="41";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==22) {
                                                            identifier="42";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==23) {
                                                            identifier="43";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }

                                                    }
                                                }).create().show();
                                    }
                                    else  if (finding.toLowerCase().equals("4")) {
                                        String[] quantifier={"20","21","22","23","24","25","26","27","28","29",
                                                "30","31","32","33","34","35","36","37","38","39","40","41","42","43"};
                                        AlertDialog.Builder builder=new AlertDialog.Builder(IndividualProductActivity4.this);
                                        builder.setTitle("Size/Style")
                                                .setItems(quantifier, new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        if (which==0) {
                                                            identifier="20";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==1) {
                                                            identifier="21";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==2) {
                                                            identifier="22";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==3) {
                                                            identifier="23";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==4) {
                                                            identifier="24";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        //6
                                                        if (which==5) {
                                                            identifier="25";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==6) {
                                                            identifier="26";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==7) {
                                                            identifier="27";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==8) {
                                                            identifier="28";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==9) {
                                                            identifier="29";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        //11
                                                        if (which==10) {
                                                            identifier="30";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==11) {
                                                            identifier="31";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==12) {
                                                            identifier="32";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==13) {
                                                            identifier="33";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==14) {
                                                            identifier="34";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        //16
                                                        if (which==15) {
                                                            identifier="35";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==16) {
                                                            identifier="36";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==17) {
                                                            identifier="37";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==18) {
                                                            identifier="38";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==19) {
                                                            identifier="39";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        //20
                                                        if (which==20) {
                                                            identifier="40";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==21) {
                                                            identifier="41";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==22) {
                                                            identifier="42";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==23) {
                                                            identifier="43";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }

                                                    }
                                                }).create().show();
                                    }
                                    else  if (finding.toLowerCase().equals("5")) {
                                        String[] ss ={"S","M","L","XL","XXL"};
                                        AlertDialog.Builder builder=new AlertDialog.Builder(IndividualProductActivity4.this);
                                        builder.setTitle("Size/Style")
                                                .setItems(ss, new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        if (which==0) {
                                                            identifier="S";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==1) {
                                                            identifier="M";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==2) {
                                                            identifier="L";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==3) {
                                                            identifier="XL";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                        else  if (which==4) {
                                                            identifier="XXL";
                                                            addToCartProcess(false);
                                                            startActivity(new Intent(IndividualProductActivity4.this, CartActivity.class));
                                                            finish();
                                                        }
                                                    }
                                                }).create().show();
                                    }

                                }
                            }
                        }
                    });
        }

    }

    private void addToCartProcess(final boolean addToCart){
        final KProgressHUD progressDialog=  KProgressHUD.create(IndividualProductActivity4.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();

        firestore.collection("Cart").document(useremail).collection(username+" Cart")
                .document(String.valueOf(model.getCardid())).set(getProductObject()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                progressDialog.dismiss();
                session.increaseCartValue();
                if (addToCart==true) {
                    Toasty.success(IndividualProductActivity4.this, "Added to Cart", 2000).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toasty.error(IndividualProductActivity4.this,"Failed to add.",2000).show();
            }
        });

    }

    public void addToCart(View view) {

        if ( customheader.getText().toString().length() == 0 ||  custommessage.getText().toString().length() ==0 ){

            Snackbar.make(view, "Header or Message Empty", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else {

            addToCartProcess(true);


        }
    }

}