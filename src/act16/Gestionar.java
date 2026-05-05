/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package act16;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author borja
 */
public class Gestionar extends JDialog{
    
    //Aqui voy a crear otra array que igualare a la que me he pasado por parametros
    private ArrayList<Llibre> lista;
    //Una tabla donde printearemos toda la array
    private DefaultTableModel modelotabla;
    //Declaramos la tabla
    private JTable tabla;
    
    public Gestionar(JFrame padre, ArrayList<Llibre> biblioteca) {
        super(padre, "Gestió de Llibres", true);
        this.lista = biblioteca;
        setSize(700, 450);

        // Configuracion de la Tabla
        modelotabla = new DefaultTableModel(new String[]{"Títol", "Autor", "Any", "Tipus"}, 0);
        tabla = new JTable(modelotabla);
        //Mostramos toda la info que haya
        refrescarTaula();

        // Botones
        JPanel pnlBotons = new JPanel();
        JButton btnAdd = new JButton("Afegir");
        JButton btnEdit = new JButton("Editar");
        JButton btnDel = new JButton("Eliminar");
        
        btnAdd.addActionListener(e -> botonAñadir());
        
        btnEdit.addActionListener(e -> botonEditar());
        
        btnDel.addActionListener(e -> botonEliminar());

        pnlBotons.add(btnAdd); 
        pnlBotons.add(btnEdit); 
        pnlBotons.add(btnDel);
        
        setLayout(new BorderLayout());
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(pnlBotons, BorderLayout.SOUTH);
        
        setLocationRelativeTo(padre);
        setVisible(true);  
        
    }

    private void botonAñadir() {
        JPanel panel = new JPanel(new GridLayout(4, 2));
        JComboBox<String> comboTipus = new JComboBox<>(new String[]{"Llibre", "Article", "Revista"});
        JTextField txtTitol = new JTextField();
        JTextField txtAutor = new JTextField();
        JTextField txtAny = new JTextField();

        panel.add(new JLabel("Tipus:")); panel.add(comboTipus);
        panel.add(new JLabel("Títol:")); panel.add(txtTitol);
        panel.add(new JLabel("Autor:")); panel.add(txtAutor);
        panel.add(new JLabel("Any:")); panel.add(txtAny);

        int result = JOptionPane.showConfirmDialog(this, panel, "Afegir Nou Element", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String tipus = (String) comboTipus.getSelectedItem();
                String t = txtTitol.getText();
                String a = txtAutor.getText();
                int y = Integer.parseInt(txtAny.getText().trim());

                // Analizamos qué instanciar
                Llibre nouElement;
                if (tipus.equals("Llibre")) nouElement = new Llibres(t, a, y);
                else if (tipus.equals("Article")) nouElement = new Articles(t, a, y);
                else nouElement = new Revistes(t, a, y);

                lista.add(nouElement);
                refrescarTaula();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "L'any ha de ser un número.");
            }
        }
    }

    private void botonEditar() {
        //Miramos si hay una fila seleccionada
        int fila = tabla.getSelectedRow();
            if (fila != -1) {
                //Si la ha seleccionado haremos lo siguiente
                //le diremos que el elemento es igual a el seleccionado en la fila
                Llibre element = lista.get(fila);
                //Por lo tanto guardamos lo que ponga el usuario a traves de estos JOptionPane
                //Para luego trasladarlos a la array
                String nombre = JOptionPane.showInputDialog(this, "Nou Títol:", element.getTitol());
                String autor = JOptionPane.showInputDialog(this, "Nou Autor:", element.getAutor());
                String nyStr = JOptionPane.showInputDialog(this, "Nou Any:", element.getAno());

                //Comprueva que lo que haya introducido el usuario no este vacio
                //o que el ano sea un numero, si todo va bien con los setters
                //cambiara toda la info del array y la tabla ya que utilizamos
                //refrescarTabla acto seguido de modificarlo
                if (nombre != null && autor != null && nyStr != null) {
                    try {
                        element.setTitol(nombre);
                        element.setAutor(autor);
                        element.setAno(Integer.parseInt(nyStr.trim()));
                        
                        refrescarTaula();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Any no vàlid.");
                    }
                }
            }
    }
    
    private void refrescarTaula() {
        //Aqui lo que hacemos es actualizar la tabla con todo lo que encuentre en
        //el array lista, que es la misma que biblioteca del padre
        modelotabla.setRowCount(0);
        for (Llibre element : lista) {
            modelotabla.addRow(new Object[]{element.getTitol(), element.getAutor(), element.getAno(), element.getTipus()});
        }
    }

    private void botonEliminar() {
        //Miramos si hay algo seleccionado
        int fila = tabla.getSelectedRow();
        
            //Si hay algo seleccionado haremos lo siguiente
            if (fila != -1) {
                //Guardaremos el nombre del titulo de la fila para printearlo
                String titolLlibre = (String) modelotabla.getValueAt(fila, 1); 

                //Aqui mostraremos el nombre y le preguntaremos si quiere eliminar
                int confirmacio = JOptionPane.showConfirmDialog(
                    this, 
                    "Estàs segur que vols eliminar: " + titolLlibre + "?", 
                    "Confirmar eliminació", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.WARNING_MESSAGE
                );

                //Si la confirmacion es que si
                if (confirmacio == JOptionPane.YES_OPTION) {
                    //Eliminaremos del array todo lo que hay en la fila
                    lista.remove(fila);
                    //Printearemos de nuevo toda la array para actualizar la tabla
                    refrescarTaula();
                    //Y le diremos que se a eliminado correctamente
                    JOptionPane.showMessageDialog(this, "Element eliminat correctament.");
                }
                
            } else {
                // Si no hay nada seleccionado, avisaremos a el usuario
                JOptionPane.showMessageDialog(this, "Si us plau, selecciona un element de la taula.");
            }
    }
    
}
