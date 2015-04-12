package ast;

public class NodoLlamada extends NodoBase{
    
    private String nombre;
    private NodoBase argumento;
    
    public NodoLlamada(String nombre, NodoBase argumento)
    {
        super();
        this.nombre = nombre;
        this.argumento = argumento;
    }
    
    public NodoLlamada(String nombre)
    {
        super();
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public NodoBase getArgumento() {
        return argumento;
    }
    
}
