package com.view;

import com.model.Carta;
import com.model.ModeloPartida;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public class VistaConsola extends JFrame implements Observer {
    private ModeloPartida modelo;

    private JTextArea chat;
    private JTextField entrada;
    private JButton botonEnviar;
    private JPanel contenedorEntrada;
    private JPanel contenedorChat;
    private JScrollPane scroll; //Se genera una barra de scroll cuando supera el tamaño de la ventana

    public VistaConsola(ModeloPartida modelo) {
        this.modelo = modelo;
        this.modelo.agregarObserver(this);

        setTitle("La Mosca - Consola");
        this.setSize(550, 596);
        panelChat();
        panelEntrada();

        this.setLayout(new BorderLayout());
        this.add(contenedorChat,BorderLayout.NORTH);
        this.add(contenedorEntrada,BorderLayout.SOUTH);
        this.setResizable(false); //No se puede cambiar el tamaño de la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void panelChat(){
        chat = new JTextArea(33,12);
        Color color = new Color(200, 200, 200);
        chat.setBackground(color);
        scroll = new JScrollPane(chat);
        contenedorChat = new JPanel();
        contenedorChat.setLayout(new GridLayout(1,1));
        contenedorChat.add(scroll);
        String str = "¡Bienvenido a la Consola de la Mosca!\n" +
                     "Para ver los comandos de la consola ingrese: ayuda";
        mostrarMsj(str);
    }

    private void panelEntrada(){
        entrada = new JTextField(12);
        botonEnviar = new JButton("Enviar");
        contenedorEntrada = new JPanel();
        contenedorEntrada.setLayout(new GridLayout(1,2)); //Ponemos 2 columnas ya que va a contener la entrada y el boton
        contenedorEntrada.add(entrada);
        contenedorEntrada.add(botonEnviar);
    }

    public void mostrarMsj(String msj) {
        chat.append(">> " + msj + "\n\n");
    }

    public String getEntrada() {
        String str = entrada.getText().toLowerCase().replaceAll(" ","");
        entrada.setText(""); //Para limpiar el campo de entrada una vez que se leyo lo que se escribio
        return str;
    }

    public void addEntradaListener(ActionListener listener){
        botonEnviar.addActionListener(listener);
    }

    public void addTecladoListener(KeyListener listener){
        entrada.addKeyListener(listener);
    }

    public void displayErrorMessage() {
        String str = "El comando ingresado no fue reconocido, ingrese un comando permitido.\nPara más información ingrese: ayuda";
        mostrarMsj(str);
    }

    @Override
    public void update() {
        mostrarMsj("|| Estadisticas ||\n" + modelo.getEstadisticas());
    }

    @Override
    public void updateMano() {
        String cartas="|| Cartas de tu Mano ||";
        int index =0;

        //Es get 0 ya que el primero que se agrega en el modelo es el usuario humano
        for (Carta carta: modelo.getUsuarios().get(0).getMyMano()) {
            index = modelo.getUsuarios().get(0).getMyMano().indexOf(carta) + 1;
            cartas = cartas + "\n- Carta N° " + index +": " + carta.toString();
        }
        mostrarMsj(cartas);
    }

    @Override
    public void updateMesa() {
        mostrarMsj(modelo.getMesaCartas().toString());
    }

    @Override
    public void updateManoPC() {}
}