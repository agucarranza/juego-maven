package com.jcg.maven;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControllerConsola {

    private final VistaConsola vista;
    private final ModeloPartida modelo;
    private GanadorBehavior ganadorBehavior;

    public ControllerConsola(VistaConsola vista, ModeloPartida modelo, GanadorBehavior ganadorBehavior){

        this.vista = vista;
        this.modelo = modelo;
        this.ganadorBehavior = ganadorBehavior;

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
            case "valoresascendentes":
                vista.mostrarMsj("|| Valores de cartas configurados de forma ascendente ||");
                this.ganadorBehavior = new GanadorMaximo();
                break;

            case "valoresdescendentes":
                vista.mostrarMsj("|| Valores de cartas configurados de forma descendente ||");
                this.ganadorBehavior = new GanadorMinimo();
                break;

            case "invertirvalores":
                if(this.ganadorBehavior instanceof GanadorMaximo) {
                    this.ganadorBehavior = new GanadorMinimo();
                    vista.mostrarMsj("|| Valores de cartas configurados de forma descendente ||");
                } else {
                    this.ganadorBehavior = new GanadorMaximo();
                    vista.mostrarMsj("|| Valores de cartas configurados de forma ascendente ||");
                }
                break;

            case "repartir":
                modelo.getJugadaActiva().repartirManos();
                break;

            case "ayuda":
                String ayuda =  "|| Ayuda ||\nPara comenzar el juego ingrese: repartir\n" +
                                "Para realizar una jugada de descarte ingrese el N° de la carta a descatar\n" +
                                "Para ver las estadisticas ingrese: estadisticas\n" +
                                "Para que la computadora juegue su turno ingrese: juega pc\n" +
                                "Para iniciar una nueva baza ingrese: nueva baza\n" +
                                "Para iniciar una nueva jugada ingrese: nueva jugada\n" +
                                "Para configurar las cartas con valores ascendentes ingrese: valores ascendentes\n" +
                                "Para configurar las cartas con valores descendentes ingrese: valores descendentes\n" +
                                "Para ivertir los valores de las cartas ingrese: invertir valores\n" +
                                "Para finalizar la partida cierre la ventana.";
                vista.mostrarMsj(ayuda);
                break;

            case "estadisticas":
                vista.update();
                break;

            case "juegapc":
                caseJuegaPC();
                break;

            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
                int carta = Integer.parseInt(entrada);
                vista.mostrarMsj("|| Carta N° " + carta + " descartada ||");
                modelo.bajarALaMesa(modelo.getUsuarioEnTurno(), carta-1);
                System.out.println(modelo.getMesaCartas().toString());
                modelo.estadisticasEvento();
                break;

            case "nuevajugada":
                caseNuevaJugada();
                break;

            case "nuevabaza":
                caseNuevaBaza();
                break;

            default:
                vista.displayErrorMessage();
        }
    }

    private void caseNuevaBaza() {
        vista.mostrarMsj("|| Nueva baza iniciada ||");
        modelo.limpiarMesa();
        modelo.toggleTurno();
        System.out.println(modelo.getMesaCartas().toString());
        modelo.estadisticasEvento();
    }

    private void caseNuevaJugada() {
        vista.mostrarMsj("|| Nueva jugada iniciada ||");
        modelo.asignarPuntoUsuario();
        for (Usuario usuario: modelo.getUsuarios())
            usuario.clearBazas();
        modelo.startJugada();
        modelo.estadisticasEvento();
    }

    private void caseJuegaPC(){
        vista.mostrarMsj("|| Juega la computadora ||");
        int paloTriunfo = modelo.getJugadaActiva().getPaloTriunfo();
        modelo.toggleTurno();
        modelo.rotarPosicionJugador();

        Robot robot = (Robot) modelo.getUsuarioEnTurno();
        Baza baza = new Baza(paloTriunfo);
        baza.setGanadorBehavior(ganadorBehavior);

        int paloBaza = baza.getPaloBaza();
        int indiceCarta = robot.elegirCartaTirar(paloTriunfo, paloBaza);
        modelo.bajarALaMesa(robot, indiceCarta);
        modelo.getJugadaActiva().procesarBaza(paloTriunfo, baza);
        System.out.println(modelo.getMesaCartas().toString());
        modelo.estadisticasEvento();
    }

    private void caseComportamiento(GanadorBehavior ganadorBehavior){
        vista.mostrarMsj("|| Valores de cartas reconfigurados ||");
        this.ganadorBehavior = ganadorBehavior;
    }
}
