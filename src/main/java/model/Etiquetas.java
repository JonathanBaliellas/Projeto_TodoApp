/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author jbali
 */
public class Etiquetas {
    //ATRIBUTOS
    int id;
    String nome;

    public Etiquetas(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    public Etiquetas(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
