package myProject;

import java.util.ArrayList;

public class ControlGame {

    private PanelTablero panelTableroJugador, panelTableroOponente;
    private Tablero tableroJugador, tableroOponente;
    private ArrayList<Barco> barcosJugador, barcosOponentes;
    private int estadoJugador, estadoOponente;
    private String estadoJuego;
    /**
     * Constructor of ControlGame class
     */
    public ControlGame(){
        //crear y añadir barcos
        estadoJuego="añadiendo";
        barcosJugador = new ArrayList<Barco>();
        Barco portaavionJugador = new Barco(4);
        barcosJugador.add(portaavionJugador);
        Barco submarino1Jugador = new Barco(3);
        barcosJugador.add(submarino1Jugador);
        Barco submarino2ugador = new Barco(3);
        barcosJugador.add(submarino2ugador);
        Barco destructor1ugador = new Barco(2);
        barcosJugador.add(destructor1ugador);
        Barco destructor2ugador = new Barco(2);
        barcosJugador.add(destructor2ugador);
        Barco destructor3ugador = new Barco(2);
        barcosJugador.add(destructor3ugador);
        Barco fragata1ugador = new Barco(1);
        barcosJugador.add(fragata1ugador);
        Barco fragata2ugador = new Barco(1);
        barcosJugador.add(fragata2ugador);
        Barco fragata3ugador = new Barco(1);
        barcosJugador.add(fragata3ugador);

        barcosOponentes = new ArrayList<Barco>();
        Barco portaaviones = new Barco(4);
        barcosOponentes.add(portaaviones);
        Barco submarino1 = new Barco(3);
        barcosOponentes.add(submarino1);
        Barco submarino2 = new Barco(3);
        barcosOponentes.add(submarino2);
        Barco destructor1 = new Barco(2);
        barcosOponentes.add(destructor1);
        Barco destructor2 = new Barco(2);
        barcosOponentes.add(destructor2);
        Barco destructor3 = new Barco(2);
        barcosOponentes.add(destructor3);
        Barco fragata1 = new Barco(1);
        barcosOponentes.add(fragata1);
        Barco fragata2 = new Barco(1);
        barcosOponentes.add(fragata2);
        Barco fragata3 = new Barco(1);
        barcosOponentes.add(fragata3);

        tableroJugador=new Tablero();
        añadirBarcos();

        tableroOponente=new Tablero();
        tableroAleatorio();
    }
    public void añadirBarcos(){
        panelTableroJugador=new PanelTablero(tableroJugador,barcosJugador);
        panelTableroJugador.pintarMapa();
    }
    public void tableroAleatorio(){
        tableroOponente.generarTableroAleatorio(barcosOponentes);
        panelTableroOponente= new PanelTablero(tableroOponente,barcosOponentes);
        panelTableroOponente.cambiarEstado(1);
        panelTableroOponente.ocultarMapa();
    }

    public PanelTablero getPanelTableroJugador() {
        return panelTableroJugador;
    }

    public PanelTablero getPanelTableroOponente() {
        return panelTableroOponente;
    }
}
