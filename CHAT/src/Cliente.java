

import javax.swing.*;

import java.awt.event.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.*;


public class Cliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MarcoCliente mimarco=new MarcoCliente();
		
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}


class MarcoCliente extends JFrame{
	
	public MarcoCliente(){
		
		setBounds(600,300,280,350);
				
		LaminaMarcoCliente milamina=new LaminaMarcoCliente();
		
		add(milamina);
		
		setVisible(true);
		}	
	
}

class LaminaMarcoCliente extends JPanel implements Runnable{
	
	public LaminaMarcoCliente(){
		
		nick=new JTextField(5);
		
		add(nick);
		
		
		//
		JLabel texto=new JLabel("CHAT");
		
		add(texto);
		//
		
		ip=new JTextField(8);
		
		add(ip);
		////
		
		campochat=new JTextArea(12,20);
		
		add(campochat);
		
		
		/////
		
		campo1=new JTextField(20);
		
		
	
		add(campo1);		
	
		
		
		miboton=new JButton("Enviar");
		miboton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev) 
			{
				vaciarTxtField();
			}
		});
		EnviarTexto ev=	new EnviarTexto();
		
		miboton.addActionListener(ev);
		//
		
		//
		add(miboton);	
		
		Thread hilo1=new Thread(this);
		
		hilo1.start();
		
	}
	public void vaciarTxtField() {
		campo1.setText("");
	}
	
	
	private class EnviarTexto implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Apéndice de método generado automáticamente
			//System.out.println(campo1.getText());
			campochat.append("\n"+nick.getText()+": "+campo1.getText()); //para que ene el campo de mensajes salgan todos los mensajes
			try {
				Socket sock=new Socket("192.168.1.11",9999);
				
				PaqueteEnvio datos=new PaqueteEnvio();
				
				datos.setNick(nick.getText());
				datos.setIp(ip.getText());
				datos.setMensaje(campo1.getText());
				
				ObjectOutputStream paqueteDatos=new ObjectOutputStream(sock.getOutputStream());
				
				paqueteDatos.writeObject(datos);
				
				sock.close();
				//DataOutputStream flujo_salida=new DataOutputStream(sock.getOutputStream());
				//flujo_salida.writeUTF(campo1.getText());
				//flujo_salida.close();
			} catch (UnknownHostException e1) {
				
				e1.printStackTrace();
			} catch (IOException e1) {
				
				System.out.println(e1);
				//e1.printStackTrace();
			}
		}
		
	}
	
		
		
		
	private JTextField campo1;
	
	private JTextField nick;
	
	private JTextField ip;
	
	private JTextArea campochat;
	
	private JButton miboton;

	@Override
	public void run() {
		// TODO Apéndice de método generado automáticamente
		try {
			ServerSocket servidorCliente=new ServerSocket(9090);
			
			Socket cliente;
			
			PaqueteEnvio paqueteRecibido;
			while(true) {
				cliente=servidorCliente.accept();
				ObjectInputStream flujoEntrada=new ObjectInputStream(cliente.getInputStream());
				
				paqueteRecibido=(PaqueteEnvio) flujoEntrada.readObject();
				
				campochat.append("\n"+paqueteRecibido.getNick()+": "+paqueteRecibido.getMensaje());
			}
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
}

class PaqueteEnvio implements Serializable{
	
	private String nick;
	private String ip;
	private String mensaje;
	
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
}
