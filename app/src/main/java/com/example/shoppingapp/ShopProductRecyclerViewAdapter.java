package com.example.shoppingapp;



import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

import app.ProductItem;
import app.ShopProductItem;

import static android.provider.AlarmClock.EXTRA_MESSAGE;


public class ShopProductRecyclerViewAdapter extends RecyclerView.Adapter<ShopProductRecyclerViewAdapter.CustomViewHolder> {
    private List<ShopProductItem> shopProductItems;
    private Context context;
    public ShopProductRecyclerViewAdapter(Context context, List<ShopProductItem> shopProductItems) {
        this.shopProductItems = shopProductItems;
        this.context = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_shop_product_item, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        final ShopProductItem shopProductItem = shopProductItems.get(i);
        customViewHolder.shopName.setText(shopProductItem.getShop_name());
        customViewHolder.price.setText("Price: "+shopProductItem.getProduct_price()+"");
        customViewHolder.specialOffer.setText(shopProductItem.getSpecial_offers().isEmpty() ? "No Special Offer" : shopProductItem.getSpecial_offers() );
        customViewHolder.shopProductItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "http://maps.google.com/maps?&daddr=" + shopProductItem.getLatitude() + "," + shopProductItem.getLongitude();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setPackage("com.google.android.apps.maps");
                context.startActivity(intent);
            }
        });


//        customViewHolder.productItem.setOnClickListener(new View.OnClickListe     ner() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, ShopsActivity.class);
//                int message = productItem.getId();
//                intent.putExtra(EXTRA_MESSAGE, message);
//                context.startActivity(intent);
//            }
//        });
        //Setting text view title
//        customViewHolder.textView.setText(Html.fromHtml(feedItem.getTitle()));



//        customViewHolder.imageView.setOnClickListener(listener);
//        customViewHolder.textView.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return (null != shopProductItems ? shopProductItems.size() : 0);
    }


    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView shopName;
        protected  TextView price;
        protected TextView specialOffer;
        protected LinearLayout shopProductItem;
        protected ImageView shopProductImage;
        public CustomViewHolder(View view) {
            super(view);
            this.shopName = view.findViewById(R.id.shopProductItemShopName);
            this.price = view.findViewById(R.id.shopProductPrice);
            this.specialOffer = view.findViewById(R.id.shopProductSpecialOffer);
            this.shopProductItem = view.findViewById(R.id.shopProductItem);
//            this.shopLocation = (Button) view.findViewById(R.id.shopLoc_shop);


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