
package ast;

public class NodoFuncion extends NodoBase {
    
    private NodoBase cuerpo;
    private tipoVariable tipoRetorno;
    private NodoBase argumento;
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public NodoBase getArgumento() {
        return argumento;
    }

    public tipoVariable getTipoRetorno() {
        return tipoRetorno;
    }

    public NodoBase getCuerpo() {
        return cuerpo;
    }
    
    public NodoFuncion(tipoVariable tipoRetorno, NodoBase cuerpo, NodoBase argumento, String nombre)
    {
        super();
        this.tipoRetorno = tipoRetorno;
        this.cuerpo = cuerpo;
        this.argumento = argumento;
        this.nombre = nombre;
    }
    
    public NodoFuncion(tipoVariable tipoRetorno, NodoBase cuerpo, String nombre)
    {
        super();
        this.tipoRetorno = tipoRetorno;
        this.cuerpo = cuerpo;        
        this.nombre = nombre;
    }
    
    public NodoFuncion(NodoBase cuerpo)
    {
        super();
        this.tipoRetorno = tipoVariable.vacio;
        this.cuerpo = cuerpo;
    }
    
    public NodoFuncion()
    {
        super();        
    }
    
}
