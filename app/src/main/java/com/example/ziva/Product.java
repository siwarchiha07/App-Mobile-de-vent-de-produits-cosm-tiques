package com.example.ziva;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    private int imageResource;
    private String name;
    private String category;
    private boolean isFavorite;

    public Product(int imageResource, String name, String category) {
        this.imageResource = imageResource;
        this.name = name;
        this.category = category;
        this.isFavorite = false;
    }

    // Getters and Setters
    public int getImageResource() { return imageResource; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public boolean isFavorite() { return isFavorite; }
    public void setFavorite(boolean favorite) { isFavorite = favorite; }

    // Parcelable Implementation
    protected Product(Parcel in) {
        imageResource = in.readInt();
        name = in.readString();
        category = in.readString();
        isFavorite = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageResource);
        dest.writeString(name);
        dest.writeString(category);
        dest.writeByte((byte) (isFavorite ? 1 : 0));
    }

    @Override
    public int describeContents() { return 0; }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
