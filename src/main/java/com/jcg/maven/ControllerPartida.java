package com.jcg.maven;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControllerPartida {

    private final VistaPartida vista;
    private final ModeloPartida modelo;

    public ControllerPartida(VistaPartida vista, ModeloPartida modelo) {
        this.modelo = modelo;
        this.vista = vista;

        this.vista.addRepartirListener(new RepartirListener());
        this.vista.addJuegaPCListener(new JuegaPCListener());
        this.vista.addNuevaBazaListener(new NuevaBazaListener());
        this.vista.addNuevaJugadaListener(new NuevaJugadaListener());
    }

    class NuevaJugadaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            vista.displayErrorMessage("Inicia nueva partida");
            modelo.asignarPuntoUsuario();
            for (Usuario usuario: modelo.getUsuarios())
                usuario.clearBazas();
           //modelo.toggleTurno();
            modelo.startJugada();
            System.out.println(modelo.getUsuarios().get(0).getPuntos());
            System.out.println(modelo.getUsuarios().get(1).getPuntos());
        }
    }

    class NuevaBazaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            vista.displayErrorMessage("Boton nueva Baza");
            modelo.limpiarMesa();
            modelo.toggleTurno();
            System.out.println(modelo.getMesaCartas().toString());
            vista.addDescartarListener(new DescartarListener());
        }
    }

    class JuegaPCListener implements  ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            int paloTriunfo = modelo.getJugadaActiva().getPaloTriunfo();
            modelo.toggleTurno();
            modelo.rotarPosicionJugador();

            Robot robot = (Robot)modelo.getUsuarioEnTurno();
            Baza baza = new Baza(paloTriunfo);

            int paloBaza = baza.getPaloBaza();
            int indiceCarta = robot.elegirCartaTirar(paloTriunfo,paloBaza);
            modelo.bajarALaMesa(robot,indiceCarta);
            modelo.getJugadaActiva().procesarBaza(paloTriunfo, baza);
            vista.updateManoPC();
            System.out.println(modelo.getMesaCartas().toString());

            modelo.estadisticasEvento();
        }
    }

    class RepartirListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            vista.displayErrorMessage("boton repartir");
            modelo.getJugadaActiva().repartirManos();
            //Cuando las cartas están creadas, se crean sus listeners.
            vista.addDescartarListener(new DescartarListener());
        }
    }

    class DescartarListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            vista.displayErrorMessage("Clic en carta");

            int indice = Integer.parseInt(mouseEvent.getComponent().getName());
            System.out.println("Mouse evento: " + indice);
            modelo.bajarALaMesa(modelo.getUsuarioEnTurno(), indice);
            System.out.println(modelo.getMesaCartas().toString());
            modelo.notificarObservers();
        }
        @Override
        public void mousePressed(MouseEvent mouseEvent) {}
        @Override
        public void mouseReleased(MouseEvent mouseEvent) {}
        @Override
        public void mouseEntered(MouseEvent mouseEvent) {}
        @Override
        public void mouseExited(MouseEvent mouseEvent) {}
    }
}
