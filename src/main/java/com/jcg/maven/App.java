package com.jcg.maven;
//import com.sun.xml.internal.ws.wsdl.writer.document.Part;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {

        VistaPartida vista = new VistaPartida();
        ModeloPartida modelo = new ModeloPartida();
        ControllerPartida controlador = new ControllerPartida(vista, modelo);

        vista.setVisible(true);





    }
}
