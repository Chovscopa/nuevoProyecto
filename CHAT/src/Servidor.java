

import javax.swing.*;


import java.awt.*;
import java.io.*;
import java.net.*;

public class Servidor  {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MarcoServidor mimarco=new MarcoServidor();
		
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
	}	
}

class MarcoServidor extends JFrame implements Runnable{
	
	/**
	 * 
	 */
	//private static final long serialVersionUID = 6558743595289596983L;

	public MarcoServidor(){
		
		setBounds(1200,300,280,350);				
			
		JPanel milamina= new JPanel();
		
		milamina.setLayout(new BorderLayout());
		
		areatexto=new JTextArea();
		
		milamina.add(areatexto,BorderLayout.CENTER);
		
		add(milamina);
		
		setVisible(true);
		
		Thread hilo1=new Thread (this);
		hilo1.start();
		}
	
	private	JTextArea areatexto;

	@Override
	public void run() {
		
		
		//System.out.println("gggg");
		
		try {
			
			ServerSocket servidor=new ServerSocket(9999);
			String nick;
			String ip;
			String mensaje;
			PaqueteEnvio paquete_recibido=new PaqueteEnvio();
			while(true) {
				
				
				Socket sockAceptar=servidor.accept();
				
				ObjectInputStream datos=new ObjectInputStream(sockAceptar.getInputStream());
				
				paquete_recibido=(PaqueteEnvio) datos.readObject();
				
				nick=paquete_recibido.getNick();
				ip=paquete_recibido.getIp();
				mensaje=paquete_recibido.getMensaje();
				
				//DataInputStream flujo_entrada=new DataInputStream(sockAceptar.getInputStream());
				
				//String mensaje=flujo_entrada.readUTF();
				
				//areatexto.append("\n"+mensaje);
				
				//sockAceptar.close();
				
				areatexto.append("\n"+nick+": "+mensaje+" para: "+ ip);
				
				Socket eviaDestinatario=new Socket(ip,9090);
				
				ObjectOutputStream paqueteReenvio=new ObjectOutputStream(eviaDestinatario.getOutputStream());
				
				paqueteReenvio.writeObject(paquete_recibido);
				
				paqueteReenvio.close();
				
				eviaDestinatario.close();
				
				sockAceptar.close();
			}
			
		} catch (IOException | ClassNotFoundException e) {
		
			e.printStackTrace();
		}
	}
}
