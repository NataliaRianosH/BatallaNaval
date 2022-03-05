package myProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
/**
 * Esta clase es usada para hacer visible un tablero
 * @autor Natalia Riaños Horta (2042568) rianos.natalia@correounivalle.edu.co
 * Miguel Ángel Ospina Hernández (2040634) miguel.ospina.hernandez@correounivalle.edu.co
 * @version v.1.0.0 date: 1/03/2022
 */
public class PanelTablero extends JPanel{
    private ArrayList <Barco> barcos;
    private ArrayList <ArrayList <JLabel>> mapaLabels;
    private Tablero tableroJugador;
    private JLabel labelSeleccionado;
    private int px=0, //posicion x del Label seleccionado
                py=0, //posición y del label seleccionado
                barcosEnElMapa=0;// cantidad de barcos en el mapa
    private Escucha escucha;
    private int estadoDelJuego=0; //ingresar mapa, 1, jugar

    /**
     * Constructor of PanelJugador class
     */
    public PanelTablero(){
        this.setPreferredSize(new Dimension(450, 450));
        escucha=new Escucha();
        tableroJugador=new Tablero();
        mapaLabels=new  ArrayList <ArrayList <JLabel>>();
        labelSeleccionado= new JLabel();
        barcos=new ArrayList<Barco>();
       // mapaJugador= new ArrayList <ArrayList<Integer>>();
        //mapaJugador=tableroJugador.getTablero();
        //crear los barcos y añadirlos al array:
        Barco portaaviones= new Barco(4);
        barcos.add(portaaviones);
        Barco submarino1=new Barco(3);
        barcos.add(submarino1);
        Barco submarino2=new Barco(3);
        barcos.add(submarino2);
        Barco destructor1=new Barco(2);
        barcos.add(destructor1);
        Barco destructor2=new Barco(2);
        barcos.add(destructor2);
        Barco destructor3=new Barco(2);
        barcos.add(destructor3);
        Barco fragata1=new Barco(1);
        barcos.add(fragata1);
        Barco fragata2=new Barco(1);
        barcos.add(fragata2);
        Barco fragata3=new Barco(1);
        barcos.add(fragata3);
        obtenerMapa(); //crea el mapa
        pintarMapa();
    }
    /**
     * Pinta el mapa
     */
    public void pintarMapa(){
        System.out.println("Se va a pintar el mapa");
        for(int fila=0; fila<tableroJugador.size();fila++){ //sacar las filas
            for (int x=0; x<tableroJugador.get(fila).size();x++){ //sacar las columnas
                JLabel label=mapaLabels.get(fila).get(x);
                label.setOpaque(true);
                switch (tableroJugador.get(fila).get(x)){
                    case 0:
                        label.setBackground(Color.WHITE);
                        break;
                    case 1:
                        System.out.println("Se va a pintar pink");
                        label.setBackground(Color.pink);
                        break;
                    case 2:
                        label.setBackground(Color.orange);
                        break;
                }
                label.setPreferredSize(new Dimension(40, 40));
                label.setFont(new Font("Serif", Font.PLAIN, 20));
                label.setText(Integer.toString(tableroJugador.get(fila).get(x)));
                this.add(label);

            }
        }
    }
    /**
     * cambia el estado del juego
     */
    public void cambiarEstado(int nuevoEstado){
     estadoDelJuego=nuevoEstado;
    }

    /**
     * Obtiene/ actualiza el mapa de los Jlabels
     */

    public void obtenerMapa(){
        this.removeAll();
         mapaLabels.clear();
        for(int fila=0; fila<tableroJugador.size();fila++){
            ArrayList <JLabel> nuevaFila= new ArrayList <JLabel>();
            for (int x=0; x<tableroJugador.get(fila).size();x++){
                JLabel nuevoLabel=new JLabel();
                nuevoLabel.setText(Integer.toString(tableroJugador.get(fila).get(x)));
                nuevoLabel.addMouseListener(escucha);
                nuevaFila.add(nuevoLabel);
            }
            mapaLabels.add(nuevaFila);
        }
    }

    /**
     * nos dice si un objeto se encuentra en un array. se utiliza para identificar cual es el laber seleccionado
     */
    private boolean seEncuentra(Object label, ArrayList<JLabel> buscarAquí){
        if(buscarAquí.indexOf(label)==-1){
            return false;
        }else {
            return true;
        }
    }
    /**
     * identifica cual el Label que se seleccionó
     */
    private JLabel identificarSeleccion (MouseEvent e){
        JLabel seleccionado=new JLabel("");
        int x=0, y=0;
        while(seleccionado!=e.getSource() && y <mapaLabels.size()){ //segun la cant de filas
            if(seEncuentra(e.getSource(), mapaLabels.get(y))){ //quiere decir q está en ese y
                py=y;
                break;
            }else{
                y++;
            }
        }
        px=mapaLabels.get(py).indexOf(e.getSource());
        labelSeleccionado=mapaLabels.get(py).get(px);
        return labelSeleccionado;
    }

    /**
     * remueve la escucha de todos los labels
     */

    private void removerEscucha(){
        for(int fila=0; fila<mapaLabels.size();fila++){
            for (int x=0; x<mapaLabels.get(fila).size();x++){
                mapaLabels.get(fila).get(x).removeMouseListener(escucha);
            }
        }
    }
    private class Escucha implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            if(mouseEvent.getButton()==3){ //click Derecho
                tableroJugador.borrarBarco(barcos.get(barcosEnElMapa),px,py);
                barcos.get(barcosEnElMapa).cambiarOrientacion();
                tableroJugador.pintarBarco(barcos.get(barcosEnElMapa),px,py);
                pintarMapa();
            }else{
                tableroJugador.añadirBarco(barcos.get(barcosEnElMapa),px,py);
                barcosEnElMapa++;
                pintarMapa();
                if(barcosEnElMapa>=barcos.size()) {
                    removerEscucha();
                    JOptionPane.showMessageDialog(null, "ya ingresaste todos los barcos");
                    estadoDelJuego=1;
                    tableroJugador.mostrarPorConsola();
                }
            }
        }
        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }
        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }
        @Override
        public void mouseEntered(MouseEvent mouseEvent) {
            identificarSeleccion(mouseEvent);
            System.out.println(px + " y " +py);
            tableroJugador.pintarBarco(barcos.get(barcosEnElMapa),px,py);
            pintarMapa();
        }
        @Override
        public void mouseExited(MouseEvent mouseEvent) {
              tableroJugador.borrarBarco(barcos.get(barcosEnElMapa),px,py);
        }
    }



}
