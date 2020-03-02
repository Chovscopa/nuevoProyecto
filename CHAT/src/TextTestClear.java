
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class TextTestClear extends JFrame {


	private JTextField txtField;
	private JButton btnClear;
	
	
	public TextTestClear() {
		// Creo la parte gráfica de los contenedores.
		Container c = getContentPane();
		setTitle("Ejemplo de limpiar JTextField");
		c.setLayout(new FlowLayout());
		setSize(200, 100);
		setLocation(300, 300);
		txtField = new JTextField(12);
		btnClear = new JButton("Limpia");
		//Al botón le agrego el listener.
		btnClear.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev) 
			{
				clearTxtField();
			}
		});
		//Los añado al contenedor
		c.add(txtField);
		c.add(btnClear);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	// Procedimiento que limpia el formulario...
	// como ves es muy sencillo
	public void clearTxtField() {
		txtField.setText("");
	}

	public static void main(String args[]) {
		new TextTestClear();

	}
}