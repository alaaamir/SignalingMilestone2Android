package com.example.shoppingapp;



import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import app.ProductItem;

import static android.provider.AlarmClock.EXTRA_MESSAGE;


public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.CustomViewHolder> {
    private List<ProductItem> productItemList;
    private Context context;
    public ProductRecyclerViewAdapter(Context context, List<ProductItem> productItemList) {
        this.productItemList = productItemList;
        this.context = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_product_item, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        final ProductItem productItem = productItemList.get(i);
        customViewHolder.productName.setText(productItem.getProduct_name());
        customViewHolder.productDescription.setText(productItem.getDescription());
        //Download image using picasso library
        if (!TextUtils.isEmpty(productItem.getImageUrl())) {



            Picasso.get()
                    .load(productItem.getImageUrl())
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(customViewHolder.productImage);
        }

        customViewHolder.productItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShopsActivity.class);
                int message = productItem.getId();
                intent.putExtra(EXTRA_MESSAGE, message);
                context.startActivity(intent);
            }
        });
        //Setting text view title
//        customViewHolder.textView.setText(Html.fromHtml(feedItem.getTitle()));



//        customViewHolder.imageView.setOnClickListener(listener);
//        customViewHolder.textView.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return (null != productItemList ? productItemList.size() : 0);
    }


    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView productImage;
        protected TextView productName;
        protected TextView productDescription;
        protected LinearLayout productItem;


        public CustomViewHolder(View view) {
            super(view);
            this.productItem = view.findViewById(R.id.productItem);
            this.productImage = (ImageView) view.findViewById(R.id.product_image_product);
            this.productName = (TextView) view.findViewById(R.id.product_name_product);
            this.productDescription = (TextView) view.findViewById(R.id.product_description_product);


        }
    }


//    public OnItemClickListener getOnItemClickListener() {
//        return onItemClickListener;
//    }
//
//    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
//        this.onItemClickListener = onItemClickListener;
//    }
}