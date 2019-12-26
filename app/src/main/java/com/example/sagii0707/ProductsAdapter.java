package com.example.sagii0707;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Belal on 10/18/2017.
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {


    private Context mCtx;
    private List<Product> productList;

    public ProductsAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.product, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        final Product product = productList.get(position);

        //loading the image
        Glide.with(mCtx)
                .load("http://treetime.tk/upload/"+product.getImage())
                .into(holder.imageView);

        holder.Garchig.setText(product.getEnglish());
        holder.Duuchin.setText(product.getMongolian());


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView Garchig, Duuchin;
        ImageView imageView;
        RelativeLayout relativeLayout;

        public ProductViewHolder(View itemView) {
            super(itemView);

            relativeLayout = itemView.findViewById(R.id.layout0707);
            Garchig = itemView.findViewById(R.id.garchig);
            Duuchin = itemView.findViewById(R.id.duuchin);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
