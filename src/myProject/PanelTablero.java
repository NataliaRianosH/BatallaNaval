package myProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * Esta clase es usada para hacer visible el tablero en el que jugará la maquina
 * @autor Natalia Riaños Horta (2042568) rianos.natalia@correounivalle.edu.co
 * Miguel Ángel Ospina Hernández (2040634) miguel.ospina.hernandez@correounivalle.edu.co
 * @version v.1.0.0 date: 1/03/2022
 */
public class PanelTablero extends JPanel {
    private ArrayList<Barco> barcos;
    private ArrayList<ArrayList<JLabel>> mapaLabels;
    private Tablero tableroJugador;
    private JLabel labelSeleccionado;
    private int px = 0, //posicion x del Label seleccionado
            py = 0, //posición y del label seleccionado
            barcosEnElMapa = 0, // cantidad de barcos en el mapa
            cantBarcosEncontrados=0;
    private Escucha escucha;
    private int estadoDelJuego = 0; //0 añadir barcos, 1 buscar barcos
    private Barco barcoSeleccionado;
    private PanelTableroJuego oponente;
    // private ControlGame controlGame;

    /**
     * Constructor of PanelJugador class
     */
    public PanelTablero() {
        //.setIcon(new ImageIcon(getClass().getResource("/imagenes/titulo.png")));
        //  controlGame= new ControlGame();
        barcos = new ArrayList<Barco>();
        Barco portaavionJugador = new Barco(4);
        barcos.add(portaavionJugador);
        Barco submarino1Jugador = new Barco(3);
        barcos.add(submarino1Jugador);
        Barco submarino2ugador = new Barco(3);
        barcos.add(submarino2ugador);
        Barco destructor1ugador = new Barco(2);
        barcos.add(destructor1ugador);
        Barco destructor2ugador = new Barco(2);
        barcos.add(destructor2ugador);
        Barco destructor3ugador = new Barco(2);
        barcos.add(destructor3ugador);
        Barco fragata1ugador = new Barco(1);
        barcos.add(fragata1ugador);
        Barco fragata2ugador = new Barco(1);
        barcos.add(fragata2ugador);
        Barco fragata3ugador = new Barco(1);
        barcos.add(fragata3ugador);

        //  oponente= new PanelTablero(controlDeJuego);
        this.setPreferredSize(new Dimension(450, 450));
        escucha = new Escucha();
        tableroJugador= new Tablero();
        mapaLabels = new ArrayList<ArrayList<JLabel>>();
        labelSeleccionado = new JLabel();
        obtenerMapa(); //crea el mapa
        pintarMapa();
    }

    public void jugada(){
        Random aleatorio = new Random();
        py= aleatorio.nextInt(10+0);
        px=aleatorio.nextInt(10+0);
        System.out.println(" JUGADA ALEATORIA " +py +px);
        labelSeleccionado=mapaLabels.get(py).get(px);

        if(labelSeleccionado.getIcon()!=null){
            System.out.println("Tiene icono");
            do{
                py= aleatorio.nextInt(10+0);
                px= aleatorio.nextInt(10+0);
                labelSeleccionado=mapaLabels.get(py).get(px);
            }while (labelSeleccionado.getIcon()!=null);

        }else{
            labelSeleccionado=mapaLabels.get(py).get(px);

        }
        pintarMapa();
    }

    /**
     * Pinta el mapa, segun el estado del juego
     */

