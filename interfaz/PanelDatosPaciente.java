/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unidades.cupi2.sistemapacientes.interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.TitledBorder;
import javax.swing.*;

/**
 *
 * @author Kevin
 */
public class PanelDatosPaciente extends JPanel implements ActionListener{
    
    private final static String CALCULAR_EDAD = "CALCULAR EDAD";
    
    private InterfazSistemaPacientes principal;
    
    private JLabel labNombre;
    private JTextField txtNombre;
    
    private JLabel labApellido;
    private JTextField txtApellido;
    
    private JLabel labFNacimiento;
    private JTextField txtFNacimiento;
    
    private JLabel labSexo;
    private JTextField txtSexo;
    
    private JLabel labImagen;
    
    private JButton butEdad;
    
    private JTextField txtEdad;
    
    public PanelDatosPaciente(InterfazSistemaPacientes v){
        principal=v;
        
        setLayout(new BorderLayout());
        TitledBorder border = BorderFactory.createTitledBorder("Datos del paciente");
        border.setTitleColor(Color.BLUE);
        setBorder(border);
        
        
        JPanel panelFoto;
        
        JPanel panelInformacion;
        
        panelFoto = new JPanel();
        add(panelFoto, BorderLayout.EAST);
        
        panelInformacion = new JPanel();
        add(panelInformacion, BorderLayout.CENTER);
        
        labNombre= new JLabel("Nombre: ");
        labApellido=new JLabel("Apellido: ");
        labSexo=new JLabel("Sexo: ");
        labFNacimiento=new JLabel("Fecha de Nacimiento: ");
        
        txtNombre=new JTextField(15);
        txtNombre.setEditable(false);
        txtNombre.setBackground(Color.LIGHT_GRAY);
        txtNombre.setForeground(Color.BLUE);
        
        txtApellido=new JTextField(15);
        txtApellido.setEditable(false);
        txtApellido.setBackground(Color.LIGHT_GRAY);
        txtApellido.setForeground(Color.BLUE);
        
        txtSexo=new JTextField(2);
        txtSexo.setEditable(false);
        txtSexo.setBackground(Color.LIGHT_GRAY);
        txtSexo.setForeground(Color.BLUE);
        
        txtFNacimiento=new JTextField(15);
        txtFNacimiento.setEditable(false);
        txtFNacimiento.setBackground(Color.LIGHT_GRAY);
        txtFNacimiento.setForeground(Color.BLUE);
        
        butEdad=new JButton("Calcular Edad");
        butEdad.setActionCommand(CALCULAR_EDAD);
        txtEdad= new JTextField(10);
        txtEdad.setEditable(false);
        
        
        panelInformacion.setLayout(new GridLayout(6, 2));
        panelInformacion.add(labNombre);
        panelInformacion.add(txtNombre);
        panelInformacion.add(labApellido);
        panelInformacion.add(txtApellido);
        panelInformacion.add(labSexo);
        panelInformacion.add(txtSexo);
        panelInformacion.add(labFNacimiento);
        panelInformacion.add(txtFNacimiento);
        panelInformacion.add(butEdad);
        panelInformacion.add(txtEdad);
        
        labImagen = new JLabel("FOTO");
        panelFoto.add(labImagen);
        
        butEdad.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando=e.getActionCommand();
        if (comando.equals(CALCULAR_EDAD)) {
            principal.calcularEdad();
        }
    }
    
    public void mostrarEdad(String pEdad){
        txtEdad.setText(pEdad+"");
    }
    
    public String darEdad(){
        String rta=txtEdad.getText();
        return rta;
    }
    
    public void limpiarCampos(){
        txtEdad.setText("");
    }
    
    public void actualizarCampos(String pNombre, String pApellido,String pSexo, String pFNacimiento, String pImagen){
        txtNombre.setText(pNombre);
        txtApellido.setText(pApellido);
        txtSexo.setText(pSexo);
        txtFNacimiento.setText(pFNacimiento);
        labImagen.setIcon(new ImageIcon(pImagen));
        txtEdad.setText("");
        
    }
}
