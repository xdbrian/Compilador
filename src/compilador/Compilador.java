package compilador;

import ast.*;
import java_cup.runtime.*;

public class Compilador {
	
	

	public static void main(String[] args) throws Exception {
		@SuppressWarnings("deprecation")
		SymbolFactory sf = new DefaultSymbolFactory();
		parser parser_obj;
		if (args.length==0) 
			parser_obj=new parser(new Scanner(System.in,sf),sf);
		else 
			parser_obj=new parser(new Scanner(new java.io.FileInputStream(args[0]),sf),sf);

		UtGen.debug=true; //NO muestro mensajes de depuracion del generador (UTGen) para que el codigo sea compatible con la version visual de la TM
		//Para ver depuracion de analisis sintactico se debe ir al parser.java y colocar modoDepuracion en true
		parser_obj.parse();
		NodoBase root=parser_obj.action_obj.getASTroot();
		System.out.println();
		System.out.println("IMPRESION DEL AST GENERADO");
		System.out.println();
		ast.Util.imprimirAST(root);
		TablaSimbolos ts = new TablaSimbolos();
		ts.cargarTabla(root);
		ts.ImprimirClaves();
	    //REALIZAR ACA ANALISIS SEMANTICO
		Generador.setTablaSimbolos(ts);
		Generador.generarCodigoObjeto(root);
	}

}
