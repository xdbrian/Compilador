package compilador;

import ast.*;
import java.util.*;

public class RegistroFuncion {

    private tipoVariable tipoRetorno;
    private LinkedHashMap<String, RegistroSimbolo> parametros;
    private HashMap<String, RegistroSimbolo> tabla;
    private int direccionMemoria;    

    public RegistroFuncion(tipoVariable tipoRetorno, LinkedHashMap<String, RegistroSimbolo> parametros, int direccionMemoria) {
        this.tipoRetorno = tipoRetorno;
        this.parametros = parametros;
        tabla = new HashMap<>();
        this.direccionMemoria = direccionMemoria;
    }

    public HashMap<String, RegistroSimbolo> getTabla() {
        return tabla;
    }

    public tipoVariable getTipoRetorno() {
        return tipoRetorno;
    }

    public HashMap<String, RegistroSimbolo> getParametros() {
        return parametros;
    }

    public int getDireccionMemoria() {
        return direccionMemoria;
    }

    public void setDireccionMemoria(int direccionMemoria) {
        this.direccionMemoria = direccionMemoria;
    }
}
