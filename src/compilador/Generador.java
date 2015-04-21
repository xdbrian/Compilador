package compilador;

import ast.*;

public class Generador {

    private static int desplazamientoTmp = 0;
    private static TablaSimbolos tablaSimbolos = null;
    private static int localidadPreludioFunciones;

    public static void setTablaSimbolos(TablaSimbolos tabla) {
        tablaSimbolos = tabla;
    }

    public static void generarCodigoObjeto(NodoBase raiz) {
        System.out.println();
        System.out.println();
        System.out.println("------ CÓDIGO OBJETO DEL LENGUAJE TINY GENERADO PARA LA TM ------");
        System.out.println();
        System.out.println();
        generarPreludioEstandar();
        reservarPreludioFunciones(raiz);
        generar(raiz);
        UtGen.emitirComentario("Fin de la ejecución:");
        UtGen.emitirRO("HALT", 0, 0, 0, "");
    }

    private static void reservarPreludioFunciones(NodoBase nodo) {
        NodoFuncion n = (NodoFuncion) nodo;
        localidadPreludioFunciones = UtGen.emitirSalto(2);
        n = (NodoFuncion) n.getHermanoDerecha();
        while (n != null) {
            UtGen.emitirSalto(2);
            n = (NodoFuncion) n.getHermanoDerecha();
        }
        UtGen.emitirSalto(1);
    }

    private static void generar(NodoBase nodo) {
        if (tablaSimbolos != null) {
            if (nodo instanceof NodoIf) {
                generarIf(nodo);
            } else if (nodo instanceof NodoRepeat) {
                generarRepeat(nodo);
            } else if (nodo instanceof NodoAsignacion) {
                generarAsignacion(nodo);
            } else if (nodo instanceof NodoLeer) {
                generarLeer(nodo);
            } else if (nodo instanceof NodoEscribir) {
                generarEscribir(nodo);
            } else if (nodo instanceof NodoValor) {
                generarValor(nodo);
            } else if (nodo instanceof NodoIdentificador) {
                generarIdentificador(nodo);
            } else if (nodo instanceof NodoOperacion) {
                generarOperacion(nodo);
            } else if (nodo instanceof NodoFuncion) {
                generarFuncion(nodo);
            } else if (nodo instanceof NodoFor) {
                generarFor(nodo);
            } else if (nodo instanceof NodoLlamada) {
                generarLlamada(nodo);
            } else if (nodo instanceof NodoReturn) {
                generarReturn(nodo);
            }
            if (nodo.TieneHermano()) {
                generar(nodo.getHermanoDerecha());
            }
        } else {
            System.out.println("Fije la tabla de símbolos a usar antes de generar código objeto.");
        }
    }

    private static void generarReturn(NodoBase nodo) {
        if (UtGen.debug) {
            UtGen.emitirComentario("Retorno:");
        }
        generar(((NodoReturn) nodo).getValor());
        UtGen.emitirRM("LDA", UtGen.PC, 0, UtGen.AC2, "Saltar a la dirección guardada en AC2.");
    }

    private static void generarLlamada(NodoBase nodo) {
        if (UtGen.debug) {
            UtGen.emitirComentario("Llamada:");
        }
        UtGen.emitirRM("ST", UtGen.AC, desplazamientoTmp--, UtGen.MP, "");
        UtGen.emitirRM("LD", UtGen.AC3, ++desplazamientoTmp, UtGen.MP, "");
        String identificador = ((NodoLlamada) nodo).getNombre();
        NodoBase n = ((NodoLlamada) nodo).getArgumento();
        while (n != null) {
            if (n instanceof NodoOperacion) {
                generarOperacion(n);
            } else if (n instanceof NodoIdentificador) {
                generarIdentificador(n);
            } else if (n instanceof NodoValor) {
                generarValor(n);
            } else if (n instanceof NodoLlamada) {
                generarLlamada(n);
            }
            UtGen.emitirRM("ST", UtGen.AC, desplazamientoTmp--, UtGen.MP, "Guardar en " + (UtGen.MP + desplazamientoTmp + 1) + " el valor de AC.");
            n = n.getHermanoDerecha();
        }
        for (int i = tablaSimbolos.buscarAmbito(identificador).getParametros().size() - 1; i >= 0; i--) {
            int direccion = ((RegistroSimbolo) tablaSimbolos.buscarAmbito(identificador).getParametros().values().toArray()[i]).getDireccionMemoria();
            UtGen.emitirRM("LD", UtGen.AC, ++desplazamientoTmp, UtGen.MP, "Cargar en AC el valor de temporal.");
            UtGen.emitirRM("ST", UtGen.AC, direccion, UtGen.GP, "Guardar en " + direccion + " el valor de AC.");
        }
        int localidadFuncion = tablaSimbolos.buscarAmbito(identificador).getDireccionMemoria();
        UtGen.emitirRM("LDA", UtGen.AC2, 1, UtGen.PC, "Cargar en AC2 la dirección de retorno.");
        UtGen.emitirRM("LD", UtGen.PC, localidadFuncion, UtGen.GP, "Saltar a la función " + identificador + ".");
        UtGen.emitirRM("ST", UtGen.AC3, desplazamientoTmp--, UtGen.MP, "Guardar en " + (UtGen.MP + desplazamientoTmp + 1) + " el valor de AC.");
    }

