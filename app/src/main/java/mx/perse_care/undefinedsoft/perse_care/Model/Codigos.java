package mx.perse_care.undefinedsoft.perse_care.Model;

public class Codigos {
    int numero;
    String codigo;

    public Codigos() {
    }

    public Codigos(int numero, String codigo) {
        this.numero = numero;
        this.codigo = codigo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
