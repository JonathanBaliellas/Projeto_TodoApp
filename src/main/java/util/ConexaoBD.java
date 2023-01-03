package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConexaoBD {
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/capgemini_todoapp";
    public static final String USER = "root";
    public static final String PASS = "";

    public static Connection conectar(){
        try{
            Class.forName(DRIVER);
            System.out.println("Conexão com BD estabelecida");
            return DriverManager.getConnection(URL, USER, PASS);
        }catch (Exception e){
            throw new RuntimeException("Erro na conexão com o banco de dados. ", e);
        }
    }

    public static void desconectar(Connection conexao){
        try{
            if(conexao != null) conexao.close();
            System.out.println("Conexão com BD encerrada");
        }catch (Exception e){
            throw new RuntimeException("Erro ao desconectar o banco de dados. ", e);
        }
    }

    public static void desconectar(Connection conexao, PreparedStatement declaracao){
        try{
            if(conexao != null) conexao.close();
            if(declaracao != null) declaracao.close();
            System.out.println("Conexão com BD encerrada");
        }catch (Exception e){
            throw new RuntimeException("Erro ao desconectar o banco de dados. ", e);
        }
    }

    public static void desconectar(Connection conexao, PreparedStatement declaracao, ResultSet consulta){
        try{
            if(conexao != null) conexao.close();
            if(declaracao != null) declaracao.close();
            if(consulta != null) consulta.close();
            System.out.println("Conexão com BD encerrada");
        }catch (Exception e){
            throw new RuntimeException("Erro ao desconectar o banco de dados. ", e);
        }
    }
}
