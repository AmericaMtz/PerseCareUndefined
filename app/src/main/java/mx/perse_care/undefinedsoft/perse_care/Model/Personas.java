package mx.perse_care.undefinedsoft.perse_care.Model;

public class Personas {
    private String email;
    private String nombre;
    private String sexo;

    public Personas(String email, String nombre, String sexo) {
        this.email = email;
        this.nombre = nombre;
        this.sexo = sexo;
    }
    public Personas (){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
