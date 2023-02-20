package hamburguesas.consola;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


import hamburguesas.modelo.Combo;
import hamburguesas.modelo.Pedido;
import hamburguesas.modelo.ProductoAjustado;
import hamburguesas.modelo.ProductoMenu;
import hamburguesas.modelo.Restaurante;
import hamburguesas.modelo.ingrediente;

public class Aplicacion {

	private Restaurante infoRestaurante;
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		Aplicacion aplicacion = new Aplicacion();
		aplicacion.ejecutarAplicacion();
	}
	
	public void ejecutarAplicacion() throws FileNotFoundException, IOException, ClassNotFoundException
	{
		System.out.println("Restaurante El Corral");

		boolean continuar = true;
		while (continuar)
		{
			try
			{
				mostrarMenu();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 1)
					ejecutarCargarInfoRestaurante();
				else if (opcion_seleccionada == 2)
					ejecutarOpcionDos();
				else if (opcion_seleccionada == 3)
					ejecutarOpcionTres();
				else if (opcion_seleccionada == 4)
					ejecutarOpcionCuatro();
				else if (opcion_seleccionada == 5)
					ejecutarOpcionCinco();
				else if (opcion_seleccionada == 7)
				{
					System.out.println("Saliendo de la aplicación ...");
					continuar = false;
				}
				else if (infoRestaurante == null)
				{
					System.out.println("Para poder ejecutar esta opción primero debe cargar los datos del Restaurante.");
				}
				else
				{
					System.out.println("Por favor seleccione una opción válida.");
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("Debe seleccionar uno de los números de las opciones.");
			}
			}
		}
	
	public void imprimir() {
		for (ingrediente i : infoRestaurante.getIngredientes())
		{
			System.out.print(i.getNombre());
			System.out.print(i.getCostoAdicional());
			System.out.print("\n");
			
			
		}
	}
	
	private void ejecutarOpcionDos() {
		String nombre = input("Indique el nombre con el cual se registrará el pedido ");
		String direccion = input("Indique su dirección de residencia ");
		infoRestaurante.iniciarPedido(nombre, direccion);
		int opcion = Integer.parseInt(input("Si desea ver el menu de productos individuales, presione 1. Si desea ver el menu de combos, presione 2."));
		if (opcion == 1) {
			System.out.print("Menu por separado: " + "\n");
			int nr_opcion = 1;
			for (ProductoMenu element: infoRestaurante.getMenuBase()) {
			System.out.print("Opción " + Integer.toString(nr_opcion) + ": " + element.getNombre() + " " + Integer.toString(element.getPrecio()) + "$" + "\n");
			nr_opcion += 1;

			
		}
			int indice = Integer.parseInt(input("Seleccione el numero del producto que desee: ")) - 1;
			ProductoMenu producto = infoRestaurante.getMenuBase().get(indice);
			int a = Integer.parseInt(input(("Oprima 1 si quiere hacer modificaciones al producto. De lo contrario oprima 2.")));
			
			if (a==1) {
			ProductoAjustado ajustado = new ProductoAjustado(producto);
			a = Integer.parseInt(input(("Si desea agregar un ingrediente al producto, oprima 1. De lo contrario, oprima 2.")));
			if (a==1) {
				
				System.out.print("Menú de ingredientes: " + "\n");
				nr_opcion = 1;
				for (ingrediente element: infoRestaurante.getIngredientes()) {
				System.out.print("Opción " + Integer.toString(nr_opcion) + ": " + element.getNombre() + " " + Integer.toString(element.getCostoAdicional()) + "$" + "\n");
				nr_opcion += 1;
			}
				indice = Integer.parseInt(input("Seleccione el numero del ingrediente que desee agregar: ")) - 1;
				ingrediente adicion = infoRestaurante.getIngredientes().get(indice);
				ajustado.addIngrediente(adicion);
			}
			a = Integer.parseInt(input(("Si desea eliminar un ingrediente al producto, oprima 1. De lo contrario, oprima 2.")));
				if (a==1) {
				
				System.out.print("Menú de ingredientes: " + "\n");
				nr_opcion = 1;
				for (ingrediente element: infoRestaurante.getIngredientes()) {
				System.out.print("Opción " + Integer.toString(nr_opcion) + ": " + element.getNombre() + " " + Integer.toString(element.getCostoAdicional()) + "$" + "\n");
				nr_opcion += 1;
			}
				indice = Integer.parseInt(input("Seleccione el numero del ingrediente que desee eliminar: ")) - 1;
				ingrediente eliminacion = infoRestaurante.getIngredientes().get(indice);
				ajustado.removeIngrediente(eliminacion);
				}
			
				infoRestaurante.getPedidoEnCurso().agregarProducto(ajustado);
			}
			
			else {
				infoRestaurante.getPedidoEnCurso().agregarProducto(producto);
				
			}
		}
		else if (opcion == 2) {
			System.out.print("Menu de combos: " + "\n");
			int nr_opcion = 1;
			for (Combo element: infoRestaurante.getCombos()) {
				System.out.print("Opción " + Integer.toString(nr_opcion) + ": " + element.getNombre() + " " + Integer.toString(element.getPrecio()) + "$" + "\n");
				nr_opcion += 1;
			}
			int indice = Integer.parseInt(input("Seleccione el numero del combo que desee: ")) - 1;
			Combo producto = infoRestaurante.getCombos().get(indice);
			int a = Integer.parseInt(input(("Oprima 1 si añadir un producto al combo. De lo contrario oprima 2.")));
			if (a==1) {
				System.out.print("Menu por separado: " + "\n");
				nr_opcion = 1;
				for (ProductoMenu element: infoRestaurante.getMenuBase()) {
				System.out.print("Opción " + Integer.toString(nr_opcion) + ": " + element.getNombre() + " " + Integer.toString(element.getPrecio()) + "$" + "\n");
				nr_opcion += 1;

				
			}
				indice = Integer.parseInt(input("Seleccione el numero del producto que desee: ")) - 1;
				ProductoMenu agregado = infoRestaurante.getMenuBase().get(indice);
				producto.agregarItemACombo(agregado);
			}
			
			infoRestaurante.getPedidoEnCurso().agregarProducto(producto);
		}
		
		
	}
	
	private void ejecutarOpcionTres() {
		System.out.print( infoRestaurante.getPedidoEnCurso().generarTextoFactura());
		
	}
	
	private void ejecutarOpcionCuatro() throws IOException {
		infoRestaurante.getPedidoEnCurso().guardarFactura();
		infoRestaurante.GuardarObjetoEnFile();
	}
	
	
	
	public void mostrarMenu()
	{
		System.out.println("\nOpciones de la aplicación\n");
		System.out.println("1. Cargar información del Restaurante");
		System.out.println("2. Iniciar pedido");
		System.out.println("3. imprimir factura");
		System.out.println("4. Cerrar y guardar pedido");
		System.out.println("5. Leer historial de pedidos");
		System.out.println("7. Salir de la aplicación");
		
	}
	
	private void ejecutarCargarInfoRestaurante() throws FileNotFoundException, IOException {
		this.infoRestaurante = new Restaurante();
		infoRestaurante.CargarProductos("./data/menu.csv");
		infoRestaurante.CargarIngredientes("./data/ingredientes.csv");
		infoRestaurante.CargarCombos("./data/combos.csv");
		
	}
	
	private void ejecutarOpcionCinco() throws FileNotFoundException, ClassNotFoundException, IOException {
		infoRestaurante.LeerObjetoEnFile();
		for (Pedido element: infoRestaurante.getPedidosGuardados()) {
			System.out.print(element.getIdPedido());
			
		}
		//No esta terminado, se iba a usar un lector de Objetos para hacerlo y luego aplicar el equals para saber si es un pedido identico al actual
		
		
	}
	
	
	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
	
	
}
