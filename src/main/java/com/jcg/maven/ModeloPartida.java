package com.jcg.maven;

import java.util.ArrayList;

public class ModeloPartida {

    private Jugada jugadaActiva;
    private final MesaCartas mesaCartas;
    private final Mazo mazo;
    private final Usuario humano;
    private final Usuario robot;
    private boolean turno = false;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Jugada> jugadas;
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
        System.out.println("Partida creada");
    }

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
        Jugada jugada = new Jugada(usuarios,mazo,mesaCartas);
        jugadas.add(jugada);
        jugadaActiva = jugada;
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
                if (usuarios.indexOf(usuario)==usuarios.size()-1)
                    usuario.setPie(true);
                else usuario.setPie(false);
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

    public Jugada getUltimaJugada() {
        return jugadas.get(jugadas.size()-1);
    }

    public Usuario getUsuarioEnTurno() {
        return usuarioEnTurno;
    }

    public MesaCartas getMesaCartas() {
        return mesaCartas;
    }

    public Jugada getJugadaActiva() {
        return jugadaActiva;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }
}
