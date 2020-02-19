import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import funciones.Funciones;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

@WebServlet("/ConsultaServlet")
public class ConsultaServlet extends HttpServlet {
	// El m etodo doGet() se ejecuta una vez por cada petici on HTTP GET.
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		embudo(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		embudo(request, response);
	}

	
	public void embudo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		HttpSession sesion=request.getSession();
		
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		
		PrintWriter out2 = response.getWriter();
	
		
		try {
			
			//////////////// FORMULARIO ///////////////
			if (request.getParameter("enviar") == null) {
				
				formulario(out2);
				
			} else {
				
				int contador=0;
				/*
				request.getSession().setAttribute(request.getParameter("Nombre"), count);
				int contador=(int) request.getSession().getAttribute("contador");
				*/
				String errores="";
				Connection conn = null;
				Statement stmt = null;
				conn = Funciones.conexion();
				if(contador!=3) {
					out.println(contador);
				
					if (Funciones.checkUsuario1(conn,request.getParameter("Nombre"),request.getParameter("Clave"))) {
						
						out.println("<p>Bienvenido " + request.getParameter("Nombre") + "</p>");
						
						
						sesion.setAttribute("nombre", request.getParameter("Nombre"));
						out.println("<p><a href='Bienvenido'>Bienvenido</a></p>");
						
						
					} else {
						
						if(request.getParameter("Nombre")=="") {
							errores+="Debes introducir nombre"+"<br>";
						}
						else {
							sesion.setAttribute("nombre",request.getParameter("Nombre"));
						}
						if(request.getParameter("Clave")=="") {
							errores+="Debes introducir contraseña"+"<br>";
						}
						contador++;
						//REPINTADO
						repintado(out,errores);
						
						out.println(contador);//////////////////////////////////
					
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
				else {
					RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/Cambio");
					dispatcher.forward(request, response);
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
		out2.println("<fieldset>\n" + "<legend>Formulario de login</legend><br/>");
		out2.println("<form action=\"ConsultaServlet\" method=\"post\" ");
		out2.println("<p style=\"color:red\"/>"+errores+"<br></p>");
		out2.println("<label for=\"nombre\">Nombre</label> <input type=\"text\" name=\"Nombre\" /> <br/> <br/>");
		out2.println("<label for=\"clave\">Clave</label> <input type=\"password\" name=\"Clave\" /> <br/> <br/>");
		out2.println("<p><a href='Cambio'>¿Cambio de contraseña?</a></p>");
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
		out2.println("<fieldset>\n" + "<legend>Formulario de login</legend><br/>");
		out2.println("<form action=\"ConsultaServlet\" method=\"post\" ");
		
		out2.println("<label for=\"nombre\">Nombre</label> <input type=\"text\" name=\"Nombre\" /> <br/> <br/>");
		out2.println("<label for=\"clave\">Clave</label> <input type=\"password\" name=\"Clave\" /> <br/> <br/>");
		out2.println("<p><a href='Cambio'>¿Cambio de contraseña?</a></p>");
		out2.println("<input type=\"submit\" value=\"Enviar\" name=\"enviar\" />");

		out2.println("</form>\n" + "</fieldset>");
		out2.println("</body>");
		out2.println("</html>");
	}

	
	
	
}

/* 
 * create database tienda5 CREATE TABLE usuarios( nombre VARCHAR(20) NOT NULL
 * PRIMARY KEY UNIQUE, pass VARCHAR(200) NOT NULL, pregunta VARCHAR(200),
 * respuesta VARCHAR(200)); INSERT INTO usuarios VALUES('Ovidio','1234','Nombre
 * de tu padre','Eduardo'); INSERT INTO usuarios VALUES('juan','1234','Nombre de
 * tu padre','Perico'); ; delete from usuarios where nombre='Ovidio';
 */