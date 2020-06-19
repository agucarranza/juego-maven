package com.jcg.maven;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Se genera una interfaz para que los controller tengan acceso
 * a los listener.
 */
public interface Controller {

    class NuevaJugadaListener implements ActionListener {
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
            for (Usuario usuario: modelo.getUsuarios())
                usuario.clearBazas();
            modelo.startJugada();
            modelo.estadisticasEvento();
        }
    }

    class NuevaBazaListener implements ActionListener {
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
            vista.addDescartarListener(new ControllerPartida.DescartarListener(vista,modelo));
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
            Robot robot = (Robot)modelo.getUsuarioEnTurno();
            Baza baza = new Baza(paloTriunfo);
            baza.setGanadorBehavior(ganadorBehavior);
            int paloBaza = baza.getPaloBaza();
            int indiceCarta = robot.elegirCartaTirar(paloTriunfo,paloBaza);
            modelo.bajarALaMesa(robot,indiceCarta);
            modelo.getJugadaActiva().procesarBaza(paloTriunfo, baza);
            System.out.println(modelo.getMesaCartas().toString());
            modelo.estadisticasEvento();
        }
    }

    class RepartirListener implements ActionListener {
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
            vista.addDescartarListener(new ControllerPartida.DescartarListener(vista, modelo));
        }
    }

    class InvertirValoresListener implements ActionListener {
        VistaPartida vista;
        ModeloPartida modelo;
        GanadorBehavior ganadorBehavior;
        public InvertirValoresListener(VistaPartida vista, ModeloPartida modelo, GanadorBehavior ganadorBehavior){
            this.vista = vista;
            this.modelo = modelo;
            this.ganadorBehavior = ganadorBehavior;
        }
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            vista.displayErrorMessage("boton invertir valores");
            if(this.ganadorBehavior instanceof GanadorMaximo)
                this.ganadorBehavior = new GanadorMinimo();
            else
                this.ganadorBehavior = new GanadorMaximo();
        }
    }

    class DescartarListener implements MouseListener {
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
        public void mousePressed(MouseEvent mouseEvent) {}
        @Override
        public void mouseReleased(MouseEvent mouseEvent) {}
        @Override
        public void mouseEntered(MouseEvent mouseEvent) {}
        @Override
        public void mouseExited(MouseEvent mouseEvent) {}
    }
}
