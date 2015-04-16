package compilador;

import ast.*;
import java.util.*;

public class RegistroFuncion {

    private tipoVariable tipoRetorono;
    private HashMap<String, RegistroSimbolo> parametros;
    private HashMap<String, RegistroSimbolo> tabla;

    public RegistroFuncion(tipoVariable tipoRetorono, HashMap<String, RegistroSimbolo> parametros) {
        this.tipoRetorono = tipoRetorono;
        this.parametros = parametros;
        tabla = new HashMap<>();
    }

    public HashMap<String, RegistroSimbolo> getTabla() {
        return tabla;
    }

    public tipoVariable getTipoRetorono() {
        return tipoRetorono;
    }

    public HashMap<String, RegistroSimbolo> getParametros() {
        return parametros;
    }
    
}
