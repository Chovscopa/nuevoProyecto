package usuario;

public class User {
	String usuario;
	String pass;
	int rol;
	
public User(String usuario, String pass, int rol) {
	this.usuario=usuario;
	this.pass=pass;
	this.rol=rol;
}

public String getUsuario() {
	return this.usuario;
}

public void setUsuario(String usuario) {
	this.usuario = usuario;
}

public String getPassword() {
	return this.pass;
}

public void setPassword(String pass) {
	this.pass = pass;
}

public void setRol(int rol) {
	this.rol=rol;
}

public int getRol() {
	return this.rol;
}


}