package hamburguesas.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Restaurante implements Serializable {

	private List<ingrediente> Ingredientes;
	private List<ProductoMenu> ProductosBase;
	private List<Combo> Combos;
	private Pedido pedidoEnCurso;
	private HashMap<String,ProductoMenu> mapaProductos;
	private List<Pedido> PedidosGuardados;
	
	public Restaurante() {
		this.Ingredientes = new ArrayList<ingrediente>();
		this.ProductosBase = new ArrayList<ProductoMenu>();
		this.Combos = new ArrayList<Combo>();
		this.pedidoEnCurso = null;
		this.PedidosGuardados = new ArrayList<Pedido>();
		
	}
	
	public void iniciarPedido(String NombreCliente, String DireccionCliente) {
		this.pedidoEnCurso = new Pedido(NombreCliente, DireccionCliente);
		
	}
	
	
	
	public void cerrarYguardarPedido() {
		//de la clase Pedido hacer generattextofactura y guardarfactura, escribir en el archivo de facturas
		//para guardar la orden, mostrar la factura en las app? no void
	}
	
	public List<ProductoMenu> getMenuBase(){
		return ProductosBase;
		//imprimir correctamente la información para el usuario
	}
	
	public List<ingrediente> getIngredientes(){
		return Ingredientes;
		
	}
	
	public List<Combo> getCombos(){
		return Combos;
		}
	
	public Pedido getPedidoEnCurso() {
		return pedidoEnCurso;
	}
	
	
	
	public void CargarProductos(String archivoProductos)throws FileNotFoundException, IOException
	 {
		BufferedReader lector = new BufferedReader(new FileReader(archivoProductos));
		String linea = lector.readLine();
		this.mapaProductos = new HashMap<String, ProductoMenu>();
		while (linea != null) {
			String[] partes = linea.split(";");
			String nombre = partes[0];
			int precio = Integer.parseInt(partes[1]);
			ProductoMenu producto = new ProductoMenu(nombre, precio);
			ProductosBase.add(producto);
			mapaProductos.put(nombre, producto);
			linea = lector.readLine();
		}
		lector.close();

		
	}
	
	public void CargarIngredientes(String archivoIngredientes)throws FileNotFoundException, IOException{
		BufferedReader lector = new BufferedReader(new FileReader(archivoIngredientes));
		String linea = lector.readLine();
		while (linea != null) {
			String[] partes = linea.split(";");
			String nombre = partes[0];
			int precio = Integer.parseInt(partes[1]);
			ingrediente ingrediente = new ingrediente(nombre, precio);
			Ingredientes.add(ingrediente);
			linea = lector.readLine();
		}
		lector.close();
	}

	public void CargarCombos(String archivoCombos)throws FileNotFoundException, IOException{
		BufferedReader lector = new BufferedReader(new FileReader(archivoCombos));
		String linea = lector.readLine();
		while (linea != null) {
			String[] partes = linea.split(";");
			String nombreCombo = partes[0];
			int descuento = (Integer.parseInt(partes[1].replace("%", "")));
			Combo combo = new Combo(nombreCombo, descuento);
			for (int i = 2; i<=4;i++) {
				String nombre = partes[i];
				ProductoMenu producto = mapaProductos.get(nombre);
				combo.itemsComboOriginales(producto);
				
			}
			Combos.add(combo);
			linea = lector.readLine();
			
			
		}
		lector.close();
	}
	
	public void CerrarYGuardarPedido() throws IOException {
		pedidoEnCurso.guardarFactura();
	}

	
	public boolean HayPedidoIdentico() throws IOException {
		BufferedReader lector = new BufferedReader (new FileReader("./data/facturas.txt"));
		String linea = lector.readLine();
		while(linea != null) {
			
		}
		
		for (Producto element: pedidoEnCurso.getItems()) {
			element.equals(element);
		}
		return true;
	}
	
	
	public void GuardarObjetoEnFile() throws FileNotFoundException, IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("objects.txt"));
		out.writeObject(pedidoEnCurso);
		
	}
	
	public void LeerObjetoEnFile() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream (new FileInputStream("objects.txt"));
		Pedido guardado = (Pedido) in.readObject();
		PedidosGuardados.add(guardado);
		
		
	}
	
	public List<Pedido> getPedidosGuardados(){
		return PedidosGuardados;
	}
	
	
}
	
	
	

