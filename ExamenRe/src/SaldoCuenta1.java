

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import generacionDinamica.Datos;

/**
 * Servlet implementation class SaldoCuenta1
 */
@WebServlet("/SaldoCuenta1")
public class SaldoCuenta1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public SaldoCuenta1() {
        super();
       
    }

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		embudo(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		embudo(request, response);
	}
	
	protected void embudo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HttpSession session = request.getSession();
		//Datos.cargarDatosOrigen();
		//request.getSession().setAttribute("cuentas[]", Datos.datosCuentas);
		

		String[] cue=request.getParameterValues("cuentas[]");
		request.getSession().setAttribute("cuentas[]", cue);
		String[] sel = (String[]) request.getSession().getAttribute("cuentas[]");

		String exo="";
		for(int i=0;i<sel.length;i++){
			exo=sel[i];
		}
		
		PrintWriter out = response.getWriter();
		try {
			out.println(exo);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	
	}

}
