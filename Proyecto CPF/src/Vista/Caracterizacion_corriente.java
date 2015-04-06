package Vista;

import java.util.LinkedList;
import java.util.Vector;
import javax.swing.BorderFactory;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

import Logica.Objeto_Compuestos_Caracterizables;
import Logica.Objeto_fracciones_no_caracterizables;
import Persistencia.Lectura_HC_Compuestos_Caracterizables;
import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JMenuBar;
import javax.swing.table.DefaultTableModel;


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
public class Caracterizacion_corriente extends JFrame {

	public static LinkedList<Objeto_Compuestos_Caracterizables> lista_de_compuestos= new LinkedList<Objeto_Compuestos_Caracterizables>();
	private JLabel Logo;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JTextField sal;
	private JLabel jLabel8;
	private JLabel jLabel7;
	private JPanel Caracterizacion_gas;
	private JPanel Caracterizacion_crudo;
	private JSeparator jSeparator1;
	private JButton Aceptar;
	private JLabel jLabel6;
	private JTextField Presion;
	private JTextField Temperatura;
	private JTextField Flujo_Gas;
	private JTextField Flujo_agua;
	private JTextField Flujo_crudo;
	private JLabel jLabel1;
	private DefaultTableModel model;
	private JButton normalizar_gas;
	private JTextField total2;
	private JLabel jLabel10;
	private JButton Normalizar_crudo;
	private JLabel jLabel9;
	private JTextField total;
	private JTable table;
	private DefaultTableModel model2;
	private JTable table2;
	public static LinkedList<Objeto_Compuestos_Caracterizables> lista_final_compuestos= new LinkedList<Objeto_Compuestos_Caracterizables>();

