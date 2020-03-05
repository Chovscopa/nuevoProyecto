package utilidades;


import java.util.Iterator;
import java.util.LinkedHashMap;

public class generacionDinamica {
	
	
	
	
	public static String generaSelect1 (LinkedHashMap<String, String> arrayCuentas,  String[] _cuentas) {
        String cadena = "<select name=\"cuentas[]\" >";
        int numCuentas = 0;
        int i=0;
        int cont=0;
        
        if (_cuentas!=null) {
            numCuentas= _cuentas.length;  
        }
        System.out.println("num"+numCuentas);
        Iterator<String> iterador = arrayCuentas.keySet().iterator();
        while (iterador.hasNext()) {
            String clave = (String)iterador.next();
            String valor = (String)arrayCuentas.get(clave);
            if ( i<numCuentas && _cuentas[i].equals(clave)) {                
                cadena += "<option value=\"" + clave + "\"  selected=\"selected\">" + valor + "</option>";
                
                i++;
            } else {
                cadena += "<option value=\"" + clave + "\">" + valor + "</option>";
            }
            cont++;
            System.out.println(cont+" "+i+clave+valor); 
        }
        cadena +="</select>";
        return cadena;
    }
	
	public static String generaSelect2 (LinkedHashMap<String, String> arrayCuentas,  String[] _cuentas) {
        String cadena = "<select name=\"cuentas[]2\" >";
        int numCuentas = 0;
        int i=0;
        int cont=0;
        
        if (_cuentas!=null) {
            numCuentas= _cuentas.length;  
        }
        System.out.println("num"+numCuentas);
        Iterator<String> iterador = arrayCuentas.keySet().iterator();
        while (iterador.hasNext()) {
            String clave = (String)iterador.next();
            String valor = (String)arrayCuentas.get(clave);
            if ( i<numCuentas && _cuentas[i].equals(clave)) {                
                cadena += "<option value=\"" + clave + "\"  selected=\"selected\">" + valor + "</option>";
                
                i++;
            } else {
                cadena += "<option value=\"" + clave + "\">" + valor + "</option>";
            }
            cont++;
            System.out.println(cont+" "+i+clave+valor); 
        }
        cadena +="</select>";
        return cadena;
    }
	
	
	
	
	

	
}
