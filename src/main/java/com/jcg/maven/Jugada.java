package com.jcg.maven;

import java.util.ArrayList;

public class Jugada {
    private final ArrayList<Usuario> arrayUsuarios;
    private final Mazo mazo;
    private int paloTriunfo = -1;
    private final MesaCartas mesaCartas;

    public Jugada(ArrayList<Usuario> arrayUsuarios, Mazo mazo, MesaCartas mesaCartas) {
        this.arrayUsuarios = arrayUsuarios;
        this.mazo = mazo;
        this.mazo.barajar((int)(Math.random()));  //FIXME
        this.mesaCartas = mesaCartas;
        System.out.println("Jugada creada!");
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
                if ((i==4)&&(usuario.isPie()))
                    paloTriunfo = carta.getPalo();
            }
        }
    }

    /**
     * Esta funcion deberia llamarse cuando todos los jugadores
     * han tirado una carta a la mesa.
     * Se calcula el ganador
     * Se le agrega la baza al jugador ganador.
     * @return La baza generada
     */
    public Baza generarBaza(int paloTriunfo) throws Exception {
        ArrayList<Carta> cartas = mesaCartas.getCartas();

        if ( (cartas.size() != arrayUsuarios.size()) || (paloTriunfo < 0) )
            throw new ArrayIndexOutOfBoundsException("No han tirado todos los jugadores," +
                                                     "o todavia no se ha establecido el triunfo");

        Baza baza = new Baza(paloTriunfo);
        for (Carta carta: cartas)
            baza.agregarCarta(carta);

        Usuario usuarioGanador = baza.calcularGanador();
        usuarioGanador.agregarBaza(baza);
        return baza;
    }

    public int getPaloTriunfo() {
        return paloTriunfo;
    }

}



