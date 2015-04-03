package Vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


/**
 * This code was edited or generated using CloudGarden's Jigloo
 * SWT/Swing GUI Builder, which is free for non-commercial
 * use. If Jigloo is being used commercially (ie, by a corporation,
 * company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details.
 * Use of Jigloo implies acceptance of these licensing terms.
 * A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
 * THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
 * LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class Cuestionario_inicial_para_el_diseño extends JFrame {
	private JTextField textField;

	
	public Cuestionario_inicial_para_el_diseño() {

		{

	
		

		//===============================
		// Botones de la pantalla
		//===============================

		

		this.repaint();
		this.setTitle("Proyecto De Maestria Unidades De Tratamiento Temprano De Una Corriente De Crudo ***U.Nacional---Ing Nina Marcela Perez B***");
		this.setVisible(true);
		this.setSize(1200,800);
		}
		getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(174, 142, 86, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(88, 145, 46, 14);
		getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed1(ActionEvent arg0) {
			}

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		btnNewButton.setBounds(315, 346, 89, 23);
		getContentPane().add(btnNewButton);

	}

	//===============================
	//Funcion Principal
	//===============================

	public static void main(String[] args) {
		new corriente_de_alimento();


	}
}
