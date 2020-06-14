package com.jcg.maven;

public class Carta {
    public static final int ORO     = 4;
    public static final int COPA    = 3;
    public static final int ESPADA  = 2;
    public static final int BASTO   = 1;

    private final int palo;
    private final int numero;
    private Usuario owner;


    public Carta(int myPalo, int myNumero )
    {
        if ( ( myNumero > 10 ) || (myPalo > 4) ){
            throw new IndexOutOfBoundsException();
        }
   	    else
            palo = myPalo;
            numero = myNumero;
    }

    public int getNumero() {
        return numero;
    }

    public int getPalo() {
        return palo;
    }

    public void setOwner(Usuario owner) {
        this.owner = owner;
    }

    public Usuario getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return //"Carta{" +
                "<html>" +
                "palo=" + palo +
                " num=" + numero +
                " own=" + owner +
                "</html>";
    }
}
