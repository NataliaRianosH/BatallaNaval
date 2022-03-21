package myProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used for ...
 * @autor Natalia Riaños Horta (2042568) rianos.natalia@correounivalle.edu.co
 * @version v.1.0.0 date: 1/03/2022
 */
public class GUI extends JFrame {
    private Header headerProject;
    private ControlGame controlGame;
    private Escucha escucha;
    private JButton instrucciones, continuar, verTableroOponente;
    JPanel panelOponente, panelJugador;
    /**
     * Constructor of GUI class
     */
    public GUI(){
        initGUI();

        //Default JFrame configuration
        this.setTitle("Batalla Naval");
        //this.setSize(200,100);
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        //Set up JFrame Container's Layout
        //Create Listener Object and Control Object
        //Set up JComponents
        headerProject = new Header("Header ...", Color.BLACK);
        escucha=new Escucha();
        controlGame =new ControlGame();
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        panelJugador=new JPanel();
        panelJugador.setBackground(Color.black);
        panelJugador.add(controlGame.getPanelTableroJugador());
        this.add(panelJugador, constraints);


        instrucciones=new JButton("intrucciones");
        instrucciones.addActionListener(escucha);
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.add(instrucciones,constraints);

        continuar=new JButton("continuar");
        continuar.addActionListener(escucha);
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(continuar,constraints);

        panelOponente= new JPanel();
        panelOponente.setBackground(Color.PINK);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        panelOponente.add(controlGame.getPanelTableroOponente());
        panelOponente.setVisible(false);
        this.add(panelOponente);

        verTableroOponente=new JButton("ver tablero");
        constraints.gridx =0 ;
        constraints.gridy = 3;
        verTableroOponente.setVisible(true);
        verTableroOponente.addActionListener(escucha);

        this.add(verTableroOponente,constraints);

    }
    public void addPanel(){
        panelOponente.setVisible(true);
        panelJugador.setVisible(false);
        continuar.setVisible(false);
        verTableroOponente.setVisible(true);
        this.pack();

    }
    public void visualizarPaneles(){
        panelJugador.setVisible(true);
        panelOponente.setVisible(true);
        this.pack();

    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUI miProjectGUI = new GUI();

        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==instrucciones){
                JOptionPane.showMessageDialog(null,"", "instrucciones",-1,new ImageIcon(getClass().getResource("/imagenes/instrucciones.png")));
            }
            if(e.getSource()==continuar){
                if(controlGame.getPanelTableroJugador().getEstadoDelJuego()==0){
                    JOptionPane.showMessageDialog(null,"debes insertar todos los barcos para continuar");
                }else{
                    addPanel();
                }

            }
            if(e.getSource()==verTableroOponente){
                visualizarPaneles();

            }

        }
    }

}
