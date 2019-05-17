package mx.perse_care.undefinedsoft.perse_care.Model;

public class InfoPeces {

    private String especie;
    private String nombre;

    public InfoPeces() {
    }

    public InfoPeces( String especie, String nombre) {

        this.especie = especie;
        this.nombre = nombre;
    }


    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
