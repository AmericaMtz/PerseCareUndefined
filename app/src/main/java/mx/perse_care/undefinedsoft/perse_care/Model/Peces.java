package mx.perse_care.undefinedsoft.perse_care.Model;

public class Peces {
    private String TipoAguaDePeces;
    private String nombre;
    private String especie;
    private String posicion;
    private String Alimentacion;
    private String Colores;
    private String Comportamiento;
    private String Cuidados;
    private String Horariosdecomida;
    private String Longevidad;
    private String PH;
    private String Tamaño;
    private String Temperatura;
    private String Tipodeagua;

    public Peces() {

    }

    public Peces(String tipoAguaDePeces, String nombre, String especie, String posicion, String alimentacion, String colores, String comportamiento, String cuidados, String horariosdecomida, String longevidad, String PH, String tamaño, String temperatura, String tipodeagua) {
        TipoAguaDePeces = tipoAguaDePeces;
        this.nombre = nombre;
        this.especie = especie;
        this.posicion = posicion;
        Alimentacion = alimentacion;
        Colores = colores;
        Comportamiento = comportamiento;
        Cuidados = cuidados;
        Horariosdecomida = horariosdecomida;
        Longevidad = longevidad;
        this.PH = PH;
        Tamaño = tamaño;
        Temperatura = temperatura;
        Tipodeagua = tipodeagua;
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

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getAlimentacion() {
        return Alimentacion;
    }

    public void setAlimentacion(String alimentacion) {
        Alimentacion = alimentacion;
    }

    public String getColores() {
        return Colores;
    }

    public void setColores(String colores) {
        Colores = colores;
    }

    public String getComportamiento() {
        return Comportamiento;
    }

    public void setComportamiento(String comportamiento) {
        Comportamiento = comportamiento;
    }

    public String getCuidados() {
        return Cuidados;
    }

    public void setCuidados(String cuidados) {
        Cuidados = cuidados;
    }

    public String getHorariosdecomida() {
        return Horariosdecomida;
    }

    public void setHorariosdecomida(String horariosdecomida) {
        Horariosdecomida = horariosdecomida;
    }

    public String getLongevidad() {
        return Longevidad;
    }

    public void setLongevidad(String longevidad) {
        Longevidad = longevidad;
    }

    public String getPH() {
        return PH;
    }

    public void setPH(String PH) {
        this.PH = PH;
    }

    public String getTamaño() {
        return Tamaño;
    }

    public void setTamaño(String tamaño) {
        Tamaño = tamaño;
    }

    public String getTemperatura() {
        return Temperatura;
    }

    public void setTemperatura(String temperatura) {
        Temperatura = temperatura;
    }

    public String getTipodeagua() {
        return Tipodeagua;
    }

    public void setTipodeagua(String tipodeagua) {
        Tipodeagua = tipodeagua;
    }
}
