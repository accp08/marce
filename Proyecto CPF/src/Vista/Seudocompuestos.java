package Vista;

import java.util.LinkedList;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import Logica.Determinación_del_equilibrio;
import Logica.Ecuación_de_estado_SRW_modificada;
import Logica.Metodos_De_Caracterizacion;
import Logica.Compuesto_Caracterizable;
import Logica.Fracciones_No_Caracterizables;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

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
public class Seudocompuestos extends JFrame {
	private JTable table;
	private JTable jTable1;
	private JButton estimar;
	private JButton limpiar;
	private JButton Agregar;
	private JComboBox jComboBox;
	private JLabel jLabel4;
	private JButton jButton2;
	private JTextField jTextField3;
	private JTextField jTextField2;
	private JTextField jTextField1;
	private JLabel jLabel3;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private DefaultTableModel model ;
	private int cont =1;
	static LinkedList<Fracciones_No_Caracterizables> lista_fracciones_no_caracterizables = new LinkedList<Fracciones_No_Caracterizables>();
	public Seudocompuestos(final LinkedList<Compuesto_Caracterizable> lista_final_compuestos) {

		//===============================
		// Variables 
		//===============================

		getContentPane().setBackground(SystemColor.menu);
		getContentPane().setLayout(null);

		//===============================
		// TITULO
		//===============================

		{
			JLabel lblSeleccioneLosComponentes = new JLabel("Ingrese los datos conocidos de la fracción Pesada");
			lblSeleccioneLosComponentes.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblSeleccioneLosComponentes.setBounds(14, 140, 322, 28);
			getContentPane().add(lblSeleccioneLosComponentes);
			lblSeleccioneLosComponentes.setText("Ingrese los seudocompuestos:");
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


			model = new DefaultTableModel();
			table = new JTable();
			table.setModel(model);
			model.addColumn("Nombre");
			model.addColumn("Masa molecular");
			model.addColumn("Punto de Ebullición");
			model.addColumn("Gravedad Especifica");
			model.addColumn("Temperatura Critica");
			model.addColumn("Presion Critica");
			model.addColumn("Volumen Critico");
			model.addColumn("Factor acentrico");

			JScrollPane pane = new JScrollPane(table);
			getContentPane().add(pane);
			pane.setBounds(7, 301, 1162, 315);
			getContentPane().add(pane);



		}
		{
			jLabel1 = new JLabel();
			getContentPane().add(jLabel1);
			jLabel1.setText("Masa Molecular (lb/lbmol)");
			jLabel1.setBounds(336, 140, 168, 28);
			jLabel1.setFont(new java.awt.Font("Tahoma",1,12));
		}
		{
			jLabel2 = new JLabel();
			getContentPane().add(jLabel2);
			jLabel2.setText("Punto de Ebullicion (°F)");
			jLabel2.setBounds(532, 140, 168, 28);
			jLabel2.setFont(new java.awt.Font("Tahoma",1,12));
		}
		{
			jLabel3 = new JLabel();
			getContentPane().add(jLabel3);
			jLabel3.setText("Gravedad Especifica");
			jLabel3.setBounds(742, 140, 147, 28);
			jLabel3.setFont(new java.awt.Font("Tahoma",1,12));
		}
		{
			jTextField1 = new JTextField();
			getContentPane().add(jTextField1);
			jTextField1.setBounds(350, 168, 112, 28);
			jTextField1.setText("96");
		}
		{
			jTextField2 = new JTextField();
			getContentPane().add(jTextField2);
			jTextField2.setBounds(539, 168, 112, 28);
			jTextField2.setText("198");
		}
		{
			jTextField3 = new JTextField();
			getContentPane().add(jTextField3);
			jTextField3.setBounds(749, 168, 112, 28);
			jTextField3.setText("0.7365");
		}
		{
			jLabel4 = new JLabel();
			getContentPane().add(jLabel4);
			jLabel4.setText("Recuerde que Minimo debe llenar 2 campos");
			jLabel4.setBounds(434, 203, 322, 28);
			jLabel4.setFont(new java.awt.Font("Tahoma",1,14));
		}
		{
			JLabel lblSeleccioneLosComponentes = new JLabel("Ingrese los datos conocidos de la fracción Pesada");
			lblSeleccioneLosComponentes.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblSeleccioneLosComponentes.setBounds(154, 644, 623, 28);
			getContentPane().add(lblSeleccioneLosComponentes);
			lblSeleccioneLosComponentes.setText("Elija el metodo de estimación :");
		}
		{
			ComboBoxModel jComboBoxModel = new DefaultComboBoxModel(
					new String[] { "Riazi-Daubert1", "Riazi-Daubert2","Lee-Kesler","Winn-Sim-Daubert","Watansiri-Owens-Starling" });
			jComboBox = new JComboBox();
			getContentPane().add(jComboBox);
			jComboBox.setModel(jComboBoxModel);
			jComboBox.setBounds(476, 644, 168, 28);
		}
		{
			Agregar = new JButton();
			getContentPane().add(Agregar);
			Agregar.setText("AGREGAR PSUDOCOMPONENTE");
			Agregar.setFont(new java.awt.Font("Tahoma",1,15));
			Agregar.setBounds(441, 238, 294, 28);
			Agregar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					Fracciones_No_Caracterizables f = new Fracciones_No_Caracterizables();
					if ((!jTextField1.getText().equals("")&&!jTextField2.getText().equals("")&&!jTextField3.getText().equals(""))
							|| (!jTextField1.getText().equals("")&&!jTextField2.getText().equals(""))
							|| (!jTextField1.getText().equals("")&&!jTextField3.getText().equals(""))
							|| (!jTextField2.getText().equals("")&&!jTextField3.getText().equals(""))


					){

						Vector row = new Vector();
						row.add("Pseudo_"+cont);
						if (!jTextField1.getText().equals("")){
							row.add(jTextField1.getText());
						}
						else{
							row.add("");
						}
						if (!jTextField2.getText().equals("")){
							Double num = Double.parseDouble(jTextField2.getText())+459.67;
							row.add(num+"");
						}else{
							row.add("");
						}
						if (!jTextField3.getText().equals("")){
							row.add(jTextField3.getText());
						}
						else{
							row.add("");
						}
					
						model.addRow(row);
					}
					else{
						JOptionPane.showMessageDialog(null, "Recuerde que minimo debe llenar 2 campos");
					}



					cont++;




				}
			});
		}
		{
			limpiar = new JButton();
			getContentPane().add(limpiar);
			limpiar.setText("BORRAR TABLA");
			limpiar.setFont(new Font("Tahoma",Font.PLAIN,15));
			limpiar.setBounds(924, 161, 259, 28);
			limpiar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {

					for( int i = model.getRowCount() - 1; i >= 0; i-- ) {
						model.removeRow(i);
					}
					lista_fracciones_no_caracterizables = new LinkedList<Fracciones_No_Caracterizables>();
					cont=1;
				}
			});
		}
		{
			estimar = new JButton();
			getContentPane().add(estimar);
			estimar.setText("ESTIMAR");
			estimar.setFont(new Font("Tahoma", Font.PLAIN, 15));
			estimar.setBounds(672, 644, 140, 28);
			estimar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					lista_fracciones_no_caracterizables=tabletolist();
					System.out.println(lista_final_compuestos.get(0).getNombre());
					if (jComboBox.getSelectedItem().equals("Riazi-Daubert1")){
						Metodos_De_Caracterizacion c = new Metodos_De_Caracterizacion() ;
						lista_fracciones_no_caracterizables=c.RiaziDaubert1(lista_fracciones_no_caracterizables);
						generarnuevatabla(lista_fracciones_no_caracterizables);
					}
					else if (jComboBox.getSelectedItem().equals("Riazi-Daubert2")){
						Metodos_De_Caracterizacion c = new Metodos_De_Caracterizacion() ;
						lista_fracciones_no_caracterizables=c.RiaziDaubert2(lista_fracciones_no_caracterizables);
						generarnuevatabla(lista_fracciones_no_caracterizables);


					}
					
					else if (jComboBox.getSelectedItem().equals("Lee-Kesler")){
						Metodos_De_Caracterizacion c = new Metodos_De_Caracterizacion() ;
						lista_fracciones_no_caracterizables=c.LeeKesler(lista_fracciones_no_caracterizables);
						generarnuevatabla(lista_fracciones_no_caracterizables);


					}
					else if (jComboBox.getSelectedItem().equals("Winn-Sim-Daubert")){
						Metodos_De_Caracterizacion c = new Metodos_De_Caracterizacion() ;
						lista_fracciones_no_caracterizables=c.WinSimDaubert(lista_fracciones_no_caracterizables);
						generarnuevatabla(lista_fracciones_no_caracterizables);

					}
					else if (jComboBox.getSelectedItem().equals("Watansiri-Owens-Starling")){
						Metodos_De_Caracterizacion c = new Metodos_De_Caracterizacion() ;
						lista_fracciones_no_caracterizables=c.WatansiriOwensStarling(lista_fracciones_no_caracterizables);
						generarnuevatabla(lista_fracciones_no_caracterizables);


					}





				}

				private LinkedList<Fracciones_No_Caracterizables> tabletolist() {
					LinkedList<Fracciones_No_Caracterizables> list = new LinkedList<Fracciones_No_Caracterizables>();
					
					for (int i = 0; i < model.getRowCount(); i++) {
						Fracciones_No_Caracterizables f = new Fracciones_No_Caracterizables();
						for (int j = 0; j < 4; j++) {
							String celda =model.getValueAt(i, j).toString();
							
							if(j==0){
								f.setNombre(celda);
							}
							else if (j==1&&!celda.equals("")){
								f.setMasa_molecular(Double.parseDouble(celda));
							}
							else if (j==2&&!celda.equals("")){
								f.setPunto_de_ebullicion_estandar(Double.parseDouble(celda));
							}
							else if (j==3&&!celda.equals("")){
								f.setGravedad_especifica(Double.parseDouble(celda));
							}
							

						}
						list.add(f);

					}

					return list;
				}
			});

		}
		{
			jButton2 = new JButton();
			getContentPane().add(jButton2);
			jButton2.setText("SIGUIENTE-->");
			jButton2.setFont(new java.awt.Font("Tahoma",1,15));
			jButton2.setBounds(966, 644, 161, 28);
			jButton2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					Float temperatura =(float) 0.0;
					Float presion=(float) 0.0;
					Float flujo_crudo_gas=(float) 0.0;
						//new Ecuación_de_estado_SRW_modificada(lista_final_compuestos,lista_fracciones_no_caracterizables,temperatura,presion,flujo_crudo_gas);
						new Determinación_del_equilibrio(lista_final_compuestos, lista_fracciones_no_caracterizables, temperatura, presion, flujo_crudo_gas);
				}
			});
		}

		this.repaint();
		this.setTitle("Proyecto De Maestria Unidades De Tratamiento Temprano De Una Corriente De Crudo ***U.Nacional---Ing Nina Marcela Perez B***");
		this.setVisible(true);
		this.setSize(1200,800);
		//this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		

	}






	protected void generarnuevatabla(
			LinkedList<Fracciones_No_Caracterizables> lista_fracciones_no_caracterizables2) {
		Vector row = new Vector();

		for( int i = model.getRowCount() - 1; i >= 0; i-- ) {
			model.removeRow(i);
		}
		for (int i = 0; i < lista_fracciones_no_caracterizables2.size(); i++) {
			row.add(lista_fracciones_no_caracterizables2.get(i).getNombre());
			row.add(lista_fracciones_no_caracterizables2.get(i).getMasa_molecular());
			row.add(lista_fracciones_no_caracterizables2.get(i).getPunto_de_ebullicion_estandar());
			row.add(lista_fracciones_no_caracterizables2.get(i).getGravedad_especifica());
			row.add(lista_fracciones_no_caracterizables2.get(i).getTemperatura_critica_Tc());
			row.add(lista_fracciones_no_caracterizables2.get(i).getPresión_critica_Pc());
			row.add(lista_fracciones_no_caracterizables2.get(i).getVolumen_critico_Vc());
			row.add(lista_fracciones_no_caracterizables2.get(i).getFactor_acentrico_w());
			
			model.addRow(row);
			row = new Vector();
			
			
			
		}
		
	}






	
}
