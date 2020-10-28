package com.model;

public final class MazoUtils {
    public static final int NCARDS = 40;

    private static Carta[] miMazo;
    private static int currentCard;

    //SINGLETON

    private static final MazoUtils instance = new MazoUtils();// = new Mazo();
    private MazoUtils() {}

    public static MazoUtils getInstance() {
        miMazo = new Carta[NCARDS];
        int i = 0;

        for ( int suit = Carta.BASTO; suit <= Carta.ORO; suit++ ) {
            for (int rank = 1; rank <= 10; rank++) {
                miMazo[i++] = new Carta(suit, rank);
            }
        }

        currentCard = 0;
        //System.out.println("Mazo creado");

        return instance;
    }

    public static void barajar(int n) {
        int i, j, k;

        for ( k = 0; k < n; k++ )
        {
            i = (int) ( NCARDS * Math.random() );  // Pick 2 random cards
            j = (int) ( NCARDS * Math.random() );  // in the deck

   	     /* ---------------------------------
   		swap these randomly picked cards
   		--------------------------------- */
            Carta tmp = miMazo[i];
            miMazo[i] = miMazo[j];
            miMazo[j] = tmp;
        }
        currentCard = 0;   // Reset current card to deal
    }

    public static Carta repartir() {

            if ( currentCard < NCARDS )
            {
                return  miMazo[ currentCard++ ] ;
            }
            else
            {
                //System.out.println("Out of cards error");
                return null ;  // Error;
            }
    }
}
