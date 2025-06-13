package com.example.ziva;


import com.example.ziva.R.*;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView;
    List<Product> productList, filteredList;
    ProductAdapter adapter;

    private DrawerLayout drawerLayout;
    private ImageView menuIcon;

    ToggleButton filterAll, filterMakeup, filterSkincare, filterBrushes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        // Initialisation du DrawerLayout et du menu
        drawerLayout = findViewById(id.drawer_layout);
        menuIcon = findViewById(id.menuIcon);

        // Toolbar pour gérer le menu
        Toolbar toolbar = findViewById(id.toolbar);
        setSupportActionBar(toolbar);

        // Ajout du bouton de bascule pour ouvrir le Drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Configuration du NavigationView
        NavigationView navigationView = findViewById(id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Gérer l'icône du menu pour ouvrir le Drawer
        menuIcon.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        // Initialisation des boutons de filtre
        initFilterButtons();

        // Initialisation de RecyclerView
        recyclerView = findViewById(id.recyclerViewProducts);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Charger les produits
        productList = new ArrayList<>();
        populateProductList();

        // Adapter
        adapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(adapter);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_favorites:
                startActivity(new Intent(this, FavoritesActivity.class));
                break;

            case R.id.nav_dashboard:
                Toast.makeText(this, "Dashboard sélectionné", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_account:
                Toast.makeText(this, "Mon Compte sélectionné", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_logout:
                Toast.makeText(this, "Déconnexion réussie", Toast.LENGTH_SHORT).show();
                break;

            default:
                Toast.makeText(this, "Option non reconnue", Toast.LENGTH_SHORT).show();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }



    private void initFilterButtons() {
        // Initialise les boutons et leur listener
        filterAll = findViewById(id.filterAll);
        filterMakeup = findViewById(id.filterMakeup);
        filterSkincare = findViewById(id.filterSkincare);
        filterBrushes = findViewById(id.filterBrushes);

        filterAll.setOnClickListener(this);
        filterMakeup.setOnClickListener(this);
        filterSkincare.setOnClickListener(this);
        filterBrushes.setOnClickListener(this);
    }

    private void populateProductList() {
        productList.add(new Product(R.drawable.gloss, "Rouge à lèvres", "Makeup"));
        productList.add(new Product(R.drawable.oill, "Fond de teint", "Makeup"));
        productList.add(new Product(R.drawable.oil, "Mascara", "Makeup"));
        productList.add(new Product(R.drawable.bronzer, "Crème hydratante", "Skincare"));
        productList.add(new Product(R.drawable.oill, "Brosse visage", "Brushes"));
        productList.add(new Product(R.drawable.oill, "Brosse visage", "Brushes"));
        productList.add(new Product(R.drawable.oill, "Brosse visage", "Brushes"));
        productList.add(new Product(R.drawable.oill, "Brosse visage", "Brushes"));
    }

    @Override
    public void onClick(View v) {
        // Réinitialiser tous les boutons
        resetAllButtons();

        // Appliquer le filtre en fonction du bouton cliqué
        if (v.getId() == id.filterAll) {
            updateSelectedButton(filterAll);
            adapter.setFilteredList(productList);
        } else if (v.getId() == id.filterMakeup) {
            updateSelectedButton(filterMakeup);
            adapter.setFilteredList(filterByCategory("Makeup"));
        } else if (v.getId() == id.filterSkincare) {
            updateSelectedButton(filterSkincare);
            adapter.setFilteredList(filterByCategory("Skincare"));
        } else if (v.getId() == id.filterBrushes) {
            updateSelectedButton(filterBrushes);
            adapter.setFilteredList(filterByCategory("Brushes"));
        }
    }

    private List<Product> filterByCategory(String category) {
        // Filtrer les produits par catégorie
        filteredList = new ArrayList<>();
        for (Product product : productList) {
            if (product.getCategory().equals(category)) {
                filteredList.add(product);
            }
        }
        return filteredList;
    }

    private void updateSelectedButton(ToggleButton selectedButton) {
        selectedButton.setChecked(true);
        selectedButton.setBackgroundResource(R.drawable.button_selected_bg);
        selectedButton.setTextColor(getResources().getColor(R.color.white));
    }

    private void resetAllButtons() {
        // Réinitialiser le style des boutons
        ToggleButton[] buttons = {filterAll, filterMakeup, filterSkincare, filterBrushes};
        for (ToggleButton button : buttons) {
            button.setChecked(false);
            button.setBackgroundResource(R.drawable.button_unselected_bg);
            button.setTextColor(getResources().getColor(R.color.black));
        }
    }
}
