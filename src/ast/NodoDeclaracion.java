
package ast;

public class NodoDeclaracion extends NodoBase {
    
    private tipoVariable tipo;   
    private NodoBase variable;
    private int numLinea;
    
    public tipoVariable getTipo() {
        return tipo;
    }

    public NodoBase getVariable() {
        return variable;
    }
    
    public NodoDeclaracion(NodoBase variable, tipoVariable tipo, int numLinea)
    {
        super();
        this.variable = variable;
        this.tipo = tipo;
        this.numLinea = numLinea;
    }
    
    public NodoDeclaracion(NodoVariable variable)
    {
        super();
        this.variable = variable;
    }

    public int getNumLinea() {
        return numLinea;
    }
    
}
