package myProject;

import java.util.ArrayList;

public class ControlGame {

    private PanelTablero  panelTablero; //donde va a jugar el oponente
    private PanelTableroJuego panelTableroJugador; //donde voy a jugar


    private Tablero tableroJugador, tableroOponente;
    private ArrayList<Barco> barcosJugador, barcosOponentes;

    /**
     * Constructor of ControlGame class
     */
    public ControlGame(){
        panelTablero = new PanelTablero();
        panelTableroJugador= new PanelTableroJuego(panelTablero);
    }

    public PanelTableroJuego getPanelTableroJugador() {
        return panelTableroJugador;
    }

    public PanelTablero getPanelTablero() {
        return panelTablero;
    }

    public ArrayList<Barco> getBarcosJugador() {
        return barcosJugador;
    }

    public ArrayList<Barco> getBarcosOponentes() {
        return barcosOponentes;
    }


    public Tablero getTableroJugador() {
        return tableroJugador;
    }

    public Tablero getTableroOponente() {
        return tableroOponente;
    }
}
