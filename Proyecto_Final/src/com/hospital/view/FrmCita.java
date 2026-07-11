package com.hospital.view;

import com.hospital.dao.HospitalDAO;
import com.hospital.model.Cita;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;

import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FrmCita extends JFrame {

    //==========================
    // COMPONENTES
    //==========================

    private JTextField txtPaciente;
    private JTextField txtMedico;
    private JTextField txtFecha;
    private JTextField txtHora;
    private JTextField txtMotivo;

    private JComboBox<String> cboEstado;

    private JButton btnGuardar;
    private JButton btnLimpiar;

    private JTable tabla;

    private DefaultTableModel modelo;

    //==========================
    // CONSTRUCTOR
    //==========================

    public FrmCita() {

        setTitle("REGISTRO DE CITAS");

        setSize(900,650);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        iniciarComponentes();

    }

    //==========================
    // CREAR COMPONENTES
    //==========================

    private void iniciarComponentes() {

        setLayout(null);

        //------------------------
        // TITULO
        //------------------------

        JLabel titulo = new JLabel("REGISTRO DE CITAS");

        titulo.setBounds(280,20,400,40);

        titulo.setFont(new Font("Arial",Font.BOLD,28));

        add(titulo);

        //------------------------
        // ID PACIENTE
        //------------------------

        JLabel lblPaciente = new JLabel("ID Paciente");

        lblPaciente.setBounds(90,90,120,25);

        add(lblPaciente);

        txtPaciente = new JTextField();

        txtPaciente.setBounds(220,90,180,25);

        add(txtPaciente);

        //------------------------
        // ID MEDICO
        //------------------------

        JLabel lblMedico = new JLabel("ID Médico");

        lblMedico.setBounds(90,130,120,25);

        add(lblMedico);

        txtMedico = new JTextField();

        txtMedico.setBounds(220,130,180,25);

        add(txtMedico);

        //------------------------
        // FECHA
        //------------------------

        JLabel lblFecha = new JLabel("Fecha");

        lblFecha.setBounds(90,170,120,25);

        add(lblFecha);

        txtFecha = new JTextField();

        txtFecha.setBounds(220,170,180,25);

        txtFecha.setToolTipText("AAAA-MM-DD");

        add(txtFecha);

        //------------------------
        // HORA
        //------------------------

        JLabel lblHora = new JLabel("Hora");

        lblHora.setBounds(90,210,120,25);

        add(lblHora);

        txtHora = new JTextField();

        txtHora.setBounds(220,210,180,25);

        txtHora.setToolTipText("HH:MM");

        add(txtHora);

        //------------------------
        // MOTIVO
        //------------------------

        JLabel lblMotivo = new JLabel("Motivo");

        lblMotivo.setBounds(90,250,120,25);

        add(lblMotivo);

        txtMotivo = new JTextField();

        txtMotivo.setBounds(220,250,350,25);

        add(txtMotivo);

        //------------------------
        // ESTADO
        //------------------------

        JLabel lblEstado = new JLabel("Estado");

        lblEstado.setBounds(90,290,120,25);

        add(lblEstado);

        cboEstado = new JComboBox<>();

        cboEstado.addItem("Pendiente");
        cboEstado.addItem("Atendida");
        cboEstado.addItem("Cancelada");

        cboEstado.setBounds(220,290,180,25);

        add(cboEstado);

        //------------------------
        // BOTONES
        //------------------------

        btnGuardar = new JButton("Guardar");

        btnGuardar.setBounds(180,350,140,40);
        
        btnGuardar.addActionListener(e -> guardarCita());

        add(btnGuardar);

        btnLimpiar = new JButton("Limpiar");

        btnLimpiar.setBounds(360,350,140,40);

        add(btnLimpiar);

        //------------------------
        // TABLA
        //------------------------

        modelo = new DefaultTableModel();

        modelo.addColumn("Paciente");

        modelo.addColumn("Médico");

        modelo.addColumn("Fecha");

        modelo.addColumn("Hora");

        modelo.addColumn("Motivo");

        modelo.addColumn("Estado");

        tabla = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabla);

        scroll.setBounds(40,430,800,150);

        add(scroll);

    }
    
    private void guardarCita() {

        try {

            Cita cita = new Cita();

            cita.setIdPaciente(Integer.parseInt(txtPaciente.getText()));

            cita.setIdMedico(Integer.parseInt(txtMedico.getText()));

            cita.setFechaCita(LocalDate.parse(txtFecha.getText()));

            cita.setHoraCita(LocalTime.parse(txtHora.getText()));

            cita.setMotivo(txtMotivo.getText());

            cita.setEstado(cboEstado.getSelectedItem().toString());

            HospitalDAO dao = new HospitalDAO();

            if (dao.registrarCita(cita)) {

                modelo.addRow(new Object[]{

                        cita.getIdPaciente(),

                        cita.getIdMedico(),

                        cita.getFechaCita(),

                        cita.getHoraCita(),

                        cita.getMotivo(),

                        cita.getEstado()

                });

                JOptionPane.showMessageDialog(this,
                        "Cita registrada correctamente.");

            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(this,
                    ex.getMessage());

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(this,
                    "Datos inválidos.");

        }

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new FrmCita().setVisible(true);

        });

    }

}