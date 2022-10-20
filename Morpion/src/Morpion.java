import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Morpion {
    public static void main(String[] args) throws Exception {
        // Etablissement de la connexion
        Socket socket;
        DataInputStream in;
        DataOutputStream out;
        socket = new Socket("192.168.121.134", 1234);

        // Choix du nom pour ajout a la liste d'attente
        System.out.println("Choisis un nom :");
        Scanner s = new Scanner(System.in);
        String nom = s.nextLine();

        // Demande d'ajout
        try {
            out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("CONNECT "+nom);
            while(!socket.isClosed()){
                try{
                    out = new DataOutputStream(socket.getOutputStream());
                    in = new DataInputStream(socket.getInputStream());


                    String message = in.readUTF();
                    System.out.println(message);
                    message = null;
                    /*
                    out.writeUTF(scn.nextLine());

                    String received = in.readUTF();
                    System.out.println(received);*/

                }catch(IOException ex) {
                    System.out.println(ex);
                }
            }
            System.out.println("Serveur fermé");
        } finally {
            try {
                socket.close();
                System.out.println("Client fermé");
            } catch (IOException ex) {
                System.out.println("Erreur");
            }

        }

    }
}