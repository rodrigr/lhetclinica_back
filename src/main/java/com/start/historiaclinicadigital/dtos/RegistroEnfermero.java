package com.start.historiaclinicadigital.dtos;

public class RegistroEnfermero {
    private String nombre;
    private String apellido;
    private String email;
    private int documento;
    private String password;
    private String telefono;

    public RegistroEnfermero(String nombre, String apellido, String email, int documento, String password, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.documento = documento;
        this.password = password;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean checkForNullOrEmpty(){
        return this.getNombre() == null || this.getNombre().isEmpty() ||
                this.getApellido() == null || this.getApellido().isEmpty() ||
                this.getEmail() == null || this.getEmail().isEmpty() ||
                this.getPassword() == null || this.getPassword().isEmpty() ||
                this.getDocumento() == 0;
    }
}
