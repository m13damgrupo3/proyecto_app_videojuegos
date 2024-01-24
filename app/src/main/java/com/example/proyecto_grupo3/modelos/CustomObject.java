package com.example.proyecto_grupo3.modelos;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class CustomObject implements Parcelable {
    private String Nombre;        // Nombre del videojuego
    private String Genero;        // Género del videojuego
    private String Descripcion;   // Descripción del videojuego
    private String Tipo;           // Tipo de videojuego

    private String idVideojuego;  // Identificador único del videojuego
    private String ImageUrl1;     // URL de la imagen principal del videojuego
    private String ImageUrl2;     // URL de una imagen adicional del videojuego
    private String ImageUrl3;     // URL de otra imagen adicional del videojuego
    private String ImageUrl4;     // URL de una cuarta imagen adicional del videojuego

    // Constructor predeterminado requerido para Firebase
    public CustomObject() {
    }

    // Constructor que toma los detalles del videojuego como parámetros
    public CustomObject(String descripcion, String Genero, String nombre, String imageUrl1, String imageUrl2, String tipo, String idVideojuego) {
        this.Descripcion = descripcion;
        this.Nombre = nombre;
        this.ImageUrl1 = imageUrl1;
        this.ImageUrl2 = imageUrl2;
        this.Tipo = tipo;
        this.idVideojuego = idVideojuego;
        this.Genero = Genero;
    }

    // Constructor de Parcelable utilizado para la creación de objetos desde Parcel
    protected CustomObject(Parcel in) {
        Nombre = in.readString();
        Genero = in.readString();
        Descripcion = in.readString();
        Tipo = in.readString();
        idVideojuego = in.readString();
        ImageUrl1 = in.readString();
        ImageUrl2 = in.readString();
        ImageUrl3 = in.readString();
        ImageUrl4 = in.readString();
    }

    // Implementación de Parcelable.Creator para crear instancias de CustomObject desde Parcel
    public static final Creator<CustomObject> CREATOR = new Creator<CustomObject>() {
        @Override
        public CustomObject createFromParcel(Parcel in) {
            return new CustomObject(in);
        }

        @Override
        public CustomObject[] newArray(int size) {
            return new CustomObject[size];
        }
    };

    // Métodos getter y setter para acceder a los atributos privados
    public void setGenero(String genero) {
        Genero = genero;
    }

    public void setIdVideojuego(String idVideojuego) {
        this.idVideojuego = idVideojuego;
    }

    public String getGenero() {
        return Genero;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public String getIdVideojuego() {
        return idVideojuego;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getImageUrl1() {
        return ImageUrl1;
    }

    public String getImageUrl2() {
        return ImageUrl2;
    }

    public String getImageUrl3() {
        return ImageUrl3;
    }

    public String getImageUrl4() {
        return ImageUrl4;
    }

    public String getDescription() {
        return Descripcion;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public void setImageUrl1(String imageUrl1) {
        this.ImageUrl1 = imageUrl1;
    }

    public void setImageUrl2(String imageUrl2) {
        this.ImageUrl2 = imageUrl2;
    }

    public void setImageUrl3(String imageUrl3) {
        this.ImageUrl3 = imageUrl3;
    }

    public void setImageUrl4(String imageUrl4) {
        this.ImageUrl4 = imageUrl4;
    }

    public void setDescripcion(String descripcion) {
        this.Descripcion = descripcion;
    }

    public void setTipo(String tipo) {
        this.Tipo = tipo;
    }

    // Métodos requeridos para Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(Nombre);
        dest.writeString(Genero);
        dest.writeString(Descripcion);
        dest.writeString(Tipo);
        dest.writeString(idVideojuego);
        dest.writeString(ImageUrl1);
        dest.writeString(ImageUrl2);
        dest.writeString(ImageUrl3);
        dest.writeString(ImageUrl4);
    }
}
