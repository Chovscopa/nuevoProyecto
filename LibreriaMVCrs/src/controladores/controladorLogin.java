package controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelos.*;


@WebServlet("/controladorLogin")
public class controladorLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public controladorLogin() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion=request.getSession(false);
		String ruta="/login.jsp";
		
		if(sesion!=null) {
			
			
			
			if(sesion.getAttribute("cont")!=null) {
				request.setAttribute("no", "<p>Login incorrecto</p>");
			}
		}
		
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher(ruta);
		dispatcher.forward(request,response);
	}

}
/*
<Resource name="jdbc/tiendalibros" auth="Container" type="javax.sql.DataSource"
maxActive="100" maxIdle="30" maxWait="10000" removeAbandoned="true"
username="root" password="admin" driverClassName="com.mysql.jdbc.Driver"
url="jdbc:mysql://localhost:3306/tiendalibros" />
*/