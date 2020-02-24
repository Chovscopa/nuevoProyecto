package generacionDinamica;

import java.util.Iterator;
import java.util.LinkedHashMap;

public class generacionDinamica {
	
	
	public static String generarButton(String genero) {
		String cadena = "Genero: ";
		if (genero == null) {
			cadena += "<label>Mujer</label> <input type=\"radio\" value=\"F\" name=\"genero\" />";
			cadena += "<label>Hombre</label> <input type=\"radio\" value=\"M\" name=\"genero\" />";
		} else if (genero.equals("F")) {
			cadena += "<label>Mujer</label> <input type=\"radio\" value=\"F\" name=\"genero\" checked=\"checked\" />";
			cadena += "<label>Hombre</label> <input type=\"radio\" value=\"M\" name=\"genero\" />";
		} else {
			cadena += "<label>Mujer</label> <input type=\"radio\" value=\"F\" name=\"genero\" />";
			cadena += "<label>Hombre</label> <input type=\"radio\" value=\"M\" name=\"genero\" checked=\"checked\" />";
		}

		return cadena;
	}
	
	public static String generaSelectPaises (LinkedHashMap<String, String> arrayPaises,  String[] _paises) {
        String cadena = "Nacionalidad:  <select name=\"paises[]\" multiple=\"multiple\">";
        int numPaises = 0;
        int i=0;
        int cont=0;
        
        if (_paises!=null) {
            numPaises= _paises.length;  // Si el parametro paises recibido no es nulo se calcula su longitud
        }
        System.out.println("num"+numPaises);
        Iterator<String> iterador = arrayPaises.keySet().iterator();
        while (iterador.hasNext()) {
            String clave = (String)iterador.next();
            String valor = (String)arrayPaises.get(clave);
            if ( i<numPaises && _paises[i].equals(clave)) {                
                cadena += "<option value=\"" + clave + "\"  selected=\"selected\">" + valor + "</option>";
                i++;
            } else {
                cadena += "<option value=\"" + clave + "\">" + valor + "</option>";
            }
            cont++;
            System.out.println(cont+" "+i+clave+valor); // para control interno
        }
        cadena +="</select>";
        return cadena;
    }
	
	public static String generaChecboxCoP(String cop) {
        String out="Casado o pareja de hecho";
        if (cop!=null) {
            out+= "<input type=\"checkbox\" name=\"casadoOpareja\" value=\"Si\" checked=\"checked\" />" + "\n";
        }
        else {
        	out+= "<input type=\"checkbox\" name=\"casadoOpareja\" value=\"Si\" />" + "\n";
        }
        return out;
	}
	
	public static String limpiarNull1(String value) {
		if (value == null) {
			value = "No";
		}
		return value;
	}
	
	public static String generaChecboxH(String h) {
        String out="Hijos";
        if (h!=null) {
            out+= "<input type=\"checkbox\" name=\"hijo\" value=\"Si\" checked=\"checked\" />" + "\n";
        }
        else {
        	out+= "<input type=\"checkbox\" name=\"hijo\" value=\"Si\" />" + "\n";
        }
        return out;
	}
	
	public static String limpiarNull2(String value) {
		if (value == null) {
			value = "No";
		}
		return value;
	}
	
}
