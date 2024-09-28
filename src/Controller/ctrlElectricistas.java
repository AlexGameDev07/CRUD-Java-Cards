package Controller;

import Model.mdlElectricista;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import View.JfrmDashBoardElectricista;
import javax.swing.JOptionPane;

public class ctrlElectricistas implements MouseListener, KeyListener{
    
    private mdlElectricista model;
    private JfrmDashBoardElectricista view;
    
    public ctrlElectricistas(mdlElectricista model, JfrmDashBoardElectricista view) {
        this.model = model;
        this.view = view;
        
        view.txtNombre.addKeyListener(this);
        view.txtApellido.addKeyListener(this);
        view.txtEmail.addKeyListener(this);
        view.jsEdad.addKeyListener(this);
        view.jsPeso.addKeyListener(this);
        
        view.jbtnAgregar.addMouseListener(this);

        model.Mostrar(view);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == view.jbtnAgregar)
            if ((float)view.jsPeso.getValue() == 0 
                    || view.txtNombre.getText().isEmpty() || view.txtApellido.getText().isEmpty() 
                    || view.txtEmail.getText().isEmpty() || (int)view.jsEdad.getValue()== 0) 
            {
                JOptionPane.showMessageDialog(view, "Debes llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if((int)view.jsEdad.getValue() < 18){
                JOptionPane.showMessageDialog(view, "La edad debe ser mayor a 18 años", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
             
                try {
                    //Asignar lo de la vista al modelo
                    model.setNombre(view.txtNombre.getText());
                    model.setApellido(view.txtApellido.getText());
                    model.setEdad(Integer.parseInt(view.jsEdad.getValue().toString()));
                    model.setPeso((float)view.jsPeso.getValue());
                    model.setCorreo(view.txtEmail.getText());
                    model.setId(view.id);
                    //Ejecutar el metodo 
                    if(view.jbtnAgregar.getText()=="Agregar"){
                        model.Guardar();
                    }
                    else{
                        model.Actualizar();
                    }
                    model.Mostrar(view);
                    model.Limpiar(view);
                } 
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex,"Error", JOptionPane.WARNING_MESSAGE);
                }
                    
            }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
