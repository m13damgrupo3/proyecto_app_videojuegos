package com.example.proyecto_grupo3.modelos;

import java.util.ArrayList;

public class Comentario {
    private String idUsuario;
    private String nombreUsuario;
    private String idComentario;
    private String idVideojuego;
    private ArrayList<String> comentarios;

    public Comentario() {
        // Constructor predeterminado necesario para Firebase
        comentarios = new ArrayList<>();
    }

    /**
     * Agrega un comentario a la lista de comentarios.
     *
     * @param comentario El comentario a agregar.
     */
    public void adComentario(String comentario) {
        this.comentarios.add(comentario);
    }

    /**
     * Obtiene la lista de comentarios.
     *
     * @return La lista de comentarios.
     */
    public ArrayList<String> getComentarios() {
        return comentarios;
    }

    /**
     * Establece el ID del usuario.
     *
     * @param idUsuario El ID del usuario.
     */
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Establece el ID del comentario.
     *
     * @param idComentario El ID del comentario.
     */
    public void setIdComentario(String idComentario) {
        this.idComentario = idComentario;
    }

    /**
     * Establece el ID del videojuego asociado al comentario.
     *
     * @param idVideojuego El ID del videojuego.
     */
    public void setIdVideojuego(String idVideojuego) {
        this.idVideojuego = idVideojuego;
    }

    /**
     * Obtiene el ID del usuario.
     *
     * @return El ID del usuario.
     */
    public String getIdUsuario() {
        return idUsuario;
    }

    /**
     * Obtiene el ID del comentario.
     *
     * @return El ID del comentario.
     */
    public String getIdComentario() {
        return idComentario;
    }

    /**
     * Obtiene el ID del videojuego asociado al comentario.
     *
     * @return El ID del videojuego.
     */
    public String getIdVideojuego() {
        return idVideojuego;
    }

    /**
     * Obtiene el ID del usuario.
     *
     * @return El ID del usuario.
     */
    public String getidUsuario() {
        return idUsuario;
    }

    /**
     * Establece el ID del usuario.
     *
     * @param idUsuario El ID del usuario.
     */
    public void setidUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Establece el ID del videojuego asociado al comentario.
     *
     * @param idVideojuego El ID del videojuego.
     */
    public void setidVideojuego(String idVideojuego) {
        this.idVideojuego = idVideojuego;
    }
}
