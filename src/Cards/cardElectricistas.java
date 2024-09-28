package Cards;

import Model.mdlElectricista;
import View.JfrmDashBoardElectricista;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Lenovo V-14
 */
public class cardElectricistas extends JPanel {

    private JLabel lblNombreUsuario;
    private JLabel lblCorreo;
    private JButton btnEliminar;
    private JButton btnEditar;

    private String nombre;
    private String apellido;
    private String correo;
    
    private JPanel panelContenedor; // Panel contenedor

    public cardElectricistas(mdlElectricista objElectricista, JfrmDashBoardElectricista view) {

        var panelContenedor = view.jpCards;
        this.panelContenedor = panelContenedor; // Guardamos el panel que contiene la tarjeta

        this.nombre = objElectricista.getNombre();
        this.apellido = objElectricista.getApellido();
        this.correo = objElectricista.getCorreo();

        System.out.println(nombre + "," + apellido + "," + correo);

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(250, 50)); // Tamaño de la tarjeta: 250x50
        setOpaque(false); // Hacer el panel transparente para ver el borde redondeado

        // Panel superior
        JPanel panelSuperior = new JPanel(new BorderLayout());
        lblNombreUsuario = new JLabel(nombre + " " + apellido);
        lblNombreUsuario.setFont(new Font("Arial", Font.BOLD, 12));
        lblCorreo = new JLabel(correo);
        lblCorreo.setFont(new Font("Arial", Font.PLAIN, 10));
        panelSuperior.add(lblNombreUsuario, BorderLayout.WEST);

        // Botón de eliminar (X roja)
        btnEliminar = new JButton("X");
        btnEliminar.setBackground(Color.RED);
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setPreferredSize(new Dimension(25, 25));

        btnEliminar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Eliminar del modelo (base de datos o DAO)
                objElectricista.Eliminar();
                
                // Eliminar la tarjeta del panel visualmente
                panelContenedor.remove(cardElectricistas.this);
                
                // Actualizar el panel para que se vea reflejada la eliminación
                panelContenedor.revalidate();
                panelContenedor.repaint();
                
                System.out.println("Tarjeta eliminada: " + nombre + " " + apellido);
            }

        });
        panelSuperior.add(btnEliminar, BorderLayout.EAST);

        // Panel inferior
        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.add(lblCorreo, BorderLayout.WEST);

        // Botón de editar (Lápiz celeste)
        btnEditar = new JButton("Editar");
        btnEditar.setBackground(Color.CYAN);
        btnEditar.setForeground(Color.WHITE);
        btnEditar.setPreferredSize(new Dimension(25, 25));
        btnEditar.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Cargar los datos de la tarjeta en los campos de texto de la vista
        view.txtNombre.setText(objElectricista.getNombre());
        view.txtApellido.setText(objElectricista.getApellido());
        view.txtEmail.setText(objElectricista.getCorreo());
        view.jsEdad.setValue(objElectricista.getEdad());
        view.jsPeso.setValue(objElectricista.getPeso());

        // Ocultar el botón "Agregar" y mostrar el botón "Guardar"
        view.jbtnAgregar.setText("Guardar");
        view.id = objElectricista.getId();
        
    }
});

        panelInferior.add(btnEditar, BorderLayout.EAST);

        // Añadir paneles
        add(panelSuperior, BorderLayout.NORTH);
        add(panelInferior, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 10, 10)); // Radio de 5
    }

}
