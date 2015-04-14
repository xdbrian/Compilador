package compilador;

import java.util.*;

import ast.*;

public class TablaSimbolos {
	private HashMap<String, RegistroSimbolo> tabla;
	private int direccion;  //Contador de las localidades de memoria asignadas a la tabla
	
	public TablaSimbolos() {
		super();
		tabla = new HashMap<String, RegistroSimbolo>();
		direccion=0;
	}

	public void cargarTabla(NodoBase raiz,int x){
		while (raiz != null) {
	    if (raiz instanceof NodoDeclaracion){
                String tipo=((NodoDeclaracion)raiz).getTipo().toString();
                String valor=((NodoVariable)(((NodoDeclaracion)raiz).getVariable())).getNombre();
                
	    	InsertarSimbolo(tipo,valor,-1,x);
                System.out.println(valor);
                NodoVariable nodo=(NodoVariable)(((NodoVariable)(((NodoDeclaracion)raiz).getVariable())).getHermanoDerecha());
                while(nodo!=null)
                {
               
                    System.out.println(nodo.getNombre());
                    InsertarSimbolo(tipo,nodo.getNombre(),-1,x);
                    nodo=(NodoVariable)nodo.getHermanoDerecha();
                }
                
                
	    	//TODO: Añadir el numero de linea y localidad de memoria correcta
	    }

	    /* Hago el recorrido recursivo */
	    if (raiz instanceof  NodoIf){
	    	cargarTabla(((NodoIf)raiz).getPrueba(),x);
	    	cargarTabla(((NodoIf)raiz).getParteThen(),x);
	    	if(((NodoIf)raiz).getParteElse()!=null){
	    		cargarTabla(((NodoIf)raiz).getParteElse(),x);
	    	}
	    }
	    else if (raiz instanceof  NodoRepeat){
	    	cargarTabla(((NodoRepeat)raiz).getCuerpo(),x);
	    	cargarTabla(((NodoRepeat)raiz).getPrueba(),x);
	    }
	    else if (raiz instanceof  NodoAsignacion)
	    	cargarTabla(((NodoAsignacion)raiz).getExpresion(),x);
	    else if (raiz instanceof  NodoEscribir)
	    	cargarTabla(((NodoEscribir)raiz).getExpresion(),x);
	    else if (raiz instanceof NodoOperacion){
	    	cargarTabla(((NodoOperacion)raiz).getOpIzquierdo(),x);
	    	cargarTabla(((NodoOperacion)raiz).getOpDerecho(),x);
	    }else if (raiz instanceof NodoFuncion){
	    cargarTabla(((NodoFuncion)raiz).getCuerpo(),x++);
            
	  }
	    raiz = raiz.getHermanoDerecha();
	  }
	}
	
	//true es nuevo no existe se insertara, false ya existe NO se vuelve a insertar 
	public boolean InsertarSimbolo(String tipo,String identificador, int numLinea,int x){
		RegistroSimbolo simbolo;
                
		if(tabla.containsKey(identificador)){
			return false;
		}else{
			simbolo= new RegistroSimbolo(tipo,numLinea,direccion++);
			tabla.put(identificador,simbolo);
			return true;			
		}
	}
	
	public RegistroSimbolo BuscarSimbolo(String identificador){
		RegistroSimbolo simbolo=(RegistroSimbolo)tabla.get(identificador);
                
		return simbolo;
	}
	
	public void ImprimirClaves(){
		System.out.println("*** Tabla de Simbolos ***");
		for( Iterator <String>it = tabla.keySet().iterator(); it.hasNext();) { 
            String s = (String)it.next();
	    System.out.println("Consegui Key "+s+": tipo "+BuscarSimbolo(s).getTipo()+" con direccion: " + BuscarSimbolo(s).getDireccionMemoria());
		}
	}

	public int getDireccion(String Clave){
		return BuscarSimbolo(Clave).getDireccionMemoria();
	}
	
	/*
	 * TODO:
	 * 1. Crear lista con las lineas de codigo donde la variable es usada.
	 * */
}
