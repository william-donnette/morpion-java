package Morpion.src.Socket;

public class Joueur{
    private int id;
    private String name;


    public Joueur(int id, String name){
        this.id = id;
        this.name = name;
    }
    public Joueur(int id){
        this(id, "");
    }

    public Joueur(String name){
        this(-1, name);
    }

    public void setName(String name){
        this.name = name;
    }
}