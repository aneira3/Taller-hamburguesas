package hamburguesas.modelo;

import java.io.Serializable;

public class ProductoMenu implements Producto, Serializable {

	
	private String nombre;
	
	private int precioBase;
	
	
	public ProductoMenu (String elNombre, int elprecioBase) {
		
		this.nombre = elNombre;
		this.precioBase = elprecioBase;
		
		
	}
	

	@Override
	public int getPrecio() {
		// TODO Auto-generated method stub
		return precioBase;
	}


	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return nombre;
	}


	@Override
	public String generarTextoFactura() {
		// TODO Auto-generated method stub
		return nombre + "\n";
	}
	
	
}
