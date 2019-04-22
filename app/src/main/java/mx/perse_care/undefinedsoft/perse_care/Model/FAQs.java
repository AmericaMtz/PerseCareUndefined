package mx.perse_care.undefinedsoft.perse_care.Model;

public class FAQs {
    private String pregunta, respuesta;
    private int position;

    public FAQs(String pregunta, String respuesta, int position) {
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.position = position;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public FAQs() {
    }
}
