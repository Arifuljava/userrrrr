package com.messas.bdshopbazargardennurserybd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.messas.starlifeuser_user_user.R;
import com.squareup.picasso.Picasso;

public class AllCateGoryies extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_cate_goryies);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("All Categories");
        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_myarrow);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_myarrow);
        getSupportActionBar().setElevation(10.0f);
        getSupportActionBar().setElevation(10.0f);

       CardView dailyCheckCard=findViewById(R.id.dailyCheckCard);
        dailyCheckCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ShoesActivity.class);
           startActivity(intent);
            }
        });
        CardView taskCard=findViewById(R.id.taskCard);
        taskCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Tshirts.class);
          startActivity(intent);
            }
        });
        CardView taskCard6=findViewById(R.id.taskCard6);
        taskCard6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Bags111.class);
               startActivity(intent);
            }
        });

       CardView  dailyCheckddCard=findViewById(R.id.dailyCheckddCard);
        dailyCheckddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Others.class);
               startActivity(intent);
            }
        });
      CardView  number5=findViewById(R.id.number5);
        number5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Connect.class);
                startActivity(intent);
            }
        });
      CardView  number16=findViewById(R.id.number116);
        number16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MeToo.class);
                intent.putExtra("name","metooo");
             startActivity(intent);
            }
        });
        CardView number7=findViewById(R.id.number7);
        number7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MeToo.class);
                intent.putExtra("name","Number7");
               startActivity(intent);
            }
        });
        //8
        CardView  number8=findViewById(R.id.number8);
        number8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MeToo.class);
                intent.putExtra("name","Number8");
                startActivity(intent);
            }
        });//9
        CardView  number9=findViewById(R.id.number9);
        number9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MeToo.class);
                intent.putExtra("name","Number9");
              startActivity(intent);
            }
        });
        //10
        CardView  number10=findViewById(R.id.number10);
        number10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MeToo.class);
                intent.putExtra("name","Number10");
               startActivity(intent);
            }
        });//11
        CardView  number11=findViewById(R.id.number11);
        number11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MeToo.class);
                intent.putExtra("name","Number11");
                startActivity(intent);
            }
        });//12
        CardView  number12=findViewById(R.id.number12);
        number12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MeToo.class);
                intent.putExtra("name","Number12");
                startActivity(intent);
            }
        });//13
        CardView  number13=findViewById(R.id.number13);
        number13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MeToo.class);
                intent.putExtra("name","Number13");
                startActivity(intent);
            }
        });//14
        CardView  number14=findViewById(R.id.number14);
        number14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MeToo.class);
                intent.putExtra("name","Number14");
                startActivity(intent);
            }
        });
        //15
        CardView  number15=findViewById(R.id.number15);
        number15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MeToo.class);
                intent.putExtra("name","Number15");
                startActivity(intent);
            }
        });//16
        CardView  number161=findViewById(R.id.number16);
        number161.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MeToo.class);
                intent.putExtra("name","Number16");
                startActivity(intent);
            }
        });//17
        CardView  number17=findViewById(R.id.number17);
        number17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MeToo.class);
                intent.putExtra("name","Number17");
                startActivity(intent);
            }
        });//18
        CardView  number18=findViewById(R.id.number18);
        number18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MeToo.class);
                intent.putExtra("name","Number18");
                startActivity(intent);
            }
        });//19
        CardView  number19=findViewById(R.id.number19);
        number19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MeToo.class);
                intent.putExtra("name","Number19");
               startActivity(intent);
            }
        });//20
        CardView  number20=findViewById(R.id.number20);
        number20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MeToo.class);
                intent.putExtra("name","Number20");
               startActivity(intent);
            }
        });
        //21
        CardView  number21=findViewById(R.id.number21);
        number21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MeToo.class);
                intent.putExtra("name","Number21");
                startActivity(intent);
            }
        });
        firebaseFirestore=FirebaseFirestore.getInstance();
        ///imageandtext

        image1=findViewById(R.id.image1);
        text1=findViewById(R.id.text1);
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
        image2=findViewById(R.id.image2);
        text2=findViewById(R.id.text2);
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
        image3=findViewById(R.id.image3);
        text3=findViewById(R.id.mytext3);
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
        image4=findViewById(R.id.image4);
        text4=findViewById(R.id.mytext4);
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
        image5=findViewById(R.id.image5);
        text5=findViewById(R.id.mytext5);
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
        image6=findViewById(R.id.image6);
        text6=findViewById(R.id.mytext6);
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
        image7=findViewById(R.id.image7);
        text7=findViewById(R.id.mytext7);
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
        image8=findViewById(R.id.image8);
        text8=findViewById(R.id.mytext8);
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
        image9=findViewById(R.id.image9);
        text9=findViewById(R.id.mytext9);
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
        image10=findViewById(R.id.image10);
        text10=findViewById(R.id.mytext10);
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
        image11=findViewById(R.id.image11);
        text11=findViewById(R.id.mytext11);
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
        image12=findViewById(R.id.image12);
        text12=findViewById(R.id.mytext12);
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
        image13=findViewById(R.id.image13);
        text13=findViewById(R.id.mytext13);
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
        image14=findViewById(R.id.image14);
        text14=findViewById(R.id.mytext14);
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
        image15=findViewById(R.id.image15);
        text15=findViewById(R.id.mytext15);
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
        image16=findViewById(R.id.image16);
        text16=findViewById(R.id.mytext16);
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
        image17=findViewById(R.id.image17);
        text17=findViewById(R.id.mytext17);
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
        image18=findViewById(R.id.image18);
        text18=findViewById(R.id.mytext18);
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
        image19=findViewById(R.id.image19);
        text19=findViewById(R.id.mytext19);
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
        image20=findViewById(R.id.image20);
        text20=findViewById(R.id.mytext20);
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
        image21=findViewById(R.id.image21);
        text21=findViewById(R.id.mytext21);
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





    }
    TextView text1,text2,text3,text4,text5,text6,text7,text8,text9,text10,text11,text12,text13,text14,text15,
            text16,text17,text18,text19,text20,text21;
    ImageView image1,image2,image3,image4,image5,image6,image7,image8,image9,image10,image11,image12,image13,image14,image15
            ,image16,image17,image18,image19,image20,image21;
    FirebaseFirestore firebaseFirestore;
    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(getApplicationContext(), HomeACTIVITY.class));

        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), HomeACTIVITY.class));
    }
}