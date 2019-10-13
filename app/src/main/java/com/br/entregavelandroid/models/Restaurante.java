package com.br.entregavelandroid.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Restaurante implements Parcelable {

    private String nome;
    private String endereco;
    private String horario;
    private int fotoRestaurante;
    private List<Receita> listaDeReceita;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getFotoRestaurante() {
        return fotoRestaurante;
    }

    public void setFotoRestaurante(int fotoRestaurante) {
        this.fotoRestaurante = fotoRestaurante;
    }

    public List<Receita> getListaDeReceita() {
        return listaDeReceita;
    }

    public void setListaDeReceita(List<Receita> listaDeReceita) {
        this.listaDeReceita = listaDeReceita;
    }

    public Restaurante() {
    }

    public Restaurante(String nome, String endereco, String horario, int fotoRestaurante, List<Receita> listaDeReceita) {
        this.nome = nome;
        this.endereco = endereco;
        this.horario = horario;
        this.fotoRestaurante = fotoRestaurante;
        this.listaDeReceita = listaDeReceita;
    }

    protected Restaurante(Parcel in) {
        nome = in.readString();
        endereco = in.readString();
        horario = in.readString();
        fotoRestaurante = in.readInt();
        listaDeReceita = in.createTypedArrayList(Receita.CREATOR);
    }

    public static final Creator<Restaurante> CREATOR = new Creator<Restaurante>() {
        @Override
        public Restaurante createFromParcel(Parcel in) {
            return new Restaurante(in);
        }

        @Override
        public Restaurante[] newArray(int size) {
            return new Restaurante[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome);
        dest.writeString(endereco);
        dest.writeString(horario);
        dest.writeInt(fotoRestaurante);
        dest.writeTypedList(listaDeReceita);
    }
}
