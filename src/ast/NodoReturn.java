package ast;

public class NodoReturn extends NodoBase{
    
    private NodoBase valor;
    
    public NodoReturn(NodoBase valor)
    {
        super();
        this.valor = valor;
    }

    public NodoBase getValor() {
        return valor;
    }
        
}
