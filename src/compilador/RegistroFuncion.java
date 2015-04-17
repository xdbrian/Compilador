package compilador;

import ast.*;
import java.util.*;

public class RegistroFuncion {

    private tipoVariable tipoRetorno;
    private LinkedHashMap<String, RegistroSimbolo> parametros;
    private HashMap<String, RegistroSimbolo> tabla;    

    public RegistroFuncion(tipoVariable tipoRetorno, LinkedHashMap<String, RegistroSimbolo> parametros) {
        this.tipoRetorno = tipoRetorno;
        this.parametros = parametros;
        tabla = new HashMap<>();        
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
    
}
