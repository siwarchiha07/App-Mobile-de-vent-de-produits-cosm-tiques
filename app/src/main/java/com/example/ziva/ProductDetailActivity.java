package com.example.ziva;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        String productName = getIntent().getStringExtra("productName");

        TextView productNameText = findViewById(R.id.productNameText);
        productNameText.setText(productName);
    }
}
