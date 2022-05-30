/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unidades.cupi2.sistemapacientes.interfaz;

import java.awt.BorderLayout;
import javax.swing.*;
import uniandes.cupi2.sistemapacientes.mundo.Paciente;
import uniandes.cupi2.sistemapacientes.mundo.SistemaPacientes;

/**
 *
 * @author Kevin
 */
public class InterfazSistemaPacientes extends JFrame{
    
    private SistemaPacientes sistemaPacientes;
    private PanelDatosPaciente panelDatosPaciente;
    private PanelDatosMuestra panelDatosMuestra;
    private PanelExtensiones panelExtensiones;
    
    
    public InterfazSistemaPacientes(){
        setTitle("Sistema de Pacientes");
        setSize(700,450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        sistemaPacientes= new SistemaPacientes();
        
        panelDatosPaciente = new PanelDatosPaciente(this);
        panelDatosMuestra = new PanelDatosMuestra(this);
        panelExtensiones = new PanelExtensiones(this);
        
        add(panelDatosPaciente, BorderLayout.NORTH);
        add(panelDatosMuestra, BorderLayout.CENTER);
        add(panelExtensiones, BorderLayout.SOUTH);
    }
    
    public void actualizarInfoPacientes(Paciente pPaciente){
        String nombre=pPaciente.darNombre();
        String apellido=pPaciente.darApellido();
        
        String sexo="F";
        int iSexo=pPaciente.darSexo();
        if (iSexo==2) {
            sexo="M";
        }
        
        String fechaTomaMuestra=pPaciente.darFechaTomaMuestra();
        String fechaN=pPaciente.darFechaNacimiento();
        String imagen=pPaciente.darImagen();
        double volumenMuestra=pPaciente.darVolumenMuestra();
        double volumenEritrocitos=pPaciente.darVolumenEritrocitos();
        int conteoLeucocitos=pPaciente.darConteoLeucocitos();
        int conteoPlaquetas=pPaciente.darConteoPlaquetas();
        boolean enAyunas=pPaciente.darEnAyunas();
        
        panelDatosPaciente.actualizarCampos(nombre, apellido, sexo, fechaN, imagen);
        panelDatosMuestra.actualizarCampos(fechaTomaMuestra, enAyunas, volumenMuestra, volumenEritrocitos, conteoLeucocitos, conteoPlaquetas);
        
        panelDatosMuestra.limpiarCampos();
        
    }
    
    public void avanzar(){
        Paciente actual=sistemaPacientes.darPacienteSiguiente();
        actualizarInfoPacientes(actual);
        
    }
    
    
    
    public void calcularHematocrito(){
        String pVolumenMuestra=panelDatosMuestra.darVolumenMuestra();
        String pVolumenEritrocitos=panelDatosMuestra.darVolumenEritrocitos();
        
        if (pVolumenMuestra.trim().equals("") || pVolumenEritrocitos.trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Debe ingresar los datos.", "Calcular Hematocrito", JOptionPane.ERROR_MESSAGE);
        }else if (pVolumenMuestra.trim().matches("[0123456789.]*") && pVolumenEritrocitos.trim().matches("[0123456789.]*")) {
            double volumenMuestra=Double.parseDouble(pVolumenMuestra.trim());
            double volumenEritrocitos=Double.parseDouble(pVolumenEritrocitos.trim());
        
            sistemaPacientes.darPacienteActual().cambiarVolumenMuestra(volumenMuestra);
            sistemaPacientes.darPacienteActual().cambiarVolumenEritrocitos(volumenEritrocitos);
            
            double hematocrito=sistemaPacientes.darPacienteActual().calcularHematocrito();
            hematocrito=Math.round(hematocrito*100.0)/100.0;
            panelDatosMuestra.mostrarHematocrito(""+hematocrito);
        }else{
            JOptionPane.showMessageDialog(this, "Los datos ingresados no son correctos.","Calcular Hematocrito", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void cambiarEnAyunas(){
        if (panelDatosMuestra.estaEnAyunas()) {
            JOptionPane.showMessageDialog(this, "En ayunas", "Estado", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(this, "No en ayunas", "Estado", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void reqFuncOpcion1(){
        String resultado=sistemaPacientes.metodo1();
        JOptionPane.showMessageDialog(this, resultado,"Respuesta", JOptionPane.INFORMATION_MESSAGE);
    }
    
    //los metodos nuestros
    public void retroceder(){
        Paciente actual=sistemaPacientes.darPacienteAnterior();
        actualizarInfoPacientes(actual);
    }
    
    public void calcularEdad(){
        String pEdad=Integer.toString(sistemaPacientes.darPacienteActual().darEdad());
        panelDatosPaciente.mostrarEdad(pEdad);
    }
    
    public void calcularLeucocito(){
        String pVolumenMuestra=panelDatosMuestra.darVolumenMuestra();
        String pVolumenEritrocitos=panelDatosMuestra.darVolumenEritrocitos();
        String pConteoLecocitos=panelDatosMuestra.darConteoLeucocitos();
        
        if (pVolumenMuestra.trim().equals("") || pVolumenEritrocitos.trim().equals("") || pConteoLecocitos.trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Debe ingresar los datos.", "Calcular Leucocitos", JOptionPane.ERROR_MESSAGE);
        }else if (pVolumenMuestra.trim().matches("[0123456789.]*") && pVolumenEritrocitos.trim().matches("[0123456789.]*") && pConteoLecocitos.trim().matches("[0123456789.]*")) {
            double volumenMuestra=Double.parseDouble(pVolumenMuestra.trim());
            double volumenEritrocitos=Double.parseDouble(pVolumenEritrocitos.trim());
            int conteoLeucocitos=Integer.parseInt(pConteoLecocitos.trim());
        
            sistemaPacientes.darPacienteActual().cambiarVolumenMuestra(volumenMuestra);
            sistemaPacientes.darPacienteActual().cambiarVolumenEritrocitos(volumenEritrocitos);
            sistemaPacientes.darPacienteActual().cambiarConteoLeucocitos(conteoLeucocitos);
            
            double leucocito=sistemaPacientes.darPacienteActual().calcularLeucocitos();
            leucocito=Math.round(leucocito*100.0)/100.0;
            panelDatosMuestra.mostrarLeucocito(""+leucocito);
        }else{
            JOptionPane.showMessageDialog(this, "Los datos ingresados no son correctos.","Calcular Leucocito", JOptionPane.ERROR_MESSAGE);
        } 
    }
    
    public void reqFuncOpcion2(){
        String resultado=sistemaPacientes.metodo2();
        JOptionPane.showMessageDialog(this, resultado,"Respuesta", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void main(String args[]){
        InterfazSistemaPacientes interfaz = new InterfazSistemaPacientes();
        interfaz.setVisible(true);
        interfaz.setResizable(false);
        interfaz.setLocationRelativeTo(null);
        interfaz.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
