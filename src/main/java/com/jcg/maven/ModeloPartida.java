package com.jcg.maven;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ModeloPartida implements Sujeto{

    private static ArrayList<Observer> observers;
    private String estadisticas;

    private Jugada jugadaActiva;
    private static MesaCartas mesaCartas;
    private static Mazo mazo;
    private static Usuario humano;
    private static Robot robot;
    private static boolean turno = false;
    private static ArrayList<Usuario> usuarios;
    private static ArrayList<Jugada> jugadas;
    private static Usuario usuarioEnTurno;

    //SINGLETON

    private static final ModeloPartida instance = new ModeloPartida();

    private ModeloPartida() {}

    public static ModeloPartida getInstance() {
        mesaCartas = new MesaCartas(2);
        mazo = new Mazo();
        //aca puede ir strategy
        humano = new Usuario(Usuario.HUMANO);
        robot = new Robot();
        jugadas = new ArrayList<>();
        usuarioEnTurno = humano;
        usuarios = new ArrayList<>();
        usuarios.add(humano);
        usuarios.add(robot);

        observers = new ArrayList<>();
        System.out.println("Partida creada");

        return instance;
    }

    /**
     * Usar esta funcion para agregar cartas a la mesa y quitarla de la mano.
     * No usar las funciones individualmente.
     * @param usuario El usuario que descarta la carta
     * @param index El numero de orden de la carta descartada.
     */
    public void bajarALaMesa(Usuario usuario, int index) {
        Carta carta = usuario.descartar(index);
        mesaCartas.agregarCarta(carta);
    }

    public void toggleTurno() {
        if (turno)
            usuarioEnTurno = humano;
            else
                usuarioEnTurno = robot;
        turno = !turno;
    }

    public Jugada startJugada() {
        // implementado para que se puedan agregar mas jugadores
        // en el futuro.
        Jugada jugada = new Jugada(usuarios,mazo,mesaCartas,this);
        jugadas.add(jugada);
        jugadaActiva = jugada;

        //estadisticasEvento();
        return jugada;
    }

    /**
     * Requiere que la partida tenga por lo menos una jugada creada.
     */
    public void rotarPosicionJugador() {
        if (jugadas.isEmpty())
            throw new NullPointerException("No hay ninguna jugada creada todavia.");

        //En la primera Jugada, las posiciones son arbitrarias.
        if (jugadas.size() == 1) {
            for (Usuario usuario: usuarios) {
                usuario.setPie(usuarios.indexOf(usuario) == usuarios.size() - 1);
            }
        }
        else {
            for (Usuario usuario: usuarios) {
                if (usuario.isPie()) {
                    usuario.setPie(false);
                    // caso al final
                    if (usuarios.indexOf(usuario) == usuarios.size() - 1)
                        usuarios.get(0).setPie(true);
                    //caso general
                    else
                        usuarios.get(usuarios.indexOf(usuario)+1).setPie(true);
                }
            }
        }
    }

    public Usuario getUsuarioEnTurno() { return usuarioEnTurno; }

    public MesaCartas getMesaCartas() { return mesaCartas; }

    public Jugada getJugadaActiva() { return jugadaActiva; }

    public ArrayList<Usuario> getUsuarios() { return usuarios; }

    @Override
    public void agregarObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void quitarObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notificarObservers() {
        if (observers.size() == 0)
            throw new NullPointerException("No hay un observer registrado");

        for (Observer observer: observers) {
            observer.update();
            observer.updateMano();
            observer.updateManoPC();
            observer.updateMesa();
        }
    }

    /**
     * Funcion que hay que llamar en cada funcion que requiera actualizar las estadisticas.
     */
    public void estadisticasEvento() {
        estadisticas = "<html>" +
                "Turno: " + usuarioEnTurno.getTipoUsuario() +
                " BazasUsuario:" + humano.getMyBazas().size() +
                " BazasRobot:" + robot.getMyBazas().size() +
                " Cant.Jugadas:" + jugadas.size() +
                " PuntosUsuario:" + humano.getPuntos() +
                " PuntosRobot:" + robot.getPuntos() +
                "</html>";
        notificarObservers();
    }

    public String getEstadisticas() { return estadisticas; }

    public void limpiarMesa() {
        mesaCartas.getCartas().clear();
        notificarObservers();
    }

    /**
     * Elige el usuario con mas bazas.
     */
    public void asignarPuntoUsuario() {
        Usuario ganador = Collections.max(usuarios, Comparator.comparing(s -> s.getMyBazas().size()));
        ganador.agregarPunto();
    }
}
