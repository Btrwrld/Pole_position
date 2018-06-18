package com.tecidc.Sockets;

public class SharedData {

    static SharedData instance = null;

    /**
     * Variables que se deben actualizar conforme el recorrido del juego
     */
    public String mensaje = "";


    private void Shared_Data (){
    }

    public static SharedData getInstance(){
        if (instance == null){
            instance = new SharedData();
        }
        return instance;
    }

}