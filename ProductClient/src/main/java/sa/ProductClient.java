package sa;

import java.util.Scanner;

import javax.ws.rs.client.*;
import javax.ws.rs.core.*;

import org.glassfish.jersey.client.ClientConfig;

public class ProductClient {

	private static String baseURI = "http://localhost:8080/REST_apiii/products";
	private static Scanner sc;

	static WebTarget getWebTarget() {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);

		return client.target(baseURI);
	}

	static void testList() {
		WebTarget target = getWebTarget();

		String response = target.request().accept(MediaType.APPLICATION_JSON).get(String.class);

		System.out.println(response);
	}

	static void testGet() {
		WebTarget target = getWebTarget();
		sc = new Scanner(System.in);
		System.out.println("Introduzca ID");
		String productId = sc.nextLine();
		Product product = target.path(productId).request().accept(MediaType.APPLICATION_JSON).get(Product.class);

		System.out.println(product);
	}

	static void testAdd() {
		WebTarget target = getWebTarget();
		sc = new Scanner(System.in);
		System.out.println("Introduzca nombre");
		String nombre = sc.nextLine();
		System.out.println("Introduzca precio");
		float precio = Float.parseFloat(sc.nextLine());
		Product product = new Product(nombre, precio);
		Response response = target.request().post(Entity.entity(product, MediaType.APPLICATION_JSON), Response.class);

		System.out.println(response.getLocation().toString());
	}

	private static void testUpdate() {
		WebTarget target = getWebTarget();
		sc = new Scanner(System.in);
		System.out.println("Introduzca nombre");
		String nombre = sc.nextLine();
		System.out.println("Introduzca precio");
		float precio = Float.parseFloat(sc.nextLine());
		Product product = new Product(nombre, precio);
		System.out.println("Introduzca ID");
		String productId = sc.nextLine();
		Response response = target.path(productId).request().put(Entity.entity(product, MediaType.APPLICATION_JSON),
				Response.class);
		System.out.println(response);
	}

	private static void testDelete() {
		WebTarget target = getWebTarget();
		System.out.println("Introduzca ID");
		sc = new Scanner(System.in);
		String productId = sc.nextLine();
		Response response = target.path(productId).request().delete(Response.class);
		System.out.println(response);
	}

	static void menu() {
		int opc=0 ;
		while(opc!=6) {
			System.out.println("********************************************");
			System.out.println("pulse 1 para LISTAR ");
			System.out.println("pulse 2 para LISTAR POR ID ");
			System.out.println("pulse 3 para AÑADIR ");
			System.out.println("pulse 4 para MODIFICAR ");
			System.out.println("pulse 5 para ELIMINAR ");
			System.out.println("pulse 6 para SALIR ");
			System.out.println("********************************************");
			sc = new Scanner(System.in);
			opc = sc.nextInt();
	
			switch (opc) {
	
				case 1:
					testList();
					break;
		
				case 2:
					testGet();
					break;
		
				case 3:
					testAdd();
					break;
		
				case 4:
					testUpdate();
					break;
		
				case 5:
					testDelete();
					break;
					
				case 6:
					System.out.println("adios ");
					break;
		
				default:
					break;
	
			}
		}

	}

	public static void main(String[] args) {
		menu();
	}

}