    public void pintarMapa(){
        if(estadoDelJuego==0){
            for (int fila = 0; fila < tableroJugador.size(); fila++) { //sacar las filas
                for (int x = 0; x < tableroJugador.get(fila).size(); x++) { //sacar las columnas
                    JLabel label = mapaLabels.get(fila).get(x);
                    label.setOpaque(true);
                    switch (tableroJugador.get(fila).get(x)) {
                        case 0:
                            label.setBackground(Color.WHITE);
                            //  label.setText("");
                            ;
                            break;
                        case 1:
                            label.setBackground(Color.pink);
                            break;
                        case 2:
                            label.setBackground(Color.orange);
                            break;
                    }
                    label.setPreferredSize(new Dimension(40, 40));
                    label.setFont(new Font("Serif", Font.PLAIN, 20));
                    //  label.setText(Integer.toString(tableroJugador.get(fila).get(x)));
                    this.add(label);
                }
            }
        }else{
            //aquí se pintaran imagenes
            switch (tableroJugador.get(py).get(px)){
                case 0: //no hay nada
                    labelSeleccionado.setBackground(Color.blue);
                    labelSeleccionado.setText("");
                    labelSeleccionado.setIcon(new ImageIcon(getClass().getResource("/imagenes/x.png")));
                    break;

                case 2:
                    labelSeleccionado.setBackground(Color.black);
                    labelSeleccionado.setText("");
                    identificarBarco();

                    int posicionAcambiar=0; //identificar la posicion a cambiar
                //    System.out.println("px "+ px+ " py "+ py + ". El barco es: "+barcos.indexOf(barcoSeleccionado)  + barcoSeleccionado +" orientacion "+ barcoSeleccionado.getOrientacion()+ " con las posicions:  posiciones x="+  barcoSeleccionado.getPosicionesX()+" posiciones y="+  barcoSeleccionado.getPosicionesY());
                    switch (barcoSeleccionado.getOrientacion()){
                        case 0: //horizontal
                            posicionAcambiar=barcoSeleccionado.getPosicionesX().indexOf(px);
                            break;
                        case 1: //vertical
                            posicionAcambiar=barcoSeleccionado.getPosicionesY().indexOf(py);
                            break;
                    }
                    //System.out.println("SE VA A CAMBIAR "+ posicionAcambiar);

                    barcoSeleccionado.set(posicionAcambiar, 3);
                    tableroJugador.add(3, px, py);
                   // System.out.println(barcoSeleccionado);
                    if(barcoSeleccionado.barcoEncontrado()){
                        cantBarcosEncontrados++;
                      //  System.out.println("Se van a pintar fuegos porque se encontró todo un barco");
                        for(int i=0; i<barcoSeleccionado.size();i++){
                            mapaLabels.get(barcoSeleccionado.getPosicionesY().get(i)).get(barcoSeleccionado.getPosicionesX().get(i)).setIcon(new ImageIcon(getClass().getResource("/imagenes/fuego.png")));
                        }
                        if(cantBarcosEncontrados==9){
                            JOptionPane.showMessageDialog(null,"El oponente es el ganador");

                        }
                    }else {
                        labelSeleccionado.setIcon(new ImageIcon(getClass().getResource("/imagenes/bomba.png")));
                    }

                    break;
            }
        }
    }

    public void ocultarMapa() {
        for( int fila = 0; fila<tableroJugador.size();fila++) { //sacar las fila
            for (int x = 0; x < tableroJugador.get(fila).size(); x++) { //sacar las columnas
                JLabel label = mapaLabels.get(fila).get(x);
                label.setBackground(new Color(98,175,205));
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
                //E   nuevoLabel.setText(Integer.toString(tableroJugador.get(fila).get(x)));
                nuevoLabel.addMouseListener(escucha);
                nuevaFila.add(nuevoLabel);
            }
            mapaLabels.add(nuevaFila);
        }
    }

    public void setOponente(PanelTableroJuego oponente) {
        this.oponente = oponente;
    }

