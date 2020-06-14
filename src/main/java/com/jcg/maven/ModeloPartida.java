package com.jcg.maven;

import java.util.ArrayList;

public class ModeloPartida implements Sujeto{

    private final ArrayList<Observer> observers;
    private String estadisticas;

    private Jugada jugadaActiva;
    private final MesaCartas mesaCartas;
    private final Mazo mazo;
    private final Usuario humano;
    private final Usuario robot;
    private boolean turno = false;
    private final ArrayList<Usuario> usuarios;
    private final ArrayList<Jugada> jugadas;
    private Usuario usuarioEnTurno;


    public ModeloPartida() {
        this.mesaCartas = new MesaCartas(2);
        this.mazo = new Mazo();
        //aca puede ir strategy
        this.humano = new Usuario(Usuario.HUMANO);
        this.robot = new Usuario(Usuario.ROBOT);
        this.jugadas = new ArrayList<>();
        this.usuarioEnTurno = humano;
        usuarios = new ArrayList<>();
        usuarios.add(humano);
        usuarios.add(robot);

        observers = new ArrayList<>();
        System.out.println("Partida creada");
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
        this.turno = !turno;
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
    private void rotarPosicionJugador() {
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
                "</html>";
        notificarObservers();
    }



    public String getEstadisticas() { return estadisticas; }

    public String getStringMano(int index) {
        if(index >= humano.getMyMano().size())
            return "";
        else
            return humano.getMyMano().get(index).toString();
    }



}
