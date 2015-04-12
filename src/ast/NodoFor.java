<<<<<<< HEAD
package ast;

public class NodoFor extends NodoBase {
    
    private NodoBase inicializacion;
    private NodoBase condicion;
    private NodoBase asignacion;
    private NodoBase cuerpo;
    
    public NodoFor(NodoBase inicializacion, NodoBase condicion, NodoBase asignacion, NodoBase cuerpo)
    {
        super();
        this.inicializacion = inicializacion;
        this.condicion = condicion;
        this.asignacion = asignacion;
        this.cuerpo = cuerpo;
    }

    public NodoBase getInicializacion() {
        return inicializacion;
    }

    public NodoBase getCondicion() {
        return condicion;
    }

    public NodoBase getAsignacion() {
        return asignacion;
    }

    public NodoBase getCuerpo() {
        return cuerpo;
    }
    
}
=======
package ast;

public class NodoFor extends NodoBase {
    
    private NodoBase inicializacion;
    private NodoBase condicion;
    private NodoBase asignacion;
    private NodoBase cuerpo;
    
    public NodoFor(NodoBase inicializacion, NodoBase condicion, NodoBase asignacion, NodoBase cuerpo)
    {
        super();
        this.inicializacion = inicializacion;
        this.condicion = condicion;
        this.asignacion = asignacion;
        this.cuerpo = cuerpo;
    }

    public NodoBase getInicializacion() {
        return inicializacion;
    }

    public NodoBase getCondicion() {
        return condicion;
    }

    public NodoBase getAsignacion() {
        return asignacion;
    }

    public NodoBase getCuerpo() {
        return cuerpo;
    }
    
}
>>>>>>> origin/master
