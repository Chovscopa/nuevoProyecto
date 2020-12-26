package sa;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")

public class RecursoHola {
	
	private static int contador;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String saludoContador(){

		contador++;

		return "Bienvenido a CRUD RESTful " + "Contador de visitas: " + contador;

	}
}