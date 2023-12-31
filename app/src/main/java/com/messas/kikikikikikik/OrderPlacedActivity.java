package com.messas.kikikikikikik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.messas.starlifeuser_user_user.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderPlacedActivity extends AppCompatActivity {

    @BindView(R.id.orderid)
    TextView orderidview;
    private String orderid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_placed);

        ButterKnife.bind(this);

        //check Internet Connection


        initialize();
    }

    private void initialize() {
        orderid = getIntent().getStringExtra("orderid");
        orderidview.setText(orderid);
    }

    public void finishActivity(View view) {
        Intent i = new Intent(OrderPlacedActivity.this, HomeACTIVITY.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

}