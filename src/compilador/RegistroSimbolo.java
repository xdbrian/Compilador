package compilador;

import ast.*;

public class RegistroSimbolo {

    private int direccionMemoria;
    private tipoVariable tipo;
    private int NumLinea;
    private int tamano;
    
    public RegistroSimbolo(tipoVariable tipo, int numLinea, int direccionMemoria, int tamano) {
        super();
        this.tipo = tipo;
        this.direccionMemoria = direccionMemoria;
        this.NumLinea = numLinea;
        this.tamano = tamano;
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

    public int getTamano() {
        return tamano;
    }        
}
