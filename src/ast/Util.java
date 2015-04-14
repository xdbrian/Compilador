package ast;

public class Util {
	
	static int sangria = 0;
	
	//Imprimo en modo texto con sangrias el AST
	public static void imprimirAST(NodoBase raiz){
		  sangria+=2;
		  while (raiz != null) {
		    printSpaces();
                    
		    if (raiz instanceof  NodoIf)
		    	System.out.println("If");
		    else if (raiz instanceof  NodoRepeat)
		    	System.out.println("Repeat");
		    
		    else if (raiz instanceof  NodoAsignacion)
		    	System.out.println("Asignacion a: "+((NodoAsignacion)raiz).getIdentificador());

		    else if (raiz instanceof  NodoLeer)  
		    	System.out.println("Lectura: "+((NodoLeer)raiz).getIdentificador());

		    else if (raiz instanceof  NodoEscribir)
		    	System.out.println("Escribir");
		    
		    else if (raiz instanceof NodoOperacion
		    		|| raiz instanceof NodoValor
		    		|| raiz instanceof NodoIdentificador )
		    	imprimirNodo(raiz);
/*agredando al arbol*/
                    else if(raiz instanceof NodoFuncion)
                        if(((NodoFuncion)raiz).getNombre()!=null)
                        System.out.println("Funcion");
                        else
                              System.out.println("Main");
                    
                    else if(raiz instanceof NodoFor)
                          System.out.println("For");
                    else if(raiz instanceof NodoAsignacion)
                          System.out.println("Asignacion");
                    else if(raiz instanceof NodoDeclaracion)
                          System.out.println("Declaracion");
                    else if(raiz instanceof NodoLlamada)
                          System.out.println("Llamada");
                    else if(raiz instanceof NodoOperacion)
                          System.out.println("operacion");
                    else if(raiz instanceof NodoValor)
                          System.out.println("valor");
                    else if(raiz instanceof NodoIdentificador)
                          System.out.println("Identificador");
                    else if(raiz instanceof NodoReturn)
                          System.out.println("Return");                   
                    else if(raiz instanceof NodoVariable)
                          System.out.println(((NodoVariable)raiz).getNombre());
                    
                                                                
/*agregando al arbol*/                        
		    else System.out.println("Tipo de nodo desconocido");;
		    
		    /* Hago el recorrido recursivo */
		    if (raiz instanceof  NodoIf){
		    	printSpaces();
		    	System.out.println("**Prueba IF**");
		    	imprimirAST(((NodoIf)raiz).getPrueba());
		    	printSpaces();
		    	System.out.println("**Then IF**");
		    	imprimirAST(((NodoIf)raiz).getParteThen());
		    	if(((NodoIf)raiz).getParteElse()!=null){
		    		printSpaces();
		    		System.out.println("**Else IF**");
		    		imprimirAST(((NodoIf)raiz).getParteElse());
		    	}
		    }
		    else if (raiz instanceof  NodoRepeat){
		    	printSpaces();
		    	System.out.println("**Cuerpo REPEAT**");
		    	imprimirAST(((NodoRepeat)raiz).getCuerpo());
		    	printSpaces();
		    	System.out.println("**Prueba REPEAT**");
		    	imprimirAST(((NodoRepeat)raiz).getPrueba());
		    }
		    else if (raiz instanceof  NodoAsignacion)
		    	imprimirAST(((NodoAsignacion)raiz).getExpresion());
		    else if (raiz instanceof  NodoEscribir)
		    	imprimirAST(((NodoEscribir)raiz).getExpresion());
		    else if (raiz instanceof NodoOperacion){
		    	printSpaces();
		    	System.out.println("**Expr Izquierda Operacion**");
		    	imprimirAST(((NodoOperacion)raiz).getOpIzquierdo());
		    	printSpaces();
		    	System.out.println("**Expr Derecha Operacion**");		    	
		    	imprimirAST(((NodoOperacion)raiz).getOpDerecho());
		    }
/*agregando al arbol*/                    
                    else  if (raiz instanceof NodoFuncion)
                    {
                        printSpaces();
                        if(((NodoFuncion)raiz).getNombre()!=null)
                        {
                        System.out.print(((NodoFuncion)raiz).getTipoRetorno());                        
                        System.out.print(" "+((NodoFuncion)raiz).getNombre());
                                                               
                        System.out.print("(");                        
                        imprimirAST(((NodoFuncion)raiz).getArgumento());
                        System.out.println(")");                        
                        printSpaces();
                        }
                        System.out.println("**Cuerpo**");
                        imprimirAST(((NodoFuncion)raiz).getCuerpo());                        
                        printSpaces();
                        System.out.println("**END**");
                        
                    }  else  if (raiz instanceof NodoDeclaracion)                        
                    {
                        printSpaces();
                        System.out.println("Tipo "+((NodoDeclaracion)raiz).getTipo());                                                
                        imprimirAST(((NodoDeclaracion)raiz).getVariable());
                    } else  if (raiz instanceof NodoFor)
                    {
                        printSpaces();
                        System.out.println("Declaracion");
                        imprimirAST(((NodoFor)raiz).getInicializacion());
                        printSpaces();
                        System.out.println("Condicion");
                        imprimirAST(((NodoFor)raiz).getCondicion());
                        printSpaces();
                        System.out.println("asignacion");
                        imprimirAST(((NodoFor)raiz).getAsignacion());
                        printSpaces();
                        System.out.println("Cuerpo");
                        imprimirAST(((NodoFor)raiz).getCuerpo());

                    }else  if (raiz instanceof NodoLlamada)
                    {
                        printSpaces();
                        System.out.println(((NodoLlamada)raiz).getNombre());
                        if(((NodoLlamada)raiz).getArgumento()!=null)
                        {
                            printSpaces();
                            System.out.println("argumento");
                            imprimirAST(((NodoLlamada)raiz).getArgumento());
                        }
                    }else  if (raiz instanceof NodoReturn)
                    {
                                                                       
                         imprimirAST(((NodoReturn)raiz).getValor());
                         
                    }
/*agregando al arbol*/
		    raiz = raiz.getHermanoDerecha();
		  }
		  sangria-=2;
		}

/* Imprime espacios con sangria */
static void printSpaces()
{ int i;
  for (i=0;i<sangria;i++)
	  System.out.print(" ");
}

/* Imprime informacion de los nodos */
static void imprimirNodo( NodoBase raiz )
{
	if(	raiz instanceof NodoRepeat
		||	raiz instanceof NodoLeer
		||	raiz instanceof NodoEscribir  ){
		System.out.println("palabra reservada: "+ raiz.getClass().getName());
	}
	
	if(	raiz instanceof NodoAsignacion )
		System.out.println(":=");
	
	if(	raiz instanceof NodoOperacion ){
		tipoOp sel=((NodoOperacion) raiz).getOperacion();
		if(sel==tipoOp.menor)
			System.out.println("<"); 
		if(sel==tipoOp.igual)
			System.out.println("=");
		if(sel==tipoOp.mas)
			System.out.println("+");
		if(sel==tipoOp.menos)
			System.out.println("-");
		if(sel==tipoOp.por)
			System.out.println("*");
		if(sel==tipoOp.entre)
			System.out.println("/");
	}

	if(	raiz instanceof NodoValor ){
		System.out.println("NUM, val= "+ ((NodoValor)raiz).getValor());
	}

	if(	raiz instanceof NodoIdentificador ){
		System.out.println("ID, nombre= "+ ((NodoIdentificador)raiz).getNombre());
	}

}
}
