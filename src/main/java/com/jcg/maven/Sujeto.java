package com.jcg.maven;

public interface Sujeto {

    void agregarObserver(Observer observer);

    void quitarObserver(Observer observer);

    void notificarObservers();
}
