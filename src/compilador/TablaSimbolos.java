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
                int tamano = ((NodoVariable) ((NodoDeclaracion) raiz).getVariable()).getTamano();
                int numeroLinea = ((NodoDeclaracion) raiz).getNumLinea();
                if (!insertarSimbolo(identificador, tipo, numeroLinea, tamano)) {
                    System.out.println("La variable " + identificador + " ya ha sido definida en este ámbito. Línea " + numeroLinea + ".");
                    System.exit(0);
                }
                NodoVariable nodo = (NodoVariable) (((NodoVariable) (((NodoDeclaracion) raiz).getVariable())).getHermanoDerecha());
                while (nodo != null) {
                    if (!insertarSimbolo(nodo.getNombre(), tipo, numeroLinea, nodo.getTamano())) {
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
                if (((NodoFuncion) raiz).getNombre() != null && ((NodoFuncion) raiz).getNombre().equals("main")) {
                    System.out.println("No se puede definir una función con nombre main.");
                    System.exit(0);
                }
                if (((NodoFuncion) raiz).getTipoRetorno() != tipoVariable.vacio) {
                    NodoBase cuerpo = ((NodoFuncion) raiz).getCuerpo();
                    boolean tieneReturn = false;
                    while (cuerpo != null) {
                        if (cuerpo instanceof NodoReturn) {
                            tieneReturn = true;
                            break;
                        }
                        cuerpo = cuerpo.getHermanoDerecha();
                    }
                    if (!tieneReturn) {
                        System.out.println("La función " + ((NodoFuncion) raiz).getNombre() + " debe regresar un valor.");
                        System.exit(0);
                    }
                }
            }
            if (raiz instanceof NodoIf) {
                cargarTabla(((NodoIf) raiz).getPrueba());
                cargarTabla(((NodoIf) raiz).getParteThen());
                if (((NodoIf) raiz).getParteElse() != null) {
                    cargarTabla(((NodoIf) raiz).getParteElse());
                }
                tipoVariable tipoPrueba;
                if (((NodoIf) raiz).getPrueba() instanceof NodoOperacion) {
                    tipoPrueba = ((NodoOperacion) ((NodoIf) raiz).getPrueba()).getTipoValor();
                } else {
                    tipoPrueba = determinarTipo(((NodoIf) raiz).getPrueba());
                }
                if (tipoPrueba != tipoVariable.booleano) {
                    System.out.println("La sentencia if debe evaluar una condición booleana.");
                    System.exit(0);
                }
            } else if (raiz instanceof NodoRepeat) {
                cargarTabla(((NodoRepeat) raiz).getCuerpo());
                cargarTabla(((NodoRepeat) raiz).getPrueba());
                tipoVariable tipoPrueba;
                if (((NodoRepeat) raiz).getPrueba() instanceof NodoOperacion) {
                    tipoPrueba = ((NodoOperacion) ((NodoRepeat) raiz).getPrueba()).getTipoValor();
                } else {
                    tipoPrueba = determinarTipo(((NodoRepeat) raiz).getPrueba());
                }
                if (tipoPrueba != tipoVariable.booleano) {
                    System.out.println("La sentencia repeat debe evaluar una condición booleana.");
                    System.exit(0);
                }
            } else if (raiz instanceof NodoAsignacion) {
                String identificador = ((NodoAsignacion) raiz).getIdentificador();
                if (buscarSimbolo(identificador) == null) {
                    System.out.println("La variable " + identificador + " no ha sido definida.");
                    System.exit(0);
                }
                cargarTabla(((NodoAsignacion) raiz).getExpresion());
                cargarTabla(((NodoAsignacion) raiz).getPosicion());
                tipoVariable tipoDestino = determinarTipo(identificador);
                tipoVariable tipoOrigen;
                if (((NodoAsignacion) raiz).getExpresion() instanceof NodoOperacion) {
                    tipoOrigen = ((NodoOperacion) ((NodoAsignacion) raiz).getExpresion()).getTipoValor();
                } else {
                    tipoOrigen = determinarTipo(((NodoAsignacion) raiz).getExpresion());
                }
                if (tipoOrigen != tipoDestino) {
                    System.out.println("No se puede convertir " + tipoOrigen + " en " + tipoDestino + ".");
                    System.exit(0);
                }
                if (buscarSimbolo(identificador).getTamano() > 0) {
                    if (((NodoAsignacion) raiz).getPosicion() == null) {
                        System.out.println("Debe indicar una posición de asignación para el vector " + identificador + ".");
                        System.exit(0);
                    }
                    tipoVariable tipoPosicion;
                    if (((NodoAsignacion) raiz).getPosicion() instanceof NodoOperacion) {
                        tipoPosicion = ((NodoOperacion) ((NodoAsignacion) raiz).getPosicion()).getTipoValor();
                    } else {
                        tipoPosicion = determinarTipo(((NodoAsignacion) raiz).getPosicion());
                    }                    
                    if (tipoPosicion != tipoVariable.entero) {
                        System.out.println("Las posiciones de un vector deben venir indicadas por variables enteras.");
                        System.exit(0);
                    }
                } else if (((NodoAsignacion) raiz).getPosicion() != null){
                    System.out.println("Solo puede especificar posiciones en vectores de variables.");
                    System.exit(0);
                }
            } else if (raiz instanceof NodoEscribir) {
                cargarTabla(((NodoEscribir) raiz).getExpresion());
                if (determinarTipo(((NodoEscribir) raiz).getExpresion()) != tipoVariable.entero) {
                    System.out.println("Solo se pueden escribir variables de tipo entero.");
                    System.exit(0);
                }
            } else if (raiz instanceof NodoOperacion) {
                cargarTabla(((NodoOperacion) raiz).getOpIzquierdo());
                cargarTabla(((NodoOperacion) raiz).getOpDerecho());
                determinarTipo(raiz);
                if (((NodoOperacion) raiz).getTipoValor() == tipoVariable.vacio) {
                    System.out.println("La operación no es válida entre los tipos especificados.");
                    System.exit(0);
                }
            } else if (raiz instanceof NodoFor) {
                cargarTabla(((NodoFor) raiz).getInicializacion());
                cargarTabla(((NodoFor) raiz).getCondicion());
                cargarTabla(((NodoFor) raiz).getAsignacion());
                cargarTabla(((NodoFor) raiz).getCuerpo());
                tipoVariable tipoCondicion;
                if (((NodoFor) raiz).getCondicion() instanceof NodoOperacion) {
                    tipoCondicion = ((NodoOperacion) ((NodoFor) raiz).getCondicion()).getTipoValor();
                } else {
                    tipoCondicion = determinarTipo(((NodoFor) raiz).getCondicion());
                }
                if (tipoCondicion != tipoVariable.booleano) {
                    System.out.println("La sentencia for debe evaluar una condición booleana.");
                    System.exit(0);
                }
            } else if (raiz instanceof NodoLlamada) {
                String identificador = ((NodoLlamada) raiz).getNombre();
                if (buscarAmbito(identificador) == null) {
                    System.out.println("La función " + identificador + " no ha sido definida.");
                    System.exit(0);
                }
                cargarTabla(((NodoLlamada) raiz).getArgumento());
                if (identificador.equals(ambito)) {
                    System.out.println("No se permiten llamadas recursivas a funciones.");
                    System.exit(0);
                }
                NodoBase nodo = ((NodoLlamada) raiz).getArgumento();
                int cantidadArgumentos = 0;
                while (nodo != null) {
                    if (cantidadArgumentos > buscarAmbito(((NodoLlamada) raiz).getNombre()).getParametros().size() - 1) {
                        System.out.println("El número de parámetros de la función " + identificador + " y la llamada no coindicen.");
                        System.exit(0);
                    }
                    RegistroSimbolo simbolo = (RegistroSimbolo) buscarAmbito(((NodoLlamada) raiz).getNombre()).getParametros().values().toArray()[cantidadArgumentos];
                    tipoVariable tipoArgumento;
                    if (nodo instanceof NodoOperacion) {
                        tipoArgumento = ((NodoOperacion) nodo).getTipoValor();
                    } else {
                        tipoArgumento = determinarTipo(nodo);
                    }
                    if (tipoArgumento != simbolo.getTipo()) {
                        System.out.println("El tipo del argumento " + (cantidadArgumentos + 1) + " no coincide con el esperado por la función " + identificador + ".");
                        System.exit(0);
                    }
                    cantidadArgumentos++;
                    nodo = nodo.getHermanoDerecha();
                }
                if (cantidadArgumentos != buscarAmbito(((NodoLlamada) raiz).getNombre()).getParametros().size()) {
                    System.out.println("El número de parámetros de la función " + identificador + " y la llamada no coindicen.");
                    System.exit(0);
                }
            } else if (raiz instanceof NodoReturn) {
                cargarTabla(((NodoReturn) raiz).getValor());
                RegistroFuncion funcion = buscarAmbito(ambito);
                tipoVariable tipoRetorno;
                if (((NodoReturn) raiz).getValor() instanceof NodoOperacion) {
                    tipoRetorno = ((NodoOperacion) ((NodoReturn) raiz).getValor()).getTipoValor();
                } else {
                    tipoRetorno = determinarTipo(((NodoReturn) raiz).getValor());
                }
                if (funcion.getTipoRetorno() != tipoRetorno) {
                    System.out.println("El tipo de retorno de la función " + ambito + " no coincide con el tipo esperado.");
                    System.exit(0);
                }

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
                if (determinarTipo(identificador) != tipoVariable.entero) {
                    System.out.println("La sentencia read solo acepta variables de tipo entero.");
                    System.exit(0);
                }
            }
            raiz = raiz.getHermanoDerecha();
        }
    }

    public boolean insertarSimbolo(String identificador, tipoVariable tipo, int numLinea, int tamano) {
        RegistroSimbolo simbolo;
        if (tabla.get(ambito).getTabla().containsKey(identificador)) {
            return false;
        }
        if (tabla.get(ambito).getParametros().containsKey(identificador)) {
            return false;
        }
        simbolo = new RegistroSimbolo(tipo, numLinea, direccion, tamano);
        if (tamano > 0) {
            direccion += tamano;
        } else {
            direccion++;
        }
        tabla.get(ambito).getTabla().put(identificador, simbolo);
        return true;
    }

    public boolean insertarAmbito(NodoFuncion nodo) {
        if (tabla.containsKey(ambito)) {
            return false;
        }
        LinkedHashMap<String, RegistroSimbolo> parametros = new LinkedHashMap<>();
        NodoDeclaracion parametro = (NodoDeclaracion) nodo.getArgumento();
        int direccionFuncion = direccion++;
        while (parametro != null) {
            if (parametros.containsKey(((NodoVariable) parametro.getVariable()).getNombre())) {
                System.out.println("La variable " + ((NodoVariable) parametro.getVariable()).getNombre() + " ya ha sido definida en este ámbito. Línea " + parametro.getNumLinea() + ".");
                System.exit(0);
            }
            parametros.put(((NodoVariable) parametro.getVariable()).getNombre(), new RegistroSimbolo(parametro.getTipo(), parametro.getNumLinea(), direccion++, 0));
            parametro = (NodoDeclaracion) parametro.getHermanoDerecha();
        }
        tabla.put(ambito, new RegistroFuncion(nodo.getTipoRetorno(), parametros, direccionFuncion));
        return true;
    }

    public RegistroSimbolo buscarSimbolo(String identificador) {
        RegistroSimbolo simbolo = tabla.get(ambito).getTabla().get(identificador);
        if (simbolo == null) {
            simbolo = tabla.get(ambito).getParametros().get(identificador);
        }
        return simbolo;
    }

    public RegistroFuncion buscarAmbito(String ambito) {
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
            if (buscarAmbito(ambito).getParametros().size() > 0) {
                System.out.println("\t Parámetros:");
                for (Iterator<String> it2 = tabla.get(ambito).getParametros().keySet().iterator(); it2.hasNext();) {
                    String identificador = (String) it2.next();
                    RegistroSimbolo simbolo = buscarSimbolo(identificador);
                    System.out.println("\t\t Identificador: " + identificador + ", tipo: " + simbolo.getTipo() + ", número de línea: " + simbolo.getNumLinea() + ", direccion: " + simbolo.getDireccionMemoria());
                }
            }
            for (Iterator<String> it2 = tabla.get(ambito).getTabla().keySet().iterator(); it2.hasNext();) {
                String identificador = (String) it2.next();
                RegistroSimbolo simbolo = buscarSimbolo(identificador);
                System.out.println("\t Identificador: " + identificador + ", tipo: " + simbolo.getTipo() + ", número de línea: " + simbolo.getNumLinea() + ", direccion: " + simbolo.getDireccionMemoria());
            }
        }
    }

    public int getDireccion(String clave) {
        if (clave == null) {
            clave = "main";
        }
        RegistroSimbolo simbolo = buscarSimbolo(clave);
        if (simbolo != null) {
            return simbolo.getDireccionMemoria();
        }
        RegistroFuncion funcion = buscarAmbito(clave);
        return funcion.getDireccionMemoria();
    }

    private tipoVariable determinarTipo(Object nodo) {
        if (nodo instanceof String) {
            return buscarSimbolo((String) nodo).getTipo();
        }
        if (nodo instanceof NodoIdentificador) {
            return determinarTipo(((NodoIdentificador) nodo).getNombre());
        }
        if (nodo instanceof NodoValor) {
            return ((NodoValor) nodo).getTipo();
        }
        if (nodo instanceof NodoOperacion) {
            tipoVariable operadorIzquierdo = determinarTipo(((NodoOperacion) nodo).getOpIzquierdo());
            tipoVariable operadorDerecho = determinarTipo(((NodoOperacion) nodo).getOpDerecho());
            if (operadorIzquierdo == operadorDerecho) {
                switch (((NodoOperacion) nodo).getOperacion()) {
                    case menor:
                        if (operadorIzquierdo == tipoVariable.entero) {
                            ((NodoOperacion) nodo).setTipoValor(tipoVariable.booleano);
                            return tipoVariable.booleano;
                        }
                        break;
                    case igual:
                        if (operadorIzquierdo == tipoVariable.entero) {
                            ((NodoOperacion) nodo).setTipoValor(tipoVariable.booleano);
                            return tipoVariable.booleano;
                        }
                        break;
                    case mas:
                        if (operadorIzquierdo == tipoVariable.entero) {
                            ((NodoOperacion) nodo).setTipoValor(operadorIzquierdo);
                            return operadorIzquierdo;
                        }
                        break;
                    case menos:
                        if (operadorIzquierdo == tipoVariable.entero) {
                            ((NodoOperacion) nodo).setTipoValor(operadorIzquierdo);
                            return operadorIzquierdo;
                        }
                        break;
                    case por:
                        if (operadorIzquierdo == tipoVariable.entero) {
                            ((NodoOperacion) nodo).setTipoValor(operadorIzquierdo);
                            return operadorIzquierdo;
                        }
                    case entre:
                        if (operadorIzquierdo == tipoVariable.entero) {
                            ((NodoOperacion) nodo).setTipoValor(operadorIzquierdo);
                            return operadorIzquierdo;
                        }
                        break;
                    case ylogico:
                        if (operadorIzquierdo == tipoVariable.booleano) {
                            ((NodoOperacion) nodo).setTipoValor(operadorIzquierdo);
                            return operadorIzquierdo;
                        }
                        break;
                    case ologico:
                        if (operadorIzquierdo == tipoVariable.booleano) {
                            ((NodoOperacion) nodo).setTipoValor(operadorIzquierdo);
                            return operadorIzquierdo;
                        }
                        break;
                    case mayor:
                        if (operadorIzquierdo == tipoVariable.entero) {
                            ((NodoOperacion) nodo).setTipoValor(tipoVariable.booleano);
                            return tipoVariable.booleano;
                        }
                        break;
                    case diferente:
                        if (operadorIzquierdo == tipoVariable.entero) {
                            ((NodoOperacion) nodo).setTipoValor(tipoVariable.booleano);
                            return tipoVariable.booleano;
                        }
                        break;
                    case mayorigual:
                        if (operadorIzquierdo == tipoVariable.entero) {
                            ((NodoOperacion) nodo).setTipoValor(tipoVariable.booleano);
                            return tipoVariable.booleano;
                        }
                        break;
                    case menorigual:
                        if (operadorIzquierdo == tipoVariable.entero) {
                            ((NodoOperacion) nodo).setTipoValor(tipoVariable.booleano);
                            return tipoVariable.booleano;
                        }
                        break;
                }
                return tipoVariable.vacio;
            }
            return tipoVariable.vacio;
        }
        if (nodo instanceof NodoLlamada) {
            return buscarAmbito(((NodoLlamada) nodo).getNombre()).getTipoRetorno();
        }
        return tipoVariable.vacio;
    }

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        if (ambito == null) {
            this.ambito = "main";
        } else {
            this.ambito = ambito;
        }
    }
}
