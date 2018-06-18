package com.tecidc.Sockets;

public class SharedData {

    static SharedData instance = null;


    /* DECLARAR LAS VARIABLES A UTILIZAR QUE DEBEN ACTUALIZARSE
    public int puntuacion1 = 0;
    */
    public String mensaje = "";


    private void Shared_Data (){
    }

    static SharedData getInstance(){
        if (instance == null){
            instance = new SharedData();
        }
        return instance;
    }

}