package mx.perse_care.undefinedsoft.perse_care.Model;

public class Pecera {

    private String capacidadPecera;
    private String cuentaConAdornos;
    private String porcentajePecera;

    public Pecera() {
    }

    public Pecera(String capacidadPecera, String cuentaConAdornos, String porcentajePecera) {
        this.capacidadPecera = capacidadPecera;
        this.cuentaConAdornos = cuentaConAdornos;
        this.porcentajePecera = porcentajePecera;
    }

    public String getCapacidadPecera() {
        return capacidadPecera;
    }

    public void setCapacidadPecera(String capacidadPecera) {
        this.capacidadPecera = capacidadPecera;
    }

    public String getCuentaConAdornos() {
        return cuentaConAdornos;
    }

    public void setCuentaConAdornos(String cuentaConAdornos) {
        this.cuentaConAdornos = cuentaConAdornos;
    }

    public String getPorcentajePecera() {
        return porcentajePecera;
    }

    public void setPorcentajePecera(String porcentajePecera) {
        this.porcentajePecera = porcentajePecera;
    }
}
