package com.messas.kikikikikikik;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
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

public class AllCategoryAdapter extends RecyclerView.Adapter<AllCategoryAdapter.myview> {
    public List<GenericProductModel> data;
    FirebaseFirestore firebaseFirestore;
    SimpleExoPlayer simpleExoPlayer;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestoreg;
    private Animation topAnimation, bottomAnimation, startAnimation, endAnimation;
    public AllCategoryAdapter(List<GenericProductModel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public AllCategoryAdapter.myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_cardview_layout, parent, false);
        return new AllCategoryAdapter.myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllCategoryAdapter.myview holder, final int position) {
        firebaseAuth= FirebaseAuth.getInstance();
        GenericProductModel model=new GenericProductModel();
        firebaseFirestore= FirebaseFirestore.getInstance();
        ///Toast.makeText(holder.itemView.getContext(), ""+data.get(position).getCardname(), Toast.LENGTH_SHORT).show();
        holder.cardname.setText(data.get(position).getCardname());
        holder.cardprice.setText("৳ "+ data.get(position).getCardprice());
        Picasso.get().load(data.get(position).getCardimage()).into(holder.cardimage);

        Random rand = new Random();
        int value = rand.nextInt(50);
        holder.soldproducts.setText(""+value);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), IndividualProductActivity10.class);
                intent.putExtra("product",data.get(position));
                intent.putExtra("uuod",""+data.get(position).getCardid());
                //   Toast.makeText(ShoesActivity.this, ""+getItem(position).getCardid(), Toast.LENGTH_SHORT).show();
                v.getContext().startActivity(intent);
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
        TextView cardprice,soldproducts;
        View mView;

        public myview(@NonNull View itemView) {
            super(itemView);
            mView =itemView;
            cardname = itemView.findViewById(R.id.cardcategory);
            cardimage = itemView.findViewById(R.id.cardimage);
            cardprice = itemView.findViewById(R.id.cardprice);
            soldproducts=itemView.findViewById(R.id.soldproducts);

        }
    }
}