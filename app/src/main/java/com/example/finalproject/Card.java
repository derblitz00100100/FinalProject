package com.example.finalproject;

import android.os.Parcel;
import android.os.Parcelable;

public class Card implements Parcelable {
    private String name;
    private int elixir;
    private String type;
    private int arena;
    private String description;
    private String image;

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", elixir=" + elixir +
                ", type='" + type + '\'' +
                ", arena=" + arena +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getElixir() {
        return elixir;
    }

    public void setElixir(int elixir) {
        this.elixir = elixir;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getArena() {
        return arena;
    }

    public void setArena(int arena) {
        this.arena = arena;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.elixir);
        dest.writeString(this.type);
        dest.writeInt(this.arena);
        dest.writeString(this.description);
        dest.writeString(this.image);
    }

    public Card() {
    }

    protected Card(Parcel in) {
        this.name = in.readString();
        this.elixir = in.readInt();
        this.type = in.readString();
        this.arena = in.readInt();
        this.description = in.readString();
        this.image = in.readString();
    }

    public static final Creator<Card> CREATOR = new Creator<Card>() {
        @Override
        public Card createFromParcel(Parcel source) {
            return new Card(source);
        }

        @Override
        public Card[] newArray(int size) {
            return new Card[size];
        }
    };
}
