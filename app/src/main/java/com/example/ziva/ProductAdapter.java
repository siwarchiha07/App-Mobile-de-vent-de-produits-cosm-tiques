package com.example.ziva;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;
    private List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate le layout item_product
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        // Afficher l'image et le nom du produit
        holder.productImage.setImageResource(product.getImageResource());
        holder.productName.setText(product.getName());

        // Mettre à jour l'icône étoile selon l'état actuel
        updateFavoriteIcon(holder.favoriteButton, product.isFavorite());

        // Gestion du clic sur l'icône étoile
        holder.favoriteButton.setOnClickListener(v -> {
            // Basculer l'état favori du produit
            product.setFavorite(!product.isFavorite());

            // Mettre à jour l'icône d'affichage
            updateFavoriteIcon(holder.favoriteButton, product.isFavorite());
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    // Méthode pour mettre à jour la liste filtrée
    public void setFilteredList(List<Product> filteredList) {
        this.productList = filteredList;
        notifyDataSetChanged();
    }

    // Mettre à jour l'icône étoile en fonction de l'état favori
    private void updateFavoriteIcon(ImageView favoriteButton, boolean isFavorite) {
        favoriteButton.setImageResource(
                isFavorite ? android.R.drawable.star_big_on : android.R.drawable.star_big_off
        );
    }

    // ViewHolder pour contenir les vues de l'item
    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage, favoriteButton;
        TextView productName;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.productImage);
            favoriteButton = itemView.findViewById(R.id.favoriteButton);
            productName = itemView.findViewById(R.id.productName);
        }
    }
}
