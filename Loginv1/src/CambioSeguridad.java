

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/CambioSeguridad")
public class CambioSeguridad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CambioSeguridad() {
        super();
       
    }

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		embudo(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		embudo(request, response);
	}
	
	protected void embudo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		

		PrintWriter out2 = response.getWriter();
		String usuario=(String) request.getSession().getAttribute("nombre");
		try {
			//////////////// FORMULARIO ///////////////
			if (request.getParameter("enviar") == null) {
				out.println(usuario);
				formulario(out2);
				
			} else {
				
				String errores="";
				Connection conn = null;
				Statement stmt = null;
				conn = conexion();
				Connection conn2=null;
				conn2=conexion();
				
				if (updateRegistro(conn2,usuario,request.getParameter("clavenueva"),request.getParameter("confirmarclavenueva"))) {
					
						
					
						out.println("<p>Contraseña cambiada, " + usuario + "</p>");
						
						HttpSession sesion=request.getSession();
						
						sesion.setAttribute("usuario", request.getParameter("Nombre"));
						out.println("<p><a href='LogOut'>Logout</a></p>");
						
					
						
					
				} else {
					
					if(request.getParameter("Nombre")=="") {
						errores+="Debes introducir nombre"+"<br>";
					}
					if(request.getParameter("Clave")=="") {
						errores+="Debes introducir contraseña"+"<br>";
					}
					errores+="No coinciden las contraseñas";
					
					
					//REPINTADO
					repintado(out,errores);
				
					try {
	
						if (stmt != null)
							stmt.close();
						if (conn != null)
							conn.close();
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			
		} finally {
			out.close();
			out2.close();
			
		}
		
	}
	private void repintado(PrintWriter out2,String errores) {
		out2.println("<html>");
		out2.println("<head>");
		out2.println("<meta charset=\"UTF-8\">");
		out2.println("</head>");
		out2.println("<body>");
		out2.println("<fieldset>\n" + "<legend>Cambio de Contraseña Seguridad</legend><br/>");
		out2.println("<form action=\"CambioSeguridad\" method=\"post\" ");
		out2.println("<p style=\"color:red\"/>"+errores+"<br>");
	
		out2.println("<label for=\"clavenueva\">Contraseña nueva</label> <input type=\"password\" name=\"clavenueva\" /> <br/> <br/>");
		out2.println("<label for=\"confirmarclavenueva\">Confirmar la contraseña nueva</label> <input type=\"password\" name=\"confirmarclavenueva\" /> <br/> <br/>");
		out2.println("<input type=\"submit\" value=\"Enviar\" name=\"enviar\" />");

		out2.println("</form>\n" + "</fieldset>");
		out2.println("</body>");
		out2.println("</html>");
	}
	private void formulario(PrintWriter out2) {
		out2.println("<html>");
		out2.println("<head>");
		out2.println("<meta charset=\"UTF-8\">");
		out2.println("</head>");
		out2.println("<body>");
		out2.println("<fieldset>\n" + "<legend>Cambio de Contraseña Seguridad</legend><br/>");
		out2.println("<form action=\"CambioSeguridad\" method=\"post\" ");

		out2.println("<label for=\"clavenueva\">Contraseña nueva</label> <input type=\"password\" name=\"clavenueva\" /> <br/> <br/>");
		out2.println("<label for=\"confirmarclavenueva\">Confirmar la contraseña nueva</label> <input type=\"password\" name=\"confirmarclavenueva\" /> <br/> <br/>");
		out2.println("<input type=\"submit\" value=\"Enviar\" name=\"enviar\" />");

		out2.println("</form>\n" + "</fieldset>");
		out2.println("</body>");
		out2.println("</html>");
	}

	private Connection conexion() {
		Connection conn = null;
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();

			String userName = "root";
			String password = "";

			String url = "jdbc:mysql://localhost/tienda5";
			conn = DriverManager.getConnection(url, userName, password);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return conn;
	}
	
	private boolean updateRegistro(Connection conn,String nombre,String clavenueva,String confirmarclavenueva) throws SQLException {
		Statement stmt = null;
		boolean sw = false;
		stmt = conn.createStatement();
		
		String sqlStr = "UPDATE usuarios SET pass = '"+confirmarclavenueva+"'"+"WHERE nombre ='"+nombre+"'";
		if(clavenueva.equals(confirmarclavenueva)) {
			int rset = stmt.executeUpdate(sqlStr);
			
			sw=true;
		}else {
			
		}
		return sw;
	}

}
