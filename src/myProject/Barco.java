package myProject;

/**
 * Esta clase es usada para modelar los bardos
 * @autor Natalia Riaños Horta (2042568) rianos.natalia@correounivalle.edu.co
 * Miguel Ángel Ospina Hernández (2040634) miguel.ospina.hernandez@correounivalle.edu.co
 * @version v.1.0.0 date: 1/03/2022
 */
public class Barco {
    private int tamaño; //de 1 a 4
    private int orientacion; //0 horizontal, 1vertical
    /**
     * Constructor of Barco class
     */
    public Barco(int tamañoBarco){
        this.tamaño=tamañoBarco;
        this.orientacion=0;
    }

    public void cambiarOrientacion() {
        if (orientacion == 0) {
            orientacion = 1;
        } else {
            orientacion = 0;
        }
    }

    public int getTamaño() {
        return tamaño;
    }

    public int getOrientacion() {
        return orientacion;
    }
}
