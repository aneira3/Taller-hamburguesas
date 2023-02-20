package hamburguesas.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Combo implements Producto , Serializable{

	private int descuento;
	
	private String nombreCombo;
	
	private List<ProductoMenu> elementosCombo;
	
	private List<ProductoMenu> elementosAgregados;
	
	
	public Combo (String nombre, int eldescuento) {
		
		this.descuento = eldescuento;
		
		this.nombreCombo = nombre;
	
		this.elementosCombo = new ArrayList<ProductoMenu>();
		
		this.elementosAgregados = new ArrayList<ProductoMenu>();
		
	}
	
	
	public void itemsComboOriginales (ProductoMenu producto) {
		elementosCombo.add(producto);
		
	}
	
	public String gtNombre() {
		return nombreCombo;	
		
	}
	
	public double gtPrecio() {
		int resultado = 0;
		for (ProductoMenu element: elementosCombo) {
			resultado += element.getPrecio();
		}
		double precioDescuento = resultado * descuento;
		return precioDescuento;
		
	}
	
	public List<ProductoMenu> getelementosCombo(){
		return elementosCombo;
	}
	
	
	
	public void agregarItemACombo(ProductoMenu itemCombo) {
		elementosAgregados.add(itemCombo);
	}


	@Override
	public int getPrecio() {
		// TODO Auto-generated method stub
		int resultado = 0;
		for (ProductoMenu element: elementosCombo) {
			resultado += element.getPrecio();
		}
		double precioDescuento = (resultado * (1-(descuento/100.0)));
		
		if (elementosAgregados.size()==0) {
			return  (int) precioDescuento;
		}
		else {
			for (ProductoMenu element: elementosAgregados) {
				precioDescuento += element.getPrecio();
			}
			return (int) precioDescuento;
		}
	}


	
	
	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return nombreCombo;
	}


	@Override
	public String generarTextoFactura() {
		// TODO Auto-generated method stub
		if (elementosAgregados.size()==0) {
			return nombreCombo + "\n";
		}
		else {
			String agregados = "";
			for (ProductoMenu element: elementosAgregados) {
				agregados += element.getNombre() + " ";
			}
			
			return nombreCombo + " con adición de " + agregados + "\n";
		}
	}
	
	

}



	
