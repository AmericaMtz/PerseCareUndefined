package mx.perse_care.undefinedsoft.perse_care.Model;

public class Personas {
    private String email;
    private String nombre;
    private String sexo;
    private String contra;
    private String codigo;
    private String CantidadDePeces;
    private String TiempoLibre;
    private String User;

    public Personas() {
    }

    public Personas(String email, String nombre, String sexo, String contra, String codigo, String cantidadDePeces, String tiempoLibre, String user) {
        this.email = email;
        this.nombre = nombre;
        this.sexo = sexo;
        this.contra = contra;
        this.codigo = codigo;
        CantidadDePeces = cantidadDePeces;
        TiempoLibre = tiempoLibre;
        User = user;
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

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCantidadDePeces() {
        return CantidadDePeces;
    }

    public void setCantidadDePeces(String cantidadDePeces) {
        CantidadDePeces = cantidadDePeces;
    }

    public String getTiempoLibre() {
        return TiempoLibre;
    }

    public void setTiempoLibre(String tiempoLibre) {
        TiempoLibre = tiempoLibre;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }
}
