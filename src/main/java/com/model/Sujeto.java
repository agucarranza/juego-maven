package com.model;

import com.view.Observer;

public interface Sujeto {

    void agregarObserver(Observer observer);

    void quitarObserver(Observer observer);

    void notificarObservers();
}
