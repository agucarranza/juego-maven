package com.jcg.maven;

import java.util.ArrayList;

public interface GanadorBehavior {

    Carta seleccionarGanadorBehavior(ArrayList<Carta> cartasTriunfo, ArrayList<Carta> cartasBaza);
}