    private static void generarFor(NodoBase nodo) {
        NodoFor n = (NodoFor) nodo;
        if (UtGen.debug) {
            UtGen.emitirComentario("FOR:");
        }
        generar(n.getInicializacion());
        int localidadCondicion = UtGen.emitirSalto(0);
        generar(n.getCondicion());
        int localidadEvaluacion = UtGen.emitirSalto(1);
        generar(n.getCuerpo());
        generar(n.getAsignacion());
        UtGen.emitirRM_Abs("LDA", UtGen.PC, localidadCondicion, "Saltar a la instrucción " + localidadCondicion + ".");
        int localidadFinal = UtGen.emitirSalto(0);
        UtGen.cargarRespaldo(localidadEvaluacion);
        UtGen.emitirRM_Abs("JEQ", UtGen.AC, localidadFinal, "Si AC = 0, saltar a la instrucción " + localidadFinal + ".");
        UtGen.restaurarRespaldo();
    }

    private static void generarFuncion(NodoBase nodo) {
        String ambito = ((NodoFuncion) nodo).getNombre();
        if (ambito == null) {
            ambito = "main";
        }
        tablaSimbolos.setAmbito(ambito);
        int localidadInicioFuncion = UtGen.emitirSalto(0);
        generar(((NodoFuncion) nodo).getCuerpo());
        if (!ambito.equals("main")) {
            UtGen.emitirRM("LDA", UtGen.PC, 0, UtGen.AC2, "Saltar a la dirección guardada en AC2.");
        }
        UtGen.cargarRespaldo(localidadPreludioFunciones);
        UtGen.emitirComentario("Preludio de la función " + ambito);
        int direccion = tablaSimbolos.getDireccion(ambito);
        UtGen.emitirRM("LDC", UtGen.AC, localidadInicioFuncion, 0, "Cargar en AC la constante " + localidadInicioFuncion + ".");
        UtGen.emitirRM("ST", UtGen.AC, direccion, UtGen.GP, "Guardar en " + direccion + " el valor de AC.");
        localidadPreludioFunciones = UtGen.emitirSalto(0);
        if (ambito.equals("main")) {
            UtGen.emitirRM_Abs("LDA", UtGen.PC, localidadInicioFuncion, "Salto a la instrucción " + localidadInicioFuncion);
        }
        UtGen.restaurarRespaldo();
    }

    private static void generarIf(NodoBase nodo) {
        NodoIf n = (NodoIf) nodo;
        int localidadSaltoElse, localidadSaltoEnd, localidadActual;
        if (UtGen.debug) {
            UtGen.emitirComentario("IF:");
        }
        generar(n.getPrueba());
        localidadSaltoElse = UtGen.emitirSalto(1);
        generar(n.getParteThen());
        localidadSaltoEnd = UtGen.emitirSalto(1);
        localidadActual = UtGen.emitirSalto(0);
        UtGen.cargarRespaldo(localidadSaltoElse);
        UtGen.emitirRM_Abs("JEQ", UtGen.AC, localidadActual, "Si falso, saltar a la instrucción " + localidadActual + ".");
        UtGen.restaurarRespaldo();
        if (n.getParteElse() != null) {
            generar(n.getParteElse());
        }
        localidadActual = UtGen.emitirSalto(0);
        UtGen.cargarRespaldo(localidadSaltoEnd);
        UtGen.emitirRM_Abs("LDA", UtGen.PC, localidadActual, "Saltar a la instrucción " + localidadActual + ".");
        UtGen.restaurarRespaldo();
    }

