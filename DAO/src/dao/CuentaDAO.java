package dao;

import java.math.BigDecimal;
import java.sql.Connection;


public interface CuentaDAO {
	
	public int transferencia(Connection conn,String nc1,String nc2,String cantidad);
	public BigDecimal saldo1(Connection conn,String ncuentaParam);
	public boolean saldoInsuficiente(Connection conn,String nc1,String cantidad);
}
