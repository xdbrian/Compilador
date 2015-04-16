package ast;

public class NodoBase {
    
    private NodoBase HermanoDerecha;    

    public NodoBase(NodoBase hermanoDerecha) {
        super();
        HermanoDerecha = hermanoDerecha;
    }

    public NodoBase() {
        super();
        HermanoDerecha = null;
    }

    public NodoBase getHermanoDerecha() {
        return HermanoDerecha;
    }

    public void setHermanoDerecha(NodoBase hermanoDerecha) {
        HermanoDerecha = hermanoDerecha;
    }

    public boolean TieneHermano() {
        return (HermanoDerecha != null);
    }    
}
