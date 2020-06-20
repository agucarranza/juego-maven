package com.controller;


import com.model.*;
import com.view.VistaPartida;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControllerPartida {
    private GanadorBehavior behavior = new GanadorMaximo();
    private boolean toggleBehavior = true;

    //Solucionar problema con GanadorBehavior, para que lo puedan usar los 2 metodos a la vez y estos modifiquen el mismo objeto
    public ControllerPartida(VistaPartida vista, ModeloPartida modelo) {
        vista.addRepartirListener(new RepartirListener(vista, modelo));
        vista.addJuegaPCListener(new JuegaPCListener(vista, modelo, behavior));
        vista.addNuevaBazaListener(new NuevaBazaListener(vista, modelo));
        vista.addNuevaJugadaListener(new NuevaJugadaListener(vista, modelo));
        vista.addInvertirValoresListener(new InvertirValoresListener(vista, modelo, toggleBehavior));

    }
        static class NuevaJugadaListener implements ActionListener {
            VistaPartida vista;
            ModeloPartida modelo;

            public NuevaJugadaListener(VistaPartida vista, ModeloPartida modelo) {
                this.vista = vista;
                this.modelo = modelo;
            }

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                vista.displayErrorMessage("Inicia nueva partida");
                modelo.asignarPuntoUsuario();
                for (Usuario usuario : modelo.getUsuarios())
                    usuario.clearBazas();
                modelo.startJugada();
                modelo.estadisticasEvento();
            }
        }

        static class NuevaBazaListener implements ActionListener {
            VistaPartida vista;
            ModeloPartida modelo;

            public NuevaBazaListener(VistaPartida vista, ModeloPartida modelo) {
                this.vista = vista;
                this.modelo = modelo;
            }

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                vista.displayErrorMessage("Boton nueva Baza");
                modelo.limpiarMesa();
                modelo.toggleTurno();
                System.out.println(modelo.getMesaCartas().toString());
                modelo.estadisticasEvento();
                vista.addDescartarListener(new DescartarListener(vista, modelo));
            }
        }

        /**
         * Hay que especificar en el constructor la estrategia para ganar
         */
        class JuegaPCListener implements ActionListener {
            VistaPartida vista;
            ModeloPartida modelo;
            GanadorBehavior ganadorBehavior;

            public JuegaPCListener(VistaPartida vista, ModeloPartida modelo, GanadorBehavior ganadorBehavior) {
                this.vista = vista;
                this.modelo = modelo;
                this.ganadorBehavior = ganadorBehavior;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                int paloTriunfo = modelo.getJugadaActiva().getPaloTriunfo();
                modelo.toggleTurno();
                modelo.rotarPosicionJugador();
                Robot robot = (Robot) modelo.getUsuarioEnTurno();
                Baza baza = new Baza(paloTriunfo);
                baza.setGanadorBehavior(behavior);
                int paloBaza = baza.getPaloBaza();
                int indiceCarta = robot.elegirCartaTirar(paloTriunfo, paloBaza);
                modelo.bajarALaMesa(robot, indiceCarta);
                modelo.getJugadaActiva().procesarBaza(paloTriunfo, baza);
                System.out.println(modelo.getMesaCartas().toString());
                modelo.estadisticasEvento();
            }
        }

        static class RepartirListener implements ActionListener {
            VistaPartida vista;
            ModeloPartida modelo;

            public RepartirListener(VistaPartida vista, ModeloPartida modelo) {
                this.vista = vista;
                this.modelo = modelo;
            }

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                vista.displayErrorMessage("boton repartir");
                modelo.getJugadaActiva().repartirManos();
                //Cuando las cartas están creadas, se crean sus listeners.
                vista.addDescartarListener(new DescartarListener(vista, modelo));
            }
        }

        class InvertirValoresListener implements ActionListener {
            VistaPartida vista;
            ModeloPartida modelo;
            //GanadorBehavior ganadorBehavior;
            boolean toggle;

            public InvertirValoresListener(VistaPartida vista, ModeloPartida modelo, boolean toggle) {
                this.vista = vista;
                this.modelo = modelo;
                this.toggle = toggle;
                //this.ganadorBehavior = ganadorBehavior;
            }

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                vista.displayErrorMessage("boton invertir valores");
                if (toggle) {
                    behavior = new GanadorMinimo();
                    toggleBehavior = false;
                    System.out.println("Behavoir cambiado a minimo");
                }
                else {
                    behavior = new GanadorMaximo();
                    toggleBehavior = true;
                    System.out.println("Behavoir cambiado a maximo");
                }
            }
        }

        static class DescartarListener implements MouseListener {
            VistaPartida vista;
            ModeloPartida modelo;

            public DescartarListener(VistaPartida vista, ModeloPartida modelo) {
                this.vista = vista;
                this.modelo = modelo;
            }

            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                vista.displayErrorMessage("Clic en carta");
                int indice = Integer.parseInt(mouseEvent.getComponent().getName());
                System.out.println("Mouse evento: " + indice);
                modelo.bajarALaMesa(modelo.getUsuarioEnTurno(), indice);
                System.out.println(modelo.getMesaCartas().toString());
                modelo.estadisticasEvento();
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