	public Caracterizacion_corriente(LinkedList<Objeto_Compuestos_Caracterizables> lista_final_compuestos, LinkedList<Objeto_fracciones_no_caracterizables> lista_fracciones_no_caracterizables) {

		//===============================
		// Variables 
		//===============================



		lista_de_compuestos = Lectura_HC_Compuestos_Caracterizables.carga();
		
		getContentPane().setBackground(SystemColor.menu);
		getContentPane().setLayout(null);

		//===============================
		// TITULO
		//===============================

		{
			JLabel lblSeleccioneLosComponentes = new JLabel("Seleccione los componentes que requiere su simulación");
			lblSeleccioneLosComponentes.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblSeleccioneLosComponentes.setBounds(392, 154, 623, 28);
			getContentPane().add(lblSeleccioneLosComponentes);
			lblSeleccioneLosComponentes.setText("Caracterizacion de la corriente de alimento :");
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
				menuBar.setBounds(0, 0, 1183, 21);
				getContentPane().add(menuBar);
			}





			//===============================		
			//Boton Siguiente
			//===============================
			JButton siguiente = new JButton("Aceptar");

			siguiente.setFont(new Font("Tahoma", Font.BOLD, 15));
			siguiente.setBounds(560, 644, 147, 42);
			getContentPane().add(siguiente);
			siguiente.setText("SIGUIENTE -->");
			siguiente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {

					String[] list = {"Metodo 1", "Metodo 2", "Metodo 3"};
					JComboBox jcb = new JComboBox(list);
					jcb.setEditable(true);
					JOptionPane.showMessageDialog( null, jcb, "Seleccione el modelo de equilibrio", JOptionPane.QUESTION_MESSAGE);
					String metodo_seleccionado=(jcb.getSelectedItem().toString());
					dispose();
				}
			}
			);



		}
		//===============================		
		//LOGO
		//===============================

		{
			Logo = new JLabel();
			getContentPane().add(Logo);
			Logo.setBounds(0, 21, 1183, 126);
			Logo.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Vista/logo.png")));
			Logo.setBackground(new java.awt.Color(255,255,255));
			Logo.setForeground(new java.awt.Color(255,255,255));
			Logo.setOpaque(true);
		}
		//===============================		
		//Labels
		//===============================
		{
			jLabel1 = new JLabel();
			getContentPane().add(jLabel1);
			jLabel1.setText("Flujo de crudo (BOPD)");
			jLabel1.setFont(new java.awt.Font("Tahoma",1,12));
			jLabel1.setBounds(42, 196, 168, 28);
		}
		{
			jLabel2 = new JLabel();
			getContentPane().add(jLabel2);
			jLabel2.setText("Flujo de Agua (BWPD)");
			jLabel2.setFont(new java.awt.Font("Tahoma",1,12));
			jLabel2.setBounds(245, 196, 168, 28);
		}
		{
			jLabel3 = new JLabel();
			getContentPane().add(jLabel3);
			jLabel3.setText("Flujo de gas (MMSCFD)");
			jLabel3.setFont(new java.awt.Font("Tahoma",1,12));
			jLabel3.setBounds(462, 196, 168, 28);
		}
		{
			jLabel4 = new JLabel();
			getContentPane().add(jLabel4);
			jLabel4.setText("Temperatura (°F)");
			jLabel4.setFont(new java.awt.Font("Tahoma",1,12));
			jLabel4.setBounds(686, 196, 168, 28);
		}
		{
			jLabel5 = new JLabel();
			getContentPane().add(jLabel5);
			jLabel5.setText("Presion (Psia)");
			jLabel5.setFont(new java.awt.Font("Tahoma",1,12));
			jLabel5.setBounds(882, 196, 168, 28);
		}

		{
			jLabel8 = new JLabel();
			getContentPane().add(jLabel8);
			jLabel8.setText("Sal (PTB)");
			jLabel8.setFont(new java.awt.Font("Tahoma",1,12));
			jLabel8.setBounds(1043, 196, 168, 28);
		}


		//===============================		
		//Jtext
		//===============================

		{
			Flujo_crudo = new JTextField();
			getContentPane().add(Flujo_crudo);
			Flujo_crudo.setBounds(42, 231, 133, 28);
		}
		{
			Flujo_agua = new JTextField();
			getContentPane().add(Flujo_agua);
			Flujo_agua.setBounds(245, 231, 133, 28);
		}
		{
			Flujo_Gas = new JTextField();
			getContentPane().add(Flujo_Gas);
			Flujo_Gas.setBounds(462, 231, 140, 28);
		}
		{
			Temperatura = new JTextField();
			getContentPane().add(Temperatura);
			Temperatura.setBounds(686, 231, 112, 28);
		}
		{
			Presion = new JTextField();
			getContentPane().add(Presion);
			Presion.setBounds(882, 231, 84, 28);
		}


		{
			sal = new JTextField();
			getContentPane().add(sal);
			sal.setBounds(1043, 231, 84, 28);
		}

		{
			//===============================		
			//Boton Aceptar
			//===============================
			Aceptar = new JButton();
			getContentPane().add(Aceptar);
			Aceptar.setText("Aceptar");
			Aceptar.setFont(new java.awt.Font("Tahoma",1,15));
			Aceptar.setBounds(476, 294, 294, 28);
			Aceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					if (!Flujo_crudo.getText().equals("")){
						Caracterizacion_crudo.setVisible(true);
					}
					else{
						Caracterizacion_crudo.setVisible(false);
					}
					if (!Flujo_Gas.getText().equals("")){
						Caracterizacion_gas.setVisible(true);
					}
					else{
						Caracterizacion_gas.setVisible(false);
					}

				}
			}
			);
		}
		{
			jSeparator1 = new JSeparator();
			getContentPane().add(jSeparator1);
			jSeparator1.setBounds(0, 329, 1190, 28);
		}

		//===============================		
		//Panel de caracterizacion del crudo
		//===============================
		{
			Caracterizacion_crudo = new JPanel();
			AnchorLayout Caracterizacion_crudoLayout = new AnchorLayout();
			Caracterizacion_crudo.setLayout(Caracterizacion_crudoLayout);
			getContentPane().add(Caracterizacion_crudo);
			Caracterizacion_crudo.setBounds(7, 336, 595, 301);
			Caracterizacion_crudo.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
			Caracterizacion_crudo.setBackground(new java.awt.Color(192,192,192));

			{
				total = new JTextField();
				Caracterizacion_crudo.add(total, new AnchorConstraint(769, 989, 862, 789, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				total.setPreferredSize(new java.awt.Dimension(119, 28));

			}
			{
				jLabel6 = new JLabel();
				Caracterizacion_crudo.add(jLabel6, new AnchorConstraint(28, 777, 107, 271, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			}

		}

		//===============================		
		//Panel de caracterizacion del Gas
		//===============================
		{
			Caracterizacion_gas = new JPanel();
			AnchorLayout jPanel1Layout = new AnchorLayout();
			Caracterizacion_gas.setLayout(jPanel1Layout);
			getContentPane().add(Caracterizacion_gas);
			Caracterizacion_gas.setBounds(630, 336, 546, 301);
			Caracterizacion_gas.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
			Caracterizacion_gas.setBackground(new java.awt.Color(192,192,192));
			{
				jLabel7 = new JLabel();
				Caracterizacion_gas.add(jLabel7, new AnchorConstraint(21, 847, 101, 295, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel7.setText("Caracterizacion del Gas :");
				jLabel7.setFont(new Font("Tahoma",Font.BOLD,20));
				jLabel7.setPreferredSize(new java.awt.Dimension(301, 28));
			}


		}
		{
			jLabel6.setText("Caracterizacion del Crudo  :");
			jLabel6.setFont(new Font("Tahoma",Font.BOLD,20));
			jLabel6.setPreferredSize(new java.awt.Dimension(301, 21));

			//===============================		
			//Tabla de caracterizacion del crudo
			//===============================
			{
				model = new DefaultTableModel();
				table = new JTable(){
					//Columna 1  no modificable  
					public boolean isCellEditable(int rowIndex, int colIndex) {
						if (colIndex==1) 
							return true; 
						else 
							return false; 

					}
				};
				table.setModel(model);
				table.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						double sumatoria = 0;

						for(int i=0;i<(table.getRowCount());i++)
						{
							sumatoria= Double.parseDouble(String.valueOf(table.getValueAt(i,1)))+ sumatoria;

						}
						total.setText(String.valueOf(sumatoria));
					}

				});
				model.addColumn("Nombre");
				model.addColumn("Fraccion Molar");
				for (int i = 0; i < lista_final_compuestos.size(); i++) {
					model.addRow(new Object[]{lista_final_compuestos.get(i).getNombre(),"0"});

				}
				for (int i = 0; i < lista_fracciones_no_caracterizables.size(); i++) {
					model.addRow(new Object[]{lista_fracciones_no_caracterizables.get(i).getNombre(),"0"});

				}


				JScrollPane pane = new JScrollPane(table);
				Caracterizacion_crudo.add(pane, new AnchorConstraint(117, 989, 769, 12, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				pane.setPreferredSize(new java.awt.Dimension(581, 196));

			}
			{
				jLabel9 = new JLabel();
				Caracterizacion_crudo.add(jLabel9, new AnchorConstraint(792, 789, 862, 671, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel9.setText("Total :");
				jLabel9.setFont(new Font("Tahoma",Font.BOLD,20));
				jLabel9.setPreferredSize(new java.awt.Dimension(70, 21));
			}

			//===============================		
			//Tabla de caracterizacion del gas
			//===============================
			{
				model2 = new DefaultTableModel();
				table2 =new JTable(){
					public boolean isCellEditable(int rowIndex, int colIndex) {
						if (colIndex==1) 
							return true; 
						else 
							return false; 

					}
				};
				table2.setModel(model2);
				model2.addColumn("Nombre");
				model2.addColumn("Fración molar");
				for (int i = 0; i < lista_final_compuestos.size(); i++) {
					model2.addRow(new Object[]{lista_final_compuestos.get(i).getNombre(),"0"});

				}
				for (int i = 0; i < lista_fracciones_no_caracterizables.size(); i++) {
					model2.addRow(new Object[]{lista_fracciones_no_caracterizables.get(i).getNombre(),"0"});

				}
				table2.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						double sumatoria = 0;

						for(int i=0;i<(table2.getRowCount());i++)
						{
							sumatoria= Double.parseDouble(String.valueOf(table2.getValueAt(i,1)))+ sumatoria;

						}
						total2.setText(String.valueOf(sumatoria));
					}

				});
				JScrollPane pane2 = new JScrollPane(table2);
				Caracterizacion_gas.add(pane2, new AnchorConstraint(117, 988, 769, 13, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				pane2.setPreferredSize(new java.awt.Dimension(518, 210));
				pane2.setPreferredSize(new java.awt.Dimension(532, 196));

			}
			{
				jLabel10 = new JLabel();
				Caracterizacion_gas.add(jLabel10, new AnchorConstraint(792, 770, 862, 641, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel10.setText("Total :");
				jLabel10.setFont(new Font("Tahoma",Font.BOLD,20));
				jLabel10.setPreferredSize(new java.awt.Dimension(70, 21));
			}
			{
				total2 = new JTextField();
				Caracterizacion_gas.add(total2, new AnchorConstraint(769, 988, 862, 770, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				total2.setPreferredSize(new java.awt.Dimension(119, 28));
			}

			//===============================		
			//Boton de normalizar el gas 
			//===============================
			{
				normalizar_gas = new JButton();
				Caracterizacion_gas.add(normalizar_gas, new AnchorConstraint(885, 988, 978, 770, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				normalizar_gas.setText("Normalizar");
				normalizar_gas.setFont(new java.awt.Font("Tahoma",1,11));
				normalizar_gas.setPreferredSize(new java.awt.Dimension(119, 28));
				normalizar_gas.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						double sumatoria = 0;

						for(int i=0;i<(table2.getRowCount());i++)
						{
							Double num = Double.parseDouble(String.valueOf(table2.getValueAt(i,1)));
							num = num /Double.parseDouble(total2.getText());
							table2.setValueAt(num.toString(), i, 1);
						}



						for(int i=0;i<(table2.getRowCount());i++)
						{
							sumatoria= Double.parseDouble(String.valueOf(table2.getValueAt(i,1)))+ sumatoria;

						}
						total2.setText(String.valueOf(sumatoria));
					}
				});
			}

			{
				//===============================		
				//Boton de normalizar el crudo
				//===============================
				Normalizar_crudo = new JButton();
				Caracterizacion_crudo.add(Normalizar_crudo, new AnchorConstraint(885, 989, 978, 789, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				Normalizar_crudo.setText("Normalizar");
				Normalizar_crudo.setPreferredSize(new java.awt.Dimension(119, 28));
				Normalizar_crudo.setFont(new java.awt.Font("Tahoma",1,11));
				Normalizar_crudo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						double sumatoria = 0;

						for(int i=0;i<(table.getRowCount());i++)
						{
							Double num = Double.parseDouble(String.valueOf(table.getValueAt(i,1)));
							num = num /Double.parseDouble(total.getText());
							table.setValueAt(num.toString(), i, 1);
						}


						for(int i=0;i<(table.getRowCount());i++)
						{
							sumatoria= Double.parseDouble(String.valueOf(table.getValueAt(i,1)))+ sumatoria;

						}
						total.setText(String.valueOf(sumatoria));
					}
				});
			}

		}
		Caracterizacion_crudo.setVisible(false);
		Caracterizacion_gas.setVisible(false);			
		this.repaint();
		this.setTitle("Proyecto De Maestria Unidades De Tratamiento Temprano De Una Corriente De Crudo ***U.Nacional---Ing Nina Marcela Perez B***");
		this.setVisible(true);
		this.setSize(1200,800);


	}

	

}
