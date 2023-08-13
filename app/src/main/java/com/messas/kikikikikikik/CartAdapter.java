package com.messas.kikikikikikik;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.messas.starlifeuser_user_user.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.myview> {
    public List<SingleProductModel> data;
    FirebaseFirestore firebaseFirestore;

    public CartAdapter(List<SingleProductModel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public CartAdapter.myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout, parent, false);
        return new CartAdapter.myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.myview holder, final int position) {

        holder.cardname.setText(data.get(position).getPrname());
        holder.cardprice.setText("৳ "+data.get(position).getPrprice());
        holder.cardcount.setText("Quantity : "+data.get(position).getNo_of_items());
        holder.totalCardAmt.setText("৳ "+data.get(position).getPrprice()+" x "+data.get(position).getNo_of_items()+" = ৳ "+(Float.valueOf(data.get(position).getPrprice())*data.get(position).getNo_of_items()));
        Picasso.get().load(data.get(position).getPrimage()).into(holder.cardimage);
        holder.carddelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myview extends RecyclerView.ViewHolder {
        TextView cardname;
        ImageView cardimage;
        TextView cardprice;
        TextView cardcount;
        ImageView carddelete;
        TextView totalCardAmt;
        LinearLayout linearlayouttt;
        TextView deliverycharge;

        View mView;

        public myview(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            cardname = itemView.findViewById(R.id.cart_prtitle);
            cardimage = itemView.findViewById(R.id.image_cartlist);
            cardprice = itemView.findViewById(R.id.cart_prprice);
            cardcount = itemView.findViewById(R.id.cart_prcount);
            carddelete = itemView.findViewById(R.id.deletecard);
            totalCardAmt = itemView.findViewById(R.id.total_card_amount);
            linearlayouttt=itemView.findViewById(R.id.linearlayouttt);
            deliverycharge=itemView.findViewById(R.id.deliverycharge);

        }
    }
}