package ast;

public class NodoBase {

    private NodoBase HermanoDerecha;
    private int numeroLinea;

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

    public void setNumeroLinea(int numeroLinea) {
        this.numeroLinea = numeroLinea;
    }

    public int getNumeroLinea() {
        return numeroLinea;
    }
}
