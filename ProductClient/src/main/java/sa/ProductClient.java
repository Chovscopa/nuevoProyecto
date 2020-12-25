package sa;

import javax.ws.rs.client.*;
import javax.ws.rs.core.*;

import org.glassfish.jersey.client.ClientConfig;

public class ProductClient {
	
	private static String baseURI = "http://localhost:8080/REST_apiii/products";
	
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
	    String productId = "3";
	    Product product = target.path(productId)
	                .request().accept(MediaType.APPLICATION_JSON)
	                .get(Product.class);
	     
	    System.out.println(product);       
	}
	public static void main(String[] args) {
		testList();

	}

}
