package hamburguesas.modelo;
import java.io.Serializable;

public class ingrediente implements Serializable{

	
	private String nombre;
	
	private int costoAdicional;
	
	
	public ingrediente(String elNombre, int ElcostoAdicional) {
		
		this.nombre = elNombre;
		this.costoAdicional = ElcostoAdicional;
		
		
	}
	

	public String getNombre() {
		return nombre;
		
		
	}
	
	
	public int getCostoAdicional() {
		return costoAdicional;
		
	}
	

	
	
	
	
	
}


