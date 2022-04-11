package com.gabrielbog.openstream.threads;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.gabrielbog.openstream.AdapterManager;
import com.gabrielbog.openstream.MusicListArrays;
import com.gabrielbog.openstream.models.MusicModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ClientThread extends Thread {
    private String ipAddress;
    private int port;
    private String sendMsg;

    public ClientThread(String ipAddress, int port, String sendMsg) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.sendMsg = sendMsg;
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

            else if(msgSplit[0].equals(Constants.sFolderReq)){

                PrintWriter printWriter = Helper.getWriter(clientSocket);
                printWriter.println(sendMsg);
                Log.e(Constants.TAG,"before readline");

                AdapterManager adapterInstance = AdapterManager.getInstance();
                MusicListArrays musicListInstance = MusicListArrays.getInstance();

                //empty existent list + favorites list
                musicListInstance.setNormalMusicList(new ArrayList<>());
                adapterInstance.getRecyclerAdapter().notifyAll();
                musicListInstance.setFavoriteMusicList(new ArrayList<>());
                adapterInstance.getRecyclerFavoriteAdapter().notifyAll();

                String result = " ";
                while(true){

                    BufferedReader bufferedReader = Helper.getReader(clientSocket);
                    final String receivedTitle = bufferedReader.readLine();

                    if(receivedTitle.equals("end"))
                        break;

                    final String receivedCount = bufferedReader.readLine();

                    musicListInstance.insertFavoriteMusicElement(new MusicModel(receivedTitle, receivedTitle, receivedCount));
                    adapterInstance.getRecyclerFavoriteAdapter().notifyItemInserted(Integer.parseInt(receivedCount));
                }
                Log.e(Constants.TAG, result);
            }

        } catch (UnknownHostException unknownHostException) {
            Log.e(Constants.TAG, unknownHostException.getMessage());
            if (Constants.DEBUG) {
                unknownHostException.printStackTrace();
            }

        } catch (IOException ioException) {
            Log.e(Constants.TAG, ioException.getMessage());
            if (Constants.DEBUG) {
                ioException.printStackTrace();
            }
        }
    }
}
