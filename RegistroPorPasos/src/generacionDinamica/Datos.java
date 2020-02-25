package generacionDinamica;

import java.util.LinkedHashMap;

public class Datos {
		
	public static LinkedHashMap<String,String> arrayPaises;

	static {
		arrayPaises = new LinkedHashMap<String, String>();
		arrayPaises.put("ES", "Española");
    	arrayPaises.put("FR", "Francesa");
    	arrayPaises.put("IT", "Italiana");
    	arrayPaises.put("PT", "Portuguesa");
	}
	
}
