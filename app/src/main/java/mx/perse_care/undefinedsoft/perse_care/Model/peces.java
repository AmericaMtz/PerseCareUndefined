package mx.perse_care.undefinedsoft.perse_care.Model;

public class peces {
    private String TipoAguaDePeces;
    private String nombre;
    private String especie;


    public peces(String tipoAguaDePeces, String nombre, String especie) {
        TipoAguaDePeces = tipoAguaDePeces;
        this.nombre = nombre;
        this.especie = especie;
    }

    public peces(){
    }

    public String getTipoAguaDePeces() {
        return TipoAguaDePeces;
    }

    public void setTipoAguaDePeces(String tipoAguaDePeces) {
        TipoAguaDePeces = tipoAguaDePeces;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }
}
