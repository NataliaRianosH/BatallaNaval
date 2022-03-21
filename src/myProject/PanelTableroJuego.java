package myProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

public class PanelTableroJuego extends JPanel {
    private Tablero tablero;
    private Barco barcoSeleccionado;
    private ArrayList<ArrayList<JLabel>> mapaLabels;
    private JLabel labelSeleccionado;
    private int px, py, cantBarcosEncontrados=0;
    private Escucha escucha;
    private ArrayList<Barco> barcos;

    /**
     * Constructor of PanelJugador class
     */
    public PanelTableroJuego(PanelTablero tableoOponente){
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

        escucha=new Escucha();
        tablero= new Tablero(); //tablero aleatorio
        tablero.generarTableroAleatorio(barcos);
        System.out.println(tablero);
        // this.removerEscucha();
        this.setPreferredSize(new Dimension(450, 450));
        mapaLabels = new ArrayList<ArrayList<JLabel>>();
        obtenerMapa();
        pintarMapa();

    }

    public void obtenerMapa(){
        this.removeAll();
        mapaLabels.clear();
        for(int fila=0; fila<tablero.size();fila++){
            ArrayList <JLabel> nuevaFila= new ArrayList <JLabel>();
            for (int x=0; x<tablero.get(fila).size();x++){
                JLabel nuevoLabel=new JLabel();
                //E   nuevoLabel.setText(Integer.toString(tableroJugador.get(fila).get(x)));
                nuevoLabel.addMouseListener(escucha);
                nuevaFila.add(nuevoLabel);
            }
            mapaLabels.add(nuevaFila);
        }
    }


    public void verRespuestaS(){
        for (int fila = 0; fila < tablero.size(); fila++) { //sacar las filas
            for (int x = 0; x < tablero.get(fila).size(); x++) { //sacar las columnas
                JLabel label = mapaLabels.get(fila).get(x);
                label.setOpaque(true);
                switch (tablero.get(fila).get(x)) {
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
                label.setText(Integer.toString(tablero.get(fila).get(x)));
                this.add(label);
            }
        }
    }
    public void ocultarRespuestaS(){
        for (int fila = 0; fila < tablero.size(); fila++) { //sacar las filas
            for (int x = 0; x < tablero.get(fila).size(); x++) { //sacar las columnas
                JLabel label = mapaLabels.get(fila).get(x);
                label.setBackground(Color.WHITE);
                label.setOpaque(true);
                label.setPreferredSize(new Dimension(40, 40));
                label.setFont(new Font("Serif", Font.PLAIN, 20));
                label.setText("");
                this.add(label);
            }
        }
    }

    public void identificarBarco(){
        //  System.out.println("Se va aidentificar barco en "+ px + py);

        for(int i=0;i<barcos.size();i++){
            System.out.println("x "+barcos.get(i).getPosicionesX());
            System.out.println("y "+barcos.get(i).getPosicionesY());
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

    public void jugada(){
        //aquí se pintaran imagenes
        switch (tablero.get(py).get(px)){
            case 0: //no hay nada
                // labelSeleccionado.setBackground(Color.blue);
                labelSeleccionado.setText("");
                labelSeleccionado.setIcon(new ImageIcon(getClass().getResource("/imagenes/x.png")));
                break;

            case 2:
                // labelSeleccionado.setBackground(Color.black);
                labelSeleccionado.setText("");
                this.identificarBarco();

                int posicionAcambiar=0; //identificar la posicion a cambiar
                //   System.out.println("px "+ px+ " py "+ py + ". El barco es: "+arcos().indexOf(this.getBarcoSeleccionado())  + this.getBarcoSeleccionado() +" orientacion "+ this.getBarcoSeleccionado().getOrientacion()+ " con las posicions:  posiciones x="+  this.getBarcoSeleccionado().getPosicionesX()+" posiciones y="+  this.getBarcoSeleccionado().getPosicionesY());
                switch (barcoSeleccionado.getOrientacion()){
                    case 0: //horizontal
                        posicionAcambiar=barcoSeleccionado.getPosicionesX().indexOf(px);
                        break;
                    case 1: //vertical
                        posicionAcambiar=barcoSeleccionado.getPosicionesY().indexOf(py);
                        break;
                }
                System.out.println("SE VA A CAMBIAR "+ posicionAcambiar);

                barcoSeleccionado.set(posicionAcambiar, 3);
                tablero.add(3, px, py);
                // System.out.println(getBarcoSeleccionado);
                if(barcoSeleccionado.barcoEncontrado()){
                    cantBarcosEncontrados++;
                    System.out.println("Cantidad de barcos encontrados: "+ cantBarcosEncontrados);
                    for(int i=0; i<barcoSeleccionado.size();i++){
                        mapaLabels.get(barcoSeleccionado.getPosicionesY().get(i)).get(barcoSeleccionado.getPosicionesX().get(i)).setIcon(new ImageIcon(getClass().getResource("/imagenes/fuego.png")));
                    }
                    if(cantBarcosEncontrados==9){
                        JOptionPane.showMessageDialog(null,"Encontraste todos los barcos");

                    }

                }else {
                    labelSeleccionado.setIcon(new ImageIcon(getClass().getResource("/imagenes/bomba.png")));
                }

                break;
        }
    }

    public void pintarMapa(){
        for (int fila = 0; fila < tablero.size(); fila++) { //sacar las filas
            for (int x = 0; x < tablero.get(fila).size(); x++) { //sacar las columnas
                JLabel label = mapaLabels.get(fila).get(x);
                label.setOpaque(true);
                label.setBackground(Color.WHITE);
                label.setPreferredSize(new Dimension(40, 40));
                label.setFont(new Font("Serif", Font.PLAIN, 20));
                //E   label.setText(Integer.toString(tableroJugador.get(fila).get(x)));
                this.add(label);
            }
        }
    }
    public int generarCoordenadaAleatoria(){
        Random random = new Random();
        return 1;
    }

    boolean seEncuentra(Object label, ArrayList<JLabel> buscarAquí){
        if(buscarAquí.indexOf(label)==-1){
            return false;
        }else {
            return true;
        }
    }


    public ArrayList<Barco> getBarcos() {
        return barcos;
    }

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

    private class Escucha implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            identificarSeleccion(mouseEvent);
            jugada();
        }
        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }
        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }
        @Override
        public void mouseEntered(MouseEvent mouseEvent) {
        }
        @Override
        public void mouseExited(MouseEvent mouseEvent) {
        }
    }
}
