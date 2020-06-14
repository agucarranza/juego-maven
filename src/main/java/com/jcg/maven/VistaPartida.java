package com.jcg.maven;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class VistaPartida extends JFrame implements Observer {

    ModeloPartida modelo;

    private final ArrayList<JLabel> manoPC = new ArrayList<>();
    private final ArrayList<JLabel> manoUsuario = new ArrayList<>();
    private final JLabel titulo = new JLabel("ESTADISTICAS");
    private final JLabel estadisticas = new JLabel();


    private final ArrayList<JLabel> bazaPC = new ArrayList<>();
    private final ArrayList<JLabel> bazaUsuario = new ArrayList<>();
    ImageIcon img = new ImageIcon("dorsal.jpg");
    JButton boton,boton2;

    public VistaPartida(ModeloPartida modelo) {
        this.modelo = modelo;


        setSize(730, 600);
        setLocationRelativeTo(null);
        setTitle("La Mosca");
        panelPartida();
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        modelo.agregarObserver(this);
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
        panelBazas(panel);

    }

    /**
     * Genera las manos para los dos usuarios.
     */
    private void crearCartas(){

        int x1=10, x2=100;

        for(int i=0; i<5; i++){

            JLabel carta = new JLabel();
            JLabel carta2 = new JLabel();
            carta2.setName(Integer.toString(i));
            carta.setOpaque(true);
            carta.setBounds(x1,15,70,100);
            //llamada al metodo con valor de cartaPC
            manoPC.add(i,carta);
            carta2.setOpaque(true);
            carta2.setBounds(x2,400,70,100);
            //llamada al metodo con valor de cartaUsuario
            manoUsuario.add(i,carta2);
            x1=x1+80;
            x2=x2+80;

        }
        crearBazas();
    }

    private void crearBotones(JPanel panelAux){

        boton = new JButton("Repartir cartas");
        boton.setName("Boton1");
        boton.setFont(new Font("arial",Font.BOLD,10));
        boton.setBounds(560,270,70,20);
        panelAux.add(boton);

        boton2 = new JButton("Juega PC");
        boton2.setName("Boton1");
        boton2.setFont(new Font("arial",Font.BOLD,10));
        boton2.setBounds(560,320,70,20);
        panelAux.add(boton2);

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

    private void panelBazas(JPanel panelAux){

        titulo.setBounds(550,15,140,20);
        setLabelPuntos(titulo);

        estadisticas.setBounds(550,35,140,70);
        estadisticas.setVerticalAlignment(SwingConstants.TOP);
        setLabelPuntos(estadisticas);
        estadisticas.setText("<html>Partida no iniciada</html>");

        panelAux.add(titulo);
        panelAux.add(estadisticas);

    }

    private void crearBazas(){
        //for para ir agregando las bazas en otro metodo
        JLabel cartaBazaPC = new JLabel(img);
        cartaBazaPC.setOpaque(true);
        cartaBazaPC.setBounds(420,15,70,100);
        bazaPC.add(cartaBazaPC);
        JLabel cartaBazaUsuario = new JLabel(img);
        cartaBazaUsuario.setOpaque(true);
        cartaBazaUsuario.setBounds(10,400,70,100);
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
        labelBazaUsuario.setBounds(10,400,70,10);
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

    void addJuegaPCListener(ActionListener listener) {
        boton2.addActionListener(listener);
    }

    void addDescartarListener(MouseListener listener) {
        for (JLabel label: manoUsuario) {
        label.addMouseListener(listener);
        }
    }

    void setCartasManos(ArrayList<String> arrayCartaPc, ArrayList<String> arrayCartaUsuario) {
        for (JLabel jlabel: manoPC) {
            jlabel.setText(arrayCartaPc.get(manoPC.indexOf(jlabel)));
        }
        for (JLabel jlabel: manoUsuario) {
            jlabel.setText(arrayCartaUsuario.get(manoUsuario.indexOf(jlabel)));
        }
    }

    void setCartasUsuario(ArrayList<String> array) {
        for (JLabel jlabel: manoUsuario) {
            jlabel.setText(array.get(manoUsuario.indexOf(jlabel)));
        }
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

    void displayErrorMessage(String errorMessage){
        JOptionPane.showMessageDialog(this, errorMessage);
    }

    @Override
    public void update() {
        estadisticas.setText(modelo.getEstadisticas());
    }

    @Override
    public void updateMano() {
        for (JLabel jLabel: manoUsuario) {
            jLabel.setText(modelo.getStringMano(Integer.parseInt(jLabel.getName())));
//            jLabel.setText(modelo.getStringMano(manoUsuario.indexOf(jLabel)));
            if(modelo.getStringMano(Integer.parseInt(jLabel.getName())).equals(""))
                jLabel.setBounds(200,290,jLabel.getWidth(),jLabel.getHeight());
              //  jLabel.setBounds(230,130,jLabel.getWidth(),jLabel.getHeight());

        }
    }
}