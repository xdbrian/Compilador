package ast;

public class NodoVariable extends NodoBase {
    
    private String nombre;
    private int tamano;
    
    public String getNombre() {
        return nombre;
    }

    public int getTamano() {
        return tamano;
    }
    
    public NodoVariable(String nombre, int tamano) {
        super();
        this.nombre = nombre;
        this.tamano = tamano;
    }
    
    public NodoVariable(String nombre) {
        super();
        this.nombre = nombre;
    }
    
}
