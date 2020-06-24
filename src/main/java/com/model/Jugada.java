package com.model;

import java.util.ArrayList;
import java.util.Random;

public class Jugada {
    private final ArrayList<Usuario> arrayUsuarios;
    private final Mazo mazo;
    private int paloTriunfo = -1;
    private final MesaCartas mesaCartas;
    private final ModeloPartida partida;

    public Jugada(ArrayList<Usuario> arrayUsuarios, Mazo mazo, MesaCartas mesaCartas, ModeloPartida partida) {
        this.arrayUsuarios = arrayUsuarios;
        this.mazo = mazo;
        Random rand = new Random();
        this.mazo.barajar(rand.nextInt(100));  //FIXME
        this.mesaCartas = mesaCartas;
        System.out.println("Jugada creada!");
        this.partida = partida;
    }

    /**
     * Se le reparten a todos los usuarios 5 cartas y se carga
     * el valor de paloTriunfo.
     */
    public void repartirManos() {
        for (Usuario usuario: arrayUsuarios) {
            for(int i=0; i<5; i++) {
                Carta carta = mazo.repartir();
                usuario.recibirCarta(carta);
                if ((i==4)&&(!usuario.isPie()))
                    paloTriunfo = carta.getPalo();
            }
        }
        partida.notificarObservers();
    }

    /**
     * Esta funcion deberia llamarse cuando todos los jugadores
     * han tirado una carta a la mesa.
     * Se calcula el ganador
     * Se le agrega la baza al jugador ganador.
     */
    public void procesarBaza(int paloTriunfo, Baza baza) throws ArrayIndexOutOfBoundsException {
        ArrayList<Carta> cartas = mesaCartas.getCartas();
        if ( (cartas.size() != arrayUsuarios.size()) || (paloTriunfo < 0) )
            throw new ArrayIndexOutOfBoundsException("No han tirado todos los jugadores," +
                                                     "o todavia no se ha establecido el triunfo");
        for (Carta carta: cartas)
            baza.agregarCarta(carta);
        Usuario usuarioGanador = baza.calcularGanador();
        usuarioGanador.agregarBaza(baza);
        //return baza;
    }

    public int getPaloTriunfo() {
        return paloTriunfo;
    }
}




