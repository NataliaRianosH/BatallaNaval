package myProject;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Esta clase es usada para modelar los bardos
 * @autor Natalia Riaños Horta (2042568) rianos.natalia@correounivalle.edu.co
 * Miguel Ángel Ospina Hernández (2040634) miguel.ospina.hernandez@correounivalle.edu.co
 * @version v.1.0.0 date: 1/03/2022
 */
public class Barco extends ArrayList <Integer> {
    private int tamaño; //de 1 a 4
    private int orientacion; //0 horizontal, 1vertical
    private ArrayList<Integer> posicionesX, posicionesY;
    /**
     * Constructor of Barco class
     */
    public Barco(int tamañoBarco){
        posicionesX=new ArrayList<>();
        posicionesY=new ArrayList<>();
        this.tamaño=tamañoBarco;
        this.orientacion=0;
        for(int i=0; i<tamañoBarco;i++){
            this.add(2);
        }
    }

    public void cambiarOrientacion() {
        if (orientacion == 0) {
            orientacion = 1;
        } else {
            orientacion = 0;
        }
    }
    public boolean barcoEncontrado(){ //revisar el igual
        if(tamaño==1){
         //   System.out.println("Hay solo 1");
            return true;
        }
        for(int i=0; i<tamaño;i++){
            if(this.get(i)==2){ //si alguno es un 2
             //   System.out.println("Hay un 2, por lo que no se ha encontrado el barco");
                return false;
            }
        }
        /*
        for(int i=0; i<=tamaño;i++){
            this.add(4); //dos es nada, 3 es bomba, cuatro es fuego
        }*/

        System.out.println("no se encontró un 2 por lo que no se ha enconttrado");
        return true;
    }
    public void posicionInicial(int x, int y){ //cuando se añade un barco se debe modificar la posicion del barco
        //   posicionesX.add(x);
        // posicionesY.add(y);
        //hay que hacer un ajuste:
        int max = 10 -tamaño;

        switch (orientacion){
            case 0: //horizontal
                if(x>=max){
                    x=max;
                }
                for(int i=0; i<tamaño;i++) {
                    posicionesX.add(x);
                    posicionesY.add(y);
                    x++;
                }
                break;
            case 1:

                if(y>=max){
                    y=max;
                }
                for(int i=0;i<tamaño;i++) {
                    posicionesX.add(x);
                    posicionesY.add(y);
                    y++;
                }
                break;

        }
      //  System.out.println("El barco tienes las posiciones en x: "+ posicionesX);
        //posicionesX.forEach(System.out::println);
     //   System.out.println("El barco tienes las posiciones en y: " + posicionesY);
        //  posicionesY.forEach(System.out::println);

    }


    public int getTamaño() {
        return tamaño;
    }

    public int getOrientacion() {
        return orientacion;
    }

    public ArrayList<Integer> getPosicionesX() {
        return posicionesX;
    }

    public ArrayList<Integer> getPosicionesY() {
        return posicionesY;
    }

    public void add(int i, int px, int py) {
        this.add(i, px);
    }
}
