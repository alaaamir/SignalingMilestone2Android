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


public class ProductsActivity extends AppCompatActivity {
    private List<ProductItem> productsList;
    private RecyclerView productRecyclerView;
    private ProductRecyclerViewAdapter productsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
;;        setContentView(R.layout.activity_products);
        productRecyclerView = findViewById(R.id.productsRecycler);
        productRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        getProducts();
    }

    private void getProducts() {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://172.20.10.5:8080/android/include/product.php";

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


            JSONArray jsonarray = new JSONArray(result);
            productsList = new ArrayList<>();


            System.out.println("xddd"+jsonarray.toString());
            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);
                System.out.println("mmm"+jsonobject.toString());

                ProductItem item = new ProductItem();
                item.setProduct_name(jsonobject.getString("product_name"));
                item.setDescription(jsonobject.getString("description"));
                item.setId(jsonobject.getInt("product_ID"));
                item.setImageUrl(jsonobject.getString("image_url"));
                productsList.add(item);
            }

            System.out.println("lolol");
            productsAdapter = new ProductRecyclerViewAdapter(ProductsActivity.this, productsList);
            productRecyclerView.setAdapter(productsAdapter);

        } catch ( JSONException e) {
            e.printStackTrace();
        }
    }


}