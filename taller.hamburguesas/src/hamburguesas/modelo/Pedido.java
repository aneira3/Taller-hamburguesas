package hamburguesas.modelo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Pedido implements Serializable {

	
	private int numeroPedidos;
	
	private int idPedido;
	
	private String nombreCliente;
	
	private String direccionCliente;
	
	private List <Producto> itemsProducto;
	
	
	public Pedido(String nombre, String direccion) {
		this.nombreCliente = nombre;
		this.direccionCliente = direccion;
		this.idPedido = ThreadLocalRandom.current().nextInt(1,10000000);
		this.itemsProducto = new ArrayList<Producto>();

	}
	
	
	public int getIdPedido(){
		return idPedido;
		
	}
	
	public void agregarProducto(Producto nuevoItem) {
		itemsProducto.add(nuevoItem);
	}
	
	public List<Producto> getItems(){
		return itemsProducto;
	}
	
	private int getPrecioNetoPedido() {
		int resultado = 0;
		for (Producto element: itemsProducto) {
			resultado += element.getPrecio();
		}
		return resultado;
	}
	
	private int getIVAPedido() {
		return (int) (getPrecioNetoPedido() * (19/100.0));
	}
	
	private int getPrecioTotalPedido() {
		return getPrecioNetoPedido() + getIVAPedido();
	}
	
	public String generarTextoFactura() {
		String texto1 = "Nombre del cliente: " + nombreCliente + " Dirección del cliente: " + direccionCliente + " ID del pedido: " + 
	    Integer.toString(idPedido) + "\n";
		String texto2 = "";
		for (Producto element: itemsProducto) {
			texto2 += element.generarTextoFactura();
		}
		String texto3 = "Precio Neto del pedido: " + getPrecioNetoPedido() + "$" + "\n" + "IVA del 19% aplicado: " + getIVAPedido()
		+ "$" + "\n" + "Precio total facturado: " + getPrecioTotalPedido() + "$";
		return texto1 + texto2 + texto3;
	}
	
	
	
	public void guardarFactura() throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("./data/facturas.txt", true));
		writer.write("\n" + "\n" + "Factura: " + "\n");
		writer.write(generarTextoFactura());
		writer.close();
		
	}
	
	
}
