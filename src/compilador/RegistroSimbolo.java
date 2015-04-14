package compilador;

public class RegistroSimbolo {
	private String identificador;
 
	private int NumLinea;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
	private int DireccionMemoria;        
        private String tipo;
	
	public RegistroSimbolo(String tipo, int numLinea,
			int direccionMemoria) {
		super();
		
		NumLinea = numLinea;
		DireccionMemoria = direccionMemoria;
                
                this.tipo=tipo;
	}

	public String getIdentificador() {
		return identificador;
	}

	public int getNumLinea() {
		return NumLinea;
	}

	public int getDireccionMemoria() {
		return DireccionMemoria;
	}

	public void setDireccionMemoria(int direccionMemoria) {
		DireccionMemoria = direccionMemoria;
	}
}
