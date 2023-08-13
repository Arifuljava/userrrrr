package com.messas.kikikikikikik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.messas.starlifeuser_user_user.R;
import com.rengwuxian.materialedittext.MaterialAutoCompleteTextView;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.whygraphics.multilineradiogroup.MultiLineRadioGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class CheckoutActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinnerTextSize, spinnerTextSize1, spinnerTextSize2;
    EditText Email_Log;
    String valueFromSpinner;
    String valueFromSpinner1;
    String valueFromSpinner2;

    private static final int MAXLEN = 5;
    private final String TAG = this.getClass().getSimpleName() + "_xlr8";
    @BindView(R.id.delivery_date)
    TextView deliveryDate;
    @BindView(R.id.no_of_items)
    TextView noOfItems;
    @BindView(R.id.total_amount)
    TextView totalAmount;
    @BindView(R.id.main_activity_multi_line_radio_group)
    MultiLineRadioGroup mainActivityMultiLineRadioGroup;
    @BindView(R.id.ordername)
    MaterialEditText ordername;

    @BindView(R.id.ordernumber)
    MaterialEditText ordernumber;
    @BindView(R.id.orderaddress)
    MaterialAutoCompleteTextView orderaddress;
    MaterialAutoCompleteTextView orderaddress2;


    private ArrayList<SingleProductModel> cartcollect;
    private ArrayList<String> placed_order_images;
    private String orderDateTime;
    private static final String ALLOWED_CHARACTERS = "0123456789qwertyuiopasdfghjklzxcvbnm";
    private UserSession session;
    private FirebaseFirestore firebaseFirestore;
    private String payment_mode = "COD", order_reference_id;
    private HashMap<String, String> user;
    private String placed_user_name, getPlaced_user_email, getPlaced_user_mobile_no;
    private String currdatetime;
    private FirebaseAuth mAuth;
    private Bundle bundle;
    String[] address ={
            "Narsingdi", "Gazipur", "Shariatpur", "Narayanganj", "Tangail",
            "Kishoreganj", "Manikganj", "Munshiganj", "Rajbari", "Madaripur", "Gopalganj", "Faridpur", "Rangpur",
            "Panchagarh", "Dinajpur", "Lalmonirhat",
            "Nilphamari", "Gaibandha", "Thakurgaon", "Rangpur", "Kurigram", "Mymensingh" ,"Sherpur",
            "Jamalpur", "Netrokona", "Comilla", "Feni", "Brahmanbaria", "Rangamati", "Noakhali", "Chandpur",
            "Laxmipur", "Chittagong", "Cox’s Bazar", "Khagrachhari",
            "Bandarban", "Rajshahi", "Sirajganj", "Pabna", "Bogra",
            "Rajshahi", "Natore", "Joypurhat", "Chapainawabganj", "Naogaon", "Khulna",
            "Jessore", "Satkhira", "Meherpur", "Narail", "Chuadanga", "Kushtia", "Magura",
            "Khulna", "Bagerhat", "Jhenaidah", "Barisal", "Jhalokati",
            "Patuakhali", "Pirojpur", "Barisal", "Bhola", "Barguna", "Sylhet", "Moulvibazar", "Habiganj", "Sunamganj",
            "Taltola","Banani","Bangla Bazar","Bangla Motor","Bosundhara","Chawkbazar","Dhanmondi",
            "Ashkona","Elephent Road","Fakirerpool Booking Booth â€“ 1","Fakirerpool Booking Booth â€“ 2",
            "Monipuripara","North-South Road","Gabtoli","Raza Bazar","Green Road","Bongo Bazar",
            "Gulshan 1","Niketon","Gulshan-2","R.K Mission Road","Hatkhola","Hatirpul","Indira Road",
            "Jatrabari","Jhigatola","Postogola","Kakoli","Kakrail","Kalabagan","Kallyanpur Booking Booth â€“ 1",
            "Kallyanpur Booking Booth â€“ 2","Karwan Bazar Booking Booth","Khilgaon","Khilkhet",
            "Kochukhet","Komlapur Stadium","Imamgonj","Lalmatia â€“ 1","Lalmatia â€“ 2","Mouchak","Mirpur-1","Mirpur-10",
            "Mirpur-2","Mogbazar","New DOHS, Mohakhali","Mohakhali Booking Booth","Mohakhali","Mohammadpur Thana",
            "Shekhertek, Mohammadpur","Mohammodia Housing","Katasur, Mohammadpur","Motijheel Booking Booth â€“ 1",
            "Motijheel Booking Booth â€“ 2","Motijheel Booking Booth â€“ 3","Motijheel Booking Booth â€“ 4",
            "NakhalPara","Noya Paltan Agecny","New Eskaton Road","New Market","Nikunzo","Nilkhet","Nobabpur","Pallabi",
            "Purana Paltan Booking Booth â€“ 1","Purana Paltan Booking Booth â€“ 2","Ramna","Rampura","Rayer Bazar",
            "Rupnagar","Shahabag","Shahajadpur","Shewrapara","Sukrabad","Tejgaon","Tejgaon Gulsan Link Road Agency","Mohanagar Press Club Booking Booth",
            "Tolarbag, Mirpur","Modho Badda","Uttar Badda","Vatara Badda","Wari","Kallyanpur","UTTRA","Corporate",
            "Chamelibag","Victoria Park","Kawran Bazar","Motaleb Plaza","Jahangir Nogor University Dhaka",
            "Bosila Road Booking Booth","Banani","Topkhana Road Booking Booth","Baridhara Agency",
            "KAMARPARA","Babu Bazar","Dhanmondi","Mirpur- 11","Jatrabari","Keranigonj","Shamoly"
    };
    FirebaseAuth firebaseAuth;
    MaterialEditText payment_number,yourpay,transcstion,amountddd;
    private ClipboardManager myClipboard;
    private ClipData myClip;
    TextView totalamount,advancepayment,codpaymentamount,amount__,deliverychargeall;
    EditText notificationsetup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        amountddd=findViewById(R.id.amountddd);
        deliverychargeall=findViewById(R.id.deliverychargeall);
        amount__=findViewById(R.id.amount__);
        firebaseAuth=FirebaseAuth.getInstance();
        orderaddress2=findViewById(R.id.orderaddress2);
        totalamount=findViewById(R.id.totalamount);
        advancepayment=findViewById(R.id.advancepayment);
        codpaymentamount=findViewById(R.id.codpaymentamount);
        ButterKnife.bind(this);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        payment_number=findViewById(R.id.payment_number);
        yourpay=findViewById(R.id.yourpay);
        transcstion=findViewById(R.id.transcstion);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item,address);
        myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
        orderaddress.setThreshold(1);//will start working from first character
        orderaddress.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        orderaddress.setTextColor(Color.RED);
        notificationsetup=findViewById(R.id.notificationsetup);

        //check Internet Connection


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("CHECKOUT");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //SharedPreference for Cart Value
        session = new UserSession(getApplicationContext());
        mAuth = FirebaseAuth.getInstance();

        placed_order_images = new ArrayList<>();

        //validating session
        session.isLoggedIn();
        FirebaseApp.initializeApp(this);
        firebaseFirestore = FirebaseFirestore.getInstance();

        productdetails();
        firebaseFirestore.collection("Address")
                .document(mAuth.getCurrentUser().getEmail())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                orderaddress.setText(task.getResult().getString("address"));
                            }
                            else {
                            }
                        }

                    }
                });
        firebaseFirestore.collection("AdminNotifications")
                .document("abc@gmail.com")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                notificationsetup.setText(task.getResult().getString("address"));
                            }
                            else {
                            }
                        }

                    }
                });

        spinnerTextSize = findViewById(R.id.spinner3);
        spinnerTextSize.setOnItemSelectedListener(this);

        String[] textSizes = getResources().getStringArray(R.array.payment___);
        ArrayAdapter adapter111 = new ArrayAdapter(this,
                R.layout.spinner_row, textSizes);
        adapter111.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTextSize.setAdapter(adapter111);
    }

    private void productdetails() {

        bundle = getIntent().getExtras();

        //setting total price
        totalAmount.setText(bundle.get("totalprice").toString());
        totalamount.setText(bundle.get("totalprice").toString());
        String amountttt=bundle.get("totalprice").toString();
        amount__.setText(bundle.get("allmain").toString());
        deliverychargeall.setText(bundle.get("alldelivery").toString());

        ///amount__,deliverychargeall

        double advancepay,codpay;
        if (Double.parseDouble(amountttt)<1000) {
            advancepay=Double.parseDouble(amountttt);
            codpay=Double.parseDouble(amountttt)-advancepay;
            advancepayment.setText(""+advancepay);
            codpaymentamount.setText(""+codpay);

        }
        else if (Double.parseDouble(amountttt)>=1000&&Double.parseDouble(amountttt)<2000) {
            advancepay=800;
            codpay=Double.parseDouble(amountttt)-advancepay;
            advancepayment.setText(""+advancepay);
            codpaymentamount.setText(""+codpay);

        }
         else if (Double.parseDouble(amountttt)>=2000&&Double.parseDouble(amountttt)<3000) {
            advancepay=1200;
            codpay=Double.parseDouble(amountttt)-advancepay;
            advancepayment.setText(""+advancepay);
            codpaymentamount.setText(""+codpay);

        }
        else  if (Double.parseDouble(amountttt)>=3000&&Double.parseDouble(amountttt)<4000) {
            advancepay=2000;
            codpay=Double.parseDouble(amountttt)-advancepay;
            advancepayment.setText(""+advancepay);
            codpaymentamount.setText(""+codpay);

        }
       else   if (Double.parseDouble(amountttt)>=4000&&Double.parseDouble(amountttt)<5000) {
            advancepay=2500;
            codpay=Double.parseDouble(amountttt)-advancepay;
            advancepayment.setText(""+advancepay);
            codpaymentamount.setText(""+codpay);

        }
         else if (Double.parseDouble(amountttt)>=5000&&Double.parseDouble(amountttt)<7000) {
            advancepay=3500;
            codpay=Double.parseDouble(amountttt)-advancepay;
            advancepayment.setText(""+advancepay);
            codpaymentamount.setText(""+codpay);

        }
         else if (Double.parseDouble(amountttt)>=7000&&Double.parseDouble(amountttt)<10000) {
            advancepay=5000;
            codpay=Double.parseDouble(amountttt)-advancepay;
            advancepayment.setText(""+advancepay);
            codpaymentamount.setText(""+codpay);

        }
         else if (Double.parseDouble(amountttt)>=10000&&Double.parseDouble(amountttt)<15000) {
            advancepay=8000;
            codpay=Double.parseDouble(amountttt)-advancepay;
            advancepayment.setText(""+advancepay);
            codpaymentamount.setText(""+codpay);

        }
        else  if (Double.parseDouble(amountttt)>=15000&&Double.parseDouble(amountttt)<20000) {
            advancepay=10000;
            codpay=Double.parseDouble(amountttt)-advancepay;
            advancepayment.setText(""+advancepay);
            codpaymentamount.setText(""+codpay);

        }
         else if (Double.parseDouble(amountttt)>=20000&&Double.parseDouble(amountttt)<25000) {
            advancepay=13000;
            codpay=Double.parseDouble(amountttt)-advancepay;
            advancepayment.setText(""+advancepay);
            codpaymentamount.setText(""+codpay);

        }
         else if (Double.parseDouble(amountttt)>=25000&&Double.parseDouble(amountttt)<30000) {
            advancepay=15000;
            codpay=Double.parseDouble(amountttt)-advancepay;
            advancepayment.setText(""+advancepay);
            codpaymentamount.setText(""+codpay);

        }
        else  if (Double.parseDouble(amountttt)>=30000&&Double.parseDouble(amountttt)<40000) {
            advancepay=20000;
            codpay=Double.parseDouble(amountttt)-advancepay;
            advancepayment.setText(""+advancepay);
            codpaymentamount.setText(""+codpay);

        }
        else  if (Double.parseDouble(amountttt)>=40000&&Double.parseDouble(amountttt)<=50000) {
            advancepay=25000;
            codpay=Double.parseDouble(amountttt)-advancepay;
            advancepayment.setText(""+advancepay);
            codpaymentamount.setText(""+codpay);

        }
         else if (Double.parseDouble(amountttt)>50000) {
            advancepay=30000;
            codpay=Double.parseDouble(amountttt)-advancepay;
            advancepayment.setText(""+advancepay);
            codpaymentamount.setText(""+codpay);

        }



        //setting number of products
        noOfItems.setText(bundle.get("totalproducts").toString());

        cartcollect = (ArrayList<SingleProductModel>) bundle.get("cartproducts");

        //delivery date
        SimpleDateFormat formattedDate = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 4);  // number of days to add
        String tomorrow = (formattedDate.format(c.getTime()));
        deliveryDate.setText(tomorrow);

        mainActivityMultiLineRadioGroup.setOnCheckedChangeListener(new MultiLineRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ViewGroup group, RadioButton button) {
                payment_mode = button.getText().toString();
            }
        });

        user = session.getUserDetails();

        placed_user_name = user.get(UserSession.KEY_NAME);
        getPlaced_user_email = user.get(UserSession.KEY_EMAIL);
        getPlaced_user_mobile_no = user.get(UserSession.KEY_MOBiLE);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy-HH-mm");
        currdatetime = sdf.format(new Date());

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    public void PlaceOrder(View view) {

        if (TextUtils.isEmpty(amountddd.getText().toString())||TextUtils.isEmpty(ordername.getText().toString()) ||
                TextUtils.isEmpty(ordernumber.getText().toString()) ||
                TextUtils.isEmpty(orderaddress.getText().toString())||
                TextUtils.isEmpty(payment_number.getText().toString())||   TextUtils.isEmpty(yourpay.getText().toString())
                ||  TextUtils.isEmpty(transcstion.getText().toString())) {
            Toasty.error(getApplicationContext(), "Fill up all requirments", Toast.LENGTH_SHORT, true).show();
        } else {


            order_reference_id = "ORD-" + getRandomString(MAXLEN) + "-Beta";
            orderDateTime = new SimpleDateFormat("dd.MMM.yyyy-HH.mm.ss").format(new Date());

            for (SingleProductModel singleProductModel : cartcollect) {
                placed_order_images.add(singleProductModel.getPrimage());
            }

            if (payment_mode.equals("Bkash")||payment_mode.equals("Rocket")||payment_mode.equals("Nagad")) {
                final KProgressHUD progressDialog = KProgressHUD.create(CheckoutActivity.this)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setLabel("Placing Order. Please Wait...")
                        .setCancellable(false)
                        .setAnimationSpeed(2)
                        .setDimAmount(0.5f)
                        .show();




                goingAddrss();
                Class_Model class_model=new Class_Model(payment_number.getText().toString(),yourpay.getText().toString()
                        ,amountddd.getText().toString(),codpaymentamount.getText().toString());
                //adding user details to the database under orders table
                firebaseFirestore.collection("Orders").document(getPlaced_user_email)
                        .collection(user.get(UserSession.KEY_NAME) + " Orders").document(currdatetime)
                        .set(class_model)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });
                firebaseFirestore.collection("Orders_payment").document(firebaseAuth.getCurrentUser().getEmail())
                        .collection("List").document(order_reference_id)
                        .set(class_model)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });
                UserOrdermOdel userOrdermOdel=new UserOrdermOdel(ordername.getText().toString(),orderaddress.getText().toString()+""+orderaddress2.getText().toString(),ordernumber.getText().toString(),""+orderDateTime,""+deliveryDate.getText().toString(),firebaseAuth.getCurrentUser().getEmail(),UUID.randomUUID().toString(),""+order_reference_id);
                firebaseFirestore.collection("OrderDetails")
                        .document(firebaseAuth.getCurrentUser().getEmail())
                        .collection("List")
                        .document(""+order_reference_id)
                        .set(userOrdermOdel)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });
                firebaseFirestore.collection("Orders").document(getPlaced_user_email)
                        .collection(user.get(UserSession.KEY_NAME) + " Orders").document(currdatetime)
                        .set(getProductObject()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            firebaseFirestore.collection("Orders").
                                    document("Lost")
                                    .collection("List")
                                    .document(currdatetime+""+order_reference_id)
                                    .set(getProductObject())
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                        }
                                    });


                            firebaseFirestore.collection("Orders_Check").document("01593838386@gmail.com")
                                    .collection("Admin_Check").document(currdatetime)
                                    .set(getProductObject()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        for (final SingleProductModel model : cartcollect) {
                                            String uuid= UUID.randomUUID().toString();
                                            Long tsLong = System.currentTimeMillis() / 1000;
                                            String     ts = tsLong.toString();



                                            firebaseFirestore.collection("Orders").document(getPlaced_user_email)
                                                    .collection(user.get(UserSession.KEY_NAME) + " Orders")
                                                    .document(currdatetime).collection("Items").document(uuid).set(model)
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()) {
                                                                firebaseFirestore.collection("Orders_Check").document("01880921069@gmail.com")
                                                                        .collection("Admin_Check")
                                                                        .document(currdatetime).collection("Items").add(model)
                                                                        .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                                                            @Override
                                                                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                                                                if (task.isSuccessful()) {
                                                                                    Toasty.success(CheckoutActivity.this, "Order Placed Successfully", 2000).show();
                                                                                    Intent intent = new Intent(CheckoutActivity.this, OrderPlacedActivity.class);
                                                                                    intent.putExtra("orderid", order_reference_id);
                                                                                    intent.putExtra("custid", mAuth.getCurrentUser().getUid());
                                                                                    intent.putExtra("amount_to_pay", bundle.get("totalprice").toString());
                                                                                    startActivity(intent);
                                                                                    finish();
                                                                                }

                                                                            }
                                                                        });
                                                            }
                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    progressDialog.dismiss();
                                                    Toasty.warning(CheckoutActivity.this, "Server down! Please contact Administrator.", 2000).show();
                                                    Log.d(TAG, "Error: " + e.getMessage());
                                                }
                                            });
                                        }

                                        for (final SingleProductModel model : cartcollect) {
                                            firebaseFirestore.collection("Cart").document(getPlaced_user_email)
                                                    .collection(user.get(UserSession.KEY_NAME) + " Cart").document(String.valueOf(model.getPrid()))
                                                    .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Log.d(TAG, "Model: " + model.getPrname() + " Deleted");
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Log.d(TAG, "Deleting Error: " + e.getMessage());
                                                }
                                            });


                                        }
                                        session.setCartValue(0);
                                        progressDialog.dismiss();


                                    }


                                }
                            });

                        }

                    }
                })


                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.dismiss();
                                Toasty.warning(CheckoutActivity.this, "Server down! Please contact Administrator.", 2000).show();
                                Log.d(TAG, "Placed Order Model Error: " + e.getMessage());
                            }
                        });


            } else if (payment_mode.equals("Paytm")) {
                //Toast.makeText(this, "Feature to be added soon!", Toast.LENGTH_SHORT).show();

            } else {
                //Toast.makeText(this, "Feature to be added soon!", Toast.LENGTH_SHORT).show();
            }






        }
    }

    private void goingAddrss() {
        Map<String, String> userMap2 = new HashMap<>();

        userMap2.put("address",orderaddress.getText().toString());
        firebaseFirestore.collection("Address")
                .document(mAuth.getCurrentUser().getEmail())
                .set(userMap2)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
    }


    public String getordernumber() {

        return currdatetime.replaceAll("-", "");
    }

    public PlacedOrderModel getProductObject() {
        Long tsLong = System.currentTimeMillis() / 1000;
        String  ts = tsLong.toString();

        return new PlacedOrderModel(placed_order_images, "ORDER_PLACED", order_reference_id, orderDateTime, noOfItems.getText().toString(), totalAmount.getText().toString(), deliveryDate.getText().toString(), payment_mode, ordername.getText().toString(), ordernumber.getText().toString(), orderaddress.getText().toString(), placed_user_name, firebaseAuth.getCurrentUser().getEmail(), getPlaced_user_mobile_no, currdatetime, ts);
    }

    private static String getRandomString(final int sizeOfRandomString) {
        final Random random = new Random();
        final StringBuilder sb = new StringBuilder(sizeOfRandomString);
        for (int i = 0; i < sizeOfRandomString; ++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.spinner3) {
            valueFromSpinner = parent.getItemAtPosition(position).toString();
            if (valueFromSpinner.contains("Bkash")) {
                firebaseFirestore.collection("Payment")
                        .document("abc@gmail.com")
                        .collection("1")
                        .document("abc@gmail.com")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    if (task.getResult().exists()) {
                                        try {
                                            payment_mode="Bkash";
                                            payment_number.setText(task.getResult().getString("number"));
                                            String text = task.getResult().getString("number");
                                            myClip = ClipData.newPlainText("text", text);
                                            myClipboard.setPrimaryClip(myClip);
                                            Toast.makeText(getApplicationContext(), "Address Copied",
                                                    Toast.LENGTH_SHORT).show();
                                        }catch (Exception e) {
                                            payment_mode="Bkash";
                                            payment_number.setText(task.getResult().getString("number"));
                                            String text = task.getResult().getString("number");
                                            myClip = ClipData.newPlainText("text", text);
                                            myClipboard.setPrimaryClip(myClip);
                                            Toast.makeText(getApplicationContext(), "Address Copied",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    else {

                                        payment_number.setText("No Number Found");
                                    }
                                }
                                else {

                                    payment_number.setText("No Number Found");
                                }
                            }
                        });
                // spinner4.setText("Call Admin");
            }  else if (valueFromSpinner.contains("Nagad")) {
                firebaseFirestore.collection("Payment")
                        .document("abc@gmail.com")
                        .collection("3")
                        .document("abc@gmail.com")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    if (task.getResult().exists()) {

                                        try {
                                            payment_mode="Nagad";
                                            payment_number.setText(task.getResult().getString("number"));
                                            String text = task.getResult().getString("number");
                                            myClip = ClipData.newPlainText("text", text);
                                            myClipboard.setPrimaryClip(myClip);
                                            Toast.makeText(getApplicationContext(), "Address Copied",
                                                    Toast.LENGTH_SHORT).show();
                                        }catch (Exception e) {
                                            payment_mode="Nagad";
                                            payment_number.setText(task.getResult().getString("number"));
                                            String text = task.getResult().getString("number");
                                            myClip = ClipData.newPlainText("text", text);
                                            myClipboard.setPrimaryClip(myClip);
                                            Toast.makeText(getApplicationContext(), "Address Copied",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    else {
                                        payment_number.setText("No Number Found");
                                    }
                                }
                                else {
                                    payment_number.setText("No Number Found");
                                }
                            }
                        });
            }
            else if (valueFromSpinner.contains("Rocket")) {
                firebaseFirestore.collection("Payment")
                        .document("abc@gmail.com")
                        .collection("2")
                        .document("abc@gmail.com")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    if (task.getResult().exists()) {

                                        try { payment_mode="Rocket";

                                            payment_number.setText(task.getResult().getString("number"));
                                            String text = task.getResult().getString("number");
                                            myClip = ClipData.newPlainText("text", text);
                                            myClipboard.setPrimaryClip(myClip);
                                            Toast.makeText(getApplicationContext(), "Address Copied",
                                                    Toast.LENGTH_SHORT).show();
                                        }catch (Exception e) {
                                            payment_mode="Rocket";
                                            payment_number.setText(task.getResult().getString("number"));
                                            String text = task.getResult().getString("number");
                                            myClip = ClipData.newPlainText("text", text);
                                            myClipboard.setPrimaryClip(myClip);
                                            Toast.makeText(getApplicationContext(), "Address Copied",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    else {
                                        payment_number.setText("No Number Found");
                                    }
                                }
                                else {
                                    payment_number.setText("No Number Found");
                                }
                            }
                        });
            }


        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}