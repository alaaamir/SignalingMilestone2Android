package com.example.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import app.ProductItem;
import app.ShopItem;
import app.ShopProductItem;

import android.os.Bundle;

public class ShopsActivity extends AppCompatActivity {
    private List<ShopProductItem> shopsList;
    private RecyclerView shopRecyclerView;
    private ShopProductRecyclerViewAdapter shopsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);
        shopRecyclerView = findViewById(R.id.shopsRecycler);
        shopRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        getShops();
    }

    private void getShops() {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://172.20.10.5:8080/android/include/shop_product.php?id=1";
// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    // Display the first 500 characters of the response string.
                    System.out.println("Meaw");
                    System.out.println(response.toString());
                    parseResult(response.toString());

                }, error -> Toast.makeText(getApplicationContext(), "Failed fetching products", Toast.LENGTH_SHORT));

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
    private void parseResult(String result) {
        try {

            JSONObject jsonObject = new JSONObject(result);
            JSONArray products = (jsonObject.getJSONArray("product"));
            JSONArray shops =  jsonObject.getJSONArray("shops");
            shopsList = new ArrayList<>();
            System.out.println("xddd"+products.toString());
            for (int i = 0; i < products.length(); i++) {
                JSONObject productObj = products.getJSONObject(i);
                JSONObject shopObj = shops.getJSONObject(i);

                ShopProductItem item = new ShopProductItem();
                item.setId(productObj.getInt("shop_product_id"));
                item.setProduct_price(productObj.getInt("price"));
                item.setSpecial_offers(productObj.getString("special_offers"));
                item.setShop_name(shopObj.getString("shop_name"));
                item.setLatitude(shopObj.getString("latitude"));
                item.setLongitude(shopObj.getString("longitude"));


                shopsList.add(item);
                System.out.println("LAY LAY LAY"+item.getShop_name());
            }

            System.out.println("lolol");
            shopsAdapter = new ShopProductRecyclerViewAdapter(ShopsActivity.this, shopsList);
            System.out.println("XDDAS");
            shopRecyclerView.setAdapter(shopsAdapter);
            System.out.println("MFROD 5LS");
        } catch ( JSONException e) {
            e.printStackTrace();
        }
    }
}