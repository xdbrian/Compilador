package compilador;

import ast.*;

public class RegistroSimbolo {

    private int direccionMemoria;
    private tipoVariable tipo;
    private int NumLinea;

    public RegistroSimbolo(tipoVariable tipo, int numLinea, int direccionMemoria) {
        super();
        this.tipo = tipo;
        this.direccionMemoria = direccionMemoria;
        this.NumLinea = numLinea;
    }

    public int getDireccionMemoria() {
        return direccionMemoria;
    }

    public int getNumLinea() {
        return NumLinea;
    }

    public tipoVariable getTipo() {
        return tipo;
    }
}
