package com.br.entregavelandroid.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Receita implements Parcelable {

    private String nomePrato;
    private int fotoPrato;
    private String inforPrato;

    public Receita() {
    }

    public Receita(String nomePrato, int fotoPrato, String inforPrato) {
        this.nomePrato = nomePrato;
        this.fotoPrato = fotoPrato;
        this.inforPrato = inforPrato;
    }

    public String getNomePrato() {
        return nomePrato;
    }

    public void setNomePrato(String nomePrato) {
        this.nomePrato = nomePrato;
    }

    public int getFotoPrato() {
        return fotoPrato;
    }

    public void setFotoPrato(int fotoPrato) {
        this.fotoPrato = fotoPrato;
    }

    public String getInforPrato() {
        return inforPrato;
    }

    public void setInforPrato(String inforPrato) {
        this.inforPrato = inforPrato;
    }

    protected Receita(Parcel in) {
        nomePrato = in.readString();
        fotoPrato = in.readInt();
        inforPrato = in.readString();
    }

    public static final Creator<Receita> CREATOR = new Creator<Receita>() {
        @Override
        public Receita createFromParcel(Parcel in) {
            return new Receita(in);
        }

        @Override
        public Receita[] newArray(int size) {
            return new Receita[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nomePrato);
        dest.writeInt(fotoPrato);
        dest.writeString(inforPrato);
    }
}
