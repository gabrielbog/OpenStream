package com.gabrielbog.openstream.threads;

import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientThread extends Thread {
    private String ipAddress;
    private int port;
    private String sendMsg;
    private String serverMsg;

    public ClientThread(String ipAddress, int port, String sendMsg) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.sendMsg = sendMsg;
        serverMsg = "";
    }

    @Override
    public void run() {
        try {
            Socket clientSocket = new Socket(ipAddress, port);

            String[] msgSplit = sendMsg.split("-");
            if(msgSplit[0].equals(Constants.sLoginReq) || msgSplit[0].equals(Constants.sRegisterReq)){

                PrintWriter printWriter = Helper.getWriter(clientSocket);
                printWriter.println(sendMsg);
                Log.e(Constants.TAG,"before readline");

                BufferedReader bufferedReader = Helper.getReader(clientSocket);
                final String result = bufferedReader.readLine();
                Log.e(Constants.TAG, result);

            }

            else{

                PrintWriter printWriter = Helper.getWriter(clientSocket);
                printWriter.println(sendMsg);
                Log.e(Constants.TAG,"before readline");

                BufferedReader bufferedReader = Helper.getReader(clientSocket);
                final String result = bufferedReader.readLine();
                Log.e(Constants.TAG, result);

            }

        } catch (UnknownHostException unknownHostException) {
            Log.e(Constants.TAG, unknownHostException.getMessage());
            serverMsg = Constants.sError;
            if (Constants.DEBUG) {
                unknownHostException.printStackTrace();
            }

        } catch (IOException ioException) {
            Log.e(Constants.TAG, ioException.getMessage());
            serverMsg = Constants.sError;
            if (Constants.DEBUG) {
                ioException.printStackTrace();
            }
        }
    }

    public String getServerMsg() {
        return serverMsg;
    }
}
