package com.hospital.model;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import com.hospital.dao.HospitalDAO;

import java.awt.TextArea;
import java.awt.ScrollPane;
import java.awt.Button;
import java.util.List;
import java.util.ArrayList;

public class FrmMedico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtCmp;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JLabel lblNewLabel_1;
	private JTextField txtEstado;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_4;
	private JTable tabla;
	private JTextField txtFecha;
	private DefaultTableModel medico;
	private JTextField txtEspecialidad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMedico frame = new FrmMedico();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	
	 void Limpiartxt() {
		txtCodigo.setText("");
	}
	public FrmMedico() {
		setTitle("REGISTRO DE MEDICO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 755, 576);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(135, 28, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Código:");
		lblNewLabel.setBounds(36, 28, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblCmp = new JLabel("Cmp:");
		lblCmp.setBounds(36, 59, 46, 14);
		contentPane.add(lblCmp);
		
		txtCmp = new JTextField();
		txtCmp.setColumns(10);
		txtCmp.setBounds(135, 59, 64, 20);
		contentPane.add(txtCmp);
		
		JLabel lblNombres = new JLabel("Nombres:");
		lblNombres.setBounds(36, 99, 60, 14);
		contentPane.add(lblNombres);
		
		txtNombres = new JTextField();
		txtNombres.setColumns(10);
		txtNombres.setBounds(135, 99, 222, 20);
		contentPane.add(txtNombres);
		
		JLabel lblNewLabel_3 = new JLabel("Apellidos:");
		lblNewLabel_3.setBounds(36, 135, 60, 14);
		contentPane.add(lblNewLabel_3);
		
		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(135, 135, 222, 20);
		contentPane.add(txtApellidos);
		
		lblNewLabel_1 = new JLabel("Estado:");
		lblNewLabel_1.setBounds(36, 255, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		txtEstado = new JTextField();
		txtEstado.setColumns(10);
		txtEstado.setBounds(135, 255, 86, 20);
		contentPane.add(txtEstado);
		
		lblNewLabel_2 = new JLabel("Fec. Contrato:");
		lblNewLabel_2.setBounds(10, 215, 86, 14);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_4 = new JLabel("Especialidad:");
		lblNewLabel_4.setBounds(21, 175, 75, 14);
		contentPane.add(lblNewLabel_4);
		
		 	medico = new DefaultTableModel();

		 	medico.addColumn("Código");

		 	medico.addColumn("CMP");

		 	medico.addColumn("Nombres");

		 	medico.addColumn("Apellidos");

		 	medico.addColumn("Especialidad");
		 	
		 	medico.addColumn("Fec. Contrato");

		 	medico.addColumn("Estado");

	        tabla = new JTable(medico);

	        JScrollPane scroll = new JScrollPane(tabla);
	        scroll.setEnabled(false);

	        scroll.setBounds(28,297,687,179);

	        getContentPane().add(scroll);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(e -> guardarMedico());		
		getContentPane().add(btnGuardar);
		btnGuardar.setBounds(336, 503, 89, 23);
		contentPane.add(btnGuardar);
		
		txtFecha = new JTextField();
		txtFecha.setBounds(135, 212, 119, 23);
		txtFecha.setToolTipText("dd/mm/aaaa");
		getContentPane().add(txtFecha);
		
		txtEspecialidad = new JTextField();
		txtEspecialidad.setColumns(10);
		txtEspecialidad.setBounds(135, 172, 86, 20);
		contentPane.add(txtEspecialidad);
		
		Button btnBuscar = new Button("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {

		        btnBuscarActionPerformed(e);}

		    });
		btnBuscar.setBounds(516, 28, 70, 22);
		contentPane.add(btnBuscar);
		
		
	}
	
	 private void guardarMedico() {
		 try {
				Medico med =new Medico();
			
				med.setIdMedico(Integer.parseInt(txtCodigo.getText()));
				med.setCmp(txtCmp.getText());
				med.setNombres(txtNombres.getText());
				med.setApellidos(txtApellidos.getText());
				List cboEspecialidad;
				med.setIdEspecialidad(Integer.parseInt(txtEspecialidad.getText()));
				med.setFechaContratacion(String.valueOf(txtFecha.getText()));
				med.setEstado(txtEstado.getText().toString());
			
				HospitalDAO dao = new HospitalDAO();
		 
	
				if(dao.registrarMedico(med)) {
					medico.addRow(new Object[] {
							med.getIdMedico(),
							med.getCmp(),
							med.getNombres(),
							med.getApellidos(),
							med.getIdEspecialidad(),
							med.getFechaContratacion(),
							med.getEstado()
					});
					JOptionPane.showMessageDialog(this,
	                        "Médico registrado correctamente.");
		};
	} catch (SQLException ex) {
		JOptionPane.showMessageDialog(this,
                ex.getMessage());
	} catch (Exception ex) {

        JOptionPane.showMessageDialog(this,
                "Datos inválidos.");

	}}
	
	 private void btnBuscarActionPerformed(ActionEvent e) {
			try {

		        HospitalDAO dao = new HospitalDAO();

		        String criterio = "";

		        if(!txtApellidos.getText().trim().isEmpty()){

		            criterio = txtApellidos.getText().trim();

		        }else if(!txtEspecialidad.getText().trim().isEmpty()){

		            criterio = txtEspecialidad.getText().trim();

		        }else{

		            JOptionPane.showMessageDialog(this,
		                    "Ingrese un apellido o una especialidad");

		            return;
		        }

		        List<Medico> lista = dao.buscarMedicos(criterio);

		        medico.setRowCount(0);

		        for(Medico m : lista){

		            medico.addRow(new Object[]{

		                m.getIdMedico(),
		                m.getCmp(),
		                m.getNombres(),
		                m.getApellidos(),
		                m.getIdEspecialidad(),
		                m.getFechaContratacion(),
		                m.getEstado()

		            });

		        }

		        if(lista.isEmpty()){

		            JOptionPane.showMessageDialog(this,
		                    "No se encontraron registros");

		        }

		    } catch (Exception ex) {

		        JOptionPane.showMessageDialog(this,
		                ex.getMessage());

		    }
		
		}
	
	private void buscarMedicos(int codigo){

		Medico m=new Medico();

		      

	      

	       

		}
	
	public boolean eliminar(int codigo){

		try{

		Connection cn=new Conexion().conectar();

		PreparedStatement ps=
		cn.prepareStatement("DELETE FROM medico WHERE codigo=?");

		ps.setInt(1,codigo);

		ps.executeUpdate();

		return true;

		}catch(Exception e){}

		return false;

		}
	public ArrayList<Medico> listar(){

		ArrayList<Medico> lista=new ArrayList<>();

		try{

		Connection cn=new Conexion().conectar();

		PreparedStatement ps=
		cn.prepareStatement("SELECT * FROM medico");

		ResultSet rs=ps.executeQuery();

		while(rs.next()){

		Medico m=new Medico();

		m.setIdMedico(rs.getInt(1));
		m.setCmp(rs.getString(2));
		m.setNombres(rs.getString(3));
		m.setApellidos(rs.getString(4));
		m.setIdEspecialidad(rs.getInt(5));
		m.setFechaContratacion(rs.getString(6));
		m.setEstado(rs.getString(7));

		lista.add(m);

		}

		}catch(Exception e){}

		return lista;

		}
}
