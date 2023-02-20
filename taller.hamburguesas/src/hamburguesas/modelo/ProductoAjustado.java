package hamburguesas.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class ProductoAjustado implements Producto , Serializable{
	
	private String nombre;
	
	private int precioOriginal;
	
	private List<ingrediente> agregados;
	
	private List<ingrediente> eliminados;
	
	
	
	
	
	
	
	

	public ProductoAjustado(ProductoMenu base) {
		this.nombre = base.getNombre();
		this.precioOriginal = base.getPrecio();
		this.agregados = new ArrayList<ingrediente>();
		this.eliminados = new ArrayList<ingrediente>();
		
	}
	

	
	
	public void addIngrediente(ingrediente elemento) {
		agregados.add(elemento);
		
	}
	
	public void removeIngrediente(ingrediente elemento) {
		eliminados.add(elemento);
	}
	
	
	public int getPrecioBase() {
		return precioOriginal;
	}

	@Override
	public int getPrecio() {
		// TODO Auto-generated method stub
		int resultado = precioOriginal;
		for (ingrediente elemento:agregados){
			resultado += elemento.getCostoAdicional();
		}
		for (ingrediente elemento:eliminados) {
			resultado -= elemento.getCostoAdicional();
		}
		return resultado;
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return nombre;
	}

	@Override
	public String generarTextoFactura() {
		// TODO Auto-generated method stub
		String elementosAgregados = "Adiciones: ";
		String elementosEliminados = "Eliminaciones: ";
		if (agregados.size() > 0){
			for (ingrediente elemento:agregados) {
					elementosAgregados += elemento.getNombre() + " ";
				}
			
			}
			else {
				elementosAgregados = "0 Elementos agregados";
			}
		if (eliminados.size()>0) {
			for (ingrediente elemento:eliminados) {
				elementosEliminados += elemento.getNombre() + " ";
			}

			}
		else {
			elementosEliminados = "0 Elementos eliminados";
		}
		
		return nombre + " " + elementosAgregados + " " + elementosEliminados + "\n";
		
	}
		
		
}