    /**
     * nos dice si un objeto se encuentra en un array. se utiliza para identificar cual es el laber seleccionado
     */
    boolean seEncuentra(Object label, ArrayList<JLabel> buscarAquí){
        if(buscarAquí.indexOf(label)==-1){
            return false;
        }else {
            return true;
        }
    }
    /**
     * identifica cual el Label que se seleccionó
     */
    public JLabel identificarSeleccion (MouseEvent e){
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

    public void removerEscucha(){
        for(int fila=0; fila<mapaLabels.size();fila++){
            for (int x=0; x<mapaLabels.get(fila).size();x++){
                mapaLabels.get(fila).get(x).removeMouseListener(escucha);
            }
        }
    }

    /**
     * añade la escucha de todos los labels
     */

    public void addEscucha(){
        for(int fila=0; fila<mapaLabels.size();fila++){
            for (int x=0; x<mapaLabels.get(fila).size();x++){
                mapaLabels.get(fila).get(x).addMouseListener(escucha);
            }
        }
    }
    public void identificarBarco(){
        //  System.out.println("Se va aidentificar barco en "+ px + py);

        for(int i=0;i<barcos.size();i++){
            //System.out.println("x "+barcos.get(i).getPosicionesX());
          //  System.out.println("y "+barcos.get(i).getPosicionesY());
            //   System.out.println(barcos.get(i).getPosicionesX().indexOf(px) + " " +barcos.get(i).getPosicionesX().indexOf(py));
            if(barcos.get(i).getPosicionesX().indexOf(px)!=-1){ //está el x, ahora buscar el y
                if(barcos.get(i).getPosicionesY().indexOf(py)!=-1){
                    barcoSeleccionado=barcos.get(i);
                    //   System.out.println( "El barco es "+ i + barcos.get(i)+ " size "+barcos.get(i).size()+" Horientacion "+ barcoSeleccionado.getOrientacion());
                    break;
                }

            }
        }
    }

    public void verRespuestaS(){
        for (int fila = 0; fila < tableroJugador.size(); fila++) { //sacar las filas
            for (int x = 0; x < tableroJugador.get(fila).size(); x++) { //sacar las columnas
                JLabel label = mapaLabels.get(fila).get(x);
                label.setOpaque(true);
                label.setPreferredSize(new Dimension(40, 40));
                label.setFont(new Font("Serif", Font.PLAIN, 20));
                label.setText(Integer.toString(tableroJugador.get(fila).get(x)));
                this.add(label);
            }
        }
    }
    public void ocultarRespuestaS(){
        for (int fila = 0; fila < tableroJugador.size(); fila++) { //sacar las filas
            for (int x = 0; x < tableroJugador.get(fila).size(); x++) { //sacar las columnas
                JLabel label = mapaLabels.get(fila).get(x);
                label.setOpaque(true);
                label.setPreferredSize(new Dimension(40, 40));
                label.setFont(new Font("Serif", Font.PLAIN, 20));
                label.setText("");
                this.add(label);
            }
        }
    }

    public ArrayList<Barco> getBarcos() {
        return barcos;
    }

    public Barco getBarcoSeleccionado() {
        return barcoSeleccionado;
    }



    public int getEstadoDelJuego() {
        return estadoDelJuego;
    }

    private class Escucha implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            if(estadoDelJuego==0){
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
                        //  removerEscucha();
                        ocultarMapa();
                        //estadoDelJuego=0;
                        tableroJugador.mostrarPorConsola();
                        JOptionPane.showMessageDialog(null, "ya ingresaste todos los barcos");
                        estadoDelJuego=10;
//                        System.out.println("EL ESTADO ESs: "+getEstadoDelJuego());

                    }
                }
            }/*else if(estadoDelJuego==1){
             //   addEscucha();
                identificarSeleccion(mouseEvent);
                pintarMapa();
            }*/
        }
        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }
        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }
        @Override
        public void mouseEntered(MouseEvent mouseEvent) {
            if(estadoDelJuego==0){
                identificarSeleccion(mouseEvent);
                tableroJugador.pintarBarco(barcos.get(barcosEnElMapa),px,py);
                pintarMapa();
            }
        }
        @Override
        public void mouseExited(MouseEvent mouseEvent) {
            if(estadoDelJuego==0){
                tableroJugador.borrarBarco(barcos.get(barcosEnElMapa),px,py);

            }
        }
    }

}