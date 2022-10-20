package Socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Serveur {

    // Fonction de lancement du serveur
    public static void main(String[] args) throws IOException {
        
        ServerSocket sockserv = null;
        DataInputStream in;
        DataOutputStream out;

        sockserv = new ServerSocket(1234);
        Thread thread;
        int id = 0;
        ArrayList<Joueur> joueurs = new ArrayList<>();

        try {
            
            System.out.println("Serveur sur écoute");

            while (true) {
                try {
                    Socket sockcli = sockserv.accept();

                    System.out.println("New Connection : " + id);

                    in = new DataInputStream(sockcli.getInputStream());
                    out = new DataOutputStream(sockcli.getOutputStream());
                    Joueur j = new Joueur(id);
                    thread = new JeuThread(in, out, sockcli, j);
                    thread.start();
                    joueurs.add(j);
                    id++;
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        } finally {
            try {
                sockserv.close();
                System.out.println("Serveur fermé");
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }
}