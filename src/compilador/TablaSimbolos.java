package compilador;

import java.util.*;
import ast.*;

public class TablaSimbolos {

    private HashMap<String, RegistroFuncion> tabla;
    private int direccion;
    private String ambito;

    public TablaSimbolos() {
        super();
        tabla = new HashMap<>();
        direccion = 0;
    }

    public void cargarTabla(NodoBase raiz) {
        while (raiz != null) {
            if (raiz instanceof NodoDeclaracion) {
                tipoVariable tipo = ((NodoDeclaracion) raiz).getTipo();
                String identificador = ((NodoVariable) (((NodoDeclaracion) raiz).getVariable())).getNombre();
                int numeroLinea = ((NodoDeclaracion) raiz).getNumLinea();
                if (!insertarSimbolo(identificador, tipo, numeroLinea)) {
                    System.out.println("La variable " + identificador + " ya ha sido definida en este ámbito. Línea " + numeroLinea + ".");
                    System.exit(0);
                }
                NodoVariable nodo = (NodoVariable) (((NodoVariable) (((NodoDeclaracion) raiz).getVariable())).getHermanoDerecha());
                while (nodo != null) {
                    if (!insertarSimbolo(nodo.getNombre(), tipo, numeroLinea)) {
                        System.out.println("La variable " + nodo.getNombre() + " ya ha sido definida en este ámbito. Línea " + numeroLinea + ".");
                        System.exit(0);
                    }
                    nodo = (NodoVariable) nodo.getHermanoDerecha();
                }
            } else if (raiz instanceof NodoFuncion) {
                ambito = ((NodoFuncion) raiz).getNombre();
                if (ambito == null) {
                    ambito = "main";
                }
                if (!insertarAmbito((NodoFuncion) raiz)) {
                    System.out.println("La función " + ((NodoFuncion) raiz).getNombre() + " ya ha sido definida.");
                    System.exit(0);
                }
                cargarTabla(((NodoFuncion) raiz).getCuerpo());
            }
            if (raiz instanceof NodoIf) {
                cargarTabla(((NodoIf) raiz).getPrueba());
                cargarTabla(((NodoIf) raiz).getParteThen());
                if (((NodoIf) raiz).getParteElse() != null) {
                    cargarTabla(((NodoIf) raiz).getParteElse());
                }
            } else if (raiz instanceof NodoRepeat) {
                cargarTabla(((NodoRepeat) raiz).getCuerpo());
                cargarTabla(((NodoRepeat) raiz).getPrueba());
            } else if (raiz instanceof NodoAsignacion) {
                String identificador = ((NodoAsignacion) raiz).getIdentificador();
                if (buscarSimbolo(identificador) == null) {
                    System.out.println("La variable " + identificador + " no ha sido definida.");
                    System.exit(0);
                }
                cargarTabla(((NodoAsignacion) raiz).getExpresion());
            } else if (raiz instanceof NodoEscribir) {
                cargarTabla(((NodoEscribir) raiz).getExpresion());
            } else if (raiz instanceof NodoOperacion) {
                cargarTabla(((NodoOperacion) raiz).getOpIzquierdo());
                cargarTabla(((NodoOperacion) raiz).getOpDerecho());
            } else if (raiz instanceof NodoFor) {
                cargarTabla(((NodoFor) raiz).getInicializacion());
                cargarTabla(((NodoFor) raiz).getCondicion());
                cargarTabla(((NodoFor) raiz).getAsignacion());
                cargarTabla(((NodoFor) raiz).getCuerpo());
            } else if (raiz instanceof NodoLlamada) {                
                String identificador = ((NodoLlamada) raiz).getNombre();
                if (buscarAmbito(identificador) == null) {
                    System.out.println("La función " + identificador + " no ha sido definida.");
                    System.exit(0);
                }
                cargarTabla(((NodoLlamada) raiz).getArgumento());
            } else if (raiz instanceof NodoReturn) {
                cargarTabla(((NodoReturn) raiz).getValor());
            } else if (raiz instanceof NodoIdentificador) {                
                String identificador = ((NodoIdentificador) raiz).getNombre();
                if (buscarSimbolo(identificador) == null) {
                    System.out.println("La variable " + identificador + " no ha sido definida.");
                    System.exit(0);
                }
            } else if (raiz instanceof NodoLeer) {
                String identificador = ((NodoLeer) raiz).getIdentificador();
                if (buscarSimbolo(identificador) == null) {
                    System.out.println("La variable " + identificador + " no ha sido definida.");
                    System.exit(0);
                }
            }
            raiz = raiz.getHermanoDerecha();
        }
    }

    public boolean insertarSimbolo(String identificador, tipoVariable tipo, int numLinea) {
        RegistroSimbolo simbolo;
        if (tabla.get(ambito).getTabla().containsKey(identificador)) {
            return false;
        }
        if (tabla.get(ambito).getParametros().containsKey(identificador)) {
            return false;
        }
        simbolo = new RegistroSimbolo(tipo, numLinea, direccion++);
        tabla.get(ambito).getTabla().put(identificador, simbolo);
        return true;
    }

    public boolean insertarAmbito(NodoFuncion nodo) {
        if (tabla.containsKey(ambito)) {
            return false;
        }
        HashMap<String, RegistroSimbolo> parametros = new HashMap<>();
        NodoDeclaracion parametro = (NodoDeclaracion) nodo.getArgumento();
        while (parametro != null) {
            if (parametros.containsKey(((NodoVariable) parametro.getVariable()).getNombre())) {
                System.out.println("La variable " + ((NodoVariable) parametro.getVariable()).getNombre() + " ya ha sido definida en este ámbito. Línea " + parametro.getNumLinea() + ".");
                System.exit(0);
            }
            parametros.put(((NodoVariable) parametro.getVariable()).getNombre(), new RegistroSimbolo(parametro.getTipo(), parametro.getNumLinea(), direccion++));
            parametro = (NodoDeclaracion) parametro.getHermanoDerecha();
        }
        tabla.put(ambito, new RegistroFuncion(nodo.getTipoRetorno(), parametros));
        return true;
    }

    public RegistroSimbolo buscarSimbolo(String identificador) {
        RegistroSimbolo simbolo = tabla.get(ambito).getTabla().get(identificador);
        if (simbolo == null) {
            simbolo = tabla.get(ambito).getParametros().get(identificador);
        }
        return simbolo;
    }
    
    public RegistroFuncion buscarAmbito(String _ambito){
        return tabla.get(ambito);
    }
    
    public void imprimirClaves() {
        if (tabla.size() < 1) {
            return;
        }
        System.out.println("*** Tabla de Simbolos ***");
        for (Iterator<String> it = tabla.keySet().iterator(); it.hasNext();) {
            ambito = (String) it.next();
            System.out.println("Ámbito: " + ambito);
            for (Iterator<String> it2 = tabla.get(ambito).getTabla().keySet().iterator(); it2.hasNext();) {
                String identificador = (String) it2.next();
                RegistroSimbolo simbolo = buscarSimbolo(identificador);
                System.out.println("\t Identificador: " + identificador + ", tipo: " + simbolo.getTipo() + ", número de línea: " + simbolo.getNumLinea() + ", direccion: " + simbolo.getDireccionMemoria());
            }
        }
    }
    
//    public int getDireccion(String Clave) {
//        return buscarSimbolo(Clave).getDireccionMemoria();
//    }    

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }
}
