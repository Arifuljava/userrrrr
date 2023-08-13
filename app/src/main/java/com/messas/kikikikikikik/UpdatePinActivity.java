package com.messas.kikikikikikik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.messas.starlifeuser_user_user.R;

import es.dmoral.toasty.Toasty;

public class UpdatePinActivity extends AppCompatActivity {

    EditText spinner1,transcationpin;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    TextView textammount,showing;
    Button cirLoginButton;
    KProgressHUD kProgressHUD;
    String check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pin);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Update password");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(10.0f);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_myarrow);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_myarrow);
        getSupportActionBar().setElevation(10.0f);
        getSupportActionBar().setElevation(10.0f);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        spinner1=findViewById(R.id.spinner1);
        textammount=findViewById(R.id.textammount);
        transcationpin=findViewById(R.id.transcationpin);
        cirLoginButton=findViewById(R.id.cirLoginButton);
        showing=findViewById(R.id.showing);
        spinner1.addTextChangedListener(nameWatcher);
        kProgressHUD=KProgressHUD.create(UpdatePinActivity.this);
        cirLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ammount=spinner1.getText().toString().toLowerCase();
                String pin=transcationpin.getText().toString().toLowerCase();
                if (TextUtils.isEmpty(ammount)||TextUtils.isEmpty(pin)) {
                    Toasty.error(getApplicationContext(), "Error give right information.", Toast.LENGTH_SHORT, true).show();
                    return;
                }
                else {
                    progress_check();
                    firebaseFirestore.collection("Password")
                            .document(firebaseAuth.getCurrentUser().getEmail())
                            .update("uuid",pin.toLowerCase().toString())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        firebaseFirestore.collection("Users")
                                                .document(firebaseAuth.getCurrentUser().getUid())
                                                .update("username",pin.toLowerCase().toString())
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            kProgressHUD.dismiss();
                                                            firebaseAuth.signOut();
                                                            AlertDialog.Builder builder=new AlertDialog.Builder(UpdatePinActivity.this);
                                                            builder .setTitle("Confirmation")
                                                                    .setMessage("Password upgrading   is successfully done.")
                                                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                                        @Override
                                                                        public void onClick(DialogInterface dialog, int which) {
                                                                            dialog.dismiss();
                                                                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                                                        }
                                                                    }).create();
                                                            builder.show();

                                                        }
                                                    }
                                                });

                                    }
                                }
                            });
                }
            }
        });
    }
    private void progress_check() {
        kProgressHUD = KProgressHUD.create(UpdatePinActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Uploading  Data")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();

    }
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),HomeACTIVITY.class));
    }

    @Override
    public boolean onNavigateUp() {
        startActivity(new Intent(getApplicationContext(),HomeACTIVITY.class));
        return true;
    }
    TextWatcher nameWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //none
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //none
        }

        @Override
        public void afterTextChanged(Editable s) {

            check = s.toString();
            textammount.setVisibility(View.VISIBLE);
            firebaseFirestore.collection("Password")
                    .document(firebaseAuth.getCurrentUser().getEmail())
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                if (task.getResult().exists()) {
                                    String uuid=task.getResult().getString("uuid");
                                    if (check.toLowerCase().toString().equals(uuid)) {
                                        textammount.setText("Old password is corrent");
                                        cirLoginButton.setEnabled(true);

                                    }
                                    else {
                                        textammount.setText("Old password is incorrent");
                                        cirLoginButton.setEnabled(false);
                                    }
                                }
                                else {
                                    textammount.setText("Old password is incorrent");
                                    cirLoginButton.setEnabled(false);
                                }
                            }
                            else {
                                textammount.setText("Old password is incorrent");
                                cirLoginButton.setEnabled(false);
                            }
                        }
                    });
        }

    };
}
