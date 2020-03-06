package Logica;


import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import funciones.Funciones;
import generacionDinamica.Datos;
import pojo.Movimiento;


@WebServlet("/S2")
public class S2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public S2() {
        super();
        
    }

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		embudo(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		embudo(request, response);
	}
	
	protected void embudo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		//captar los valores de los campos del fomulario
		
		
		String[] cue=request.getParameterValues("cuentas[]");
		String[] cue2=request.getParameterValues("cuentas[]2");
		String c= request.getParameter("cantidadT");
		

		String errores="";
		
		//asignacion de los campos del formalario a variables de sesion
		
		
		
		request.getSession().setAttribute("errores", errores);
		
		
		request.getSession().setAttribute("cuentas[]", cue);
		request.getSession().setAttribute("cuentas[]2", cue2);
		request.getSession().setAttribute("c", c);
		
		String[] sel = (String[]) request.getSession().getAttribute("cuentas[]");
		
		String exo="";
		for(int i=0;i<sel.length;i++){
			exo=sel[i];
		}
		
		
		String[] sel2 = (String[]) request.getSession().getAttribute("cuentas[]2");

		String exo2="";
		for(int i=0;i<sel2.length;i++){
			exo2=sel2[i];
		}
		
		request.getSession().setAttribute("varC1", exo);///pasar la variable al saldoCuentaOrigen.jsp
		request.getSession().setAttribute("varC2", exo2);///pasar la variable al saldoCuentaDestino.jsp
		
		///////////
		if(arrayMov==null) {
			arrayMov=new ArrayList<Movimiento>();
		}
		request.getSession().setAttribute("arM", arrayMov);
		///////////
		
		
		
		
		//////MUESTRA EL SALDO DE LA CUENTA DE ORIGEN////////
		if(request.getParameter("saldoN1")!=null){
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/saldoCuentaOrigen.jsp");
			dispatcher.forward(request, response);
			
		}
		//////MUESTRA EL SALDO DE LA CUENTA DE DESTINO////////
		if(request.getParameter("saldoN2")!=null){
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/saldoCuentaDestino.jsp");
			dispatcher.forward(request, response);
			
		}
		//////MUESTRA LAS TRANSFERENCIAS////////
		if(request.getParameter("VerTransferencias")!=null){////fghfghg
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/verTransferencia.jsp");
			dispatcher.forward(request, response);
			
		}
		
		//////*******REALIZA LA TRANSERENCIA*******////////
		if(request.getParameter("Transferencia")!=null){
			if(fallos(c,errores,exo,exo2,request)) {////////////////////// 	se podrian dividir los fallos///////////////////
				RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/FormularioInicial.jsp");
				dispatcher.forward(request, response);
			}else {
					//TRANSFERENCIA//
				if(!exo.equals(exo2) && c!=null && c!=""){
					Datos.transferencia(Funciones.conexion(),exo,exo2, c);
					Datos.llenarArrayMov(arrayMov, exo, exo2,c);
					Datos.verArrayMov(arrayMov);
					
					RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/TransferOK.jsp");
					dispatcher.forward(request, response);
					
				}else {
					RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/TransferFallo.jsp");
					dispatcher.forward(request, response);
							
				}
			}
		}
	
		if(request.getParameter("Salir")!=null){
			Datos.volcarArrayMov(arrayMov, Funciones.conexion());
			session.invalidate();
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/PaginaSalida.jsp");
			dispatcher.forward(request, response);
			
		}
		
	}
	
	
	protected boolean fallos(String c,String errores,String exo,String exo2,HttpServletRequest request) {
		boolean sw=false;
		
		if (c=="") {
			errores += "El campo cantidad esta vacío <br>";
			sw=true;
		}
		if (exo.equals(exo2)) {
			errores += "No se puede transferir a la misma cuenta <br>";
			sw=true;
		}
		if (Datos.saldoInsuficiente(Funciones.conexion(),exo,c)){
			errores += "Saldo insuficiente para relizar la operacion <br>";
			sw=true;
		}
		
		request.getSession().setAttribute("errores", errores);
		return sw;
		
	}
	
	static ArrayList<Movimiento> arrayMov;
	
	

}
