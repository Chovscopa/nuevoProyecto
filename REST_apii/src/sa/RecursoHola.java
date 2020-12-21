package sa;

/*

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")

public class HelloResource {
 // static contador;
private static ArrayList<Integer> contador = new ArrayList<>();


   @GET
   @Produces(MediaType.TEXT_PLAIN)
   public String direBonjour() throws ClassNotFoundException {
   	 {
   		contador.add(1);
   		
   	}
       return "Bienvenido a CRUD RESTful 2 " + contador.size();
       
   }
}
*/

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")

public class RecursoHola {
	
	private static int contador = 0;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String direBonjour() throws ClassNotFoundException {

		contador++;

		return "Bienvenido a CRUD RESTful 2 " + "Contador de visitas: " + contador;

	}
}