    private static void generarRepeat(NodoBase nodo) {
        NodoRepeat n = (NodoRepeat) nodo;
        if (UtGen.debug) {
            UtGen.emitirComentario("Repeat:");
        }
        int localidadSaltoInicio = UtGen.emitirSalto(0);
        generar(n.getCuerpo());
        generar(n.getPrueba());
        UtGen.emitirRM_Abs("JNE", UtGen.AC, localidadSaltoInicio, "Si verdadero, saltar a la instrucción " + localidadSaltoInicio + ".");
    }

    private static void generarAsignacion(NodoBase nodo) {
        NodoAsignacion n = (NodoAsignacion) nodo;
        int direccion;
        if (UtGen.debug) {
            UtGen.emitirComentario("Asignación:");
        }
        generar(n.getExpresion());
        direccion = tablaSimbolos.getDireccion(n.getIdentificador());
        if (n.getPosicion() != null) {
            UtGen.emitirRM("ST", UtGen.AC, desplazamientoTmp--, UtGen.MP, "Guardar en temporal el valor de AC.");
            generar(n.getPosicion());
            UtGen.emitirRM("ST", UtGen.AC, desplazamientoTmp--, UtGen.MP, "Guardar en temporal el valor de AC.");
            UtGen.emitirRM("LDC", UtGen.AC, direccion, 0, "Esta es la direccion ");
            UtGen.emitirRM("LD", UtGen.AC1, ++desplazamientoTmp, UtGen.MP, "Cargar en AC1 el valor de " + (UtGen.MP + desplazamientoTmp) + ".");
            UtGen.emitirRO("ADD", UtGen.AC, UtGen.AC1, UtGen.AC, "Guardar en AC el valor de AC1 más AC.");
            UtGen.emitirRM("LD", UtGen.AC1, ++desplazamientoTmp, UtGen.MP, "Cargar en AC1 el valor de " + (UtGen.MP + desplazamientoTmp) + ".");
            UtGen.emitirRM("ST", UtGen.AC, desplazamientoTmp--, UtGen.MP, "Guardar en temporal el valor de AC.");
            UtGen.emitirRM("ST", UtGen.AC1, desplazamientoTmp--, UtGen.MP, "Guardar en temporal el valor de AC.");
            UtGen.emitirRM("LD", UtGen.AC, ++desplazamientoTmp, UtGen.MP, "Cargar en AC1 el valor de " + (UtGen.MP + desplazamientoTmp) + ".");
            UtGen.emitirRM("LD", UtGen.AC1, ++desplazamientoTmp, UtGen.MP, "Cargar en AC1 el valor de " + (UtGen.MP + desplazamientoTmp) + ".");            
            UtGen.emitirRM("ST", UtGen.AC, 0, UtGen.AC1, "Guardar en " + direccion + " el valor de AC.");
        } else {
            UtGen.emitirRM("ST", UtGen.AC, direccion, UtGen.GP, "Guardar en " + direccion + " el valor de AC.");
        }
    }

    private static void generarLeer(NodoBase nodo) {
        NodoLeer n = (NodoLeer) nodo;
        int direccion;
        if (UtGen.debug) {
            UtGen.emitirComentario("Leer:");
        }
        UtGen.emitirRO("IN", UtGen.AC, 0, 0, "Cargar en AC valor de entrada.");
        direccion = tablaSimbolos.getDireccion(n.getIdentificador());
        UtGen.emitirRM("ST", UtGen.AC, direccion, UtGen.GP, "Guardar en " + direccion + " el valor de AC.");
    }

    private static void generarEscribir(NodoBase nodo) {
        NodoEscribir n = (NodoEscribir) nodo;
        if (UtGen.debug) {
            UtGen.emitirComentario("Escribir:");
        }
        generar(n.getExpresion());
        UtGen.emitirRO("OUT", UtGen.AC, 0, 0, "Mostrar valor de AC.");
    }

    private static void generarValor(NodoBase nodo) {
        NodoValor n = (NodoValor) nodo;
        int valor;
        if (n.getTipo() == tipoVariable.entero) {
            valor = (int) n.getValor();
        } else {
            if ((boolean) n.getValor()) {
                valor = 1;
            } else {
                valor = 0;
            }
        }
        UtGen.emitirRM("LDC", UtGen.AC, valor, 0, "Cargar en AC constante " + n.getValor().toString() + ".");
    }

