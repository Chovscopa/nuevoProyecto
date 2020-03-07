package utilidades;



import java.sql.*;

import java.util.LinkedHashMap;




public class Datos {

	
	public static String[] datosCuentas = new String[7] ; ////se puede utilizar la i del rset para crear este array. se mete dentro de cargarDatosOrigen y se hace que devuelva una arraydeStrings
	//public static List<String> dC=new ArrayList<String>();
	
	public static String[] datosCuentasDestino = new String[7] ;
	//public static List<String> dCD=new ArrayList<String>();
	
	
	
	public static void cargarDatosOrigen(Connection conn) {
		
		
		try {
			String sqlStr = "select * from cuentas";
			PreparedStatement pstmt=conn.prepareStatement(sqlStr);

			
			ResultSet rset = pstmt.executeQuery();
			
			int i = 0;
			
			while (rset.next()) {
				datosCuentas[i] =(String) rset.getString("ncuenta");
				//dC.add(rset.getString("ncuenta"));
				i++;
			}
			System.out.println("Valor de la longitud"+i);
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	

	public static LinkedHashMap<String,String> arrayCuentas = new LinkedHashMap<String,String>();

		static {
			cargarDatosOrigen(Funciones.conexion());
			for(int i=0;i<datosCuentas.length;i++) {
				
				arrayCuentas.put(datosCuentas[i], datosCuentas[i]);
				//arrayCuentas.put(dC.get(i), dC.get(i));
				
			}

	}
	
	public static LinkedHashMap<String,String> arrayCuentasDestino;

		static {
		arrayCuentasDestino = new LinkedHashMap<String,String>();
	   
			for(int i=0;i<datosCuentas.length;i++) {
				arrayCuentasDestino.put(datosCuentas[i], datosCuentas[i]);
				//arrayCuentasDestino.put(dC.get(i), dC.get(i));
			}

	}

}