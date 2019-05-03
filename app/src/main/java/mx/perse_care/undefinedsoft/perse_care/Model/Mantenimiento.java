package mx.perse_care.undefinedsoft.perse_care.Model;

public class Mantenimiento {
String fecha;
String nompreProgramador;
String problema;
String tipo;
int position;

    public Mantenimiento() {
    }

    public Mantenimiento(String fecha, String nompreProgramador, String problema, String tipo, int position) {
        this.fecha = fecha;
        this.nompreProgramador = nompreProgramador;
        this.problema = problema;
        this.tipo = tipo;
        this.position = position;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNompreProgramador() {
        return nompreProgramador;
    }

    public void setNompreProgramador(String nompreProgramador) {
        this.nompreProgramador = nompreProgramador;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
