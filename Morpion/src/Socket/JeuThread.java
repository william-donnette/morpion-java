package Morpion.src.Socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class JeuThread extends Thread{
    final DataInputStream in;
    final DataOutputStream out;
    final Socket socketcli;
    final Joueur joueur;

    public JeuThread(DataInputStream dis, DataOutputStream dos, Socket s, Joueur j) {
        this.in = dis;
        this.out = dos;
        this.socketcli = s;
        this.joueur = j;
    }

    @Override
    public void run() {

        String mess = null;
        while(true){
            try {
                mess = in.readUTF();
                String[] message = mess.split(" ");
               switch(message[0]){
                   case "CONNECT" : 
                   joueur.setName(message[1]);
                    break;
               }


                


                if(mess.equals("stop")) break;
                mess = null;
                if(socketcli.isClosed()) {
                    socketcli.close();
                    System.out.println("Client fermé");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}