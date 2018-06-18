package com.tecidc.Sockets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.lang.reflect.Field;
import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

public class SocketCliente extends Thread {

    static DataOutputStream bufferSalida;
    static DataInputStream bufferEntrada;
    static OutputStreamWriter bufferaux;
    static OutputStreamWriter out;

    private boolean readyFlag = true;
    JSONObject json = new JSONObject();
    Socket socket;

    public void SocketCliente() {
        try {
            this.socket = new Socket("192.168.1.27", 15557);
            System.out.println("conectado");
            bufferEntrada = new DataInputStream(socket.getInputStream());
            bufferSalida = new DataOutputStream(socket.getOutputStream());


        } catch (IOException e) {
            e.printStackTrace();
        }

        (new Thread(new SocketCliente())).start();
    }

    public void run() {
        while (this.readyFlag) {
            try {
                DatoSocket dato = new DatoSocket("");
                dato.readObject(bufferEntrada);
                Input_Brain(dato.toString());
                System.out.println("Mensaje del Servidor: " + dato.toString());

            } catch (IOException e) {
                this.readyFlag = false;
                e.printStackTrace();
            }
        }
    }

    public void send(String mensaje) throws IOException {
        DatoSocket msj = new DatoSocket(mensaje);
        msj.writeObject(bufferSalida);
        System.out.println("Mensaje Enviado:" + mensaje);

    }

    public void send_json(Object obj1, Object obj2, Object obj3, Object obj4) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString1 = mapper.writeValueAsString(obj1);
        String jsonInString2 = mapper.writeValueAsString(obj2);
        String jsonInString3 = mapper.writeValueAsString(obj3);
        String jsonInString4 = mapper.writeValueAsString(obj4);

        DatoSocket msj = new DatoSocket(jsonInString1 + jsonInString2 + jsonInString3 + jsonInString4);
        msj.writeObject(bufferSalida);
        System.out.println("Json:" + msj);

    }


    void Input_Brain(String data_server) {
        String[] parts = data_server.split("-- ");
        String data = parts[1];

        if (data.equals("Hola soy el server")) {
            SharedData.getInstance().mensaje = data;
            System.out.println("Shared data: " + SharedData.getInstance().mensaje);
        }
    }
}

