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
    private PanelTablero tablero; //donde juega el oponente
    private PanelTableroJuego tableroJugar; //donde juego yo
    private Escucha escucha;
    private JButton instrucciones, continuar, verRespuestas;
    JPanel panelTablero, panelJugar,panelBotones;

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

        //panel tablero es donde juega el oponente
        panelTablero= new JPanel();
        panelTablero.setBackground(Color.PINK);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        // tableroJugar=new PanelTableroJuego();
        tableroJugar=controlGame.getPanelTableroJugador();
        panelTablero.add(tableroJugar);
        panelTablero.setVisible(false);
        this.add(panelTablero, constraints);

        //panel jugar es dnode juego yo
        panelJugar=new JPanel();
        panelJugar.setBackground(Color.black);
        //  tablero=new PanelTablero();
        tablero=controlGame.getPanelTablero();
        panelJugar.add(tablero);
        panelJugar.setVisible(true);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        this.add(panelJugar, constraints);

        panelBotones=new JPanel();
        panelBotones.setBackground(Color.white);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        this.add(panelBotones, constraints);

        instrucciones=new JButton("intrucciones");
        instrucciones.addActionListener(escucha);
        panelBotones.add(instrucciones);

        continuar=new JButton("continuar");
        continuar.addActionListener(escucha);
        panelBotones.add(continuar);


        verRespuestas=new JButton("ver respuestas");
        verRespuestas.setVisible(false);
        verRespuestas.addActionListener(escucha);
        panelBotones.add(verRespuestas);
    }
    public void visualizarPaneles(){
        panelJugar.setVisible(true);
        panelTablero.setVisible(true);
        continuar.setVisible(false);
        verRespuestas.setVisible(true);
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
                System.out.println("EL ESTADO ES: "+ tablero.getEstadoDelJuego());
                if(tablero.getEstadoDelJuego()==0){
                    JOptionPane.showMessageDialog(null,"debes insertar todos los barcos para continuar");
                }else if(tablero.getEstadoDelJuego()==10){
                    visualizarPaneles();
                    tablero.cambiarEstado(1);
                    //   tableroJugador.addEscucha();
                }
            }
            if(e.getSource()==verRespuestas){
                if(verRespuestas.getText()=="ver respuestas"){
                    tableroJugar.verRespuestaS();
                    tablero.verRespuestaS();
                    verRespuestas.setText("ocultar respuestas");
                }else{
                    verRespuestas.setText("ver respuestas");
                    tableroJugar.ocultarRespuestaS();
                    tablero.ocultarRespuestaS();

                }
            }

        }
    }

}
