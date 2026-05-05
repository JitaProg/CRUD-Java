
package act16;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Consultar extends JDialog{
    //Creo una lista con todo lo que hay en el array original
    private ArrayList<Llibre> listaOriginal;
    //Creo una array secundaria donde pondre lo encontrado en el buscar
    private ArrayList<Llibre> listaFiltrada;
    //Por ultimo el Jtexfield del boton buscar, la tabla y el modeloTabla
    private DefaultTableModel modeloTabla;
    private JTable tabla;
    private JTextField txtCerca;
    
    public Consultar(JFrame padre, ArrayList<Llibre> lista) {
        super(padre, "Catàleg de la Biblioteca", true);
        
        //Igualamos la lista pasada por parametros a la de aqui
        this.listaOriginal = lista;
        
        //igualemos la lista filtrada con la original para hacer una equivalencia
        //exacata con esta, al encontrar la equivalencia printearemos solo ese
        //objeto
        this.listaFiltrada = new ArrayList<>(lista);
        
        setSize(700, 450);

        JPanel pnlCerca = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtCerca = new JTextField(20);
        JButton btnCerca = new JButton("Cercar");
        JButton btnNetejar = new JButton("Mostrar Tots");

        pnlCerca.add(new JLabel("Cerca per Autor:"));
        pnlCerca.add(txtCerca);
        pnlCerca.add(btnCerca);
        pnlCerca.add(btnNetejar);

        modeloTabla = new DefaultTableModel(new String[]{"Títol", "Autor", "Any", "Tipus", "Estat"}, 0);
        tabla = new JTable(modeloTabla);
        refrescarTaula(listaFiltrada);

        JPanel pnlAccions = new JPanel();
        JButton btnEstat = new JButton("Préstec / Devolució");
        pnlAccions.add(btnEstat);


        btnCerca.addActionListener(e -> botonBuscar());

        btnNetejar.addActionListener(e -> botonLimpiar());

        btnEstat.addActionListener(e -> botonEstado());
        
        setLayout(new BorderLayout());
        add(pnlCerca, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(pnlAccions, BorderLayout.SOUTH);

        setLocationRelativeTo(padre);
        setVisible(true);
    }
    
    private void refrescarTaula(ArrayList<Llibre> llistaAMostrar) {
        //Aqui hacemos igual, hacemos que en todo momento se actualice la tabla
        //recorriendo toda la array de Llibre
        modeloTabla.setRowCount(0);
        for (Llibre elemento : llistaAMostrar) {
            modeloTabla.addRow(new Object[]{
                elemento.getTitol(), 
                elemento.getAutor(), 
                elemento.getAno(),
                elemento.getTipus(),
                elemento.getEstat()
            });
        }
    }

    private void botonEstado() {
        //Miramos que haya una fila seleccionada
        int fila = tabla.getSelectedRow();
            //Si hay alguna fila seleccionada haremos lo siguiente
            if (fila != -1) {
                //Guardamos el elemento de la lista que estamos viendo
                Llibre element = listaOriginal.get(fila);
                
                // Cambiamos el estado haciendo una equivalendia con las palabras claves
                if (element.getEstat().equals("Disponible")) {
                    element.setEstat("Prestat");
                } else {
                    element.setEstat("Disponible");
                }
                
                 // Refrescamos para ver el cambio
                refrescarTaula(listaOriginal);
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un llibre per canviar el seu estat.");
            }
    }

    private void botonBuscar() {
        String textUsuari = txtCerca.getText().trim();

        if (textUsuari.isEmpty()) {
            listaFiltrada = new ArrayList<>(listaOriginal);
        } else {
            //Creamos una nueva lista vacia donde la llenaremos con las coincidencias
            ArrayList<Llibre> resultats = new ArrayList<>();

            //Al buscar en el array si hay uan coincidencia exacta entre el nombre
            //que el usuario busca y el de la array, lo guardaremos en el array nueva
            for (Llibre buscar : listaOriginal) {
                // Comprovació estricta d'igualtat ignorant majúscules/minúscules
                if (buscar.getAutor().equalsIgnoreCase(textUsuari)) {
                    resultats.add(buscar);
                }
            }

            //Aqui por ultimo el diremos que la lista filtrada es la que hemos crado nueva
            //Para solo printear esta
            listaFiltrada = resultats;
        }

        if (listaFiltrada.isEmpty() && !textUsuari.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No s'ha trobat cap autor que coincideixi exactament amb: " + textUsuari);
        }

        //Ya que aqui volvemos a utilizar refrescarTaula y mostraremos solo el que
        //hemos encontado
        refrescarTaula(listaFiltrada);
    }

    private void botonLimpiar() {
        txtCerca.setText("");
        listaFiltrada = new ArrayList<>(listaOriginal);
        refrescarTaula(listaFiltrada);
    }
    
}
