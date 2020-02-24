package generacionDinamica;


import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Datos {
	
	//new
	
	public static LinkedHashMap<String,String> arrayPaises =  new LinkedHashMap<>;
	public static <K, V> Entry<K, V> entry(final K key, final V value) {
	    return new Entry(key, value);
	  }

	  public static <K, V> Map<K, V> asMap(final Entry<K, V>... entries) {
	    return populate(new HashMap<K, V>(), entries);
	  }
	/*
	generacionDinamica.arrayPaises.put("ES", "Española");
	generacionDinamica.arrayPaises.put("FR", "Francesa");
	generacionDinamica.arrayPaises.put("IT", "Italiana");
	generacionDinamica.arrayPaises.put("PT", "Portuguesa");
	
	
	https://objectpartners.com/2014/06/05/inline-initialization-of-java-maps/
	*/
	
}
