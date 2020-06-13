package com.jcg.maven;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;



public class VistaPartida extends JFrame {

    private ArrayList<JLabel> manoPC = new ArrayList<>();
    private ArrayList<JLabel> manoUsuario = new ArrayList<JLabel>();
    private ArrayList<JLabel> bazaPC = new ArrayList<JLabel>();
    private ArrayList<JLabel> bazaUsuario = new ArrayList<JLabel>();
    ImageIcon img = new ImageIcon("dorsal.jpg");
    JButton boton;

    public VistaPartida() {

        setSize(800, 500);
        setLocationRelativeTo(null);
        setTitle("La Mosca");

        panelPartida();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void panelPartida() {
        JPanel panel = new JPanel();
        this.getContentPane().add(panel);
        panel.setLayout(null);
        Color color = new Color(54, 146, 47);
        panel.setBackground(color);

        //54,146,47: r,g,b

        crearCartas();
        crearBotones(panel);
        acomodarCartas(panel);

    }

    /**
     * Genera las manos para los dos usuarios.
     */
    private void crearCartas(){

        int x1=10, x2=100;

        for(int i=0; i<5; i++){

            JLabel carta = new JLabel();
            JLabel carta2 = new JLabel();
            carta.setOpaque(true);
            carta.setBounds(x1,15,70,100);
            //llamada al metodo con valor de cartaPC
            manoPC.add(i,carta);
            carta2.setOpaque(true);
            carta2.setBounds(x2,305,70,100);
            //llamada al metodo con valor de cartaUsuario
            manoUsuario.add(i,carta2);
            x1=x1+80;
            x2=x2+80;

        }
        crearBazas();
    }

    private void crearBotones(JPanel panelAux){

        boton = new JButton("Repartir cartas");
        boton.setFont(new Font("arial",Font.BOLD,10));
        boton.setBounds(560,270,70,20);
        panelAux.add(boton);

    }

    private void acomodarCartas(JPanel panelAux){
        for(int i=0; i<5; i++) {
            panelAux.add(manoPC.get(i));
            panelAux.add(manoUsuario.get(i));
        }
        JLabel mazo = new JLabel(img);
        mazo.setBounds(560,150,70,100);
        mazo.setOpaque(true);
        panelAux.add(mazo);

        panelAux.add(bazaPC.get(0));
        panelAux.add(bazaUsuario.get(0));

        panelAux.add(crearLabelPC());
        panelAux.add(crearLabelUsuario());

    }

    private void crearBazas(){
        //for para ir agregando las bazas en otro metodo
        JLabel cartaBazaPC = new JLabel(img);
        cartaBazaPC.setOpaque(true);
        cartaBazaPC.setBounds(420,15,70,100);
        bazaPC.add(cartaBazaPC);
        JLabel cartaBazaUsuario = new JLabel(img);
        cartaBazaUsuario.setOpaque(true);
        cartaBazaUsuario.setBounds(10,305,70,100);
        bazaUsuario.add(cartaBazaUsuario);

    }

    private JLabel crearLabelPC(){
        JLabel labelBazaPC = new JLabel("BAZA PC");
        Color color = new Color(54,146,47);
        labelBazaPC.setBounds(420,5,70,10);
        labelBazaPC.setFont(new Font("arial",Font.ITALIC,10));
        labelBazaPC.setHorizontalAlignment(SwingConstants.CENTER);
        labelBazaPC.setForeground(Color.BLUE);
        labelBazaPC.setBackground(color);
        labelBazaPC.setOpaque(true);

        return labelBazaPC;
    }

    private JLabel crearLabelUsuario(){
        JLabel labelBazaUsuario = new JLabel("BAZA USUARIO");
        Color color = new Color(54,146,47);
        labelBazaUsuario.setBounds(10,295,70,10);
        labelBazaUsuario.setFont(new Font("arial", Font.ITALIC,8));
        labelBazaUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        labelBazaUsuario.setForeground(Color.BLUE);
        labelBazaUsuario.setBackground(color);
        labelBazaUsuario.setOpaque(true);

        return labelBazaUsuario;
    }

    void addRepartirListener(ActionListener listener) {
        boton.addActionListener(listener);
    }

    void setCartasManos(ArrayList<String> arrayCartaPc, ArrayList<String> arrayCartaUsuario) {
        for (JLabel jlabel: manoPC) {
            jlabel.setText(arrayCartaPc.get(manoPC.indexOf(jlabel)));
        }
        for (JLabel jlabel: manoUsuario) {
            jlabel.setText(arrayCartaUsuario.get(manoUsuario.indexOf(jlabel)));
        }
    }
}