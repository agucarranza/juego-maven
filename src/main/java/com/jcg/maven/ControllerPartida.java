package com.jcg.maven;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class ControllerPartida {

    private final VistaPartida vista;
    private final ModeloPartida modelo;

    public ControllerPartida(VistaPartida vista, ModeloPartida modelo) {
        this.modelo = modelo;
        this.vista = vista;

        this.vista.addRepartirListener(new RepartirListener());
        this.vista.addDescartarListener(new DescartarListener());
    }

    class RepartirListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            ArrayList<String> arrayUsuario = new ArrayList<>();
            ArrayList<String> arrayPC = new ArrayList<>();



            for (Carta carta: modelo.getUsuarios().get(0).getMyMano()) {
               arrayUsuario.add(carta.toString());
            }
            for (Carta carta: modelo.getUsuarios().get(1).getMyMano()) {
                arrayPC.add(carta.toString());
            }

            vista.setCartasManos(arrayPC, arrayUsuario);

        }
    }

    class DescartarListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            int indice = Integer.parseInt(mouseEvent.getComponent().getName());
            System.out.println("Mouse evento: " + indice);
            modelo.bajarALaMesa(modelo.getUsuarioEnTurno(), indice);
            System.out.println(modelo.getMesaCartas().toString());
            modelo.notificarObservers();
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {

        }
    }
}
