package com.jcg.maven;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControllerConsola {

    private final VistaConsola vista;
    private final ModeloPartida modelo;

    public ControllerConsola(VistaConsola vista, ModeloPartida modelo){

        this.vista = vista;
        this.modelo = modelo;

        this.vista.addEntradaListener(new EntradaListener());
        this.vista.addTecladoListener(new TecladoListener());
        }

    /*
     * Para atender el evento de cuando se apreta el boton enviar.
     */
    public class EntradaListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            leerEntrada();
        }
    }

    /*
     * Para atender el evento de cuando se apreta la tecla enter.
     */
    public class TecladoListener implements KeyListener {
        @Override
        public void keyPressed(KeyEvent keyEvent) {
            if(keyEvent.getKeyCode() == KeyEvent.VK_ENTER)
                leerEntrada();
        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {}
        @Override
        public void keyTyped(KeyEvent keyEvent) {}
    }

    private void leerEntrada(){
        String entrada = vista.getEntrada();

        switch (entrada) {
            case "repartir":
                vista.mostrarMsj(devolverCartas());
                break;

            case "ayuda":
                String ayuda = "Para comenzar el juego ingrese: Repartir \n" +
                        "Para realizar una jugada de descarte ingrese el N° de carta a descatar.\n" +
                        "Para volver a ver la estadisticas ingrese: Estadisticas.\n" +
                        "Para finalizar la partida cierre la ventana.";
                vista.mostrarMsj(ayuda);
                break;

            case "estadisticas":
                vista.mostrarMsj(modelo.getEstadisticas());
                break;

            case "1":
                caseDescarte(0);
                vista.mostrarMsj(devolverCartas());
                break;

            case "2":
                caseDescarte(1);
                vista.mostrarMsj(devolverCartas());
                break;

            case "3":
                caseDescarte(2);
                vista.mostrarMsj(devolverCartas());
                break;

            case "4":
                caseDescarte(3);
                vista.mostrarMsj(devolverCartas());
                break;

            case "5":
                caseDescarte(4);
                vista.mostrarMsj(devolverCartas());
                break;

            default:
                vista.displayErrorMessage();
        }
    }

    private String devolverCartas(){

        String cartas="Tus cartas \n";
        int index =0;

        /*for (Carta carta: modelo.getUsuarios().get(0).getMyMano()) {
            index = modelo.getUsuarios().get(0).getMyMano().indexOf(carta) + 1;
            cartas = cartas + "Carta N° " + index +": " + carta.toString() + "\n";
        }*/

        for (Carta carta: modelo.getUsuarios().get(1).getMyMano()) {
            index = modelo.getUsuarios().get(1).getMyMano().indexOf(carta) + 1;
            cartas = cartas + "Carta N° " + index +": " + carta.toString() + "\n";
        }
        return cartas;

    }

    private void caseDescarte(int index)
    {
        modelo.bajarALaMesa(modelo.getUsuarioEnTurno(), index);
        System.out.println(modelo.getMesaCartas().toString());
        modelo.notificarObservers();
    }
}
