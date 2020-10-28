package com.view;

import com.model.ModeloPartida;
import junit.framework.TestCase;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class VistaPartidaTest extends TestCase {

    ModeloPartida modelo;
    VistaPartida vista;

    public void setUp() throws Exception {
        super.setUp();
        modelo = new ModeloPartida();
        vista = new VistaPartida(modelo);
    }

    public void testAddDescartarListener() {
        modelo.startJugada();
        modelo.getJugadaActiva().repartirManos();
      vista.addDescartarListener(new MouseListener() {
          @Override
          public void mouseClicked(MouseEvent e) {

          }

          @Override
          public void mousePressed(MouseEvent e) {

          }

          @Override
          public void mouseReleased(MouseEvent e) {

          }

          @Override
          public void mouseEntered(MouseEvent e) {

          }

          @Override
          public void mouseExited(MouseEvent e) {

          }
      });
    }

    public void testUpdateMesa() {
        modelo.startJugada();
        modelo.getJugadaActiva().repartirManos();
        modelo.bajarALaMesa(modelo.getUsuarioEnTurno(),0);
        vista.updateMesa();
    }


}