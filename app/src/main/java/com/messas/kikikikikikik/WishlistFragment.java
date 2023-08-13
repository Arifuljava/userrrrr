package com.messas.kikikikikikik;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.messas.starlifeuser_user_user.R;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;


public class WishlistFragment extends Fragment {

   View view;
    SearchView name;
    //Other Variables
    private Animation topAnimation, bottomAnimation, startAnimation, endAnimation;
    private SharedPreferences onBoardingPreference;
    RelativeLayout relative;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    LottieAnimationView empty_cart;
    DocumentReference documentReference;
    RecyclerView recyclerView;
    VideoAdapter getDataAdapter1;
    List<filemodel> getList;
    String url;

    FirebaseUser firebaseUser;
    KProgressHUD progressHUD;
    String cus_name;
    public WishlistFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_wishlist, container, false);
        topAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.splash_top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.splash_bottom_animation);
        startAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.splash_start_animation);
        endAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.splash_end_animation);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();


        getList = new ArrayList<>();
        getDataAdapter1 = new VideoAdapter(getList);
        firebaseFirestore = FirebaseFirestore.getInstance();
        documentReference =  firebaseFirestore.collection("Daily_videos")
                .document();
        recyclerView = view.findViewById(R.id.rreeeed);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(getDataAdapter1);
        relative=view.findViewById(R.id.relative);
        topAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.splash_top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.splash_bottom_animation);
        startAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.splash_start_animation);
        endAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.splash_end_animation);
        relative.setAnimation(startAnimation);
        name=view.findViewById(R.id.name);
        name.setAnimation(topAnimation);
        recyclerView.setAnimation(endAnimation);
        reciveData();
        name.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //fullsearch(query);

                //phoneSerach(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                searchAllUser(newText);

                //phoneSerach1(newText);
                return false;
            }
        });

        return view;
    }
    private void searchAllUser(String newText) {
        firebaseFirestore.collection("Daily_videos")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        getList.clear();
                        for (QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots) {
                            String dta = documentSnapshot.getString("title");
                            if (dta.toLowerCase().toString().contains(newText.toLowerCase().toString())) {
                                filemodel add_customer=new filemodel(documentSnapshot.getString("title"),
                                        documentSnapshot.getString("vurl")


                                );
                                getList.add(add_customer);

                            }
                            getDataAdapter1 = new VideoAdapter(getList);
                            recyclerView.setAdapter(getDataAdapter1);


                        }
                    }
                });
    }
    private void reciveData() {

        firebaseFirestore.collection("Daily_videos")
               .addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                for (DocumentChange ds : queryDocumentSnapshots.getDocumentChanges()) {
                    if (ds.getType() == DocumentChange.Type.ADDED) {

                 /*String first;
                 first = ds.getDocument().getString("name");
                 Toast.makeText(MainActivity2.this, "" + first, Toast.LENGTH_SHORT).show();*/
                        filemodel get = ds.getDocument().toObject(filemodel.class);
                        getList.add(get);
                        getDataAdapter1.notifyDataSetChanged();
                    }

                }
            }
        });

    }
}