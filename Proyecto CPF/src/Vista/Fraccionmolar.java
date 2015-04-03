package Vista;

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

public class Fraccionmolar extends JFrame {

	public Fraccionmolar() {

		//===============================
		// Variables 
		//===============================

		getContentPane().setBackground(SystemColor.menu);
		getContentPane().setLayout(null);

		//===============================
		// TITULO
		//===============================

		{
			JLabel lblSeleccioneLosComponentes = new JLabel("Ingrese la fracción molar de los componentes");
			lblSeleccioneLosComponentes.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblSeleccioneLosComponentes.setBounds(98, 100, 621, 26);
			getContentPane().add(lblSeleccioneLosComponentes);
		}

		{



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
				menuBar.setBounds(0, 0, 850, 20);
				getContentPane().add(menuBar);
			}
			
			
			this.repaint();
			this.setTitle("Proyecto De Maestria Unidades De Tratamiento Temprano De Una Corriente De Crudo ***U.Nacional---Ing Nina Marcela Perez B***");
			this.setVisible(true);
			this.setSize(850,600);
			this.setResizable(false);

		}
	}
}
