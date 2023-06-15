package com.messas.bdshopbazargardennurserybd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.messas.starlifeuser_user_user.R;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class HomeACTIVITY extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private Toolbar mainToolbar;
    private String current_user_id;
    private BottomNavigationView mainBottomNav;
    private DrawerLayout mainDrawer;
    private ActionBarDrawerToggle mainToggle;
    private NavigationView mainNav;

    FrameLayout frameLayout;
    private TextView drawerName;
    private CircleImageView drawerImage;
    FirebaseAuth firebaseAuth;
    //firebase
    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseFirestoreSettings settings;
    private DatabaseReference mUserRef;

    private HashMap<String, String> user;
    private String name, email, photo, mobile,username;
    UserSession session;
    HomeFragment homeFragment;
    MessageFragment messageFragment;
    CartFragment cartFragment;
    WishlistFragment wishlistFragment;
    HistoryFragment historyFragment;

    //Other Variables
    private Animation topAnimation, bottomAnimation, startAnimation, endAnimation;
    private SharedPreferences onBoardingPreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_a_c_t_i_v_i_t_y);
        FirebaseApp.initializeApp(HomeACTIVITY.this);
        //check Internet Connection
        //new CheckInternetConnection(this).checkConnection();
        topAnimation = AnimationUtils.loadAnimation(HomeACTIVITY.this, R.anim.splash_top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(HomeACTIVITY.this, R.anim.splash_bottom_animation);
        startAnimation = AnimationUtils.loadAnimation(HomeACTIVITY.this, R.anim.splash_start_animation);
        endAnimation = AnimationUtils.loadAnimation(HomeACTIVITY.this, R.anim.splash_end_animation);


        session = new UserSession(getApplicationContext());
        //new CheckInternetConnection(this).checkConnection();
        Toolbar toolbar = findViewById(R.id.toolbar);
        mAuth=FirebaseAuth.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(10.0f);
        mainDrawer=findViewById(R.id.main_activity);
        mainNav = findViewById(R.id.main_nav);
        mainNav.setNavigationItemSelectedListener(this);
        frameLayout=findViewById(R.id.main_container);
        mainToggle = new ActionBarDrawerToggle(this,mainDrawer,toolbar,R.string.open,R.string.close);
        mainDrawer.addDrawerListener(mainToggle);
        mainToggle.setDrawerIndicatorEnabled(true);
        mainToggle.syncState();
        //mainNav.setAnimation(topAnimation);
        /////
        homeFragment=new HomeFragment();
        messageFragment=new MessageFragment();
        wishlistFragment=new WishlistFragment();
        cartFragment=new CartFragment();
        historyFragment=new HistoryFragment();
        mainBottomNav = findViewById(R.id.mainBottomNav);
        //mainBottomNav.setAnimation(bottomAnimation);
        mainBottomNav.setOnNavigationItemSelectedListener(selectlistner);

        //
        /*
        int a=12;
        if (a>10&&a<200) {
            Toast.makeText(this, "true", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "false", Toast.LENGTH_SHORT).show();
        }
         */

        //
        initializeFragment();
        mainBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.bottom_home:
                        flag=1;
                        replaceFragment(homeFragment);
                        searchView.setVisibility(View.VISIBLE);
                        return true;

                    case R.id.navigation_chat:
                        flag=2;
                        replaceFragment(messageFragment);
                        searchView.setVisibility(View.GONE);
                        return true;
                   /*
                    case R.id.withlist:
                        flag=2;

                        replaceFragment(wishlistFragment);
                        searchView.setVisibility(View.GONE);
                        return true;
                    */
                    case R.id.cartliost:
                        flag=2;
                        replaceFragment(cartFragment);
                        searchView.setVisibility(View.GONE);
                        return true;
                    case R.id.history:
                        flag=2;
                        replaceFragment(historyFragment);
                        searchView.setVisibility(View.GONE);
                        return true;




                    default:
                        return false;
                }
            }
        });
    }
    int flag=1;
    private BottomNavigationView.OnNavigationItemSelectedListener selectlistner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.bottom_home:
                            HomeFragment fragment2 = new HomeFragment();
                            FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
                            ft2.replace(R.id.content, fragment2, "");
                            ft2.commit();
                            break;



                    }
                    return false;
                }
            };
    private void replaceFragment(Fragment fragment){

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if (fragment == homeFragment){
            fragmentTransaction.hide(messageFragment);
            fragmentTransaction.hide(wishlistFragment);
            fragmentTransaction.hide(cartFragment);
            fragmentTransaction.hide(historyFragment);
            // fragmentTransaction.hide(historyFragment);

        } else if (fragment == messageFragment){

            fragmentTransaction.hide(homeFragment);
            fragmentTransaction.hide(wishlistFragment);
            fragmentTransaction.hide(cartFragment);
            fragmentTransaction.hide(historyFragment);
            searchView.setVisibility(View.GONE);
            //   fragmentTransaction.hide(historyFragment);

        }
        else if(fragment==wishlistFragment) {
            fragmentTransaction.hide(homeFragment);
            fragmentTransaction.hide(messageFragment);
            fragmentTransaction.hide(cartFragment);
            fragmentTransaction.hide(historyFragment);
            searchView.setVisibility(View.GONE);
        }
        else if(fragment==cartFragment) {
            fragmentTransaction.hide(homeFragment);
            fragmentTransaction.hide(messageFragment);
            fragmentTransaction.hide(wishlistFragment);
            fragmentTransaction.hide(historyFragment);
            searchView.setVisibility(View.GONE);
        }
        else if(fragment==historyFragment) {
            fragmentTransaction.hide(homeFragment);
            fragmentTransaction.hide(messageFragment);
            fragmentTransaction.hide(cartFragment);
            fragmentTransaction.hide(wishlistFragment);
            searchView.setVisibility(View.GONE);
        }



        fragmentTransaction.show(fragment);
        fragmentTransaction.commit();
    }
    public void initializeFragment(){

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_container,homeFragment);
        fragmentTransaction.add(R.id.main_container,messageFragment);
        fragmentTransaction.add(R.id.main_container,cartFragment);
        fragmentTransaction.add(R.id.main_container,wishlistFragment);
        fragmentTransaction.add(R.id.main_container,historyFragment);

        // fragmentTransaction.add(R.id.main_container,historyFragment);


        fragmentTransaction.hide(messageFragment);
        fragmentTransaction.hide(cartFragment);
        fragmentTransaction.hide(wishlistFragment);
        fragmentTransaction.hide(historyFragment);


        fragmentTransaction.commit();

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id) {
            case R.id.logout:
                AlertDialog.Builder warning = new AlertDialog.Builder(HomeACTIVITY.this)
                        .setTitle("Logout")
                        .setMessage("Are you want to logout?")
                        .setPositiveButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();



                            }
                        }).setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // ToDO: delete all the notes created by the Anon user


                                firebaseAuth.signOut();
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                finish();


                            }
                        });

                warning.show();
                break;
            case R.id.addNote:
                Toasty.success(getApplicationContext(),"You are now in home",Toasty.LENGTH_SHORT,true).show();

                break;
            case R.id.abountcompany:
                firebaseFirestore.collection("Companydetails")
                        .document("abc@gmail.com")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    if (task.getResult().exists()) {
                                        try {
                                            String number1=task.getResult().getString("number1");
                                            String number2=task.getResult().getString("number2");
                                            String email1=task.getResult().getString("email1");
                                            String email2=task.getResult().getString("email2");
                                          AlertDialog.Builder builder3=new AlertDialog.Builder(HomeACTIVITY.this);
                                          builder3.setTitle("Contact Us")
                                                  .setMessage("Numbers 1 : "+number1+"\nNumber 2 : "+number2+"\n" +
                                                          "Email 1 : "+email1+"\nEmail 2 : "+email2)
                                                  .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                      @Override
                                                      public void onClick(DialogInterface dialog, int which) {
                                                          dialog.dismiss();
                                                      }
                                                  }).create();
                                          builder3.show();


                                        }catch (Exception e) {
                                            String number1=task.getResult().getString("number1");
                                            String number2=task.getResult().getString("number2");
                                            String email1=task.getResult().getString("email1");
                                            String email2=task.getResult().getString("email2");
                                            AlertDialog.Builder builder3=new AlertDialog.Builder(HomeACTIVITY.this);
                                            builder3.setTitle("Contact Us")
                                                    .setMessage("Numbers 1 : "+number1+"\nNumber 2 : "+number2+"\n" +
                                                            "Email 1 : "+email1+"\nEmail 2 : "+email2)
                                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            dialog.dismiss();
                                                        }
                                                    }).create();
                                            builder3.show();
                                        }
                                    }
                                }
                            }
                        });
                break;
            case R.id.porf:
                startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                break;
            case R.id.withlist:
                startActivity(new Intent(getApplicationContext(),WishlistActivity.class));
                break;
            case R.id.cartliost:
                startActivity(new Intent(getApplicationContext(),CartActivity.class));
                break;
            case R.id.history:
                startActivity(new Intent(getApplicationContext(),OrderHistoryActivity.class));
                break;
            case R.id.shareapp1:
                startActivity(new Intent(getApplicationContext(),SettingsActivity.class));
                break;



        }

        return false;
    }
     SearchView searchView;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.home_menu,menu);

        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Intent i = new Intent(this,AllcategoruySearch.class);
                this.startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void searchAllUser(String newText) {
        firebaseFirestore.collection("AAllProducts")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for (QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots) {
                            String dta = documentSnapshot.getString("cardname");
                          if(dta.toLowerCase().toString().equals(newText.toLowerCase().toString())) {
                              startActivity(new Intent(getApplicationContext(),AllcategoruySearch.class));
                              //Toasty.success(getApplicationContext(),"Product existing",Toasty.LENGTH_SHORT,true).show();
                          }
                          else {
                                if (dta.toLowerCase().toString().contains(newText.toLowerCase().toString())) {
                                    startActivity(new Intent(getApplicationContext(),AllcategoruySearch.class));
                                 // Toasty.info(getApplicationContext(),"Product Searching",Toasty.LENGTH_SHORT,true).show();





                              }
                                else {
                                    startActivity(new Intent(getApplicationContext(),AllcategoruySearch.class));
                                    //Toasty.error(getApplicationContext(),"Product is not found",Toasty.LENGTH_SHORT,true).show();
                                }
                          }




                        }
                    }
                });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(HomeACTIVITY.this);
        builder.setTitle("Exit")
                .setCancelable(false)
                .setMessage("Are you want to exit")
                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finishAffinity();
            }
        }).create();
        builder.show();



    }


}