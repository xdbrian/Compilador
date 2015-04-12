package ast;

public class NodoValor extends NodoBase {
    
    private Object valor;
    private tipoVariable tipo;

    public NodoValor(Object valor, tipoVariable tipo) {
        super();
        this.valor = valor;
        this.tipo = tipo;
    }

    public NodoValor() {
        super();
    }

    public Object getValor() {
        return valor;
    }

    public tipoVariable getTipo() {
        return tipo;
    }
    
}