    private static void generarIdentificador(NodoBase nodo) {
        NodoIdentificador n = (NodoIdentificador) nodo;
        int direccion = tablaSimbolos.getDireccion(n.getNombre());
        UtGen.emitirRM("LD", UtGen.AC, direccion, UtGen.GP, "Cargar valor de " + direccion + " en AC.");
    }

    private static void generarOperacion(NodoBase nodo) {
        NodoOperacion n = (NodoOperacion) nodo;
        generar(n.getOpIzquierdo());
        UtGen.emitirRM("ST", UtGen.AC, desplazamientoTmp--, UtGen.MP, "Guardar en temporal el valor de AC.");
        generar(n.getOpDerecho());
        UtGen.emitirRM("LD", UtGen.AC1, ++desplazamientoTmp, UtGen.MP, "Cargar en AC1 el valor de " + (UtGen.MP + desplazamientoTmp) + ".");
        switch (n.getOperacion()) {
            case mas:
                UtGen.emitirRO("ADD", UtGen.AC, UtGen.AC1, UtGen.AC, "Guardar en AC el valor de AC1 más AC.");
                break;
            case menos:
                UtGen.emitirRO("SUB", UtGen.AC, UtGen.AC1, UtGen.AC, "Guardar en AC el valor de AC1 menos AC.");
                break;
            case por:
                UtGen.emitirRO("MUL", UtGen.AC, UtGen.AC1, UtGen.AC, "Guardar en AC el valor de AC1 por AC.");
                break;
            case entre:
                UtGen.emitirRO("DIV", UtGen.AC, UtGen.AC1, UtGen.AC, "Guardar en AC el valor de AC1 entre AC.");
                break;
            case menor:
                UtGen.emitirRO("SUB", UtGen.AC, UtGen.AC1, UtGen.AC, "Cargar en AC el valor de AC1 menos AC.");
                UtGen.emitirRM("JLT", UtGen.AC, 2, UtGen.PC, "Si AC < 0, saltar a la instrucción " + (UtGen.emitirSalto(0) + 2 + 1) + ".");
                UtGen.emitirRM("LDC", UtGen.AC, 0, UtGen.AC, "Cargar en AC constante 0.");
                UtGen.emitirRM("LDA", UtGen.PC, 1, UtGen.PC, "Saltar a la instrución " + (UtGen.emitirSalto(0) + 1 + 1) + ".");
                UtGen.emitirRM("LDC", UtGen.AC, 1, UtGen.AC, "Cargar en AC constante 1.");
                break;
            case igual:
                UtGen.emitirRO("SUB", UtGen.AC, UtGen.AC1, UtGen.AC, "Cargar en AC el valor de AC1 menos AC.");
                UtGen.emitirRM("JEQ", UtGen.AC, 2, UtGen.PC, "Si AC = 0, saltar a la instrucción " + (UtGen.emitirSalto(0) + 2 + 1) + ".");
                UtGen.emitirRM("LDC", UtGen.AC, 0, UtGen.AC, "Cargar en AC constante 0.");
                UtGen.emitirRM("LDA", UtGen.PC, 1, UtGen.PC, "Saltar a la instrucción " + (UtGen.emitirSalto(0) + 1 + 1) + ".");
                UtGen.emitirRM("LDC", UtGen.AC, 1, UtGen.AC, "Cargar en AC constante 1.");
                break;
            case ylogico:
                UtGen.emitirRO("MUL", UtGen.AC, UtGen.AC1, UtGen.AC, "Guardar en AC el valor de AC1 por AC.");
                UtGen.emitirRM("JGT", UtGen.AC, 2, UtGen.PC, "Si AC > 0, saltar a la instrucción " + (UtGen.emitirSalto(0) + 2 + 1) + ".");
                UtGen.emitirRM("LDC", UtGen.AC, 0, UtGen.AC, "Cargar en AC constante 0.");
                UtGen.emitirRM("LDA", UtGen.PC, 1, UtGen.PC, "Saltar a la instrución " + (UtGen.emitirSalto(0) + 1 + 1) + ".");
                UtGen.emitirRM("LDC", UtGen.AC, 1, UtGen.AC, "Cargar en AC constante 1.");
                break;
            case ologico:
                UtGen.emitirRO("ADD", UtGen.AC, UtGen.AC1, UtGen.AC, "Guardar en AC el valor de AC1 más AC.");
                UtGen.emitirRM("JEQ", UtGen.AC, 2, UtGen.PC, "Si AC = 0, saltar a la instrucción " + (UtGen.emitirSalto(0) + 2 + 1) + ".");
                UtGen.emitirRM("LDC", UtGen.AC, 1, UtGen.AC, "Cargar en AC constante 1.");
                UtGen.emitirRM("LDA", UtGen.PC, 1, UtGen.PC, "Saltar a la instrución " + (UtGen.emitirSalto(0) + 1 + 1) + ".");
                UtGen.emitirRM("LDC", UtGen.AC, 0, UtGen.AC, "Cargar en AC constante 0.");
                break;
            case mayor:
                UtGen.emitirRO("SUB", UtGen.AC, UtGen.AC1, UtGen.AC, "Cargar en AC el valor de AC1 menos AC.");
                UtGen.emitirRM("JGT", UtGen.AC, 2, UtGen.PC, "Si AC > 0, saltar a la instrucción " + (UtGen.emitirSalto(0) + 2 + 1) + ".");
                UtGen.emitirRM("LDC", UtGen.AC, 0, UtGen.AC, "Cargar en AC constante 0.");
                UtGen.emitirRM("LDA", UtGen.PC, 1, UtGen.PC, "Saltar a la instrución " + (UtGen.emitirSalto(0) + 1 + 1) + ".");
                UtGen.emitirRM("LDC", UtGen.AC, 1, UtGen.AC, "Cargar en AC constante 1.");
                break;
            case diferente:
                UtGen.emitirRO("SUB", UtGen.AC, UtGen.AC1, UtGen.AC, "Cargar en AC el valor de AC1 menos AC.");
                UtGen.emitirRM("JNE", UtGen.AC, 2, UtGen.PC, "Si AC != 0, saltar a la instrucción " + (UtGen.emitirSalto(0) + 2 + 1) + ".");
                UtGen.emitirRM("LDC", UtGen.AC, 0, UtGen.AC, "Cargar en AC constante 0.");
                UtGen.emitirRM("LDA", UtGen.PC, 1, UtGen.PC, "Saltar a la instrución " + (UtGen.emitirSalto(0) + 1 + 1) + ".");
                UtGen.emitirRM("LDC", UtGen.AC, 1, UtGen.AC, "Cargar en AC constante 1.");
            case mayorigual:
                UtGen.emitirRO("SUB", UtGen.AC, UtGen.AC1, UtGen.AC, "Cargar en AC el valor de AC1 menos AC.");
                UtGen.emitirRM("JGE", UtGen.AC, 2, UtGen.PC, "Si AC >= 0, saltar a la instrucción " + (UtGen.emitirSalto(0) + 2 + 1) + ".");
                UtGen.emitirRM("LDC", UtGen.AC, 0, UtGen.AC, "Cargar en AC constante 0.");
                UtGen.emitirRM("LDA", UtGen.PC, 1, UtGen.PC, "Saltar a la instrución " + (UtGen.emitirSalto(0) + 1 + 1) + ".");
                UtGen.emitirRM("LDC", UtGen.AC, 1, UtGen.AC, "Cargar en AC constante 1.");
                break;
            case menorigual:
                UtGen.emitirRO("SUB", UtGen.AC, UtGen.AC1, UtGen.AC, "Cargar en AC el valor de AC1 menos AC.");
                UtGen.emitirRM("JLE", UtGen.AC, 2, UtGen.PC, "Si AC <= 0, saltar a la instrucción " + (UtGen.emitirSalto(0) + 2 + 1) + ".");
                UtGen.emitirRM("LDC", UtGen.AC, 0, UtGen.AC, "Cargar en AC constante 0.");
                UtGen.emitirRM("LDA", UtGen.PC, 1, UtGen.PC, "Saltar a la instrución " + (UtGen.emitirSalto(0) + 1 + 1) + ".");
                UtGen.emitirRM("LDC", UtGen.AC, 1, UtGen.AC, "Cargar en AC constante 1.");
                break;
        }
    }

    private static void generarPreludioEstandar() {
        UtGen.emitirComentario("Compilacion TINY para el codigo objeto TM");
        UtGen.emitirComentario("Preludio estandar:");
        UtGen.emitirRM("LD", UtGen.MP, 0, UtGen.AC, "Cargar la máxima dirección desde el registro 0.");
        UtGen.emitirRM("ST", UtGen.AC, 0, UtGen.AC, "Limpiar el registro de la localidad 0.");
    }
}
