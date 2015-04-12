
package ast;

public class NodoDeclaracion extends NodoBase {
    
    private tipoVariable tipo;   
    private NodoBase variable;
    
    public tipoVariable getTipo() {
        return tipo;
    }

    public NodoBase getVariable() {
        return variable;
    }
    
    public NodoDeclaracion(NodoBase variable, tipoVariable tipo)
    {
        super();
        this.variable = variable;
        this.tipo = tipo;
    }
    
    public NodoDeclaracion(NodoVariable variable)
    {
        super();
        this.variable = variable;
    }
}
