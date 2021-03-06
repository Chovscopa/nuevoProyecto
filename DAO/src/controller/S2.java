package controller;


import java.io.IOException;

import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import utilidades.*;
import pojo.*;
import dao.*;


@WebServlet("/S2")
public class S2 extends HttpServlet {
	/*
	private DataSource pool; // Pool de conexiones a la base de datos
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			// Crea un contexto para poder luego buscar el recurso DataSource
			InitialContext ctx = new InitialContext();
			// Busca el recurso DataSource en el contexto
			pool = (DataSource)ctx.lookup("java:comp/env/jdbc/mysql_examenservidor");
			if (pool == null) {
				throw new ServletException("DataSource desconocida 'mysql_examenservidor'");
			}
		} catch (NamingException ex) {
			ex.printStackTrace();
		}
	}
	*/
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
		if(request.getParameter("VerTransferencias")!=null){
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
					//Cuenta acc = new Cuenta();
					CuentaDAOImpl cuentasIpml=new CuentaDAOImpl();
					cuentasIpml.transferencia(Funciones.conexion(), exo, exo2, c);
					
					//Datos.transferencia(Funciones.conexion(),exo,exo2, c);
					
					MovimientoDAOImpl movImpl=new MovimientoDAOImpl();
					movImpl.llenarArrayMov(arrayMov, exo, exo2,c);
					
					RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/TransferOK.jsp");
					dispatcher.forward(request, response);
					
				}else {
					RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/TransferFallo.jsp");
					dispatcher.forward(request, response);
							
				}
			}
		}
	
		if(request.getParameter("Salir")!=null){
			
			MovimientoDAOImpl movImpl=new MovimientoDAOImpl();
			movImpl.volcarArrayMov(arrayMov, Funciones.conexion());
			
			session.invalidate();
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/PaginaSalida.jsp");
			dispatcher.forward(request, response);
			
		}
		
	}
	
	
	protected boolean fallos(String c,String errores,String exo,String exo2,HttpServletRequest request) {
		boolean sw=false;
		
		CuentaDAOImpl cuentaImpl=new CuentaDAOImpl();
		
		if (c=="") {
			errores += "El campo cantidad esta vac�o <br>";
			sw=true;
		}
		if (exo.equals(exo2)) {
			errores += "No se puede transferir a la misma cuenta <br>";
			sw=true;
		}
		if (cuentaImpl.saldoInsuficiente(Funciones.conexion(),exo,c)){
			errores += "Saldo insuficiente para relizar la operacion <br>";
			sw=true;
		}
		
		request.getSession().setAttribute("errores", errores);
		return sw;
		
	}
	
	static ArrayList<Movimiento> arrayMov;
	
	

}
