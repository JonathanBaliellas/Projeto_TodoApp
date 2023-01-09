/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Etiquetas;
import util.ConexaoBD;

/**
 *
 * @author jbali
 */
public class EtiquetaController {
    public void incluir(String nomeEtiqueta){
        String sql = "INSERT INTO etiquetas (nome) VALUES (?);";//Comando SQL
        Connection conexao = null;//Conexao com BD
        PreparedStatement declaracao = null;//Declara��o SQL
        
        try{
            conexao = ConexaoBD.conectar();//Conecta com o BD
            declaracao = conexao.prepareStatement(sql);//Prepara a declara��o SQL
            
            //Cria o objeto Etiqueta e atribui valor
            declaracao.setString(1, nomeEtiqueta);
            
            //Envia o comando SQL
            declaracao.execute();
            
            //Confirma��o
            System.out.println("Etiqueta salva com sucesso!");
        }catch(Exception e){
            throw new RuntimeException("Erro ao salvar a etiqueta\n" + e.getMessage(),e);
        }finally{
            ConexaoBD.desconectar(conexao,declaracao);
        }
    }
    
    public void excluir(Etiquetas etiqueta){
        String sql = "DELETE FROM etiquetas WHERE id = ?";//Comando SQL
        Connection conexao = null;//Conex�o com o BD
        PreparedStatement declaracao = null;//Declara��o SQL
        
        try{
            conexao = ConexaoBD.conectar();//Conecta com o BD
            declaracao = conexao.prepareStatement(sql);//Prepara a declara��o SQL
            
            declaracao.setInt(0, etiqueta.getId());//Inclui par�metros
            declaracao.execute();//Executa o comando
            System.out.println("Etiqueta exclu�da com sucesso!");//Confirma��o
        }catch(Exception e){
            throw new RuntimeException("N�o foi poss�vel excluir a etiqueta\n" + e.getMessage(),e);
        }finally{
            ConexaoBD.desconectar(conexao, declaracao);
        }
    }
    
    public void atualizar(Etiquetas etiqueta){
        String sql = "UPDATE etiquetas SET nome = ? WHERE id = ?;";
        Connection conexao = null;
        PreparedStatement declaracao = null;
        
        try{
            conexao = ConexaoBD.conectar();
            declaracao = conexao.prepareStatement(sql);
            
            declaracao.setString(0, etiqueta.getNome());
            declaracao.setInt(1, etiqueta.getId());
            declaracao.execute();
            System.out.println("Etiqueta atualizada com sucesso!");
        }catch(Exception e){
            throw new RuntimeException("N�o foi poss�vel atualizar a etiqueta\n" + e.getMessage(),e);
        }finally{
            ConexaoBD.desconectar(conexao, declaracao);
        }
    }
    
    public List<Etiquetas> consultar(){
        String sql = "SELECT * FROM etiquetas;";//Comando SQL
        List<Etiquetas> listaEtiquetas = new ArrayList<Etiquetas>();
        
        Connection conexao = null;//Conex�o com BD
        PreparedStatement declaracao = null;//Declara��o SQL
        ResultSet consulta = null;//Resultado da consulta
        
        try{
            conexao = ConexaoBD.conectar();//Conecta com o BD
            declaracao = conexao.prepareStatement(sql);//Prepara a declaracao SQL
            consulta = declaracao.executeQuery();
            
            while(consulta.next()){
                Etiquetas etiqueta = new Etiquetas();//Cria o objeto Etiquetas
                
                //Inclui os valores da consulta ao objeto
                etiqueta.setId(consulta.getInt("id"));
                etiqueta.setNome(consulta.getString("nome"));
                
                listaEtiquetas.add(etiqueta);//Adiciona o objeto � lista
            }
        }catch(Exception e){
            throw new RuntimeException("N�o foi poss�vel consultar o Banco de Dados\n" + e.getMessage(),e);
        }finally{
            ConexaoBD.desconectar(conexao, declaracao, consulta);
        }
        return listaEtiquetas;
    }
}
