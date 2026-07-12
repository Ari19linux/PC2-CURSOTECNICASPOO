package com.hospital.view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import com.hospital.util.Conexion;
import javax.swing.JOptionPane;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.hospital.model.Atencion;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class FrmAtencion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textIdCita;
	private JTextField textDiagnostico;
	private JTextField textTratamiento;
	private JTextField textReceta;
	private JTable tablaAtencion;
	private DefaultTableModel modelo;
	private JScrollPane scrollPane;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmAtencion frame = new FrmAtencion();
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
	public FrmAtencion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Módulo de Atención");
		setBounds(100, 100, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel_1 = new JLabel("ID Cita:");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel lblNewLabel_4 = new JLabel("Receta:");
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel lblNewLabel_3 = new JLabel("Tratamiento:");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel lblNewLabel_2 = new JLabel("Diagnóstico:");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel lblNewLabel = new JLabel("REGISTRO DE ATENCIÓN");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 16));
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {String idTexto = textIdCita.getText();
			String diagnostico = textDiagnostico.getText();
			String tratamiento = textTratamiento.getText();
			String receta = textReceta.getText();

			if(idTexto.isEmpty() || diagnostico.isEmpty() || tratamiento.isEmpty() || receta.isEmpty()) {

			    JOptionPane.showMessageDialog(null,
			            "Debe completar todos los campos");

			    return;
			}

			int idCita = Integer.parseInt(idTexto);

		    Atencion atencion = new Atencion();

		    atencion.setIdCita(idCita);
		    atencion.setDiagnostico(diagnostico);
		    atencion.setTratamiento(tratamiento);
		    atencion.setReceta(receta);
		    try {

		        Connection cn = Conexion.conectar();

		        String sql = "INSERT INTO Atencion(id_cita, diagnostico, tratamiento, receta) VALUES (?, ?, ?, ?)";

		        PreparedStatement ps = cn.prepareStatement(sql);

		        ps.setInt(1, atencion.getIdCita());
		        ps.setString(2, atencion.getDiagnostico());
		        ps.setString(3, atencion.getTratamiento());
		        ps.setString(4, atencion.getReceta());

		        ps.executeUpdate();

		        modelo.addRow(new Object[]{
		                atencion.getIdCita(),
		                atencion.getDiagnostico(),
		                atencion.getTratamiento(),
		                atencion.getReceta()
		        });

		        JOptionPane.showMessageDialog(null, "Guardado en MySQL correctamente");


		    } catch(Exception ex){

		        JOptionPane.showMessageDialog(null, 
		                "Error al guardar: " + ex.getMessage());

		    }

		    JOptionPane.showMessageDialog(null,
		            "Atención registrada correctamente\n\n" +
		            "ID Cita: " + atencion.getIdCita() +
		            "\nDiagnóstico: " + atencion.getDiagnostico() +
		            "\nTratamiento: " + atencion.getTratamiento() +
		            "\nReceta: " + atencion.getReceta()
		    );
			}
		});
		btnGuardar.setForeground(Color.BLACK);
		btnGuardar.setFont(new Font("Arial Narrow", Font.PLAIN, 13));
		
		textIdCita = new JTextField();
		textIdCita.setColumns(10);
		
		textDiagnostico = new JTextField();
		textDiagnostico.setColumns(10);
		
		textTratamiento = new JTextField();
		textTratamiento.setColumns(10);
		
		textReceta = new JTextField();
		textReceta.setColumns(10);
		
		scrollPane = new JScrollPane();

		tablaAtencion = new JTable();
		scrollPane.setViewportView(tablaAtencion);

		modelo = new DefaultTableModel();

		modelo.addColumn("ID Cita");
		modelo.addColumn("Diagnóstico");
		modelo.addColumn("Tratamiento");
		modelo.addColumn("Receta");

		tablaAtencion.setModel(modelo);
		
		btnNewButton = new JButton("Limpiar");
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("Arial Narrow", Font.PLAIN, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {textIdCita.setText("");
			textDiagnostico.setText("");
			textTratamiento.setText("");
			textReceta.setText("");
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(134)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(textIdCita, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_2)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel_3, Alignment.TRAILING)
								.addComponent(lblNewLabel_4))
							.addGap(38)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(textDiagnostico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textTratamiento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textReceta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(342, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(113, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 528, GroupLayout.PREFERRED_SIZE)
					.addGap(35))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(162)
					.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addGap(198))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(247, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(206))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textIdCita, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(textDiagnostico, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(textTratamiento, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4)
						.addComponent(textReceta, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addComponent(scrollPane, 120, 120, 120)
					.addGap(72))
		);
		contentPane.setLayout(gl_contentPane);

	}
}
