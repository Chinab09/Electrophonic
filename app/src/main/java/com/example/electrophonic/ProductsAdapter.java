package com.example.electrophonic;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductsAdapter  extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {
    Activity activity; ArrayList<Product> arrayList;
    public ProductsAdapter(Activity activity, ArrayList<Product> arrayList) {
        this.activity = activity;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(activity.getLayoutInflater().inflate(R.layout.row_product,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Product u = arrayList.get(position);
        holder.name.setText(u.getName());
        holder.qty.setText(u.getQty());
        holder.price.setText(u.getPrice());
        holder.description.setText(u.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.startActivity(
                        new Intent(activity,UpdateActivity.class)
                                .putExtra("product",u)
                );
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,qty,price,description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.name);
            qty=  itemView.findViewById(R.id.qty);
            price=  itemView.findViewById(R.id.price);
            description=  itemView.findViewById(R.id.description);
        }
    }
}
