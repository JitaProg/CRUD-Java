
package act16;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Act16 extends JFrame{

    //Creo una array general para poder jugar con ella en los dos JDialogs
    private ArrayList<Llibre> biblioteca = new ArrayList<>();
    
    public Act16(){
        setTitle("Biblioteca Borja");
        setSize(400,200);
        
        crearComponentes();
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    

    private void crearComponentes() {
        
        //For para no tener que estar haciendo las pruebas todo el rato a mano
        for (int i = 1; i <= 15; i++) {
            if (i % 3 == 0) {
                // Añadimos un Llibre (Fisic)
                biblioteca.add(new Llibres("Llibre nº" + i, "Autor " + i, 2000 + (i % 26)));
            } else if (i % 3 == 1) {
                // Añadimos una Revista
                biblioteca.add(new Revistes("Revista Especializada " + i, "Editorial " + i, 2010 + (i % 15)));
            } else {
                // Añadimos un Article
                biblioteca.add(new Articles("Article Científic " + i, "Investigador " + i, 2020 + (i % 6)));
            }
        }
        
        JLabel wellcome = new JLabel("Bienvenido a Biblioteca Jaume Viladoms");
        JButton gestionarLlibres = new JButton("Gestionar Llibres");
        JButton consultarCataleg = new JButton("Consultar Catàleg");
        
        JPanel pTitulo = new JPanel(new GridBagLayout());
        pTitulo.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        pTitulo.add(wellcome);
        
        JPanel pBotones = new JPanel(new GridLayout(1,2,10,10));
        pBotones.setBorder(BorderFactory.createEmptyBorder(10,20,20,20));
        pBotones.add(gestionarLlibres);
        pBotones.add(consultarCataleg);
        
        //Creo los metodos que abriran los JDialogs donde gestionare/consultare
        //le paso todo lo de este JFrame (padre) y la ArrayList biblioteca
        gestionarLlibres.addActionListener(e -> new Gestionar(this, biblioteca));
        consultarCataleg.addActionListener(e -> new Consultar(this, biblioteca));
        
        setLayout(new BorderLayout());
        add(pTitulo, BorderLayout.NORTH);
        add(pBotones, BorderLayout.CENTER);
        
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        new Act16().setVisible(true);
    }
    
}
