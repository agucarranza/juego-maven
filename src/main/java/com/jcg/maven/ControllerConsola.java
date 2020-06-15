package com.jcg.maven;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControllerConsola {

    private final VistaConsola vista;
    private final ModeloPartida modelo;

    public ControllerConsola(VistaConsola vista, ModeloPartida modelo){

        this.vista = vista;
        this.modelo = modelo;

        this.vista.addRepartirListener(new RepartirListener());
    }

    public class RepartirListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            String entrada = vista.getEntrada();

            switch (entrada){
                case "repartir":

                    vista.mostrarMsj(devolverCartas());

                    break;

                case "ayuda":
                    String ayuda = "Para comenzar el juego ingrese: Repartir \n " +
                            "Para realizar una jugada de descarte ingrese el N° de carta a descatar.\n " +
                            "Para volver a ver la estadisticas ingrese: Estadisticas.\n" +
                            "Para finalizar la partida cierre la ventana. \n";
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

            }

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
