package com.messas.kikikikikikik;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.messas.starlifeuser_user_user.R;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;


public class HomeFragment extends Fragment {
View view;
    //Other Variables
    private Animation topAnimation, bottomAnimation, startAnimation, endAnimation;
    private SharedPreferences onBoardingPreference;
    public HomeFragment() {
        // Required empty public constructor
    }

   CardView dailyCheckCard,taskCard,taskCard6,dailyCheckddCard,number5,number16;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home, container, false);
        topAnimation = AnimationUtils.loadAnimation(view.getContext(), R.anim.splash_top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(view.getContext(), R.anim.splash_bottom_animation);
        startAnimation = AnimationUtils.loadAnimation(view.getContext(), R.anim.splash_start_animation);
        endAnimation = AnimationUtils.loadAnimation(view.getContext(), R.anim.splash_end_animation);
        ////cardview
        dailyCheckCard=view.findViewById(R.id.dailyCheckCard);
        dailyCheckCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),ShoesActivity.class);
                getContext().startActivity(intent);
            }
        });
        taskCard=view.findViewById(R.id.taskCard);
        taskCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),Tshirts.class);
                getContext().startActivity(intent);
            }
        });
        taskCard6=view.findViewById(R.id.taskCard6);
        taskCard6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),Bags111.class);
                getContext().startActivity(intent);
            }
        });

        dailyCheckddCard=view.findViewById(R.id.dailyCheckddCard);
        dailyCheckddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),Others.class);
                getContext().startActivity(intent);
            }
        });
        number5=view.findViewById(R.id.number5);
        number5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),Connect.class);
                getContext().startActivity(intent);
            }
        });
        TextView allcategores=view.findViewById(R.id.allcategores);
        allcategores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),AllCateGoryies.class);
                getContext().startActivity(intent);
            }
        });
        number16=view.findViewById(R.id.number116);
        number16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),MeToo.class);
                intent.putExtra("name","metooo");
                getContext().startActivity(intent);
            }
        });
        CardView  number7=view.findViewById(R.id.number7);
        number7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),MeToo.class);
                intent.putExtra("name","Number7");
                getContext().startActivity(intent);
            }
        });
        //8
        CardView  number8=view.findViewById(R.id.number8);
        number8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),MeToo.class);
                intent.putExtra("name","Number8");
                getContext().startActivity(intent);
            }
        });//9
        CardView  number9=view.findViewById(R.id.number9);
        number9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),MeToo.class);
                intent.putExtra("name","Number9");
                getContext().startActivity(intent);
            }
        });
        //10
        CardView  number10=view.findViewById(R.id.number10);
        number10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),MeToo.class);
                intent.putExtra("name","Number10");
                getContext().startActivity(intent);
            }
        });//11
        CardView  number11=view.findViewById(R.id.number11);
        number11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),MeToo.class);
                intent.putExtra("name","Number11");
                getContext().startActivity(intent);
            }
        });//12
        CardView  number12=view.findViewById(R.id.number12);
        number12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),MeToo.class);
                intent.putExtra("name","Number12");
                getContext().startActivity(intent);
            }
        });//13
        CardView  number13=view.findViewById(R.id.number13);
        number13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),MeToo.class);
                intent.putExtra("name","Number13");
                getContext().startActivity(intent);
            }
        });//14
        CardView  number14=view.findViewById(R.id.number14);
        number14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),MeToo.class);
                intent.putExtra("name","Number14");
                getContext().startActivity(intent);
            }
        });
        //15
        CardView  number15=view.findViewById(R.id.number15);
        number15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),MeToo.class);
                intent.putExtra("name","Number15");
                getContext().startActivity(intent);
            }
        });//16
        CardView  number16=view.findViewById(R.id.number16);
        number16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),MeToo.class);
                intent.putExtra("name","Number16");
                getContext().startActivity(intent);
            }
        });//17
        CardView  number17=view.findViewById(R.id.number17);
        number17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),MeToo.class);
                intent.putExtra("name","Number17");
                getContext().startActivity(intent);
            }
        });//18
        CardView  number18=view.findViewById(R.id.number18);
        number18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),MeToo.class);
                intent.putExtra("name","Number18");
                getContext().startActivity(intent);
            }
        });//19
        CardView  number19=view.findViewById(R.id.number19);
        number19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),MeToo.class);
                intent.putExtra("name","Number19");
                getContext().startActivity(intent);
            }
        });//20
        CardView  number20=view.findViewById(R.id.number20);
        number20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),MeToo.class);
                intent.putExtra("name","Number20");
                getContext().startActivity(intent);
            }
        });
        //21
        CardView  number21=view.findViewById(R.id.number21);
        number21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),MeToo.class);
                intent.putExtra("name","Number21");
                getContext().startActivity(intent);
            }
        });






       /*
        dailyCheckCard.setAnimation(startAnimation);
        taskCard.setAnimation(endAnimation);
        taskCard6.setAnimation(topAnimation);
        dailyCheckddCard.setAnimation(bottomAnimation);
        number5.setAnimation(startAnimation);
        number16.setAnimation(endAnimation);
        */
       firebaseAuth=FirebaseAuth.getInstance();
       firebaseFirestore=FirebaseFirestore.getInstance();

        db = FirebaseFirestore.getInstance();
        sliderview=view.findViewById(R.id.slider11);
        imageSlider=view.findViewById(R.id.slider1);
        slider1111=view.findViewById(R.id.slider1111);
        sliderview2=view.findViewById(R.id.sliderview2);
        db = FirebaseFirestore.getInstance();
        sliderDataArrayList = new ArrayList<>();
        sliderDataArrayLis2 = new ArrayList<>();
        // initializing our slider view and
        // firebase firestore instance.
        db = FirebaseFirestore.getInstance();


        session = new UserSession(getContext());
        getValues();
        firebaseFirestore.collection("Post_Slider")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int see = 0;
                            for (DocumentSnapshot document : task.getResult()) {
                                see++;
                            }
                            if (see<=0) {
                                imageSlider.setVisibility(View.VISIBLE);
                                inflateImageSlider();
                                sliderview.setVisibility(View.GONE);
                            //    slider1111.setVisibility(View.VISIBLE);
                                //sliderview2.setVisibility(View.GONE);
                            }
                            else if (see>0){
                                imageSlider.setVisibility(View.GONE);
                                sliderview.setVisibility(View.VISIBLE);
                                loadImages();
                               // slider1111.setVisibility(View.GONE);
                               // sliderview2.setVisibility(View.VISIBLE);

                            }
                        }
                    }
                });
        ///
        firebaseFirestore.collection("Post_Slider2")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int see = 0;
                            for (DocumentSnapshot document : task.getResult()) {
                                see++;
                            }
                            if (see<=0) {
                               // imageSlider.setVisibility(View.VISIBLE);
                                slider1111.setVisibility(View.VISIBLE);
                                sliderview2.setVisibility(View.GONE);
                                inflateImageSlider2();
                                //sliderview.setVisibility(View.GONE);

                            }
                            else if (see>0){
                               // imageSlider.setVisibility(View.GONE);
                               // sliderview.setVisibility(View.VISIBLE);
                                slider1111.setVisibility(View.GONE);
                                sliderview2.setVisibility(View.VISIBLE);
                                loadImages2();


                            }
                        }
                    }
                });

        /////nildoriya

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();

        image1=view.findViewById(R.id.image1);
        text1=view.findViewById(R.id.text1);
        firebaseFirestore.collection("ImagesCollection")
                .document("1")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                try {
                                    Picasso.get().load(task.getResult().getString("image")).into(image1);
                                    text1.setText(task.getResult().getString("text"));
                                }catch (Exception e) {
                                    Picasso.get().load(task.getResult().getString("image")).into(image1);
                                    text1.setText(task.getResult().getString("text"));
                                }
                            }
                        }
                    }
                });
        //2
        image2=view.findViewById(R.id.image2);
        text2=view.findViewById(R.id.text2);
        firebaseFirestore.collection("ImagesCollection")
                .document("2")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                try {
                                    Picasso.get().load(task.getResult().getString("image")).into(image2);
                                    text2.setText(task.getResult().getString("text"));
                                }catch (Exception e) {
                                    Picasso.get().load(task.getResult().getString("image")).into(image2);
                                    text2.setText(task.getResult().getString("text"));
                                }
                            }
                        }
                    }
                });
        //3
        image3=view.findViewById(R.id.image3);
        text3=view.findViewById(R.id.mytext3);
        firebaseFirestore.collection("ImagesCollection")
                .document("3")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                try {
                                    Picasso.get().load(task.getResult().getString("image")).into(image3);
                                    text3.setText(task.getResult().getString("text"));
                                }catch (Exception e) {
                                    Picasso.get().load(task.getResult().getString("image")).into(image3);
                                    text3.setText(task.getResult().getString("text"));
                                }
                            }
                        }
                    }
                });//4
        image4=view.findViewById(R.id.image4);
        text4=view.findViewById(R.id.mytext4);
        firebaseFirestore.collection("ImagesCollection")
                .document("4")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                try {
                                    Picasso.get().load(task.getResult().getString("image")).into(image4);
                                    text4.setText(task.getResult().getString("text"));
                                }catch (Exception e) {
                                    Picasso.get().load(task.getResult().getString("image")).into(image4);
                                    text4.setText(task.getResult().getString("text"));
                                }
                            }
                        }
                    }
                });//5
        image5=view.findViewById(R.id.image5);
        text5=view.findViewById(R.id.mytext5);
        firebaseFirestore.collection("ImagesCollection")
                .document("5")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                try {
                                    Picasso.get().load(task.getResult().getString("image")).into(image5);
                                    text5.setText(task.getResult().getString("text"));
                                }catch (Exception e) {
                                    Picasso.get().load(task.getResult().getString("image")).into(image5);
                                    text5.setText(task.getResult().getString("text"));
                                }
                            }
                        }
                    }
                });
        //6
        image6=view.findViewById(R.id.image6);
        text6=view.findViewById(R.id.mytext6);
        firebaseFirestore.collection("ImagesCollection")
                .document("6")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                try {
                                    Picasso.get().load(task.getResult().getString("image")).into(image6);
                                    text6.setText(task.getResult().getString("text"));
                                }catch (Exception e) {
                                    Picasso.get().load(task.getResult().getString("image")).into(image6);
                                    text6.setText(task.getResult().getString("text"));
                                }
                            }
                        }
                    }
                });
        //7
        image7=view.findViewById(R.id.image7);
        text7=view.findViewById(R.id.mytext7);
        firebaseFirestore.collection("ImagesCollection")
                .document("7")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                try {
                                    Picasso.get().load(task.getResult().getString("image")).into(image7);
                                    text7.setText(task.getResult().getString("text"));
                                }catch (Exception e) {
                                    Picasso.get().load(task.getResult().getString("image")).into(image7);
                                    text7.setText(task.getResult().getString("text"));
                                }
                            }
                        }
                    }
                });
        //8
        image8=view.findViewById(R.id.image8);
        text8=view.findViewById(R.id.mytext8);
        firebaseFirestore.collection("ImagesCollection")
                .document("8")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                try {
                                    Picasso.get().load(task.getResult().getString("image")).into(image8);
                                    text8.setText(task.getResult().getString("text"));
                                }catch (Exception e) {
                                    Picasso.get().load(task.getResult().getString("image")).into(image8);
                                    text8.setText(task.getResult().getString("text"));
                                }
                            }
                        }
                    }
                });//9
        image9=view.findViewById(R.id.image9);
        text9=view.findViewById(R.id.mytext9);
        firebaseFirestore.collection("ImagesCollection")
                .document("9")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                try {
                                    Picasso.get().load(task.getResult().getString("image")).into(image9);
                                    text9.setText(task.getResult().getString("text"));
                                }catch (Exception e) {
                                    Picasso.get().load(task.getResult().getString("image")).into(image9);
                                    text9.setText(task.getResult().getString("text"));
                                }
                            }
                        }
                    }
                });
        //10
        image10=view.findViewById(R.id.image10);
        text10=view.findViewById(R.id.mytext10);
        firebaseFirestore.collection("ImagesCollection")
                .document("10")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                try {
                                    Picasso.get().load(task.getResult().getString("image")).into(image10);
                                    text10.setText(task.getResult().getString("text"));
                                }catch (Exception e) {
                                    Picasso.get().load(task.getResult().getString("image")).into(image10);
                                    text10.setText(task.getResult().getString("text"));
                                }
                            }
                        }
                    }
                });
        //11
        image11=view.findViewById(R.id.image11);
        text11=view.findViewById(R.id.mytext11);
        firebaseFirestore.collection("ImagesCollection")
                .document("11")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                try {
                                    Picasso.get().load(task.getResult().getString("image")).into(image11);
                                    text11.setText(task.getResult().getString("text"));
                                }catch (Exception e) {
                                    Picasso.get().load(task.getResult().getString("image")).into(image11);
                                    text11.setText(task.getResult().getString("text"));
                                }
                            }
                        }
                    }
                });
        //12
        image12=view.findViewById(R.id.image12);
        text12=view.findViewById(R.id.mytext12);
        firebaseFirestore.collection("ImagesCollection")
                .document("12")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                try {
                                    Picasso.get().load(task.getResult().getString("image")).into(image12);
                                    text12.setText(task.getResult().getString("text"));
                                }catch (Exception e) {
                                    Picasso.get().load(task.getResult().getString("image")).into(image12);
                                    text12.setText(task.getResult().getString("text"));
                                }
                            }
                        }
                    }
                });
        //13
        image13=view.findViewById(R.id.image13);
        text13=view.findViewById(R.id.mytext13);
        firebaseFirestore.collection("ImagesCollection")
                .document("13")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                try {
                                    Picasso.get().load(task.getResult().getString("image")).into(image13);
                                    text13.setText(task.getResult().getString("text"));
                                }catch (Exception e) {
                                    Picasso.get().load(task.getResult().getString("image")).into(image13);
                                    text13.setText(task.getResult().getString("text"));
                                }
                            }
                        }
                    }
                });
        //14
        image14=view.findViewById(R.id.image14);
        text14=view.findViewById(R.id.mytext14);
        firebaseFirestore.collection("ImagesCollection")
                .document("14")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                try {
                                    Picasso.get().load(task.getResult().getString("image")).into(image14);
                                    text14.setText(task.getResult().getString("text"));
                                }catch (Exception e) {
                                    Picasso.get().load(task.getResult().getString("image")).into(image14);
                                    text14.setText(task.getResult().getString("text"));
                                }
                            }
                        }
                    }
                });//15
        image15=view.findViewById(R.id.image15);
        text15=view.findViewById(R.id.mytext15);
        firebaseFirestore.collection("ImagesCollection")
                .document("15")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                try {
                                    Picasso.get().load(task.getResult().getString("image")).into(image15);
                                    text15.setText(task.getResult().getString("text"));
                                }catch (Exception e) {
                                    Picasso.get().load(task.getResult().getString("image")).into(image15);
                                    text15.setText(task.getResult().getString("text"));
                                }
                            }
                        }
                    }
                });
        //16
        image16=view.findViewById(R.id.image16);
        text16=view.findViewById(R.id.mytext16);
        firebaseFirestore.collection("ImagesCollection")
                .document("16")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                try {
                                    Picasso.get().load(task.getResult().getString("image")).into(image16);
                                    text16.setText(task.getResult().getString("text"));
                                }catch (Exception e) {
                                    Picasso.get().load(task.getResult().getString("image")).into(image16);
                                    text16.setText(task.getResult().getString("text"));
                                }
                            }
                        }
                    }
                });
        //17
        image17=view.findViewById(R.id.image17);
        text17=view.findViewById(R.id.mytext17);
        firebaseFirestore.collection("ImagesCollection")
                .document("17")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                try {
                                    Picasso.get().load(task.getResult().getString("image")).into(image17);
                                    text17.setText(task.getResult().getString("text"));
                                }catch (Exception e) {
                                    Picasso.get().load(task.getResult().getString("image")).into(image17);
                                    text17.setText(task.getResult().getString("text"));
                                }
                            }
                        }
                    }
                });
        //18
        image18=view.findViewById(R.id.image18);
        text18=view.findViewById(R.id.mytext18);
        firebaseFirestore.collection("ImagesCollection")
                .document("18")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                try {
                                    Picasso.get().load(task.getResult().getString("image")).into(image18);
                                    text18.setText(task.getResult().getString("text"));
                                }catch (Exception e) {
                                    Picasso.get().load(task.getResult().getString("image")).into(image18);
                                    text18.setText(task.getResult().getString("text"));
                                }
                            }
                        }
                    }
                });
        //19
        image19=view.findViewById(R.id.image19);
        text19=view.findViewById(R.id.mytext19);
        firebaseFirestore.collection("ImagesCollection")
                .document("19")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                try {
                                    Picasso.get().load(task.getResult().getString("image")).into(image19);
                                    text19.setText(task.getResult().getString("text"));
                                }catch (Exception e) {
                                    Picasso.get().load(task.getResult().getString("image")).into(image19);
                                    text19.setText(task.getResult().getString("text"));
                                }
                            }
                        }
                    }
                });
        //20
        image20=view.findViewById(R.id.image20);
        text20=view.findViewById(R.id.mytext20);
        firebaseFirestore.collection("ImagesCollection")
                .document("20")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                try {
                                    Picasso.get().load(task.getResult().getString("image")).into(image20);
                                    text20.setText(task.getResult().getString("text"));
                                }catch (Exception e) {
                                    Picasso.get().load(task.getResult().getString("image")).into(image20);
                                    text20.setText(task.getResult().getString("text"));
                                }
                            }
                        }
                    }
                });
        //21
        image21=view.findViewById(R.id.image21);
        text21=view.findViewById(R.id.mytext21);
        firebaseFirestore.collection("ImagesCollection")
                .document("21")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                try {
                                    Picasso.get().load(task.getResult().getString("image")).into(image21);
                                    text21.setText(task.getResult().getString("text"));
                                }catch (Exception e) {
                                    Picasso.get().load(task.getResult().getString("image")).into(image21);
                                    text21.setText(task.getResult().getString("text"));
                                }
                            }
                        }
                    }
                });



        return view;
    }
    TextView text1,text2,text3,text4,text5,text6,text7,text8,text9,text10,text11,text12,text13,text14,text15,
            text16,text17,text18,text19,text20,text21;
    ImageView image1,image2,image3,image4,image5,image6,image7,image8,image9,image10,image11,image12,image13,image14,image15
            ,image16,image17,image18,image19,image20,image21;
    FirebaseFirestore firebaseFirestore;
    SliderView sliderview2;
    ImageSlider slider1111;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore2;
    private void getValues() {
        //validating session


        try {
            //get User details if logged in
            session.isLoggedIn();
            user = session.getUserDetails();

            name = user.get(UserSession.KEY_NAME);
            email = user.get(UserSession.KEY_EMAIL);
            mobile = user.get(UserSession.KEY_MOBiLE);
            photo = user.get(UserSession.KEY_PHOTO);
            username=user.get(UserSession.Username);
        }catch (Exception e) {
            //get User details if logged in
            session.isLoggedIn();
            user = session.getUserDetails();

            name = user.get(UserSession.KEY_NAME);
            email = user.get(UserSession.KEY_EMAIL);
            mobile = user.get(UserSession.KEY_MOBiLE);
            photo = user.get(UserSession.KEY_PHOTO);
            username=user.get(UserSession.Username);
        }
        //Toast.makeText(this, ""+username, Toast.LENGTH_SHORT).show();
    }
    SliderView sliderview;
    private UserSession session;
    private HashMap<String, String> user;
    private String name, email, photo, mobile,username;
    TextView todays;
    private StaggeredGridLayoutManager mLayoutManager;
    private void inflateImageSlider2() {
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
        slideModels.add(new SlideModel(R.drawable.app_logo, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.app_logo, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.app_logo, ScaleTypes.FIT));


        //slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/cash-money-express-ltd.appspot.com/o/salary.png?alt=media&token=627d0a8e-2bf4-4e84-84e9-5574d9b8fbaa"));

        //imageSlider.setImageList(slideModels, ScaleTypes.FIT);
        slider1111.setImageList(slideModels, ScaleTypes.FIT);
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
        slideModels.add(new SlideModel(R.drawable.app_logo, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.app_logo, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.app_logo, ScaleTypes.FIT));


        //slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/cash-money-express-ltd.appspot.com/o/salary.png?alt=media&token=627d0a8e-2bf4-4e84-84e9-5574d9b8fbaa"));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);
        slider1111.setImageList(slideModels, ScaleTypes.FIT);
    }

    FirebaseFirestore db;
    private TextView tvemail,tvphone;
    private HashMap<String,String> uaser;



    FirebaseFirestore db1;
    private ImageSlider imageSlider,imageSlider111;
    FirebaseFirestore firebaseFirestore1,firebaseFirestore11;

    private SliderAdapter adapter1,adapter2;

    private ArrayList<SliderData> sliderDataArrayList,sliderDataArrayLis2;
    private void loadImages() {
        db.collection("Post_Slider").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
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
                    adapter1 = new SliderAdapter(getContext(), sliderDataArrayList);

                    // belows line is for setting adapter
                    // to our slider view
                    sliderview.setSliderAdapter(adapter1);
                  //  sliderview2.setSliderAdapter(adapter1);

                    // below line is for setting animation to our slider.
                    sliderview.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                   // sliderview2.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);

                    // below line is for setting auto cycle duration.
                    sliderview.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
                   // sliderview2.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);

                    // below line is for setting
                    // scroll time animation
                    sliderview.setScrollTimeInSec(3);
                   // sliderview2.setScrollTimeInSec(3);

                    // below line is for setting auto
                    // cycle animation to our slider
                    sliderview.setAutoCycle(true);
                  //  sliderview2.setAutoCycle(true);

                    // below line is use to start
                    // the animation of our slider view.
                    sliderview.startAutoCycle();
                    //sliderview2.startAutoCycle();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // if we get any error from Firebase we are
                // displaying a toast message for failure
                Toast.makeText(getContext(), "Fail to load slider data..", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void loadImages2() {
        db.collection("Post_Slider2").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
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
                    sliderDataArrayLis2.add(model);

                    // after adding data to our array list we are passing
                    // that array list inside our adapter class.
                    adapter2 = new SliderAdapter(getContext(), sliderDataArrayLis2);

                    // belows line is for setting adapter
                    // to our slider view
                    //sliderview.setSliderAdapter(adapter1);
                    sliderview2.setSliderAdapter(adapter2);

                    // below line is for setting animation to our slider.
                   // sliderview.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                    sliderview2.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);

                    // below line is for setting auto cycle duration.
                   // sliderview.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
                    sliderview2.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);

                    // below line is for setting
                    // scroll time animation
                  //  sliderview.setScrollTimeInSec(3);
                    sliderview2.setScrollTimeInSec(3);

                    // below line is for setting auto
                    // cycle animation to our slider
                  //  sliderview.setAutoCycle(true);
                    sliderview2.setAutoCycle(true);

                    // below line is use to start
                    // the animation of our slider view.
                   // sliderview.startAutoCycle();
                    sliderview2.startAutoCycle();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // if we get any error from Firebase we are
                // displaying a toast message for failure
                Toast.makeText(getContext(), "Fail to load slider data..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}