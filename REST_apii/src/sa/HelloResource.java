package sa;


 
 
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
 
@Path("/")

public class HelloResource {
	private int contador=0;
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String direBonjour() throws ClassNotFoundException {
    	Conexion.abrirConexion();
    	contador++;
        return "Bienvenido a CRUD RESTful 2" + contador;
        
    }
}