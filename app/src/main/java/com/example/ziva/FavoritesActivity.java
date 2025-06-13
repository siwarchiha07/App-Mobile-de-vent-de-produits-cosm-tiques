package com.example.ziva;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ziva.Product;
import com.example.ziva.ProductAdapter;
import com.example.ziva.R;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ProductAdapter adapter;
    ArrayList<Product> favoritesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        // Récupération des favoris via Intent
        favoritesList = getIntent().getParcelableArrayListExtra("favorites");

        // Configuration RecyclerView
        recyclerView = findViewById(R.id.recyclerViewFavorites);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        if (favoritesList != null && !favoritesList.isEmpty()) {
            adapter = new ProductAdapter(this, favoritesList);
            recyclerView.setAdapter(adapter);
        } else {
            Toast.makeText(this, "Aucun produit favori", Toast.LENGTH_SHORT).show();
        }
    }
}
