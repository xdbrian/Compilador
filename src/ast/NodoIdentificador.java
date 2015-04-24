package ast;

public class NodoIdentificador extends NodoBase {

    private String nombre;
    private NodoBase posicion;

    public NodoIdentificador(String nombre) {
        super();
        this.nombre = nombre;
    }
    
    public NodoIdentificador(String nombre, NodoBase posicion) {
        super();
        this.nombre = nombre;
        this.posicion = posicion;
    }

    public NodoIdentificador() {
        super();
    }

    public String getNombre() {
        return nombre;
    }

    public NodoBase getPosicion() {
        return posicion;
    }    
}
