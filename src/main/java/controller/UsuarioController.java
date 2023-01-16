package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Usuarios;
import util.ConexaoBD;

public class UsuarioController {
    public void incluir(Usuarios usuario){
        String sql = "INSERT INTO usuarios (usuario, senha) VALUES (?,?);";
        Connection conexao = null;
        PreparedStatement declaracao = null;
        
        try{
            conexao = ConexaoBD.conectar();
            declaracao = conexao.prepareStatement(sql);
            declaracao.setString(1, usuario.getUsuario());
            declaracao.setString(2, usuario.getSenha());
            declaracao.execute();
            
            System.out.println("Usu�rio salvo com sucesso!");
        }catch(Exception e){
            throw new RuntimeException("N�o foi poss�vel incluir o usu�rio\n"
                + e.getMessage(),e);
        }finally{
            ConexaoBD.desconectar(conexao, declaracao);
        }
    }
    
    public void excluir(int id){
        String sql = "DELETE FROM usuarios WHERE id = ?;";
        Connection conexao = null;
        PreparedStatement declaracao = null;
        
        try{
            conexao = ConexaoBD.conectar();
            declaracao = conexao.prepareStatement(sql);
            declaracao.setInt(1, id);
            declaracao.execute();
            
            System.out.println("Usu�rio exclu�do com sucesso!");
        }catch(Exception e){
            throw new RuntimeException("N�o foi poss�vel excluir o usu�rio\n"
                + e.getMessage(),e);
        }finally{
            ConexaoBD.desconectar(conexao, declaracao);
        }
    }
    
    public void atualizar(Usuarios usuario){
        String sql = "UPDATE usuarios SET usuario = ?, senha = ? WHERE id = ?;";
        Connection conexao = null;
        PreparedStatement declaracao = null;
        
        try{
            conexao = ConexaoBD.conectar();
            declaracao = conexao.prepareStatement(sql);
            declaracao.setString(1, usuario.getUsuario());
            declaracao.setString(2, usuario.getSenha());
            declaracao.setInt(3, usuario.getId());
            declaracao.execute();
            
            System.out.println("Usu�rio atualizado com sucesso!");
        }catch(Exception e){
            throw new RuntimeException("N�o foi poss�vel atualizar o usu�rio\n"
                + e.getMessage(),e);
        }finally{
            ConexaoBD.desconectar(conexao, declaracao);
        }
    }
    
    public List<Usuarios> consultar(){
        String sql = "SELECT * FROM usuarios;";
        List<Usuarios> listaUsuarios = new ArrayList();
        Connection conexao = null;
        PreparedStatement declaracao = null;
        ResultSet consulta = null;
        
        try{
            conexao = ConexaoBD.conectar();
            declaracao = conexao.prepareStatement(sql);
            consulta = declaracao.executeQuery();
            
            while(consulta.next()){
                //Cria um novo objeto Usuarios e atribui valor aos atributos
                Usuarios usuario = new Usuarios();
                usuario.setId(consulta.getInt("id"));
                usuario.setUsuario(consulta.getString("usuario"));
                usuario.setSenha(consulta.getString("senha"));
                
                //Insere o usu�rio na lista
                listaUsuarios.add(usuario);
            }
        }catch(Exception e){
            throw new RuntimeException("N�o foi poss�vel consultar a base de dados\n"
                + e.getMessage(),e);
        }finally{
            ConexaoBD.desconectar(conexao, declaracao, consulta);
        }
        return listaUsuarios;
    }
    
}
