package Vista;



//erasmo2

import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import Logica.Compuesto_Caracterizable;
import Persistencia.Lectura_HC_Compuestos_Caracterizables;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JMenuBar;


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
public class Ejecutable extends JFrame {

	public static LinkedList<Compuesto_Caracterizable> lista_de_compuestos= new LinkedList<Compuesto_Caracterizable>();
	public static LinkedList<Compuesto_Caracterizable> lista_final_compuestos= new LinkedList<Compuesto_Caracterizable>();

	public Ejecutable() {

		//===============================
		// Variables 
		//===============================

		final JList listaizq;
		final JList listder;
		
		lista_de_compuestos = Lectura_HC_Compuestos_Caracterizables.carga();
		final DefaultListModel <String> datos =ingrese_nombres(lista_de_compuestos);
		final DefaultListModel <String> datos2 =new DefaultListModel();
		JScrollPane panelDesplazamiento;
		JScrollPane panelDesplazamiento2;
		getContentPane().setBackground(SystemColor.menu);
		getContentPane().setLayout(null);

		//===============================
		// TITULO
		//===============================

		{
			JLabel lblSeleccioneLosComponentes = new JLabel("Seleccione los componentes que requiere su simulación");
			lblSeleccioneLosComponentes.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblSeleccioneLosComponentes.setBounds(378, 140, 623, 28);
			getContentPane().add(lblSeleccioneLosComponentes);
		}

		{

			//===============================
			// Lista Izquierda
			//===============================

			listaizq =  new JList();
			listaizq.setFont(new Font("Tahoma", Font.PLAIN, 13));
			listaizq.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
			listaizq.setModel(datos);
			panelDesplazamiento = new JScrollPane(listaizq);
			panelDesplazamiento.setBounds(261, 240, 257, 280);
			getContentPane().add(panelDesplazamiento);



		}
		//===============================
		//Lista Derecha
		//===============================

		{

			listder = new JList();
			listder.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listder.setFont(new Font("Tahoma", Font.PLAIN, 13));
			listder.setBounds(566, 153, 155, 248);
			panelDesplazamiento2 = new JScrollPane(listder);
			panelDesplazamiento2.setBounds(761, 240, 257, 280);
			getContentPane().add(panelDesplazamiento2);


		}


		//===============================
		//Menu Superior
		//===============================

		{

			JMenuBar menuBar = new JMenuBar();
			JMenu archivo = new JMenu("  VER TESIS");
			archivo.setFont(new Font("Segoe UI", Font.BOLD, 12));
			JMenu VerMas = new JMenu("  AYUDA");
			JMenu salir = new JMenu("  SALIR");
			salir.setFont(new Font("Segoe UI", Font.BOLD, 12));
			VerMas.setFont(new Font("Segoe UI", Font.BOLD, 12));
			JMenuItem manual = new JMenuItem("Manual de uso");
			JMenuItem acerca = new JMenuItem("Acerca de :");
			VerMas.add(manual);
			VerMas.add(acerca);
			menuBar.add(archivo);
			menuBar.add(VerMas);
			menuBar.add(salir);
			menuBar.setBounds(0, 0, 1183, 21);
			getContentPane().add(menuBar);
		}

		//===============================
		// Botones de la pantalla
		//===============================

		{
			//===============================
			//Boton Agregar a la lista.
			//===============================


			JButton btnAgregar = new JButton("Agregar -->");
			btnAgregar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (!listaizq.isSelectionEmpty()){
						String seleccionado =(listaizq.getSelectedValue().toString());
						int id=listaizq.getSelectedIndex();
						datos2.addElement(seleccionado);
						listder.setModel(datos2);
						((DefaultListModel) listaizq.getModel()).remove(id);
					}



				}
			});
			btnAgregar.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnAgregar.setBounds(567, 301, 147, 42);
			getContentPane().add(btnAgregar);

			//===============================
			//Boton remove de la lista.
			//===============================

			JButton button = new JButton("<-- Remover");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (!listder.isSelectionEmpty()){
						String seleccionado =(listder.getSelectedValue().toString());
						int id=listder.getSelectedIndex();
						datos.addElement(seleccionado);
						listaizq.setModel(datos);
						((DefaultListModel) listder.getModel()).remove(id);
					}



				}
			});
			button.setFont(new Font("Tahoma", Font.PLAIN, 15));
			button.setBounds(567, 385, 147, 42);
			getContentPane().add(button);

			//===============================		
			//Boton Aceptar
			//===============================

			JButton button_1 = new JButton("Aceptar");
			button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for (int i = 0; i < lista_de_compuestos.size(); i++) {
						for (int j = 0; j < listder.getModel().getSize(); j++) {

							if (lista_de_compuestos.get(i).getNombre().equals(listder.getModel().getElementAt(j))){
								lista_final_compuestos.add(lista_de_compuestos.get(i));
							
							}


						}
						
					}
					new Seudocompuestos(lista_final_compuestos);
					dispose();
				}
			}
			);

			button_1.setFont(new Font("Tahoma", Font.BOLD, 15));
			button_1.setBounds(567, 560, 147, 42);
			getContentPane().add(button_1);
			button_1.setText("SIGUIENTE -->");

		}

		this.repaint();
		this.setTitle("Proyecto De Maestria Unidades De Tratamiento Temprano De Una Corriente De Crudo ***U.Nacional---Ing Nina Marcela Perez B***");
		this.setVisible(true);
		this.setSize(1200,800);


	}

	//===============================
	// Funcion para Agregar la Lisa de compuestos.
	//===============================

	private DefaultListModel ingrese_nombres(
			LinkedList<Compuesto_Caracterizable> lista_de_compuestos) {
		DefaultListModel datos = new DefaultListModel();

		for (int i = 0; i <lista_de_compuestos.size(); i++) {

			datos.addElement(lista_de_compuestos.get(i).getNombre());

		}
		return datos;
	}

	//===============================
	//Funcion Principal
	//===============================

	public static void main(String[] args) {
		new Ejecutable();


	}
}
