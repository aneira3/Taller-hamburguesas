package hamburguesas.modelo;

public class ingrediente {

	
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


