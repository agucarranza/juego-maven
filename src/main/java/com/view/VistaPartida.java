package com.view;

import com.model.Carta;
import com.model.ModeloPartida;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class VistaPartida extends JFrame implements Observer {
    ModeloPartida modelo;

    private final ArrayList<JLabel> manoPC = new ArrayList<>();
    private final ArrayList<JLabel> manoUsuario = new ArrayList<>();
    private ArrayList<JLabel> mesaCartas = new ArrayList<>();
    private final JLabel titulo = new JLabel("ESTADISTICAS");
    private final JLabel estadisticas = new JLabel();
    //ImageIcon img = new ImageIcon("dorsal.jpg");
    JButton botonRepartir, botonJuegaPC, botonNuevaBaza, botonNuevaJugada, botonInvertirValores;
    JPanel panel;

    public VistaPartida(ModeloPartida modelo) {
        this.modelo = modelo;
        setSize(750, 560); //1024, 768
        setLocationRelativeTo(null);
        setTitle("La Mosca");
        panelPartida();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        modelo.agregarObserver(this);
    }

    private void panelPartida() {
        this.panel = new JPanel();
        this.getContentPane().add(panel);
        panel.setLayout(null);
        Color color = new Color(54, 146, 47);
        panel.setBackground(color);
        crearBotones(panel);
        panelBazas(panel);
    }

    private void crearBotones(JPanel panelAux){

        botonRepartir = new JButton("Repartir cartas");
        botonRepartir.setName("botonRepartir");
        botonRepartir.setBounds(560,270,150,30);
        panelAux.add(botonRepartir);

        botonJuegaPC = new JButton("Juega PC");
        botonJuegaPC.setName("botonJuegaPC");
        botonJuegaPC.setBounds(560,320,150,30);
        panelAux.add(botonJuegaPC);

        botonNuevaBaza = new JButton("Nueva Baza");
        botonNuevaBaza.setName("botonNuevaBaza");
        botonNuevaBaza.setBounds(560,370,150,30);
        panelAux.add(botonNuevaBaza);

        botonNuevaJugada = new JButton("Nueva Jugada");
        botonNuevaJugada.setName("botonNuevaJugada");
        botonNuevaJugada.setBounds(560,420,150,30);
        panelAux.add(botonNuevaJugada);

        botonInvertirValores = new JButton("Invertir Valores");
        botonInvertirValores.setName("botonInvertirValores");
        botonInvertirValores.setBounds(560,470,150,30);
        panelAux.add(botonInvertirValores);
    }

    private void panelBazas(JPanel panelAux){

        titulo.setBounds(550,15,140,20);
        setLabelPuntos(titulo);

        estadisticas.setBounds(550,35,140,100);
        estadisticas.setVerticalAlignment(SwingConstants.TOP);
        setLabelPuntos(estadisticas);
        estadisticas.setText("<html>Partida no iniciada</html>");
        estadisticas.setName("estadisticas");
        panelAux.add(titulo);
        panelAux.add(estadisticas);
    }

    private void setLabelPuntos(JLabel labelAux){
        Color color = new Color(103,156,244);
        Border border = LineBorder.createBlackLineBorder();
        labelAux.setHorizontalAlignment(SwingConstants.CENTER);
        labelAux.setBackground(color);
        labelAux.setFont(new Font("arial",Font.BOLD, 10));
        labelAux.setForeground(Color.BLACK);
        labelAux.setBorder(border);
        labelAux.setOpaque(true);

    }

    public void displayErrorMessage(String errorMessage){
        JOptionPane.showMessageDialog(this, errorMessage);
    }

    @Override
    public void update() {
        estadisticas.setText(modelo.getEstadisticas());
    }

    @Override
    public void updateMano() {
         int paso = 0;
        mesaCartas = new ArrayList<>();
        for (Component component: panel.getComponents()) {
            if (component.getName() != null && StringUtils.isNumeric(component.getName()))
                panel.remove(component);
            panel.revalidate();
            panel.repaint();
        }
        for (Carta carta: modelo.getUsuarios().get(0).getMyMano()) {
            JLabel jlabel = new JLabel();
            manoUsuario.add(jlabel);
            jlabel.setOpaque(true);
            jlabel.setBounds(10+(80*paso++),400,70,100);
            panel.add(jlabel);
            jlabel.setName(String.valueOf(paso-1));
            jlabel.setText(carta.toString());
        }
        System.out.println(modelo.getUsuarios().get(0).getMyMano().toString());
    }

    public void updateManoPC() {
        int paso = 0;
        for (Component component: panel.getComponents()) {
            if (component.getName() != null && component.getName().equals("cartaPC"))
                panel.remove(component);
            panel.revalidate();
            panel.repaint();
        }
        mesaCartas = new ArrayList<>();
        for (Carta carta: modelo.getUsuarios().get(1).getMyMano()) {
            JLabel jlabel = new JLabel();
            manoPC.add(jlabel);
            jlabel.setOpaque(true);
            jlabel.setBounds(10+(80*paso++),15,70,100);
            panel.add(jlabel);
            jlabel.setText(carta.toString());
            jlabel.setName("cartaPC");
        }

    }

    public void updateMesa () {
        int paso = 0;
        mesaCartas = new ArrayList<>();
        for (Carta carta: modelo.getMesaCartas().getCartas()) {
                JLabel jlabel = new JLabel();
                mesaCartas.add(jlabel);
                jlabel.setOpaque(true);
                jlabel.setBounds(210 + (80 * paso++), 140, 70, 100);
                panel.add(jlabel);
                jlabel.setText(carta.toString());
                jlabel.setName("mesa");
        }
        if (modelo.getMesaCartas().getCartas().isEmpty()) {
            for (Component component: panel.getComponents()) {
                if (component.getName() != null && component.getName().equals("mesa"))
                    panel.remove(component);
                panel.revalidate();
                panel.repaint();
            }
        }
    }

    public void addRepartirListener(ActionListener listener) {
        botonRepartir.addActionListener(listener);
    }

    public void addJuegaPCListener(ActionListener listener) {
        botonJuegaPC.addActionListener(listener);
    }

    public void addDescartarListener(MouseListener listener) {
        for (JLabel label: manoUsuario) {
            label.addMouseListener(listener);
        }
    }

    public void addNuevaBazaListener(ActionListener listener) {
        botonNuevaBaza.addActionListener(listener);
    }

    public void addNuevaJugadaListener(ActionListener listener) {
        botonNuevaJugada.addActionListener(listener);
    }

    public void addInvertirValoresListener(ActionListener listener) { botonInvertirValores.addActionListener(listener); }
}