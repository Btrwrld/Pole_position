package com.tecidc.Sockets;

import java.net.*;
import java.io.*;


public class SocketCliente extends Thread {

    /**
     * Se declaran los Output e Input Stream para lectura y escritura de datos via Socket
     */
    static DataOutputStream bufferSalida;
    static DataInputStream bufferEntrada;


    private boolean readyFlag = true;
    Socket socket;


    /**
     * Se crea el socket y se declara la lectura y escritura
     */
    public void SocketCliente() {
        try {
            this.socket = new Socket("192.168.43.185", 15557);
            System.out.println("conectado");
            bufferEntrada = new DataInputStream(socket.getInputStream());
            bufferSalida = new DataOutputStream(socket.getOutputStream());


        } catch (IOException e) {
            e.printStackTrace();
        }

        (new Thread(new SocketCliente())).start();
    }
    /**
     * Se crea en un Thread un listener, para que el Socket siempre este esuchando al server
     */
    public void run() {
        while (this.readyFlag) {
            try {
                DatoSocket dato = new DatoSocket("");
                dato.readObject(bufferEntrada);
                Input_Brain(dato.toString());
                //System.out.println("Mensaje del Servidor: " + dato.toString());

            } catch (IOException e) {
                this.readyFlag = false;
                e.printStackTrace();
            }
        }
    }


    /**
     *Funcion para escribir datos y enviarlos al servidor
     */
    public void send(String mensaje) throws IOException {
        DatoSocket msj = new DatoSocket(mensaje);
        msj.writeObject(bufferSalida);
        //System.out.println("Mensaje Enviado:" + mensaje);

    }

    /**
     * Metodo para analizar los datos que el cliente recibe del sevidor
     */
    void Input_Brain(String data_server) {
        String[] parts = data_server.split("-- ");
        String data = parts[1];

        // BRAIN PARA LOS HUECOS

        if (data.equals("j1hueco")) {
            SharedData.getInstance().mensaje = data;
        }
        else if (data.equals("j2hueco")) {
            SharedData.getInstance().mensaje = data;
        }

        else if (data.equals("j3hueco")) {
            SharedData.getInstance().mensaje = data;
        }
        else if (data.equals("j4hueco")) {
            SharedData.getInstance().mensaje = data;
        }

        //BRAIN PARA LOS TURBOS

        else if (data.equals("j1trubo")) {
            SharedData.getInstance().mensaje = data;
        }

        else if (data.equals("j2turbo")) {
            SharedData.getInstance().mensaje = data;
        }

        else if (data.equals("j3turbo")) {
            SharedData.getInstance().mensaje = data;
     }
        else if (data.equals("j4turbo")) {
            SharedData.getInstance().mensaje = data;
        }

        //BRAIN PARA LAS VIDAS
        else if (data.equals("j1vida")) {
            SharedData.getInstance().mensaje = data;
        }

        else if (data.equals("j2vida")) {
            SharedData.getInstance().mensaje = data;
        }

        else if (data.equals("j3vida")) {
            SharedData.getInstance().mensaje = data;
        }
        else if (data.equals("j4vida")) {
            SharedData.getInstance().mensaje = data;
        }



}
}

