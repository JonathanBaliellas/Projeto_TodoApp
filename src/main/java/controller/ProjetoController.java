package controller;

import model.Projetos;
import util.ConexaoBD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProjetoController {
    public void incluir(Projetos projeto, int idUsuario){
        String sql = "INSERT INTO projetos (nome, descricao, dataCriacao, dataAtualizacao, usuarios_id)" +
                "VALUES (?, ?, ?, ?, ?);";

        Connection conexao = null;//Conexão com BD
        PreparedStatement declaracao = null;//Declaração SQL

        try{
            conexao = ConexaoBD.conectar();//Conecta com o BD
            declaracao = conexao.prepareStatement(sql);//Prepara a declaração SQL

            //Prepara os valores dos campos
            declaracao.setString(1, projeto.getNome());
            declaracao.setString(2, projeto.getDescricao());
            declaracao.setDate(3, new Date(projeto.getDataCriacao().getTime()));
            declaracao.setDate(4, new Date(projeto.getDataAtualizacao().getTime()));
            declaracao.setInt(5, idUsuario);

            //Envia os dados ao BD
            declaracao.execute();

            //Confirmação
            System.out.println("Projeto inserido com sucesso!");
        }catch (Exception e){
            throw new RuntimeException("Erro ao inserir projeto. ", e);
        }finally {
            ConexaoBD.desconectar(conexao, declaracao);
        }
    }

    public void excluir(int id){
        String sql = "DELETE FROM projetos WHERE id = ?;";

        Connection conexao = null;//Conexão com BD
        PreparedStatement declaracao = null;//Declaração SQL

        try{
            conexao = ConexaoBD.conectar();//Conecta com o BD
            declaracao = conexao.prepareStatement(sql);//Prepara a declaração SQL
            declaracao.setInt(1, id);//Insere o id do projeto na declaração
            declaracao.execute();//Envia o comando SQL
            System.out.println("Projeto excluído com sucesso!");//Confirmação
        }catch (Exception e){
            throw new RuntimeException("Erro ao excluir o projeto. ", e);
        }finally {
            ConexaoBD.desconectar(conexao, declaracao);
        }
    }

    public void atualizar(Projetos projeto){
        String sql = "UPDATE projetos SET nome = ?, descricao = ?, dataAtualizacao = ? WHERE id = ?;";

        Connection conexao = null;//Conexão com o BD
        PreparedStatement declaracao = null;//Declaração SQL

        try{
            conexao = ConexaoBD.conectar();//Conecta com o BD
            declaracao = conexao.prepareStatement(sql);//Prepara a declaração SQL

            //Atribui valores a atualizar
            declaracao.setString(1, projeto.getNome());
            declaracao.setString(2, projeto.getDescricao());
            declaracao.setDate(3, new Date(projeto.getDataAtualizacao().getTime()));
            declaracao.setInt(4, projeto.getId());

            //Executa o comando SQL
            declaracao.execute();

            //Confirmação
            System.out.println("Projeto atualizado com sucesso!");
        }catch (Exception e){
            throw new RuntimeException("Erro ao atualizar o projeto. ", e);
        }finally {
            ConexaoBD.desconectar(conexao, declaracao);
        }
    }

    public List<Projetos> consultar(int idUsuario){
        String sql = "SELECT * FROM projetos WHERE usuarios_id = ?;";
        List<Projetos> listaProjetos = new ArrayList<Projetos>();//Lista de Projetos

        Connection conexao = null;//Conex�o com BD
        PreparedStatement declaracao = null;//Declara��o SQL
        ResultSet consulta = null;//Consulta SQL

        try{
            conexao = ConexaoBD.conectar();//Conecta com o BD
            declaracao = conexao.prepareStatement(sql);//Prepara a declara��o SQL
            declaracao.setInt(1, idUsuario);
            consulta = declaracao.executeQuery();//Executa a consulta e armazena na variável

            while (consulta.next()){
                //Cria objeto Projeto com os valores retornados pela query
                Projetos projeto = new Projetos();
                projeto.setId(consulta.getInt("id"));
                projeto.setNome(consulta.getString("nome"));
                projeto.setDescricao(consulta.getString("descricao"));
                projeto.setDataCriacao(consulta.getDate("dataCriacao"));
                projeto.setDataAtualizacao(consulta.getDate("dataAtualizacao"));

                //Insere o objeto na lista
                listaProjetos.add(projeto);
            }

            //Confirmação
            System.out.println("Consulta realizada com sucesso!");
        }catch (Exception e){
            throw new RuntimeException("Erro ao consultar base de dados. ", e);
        }finally {
            ConexaoBD.desconectar(conexao, declaracao, consulta);
        }
        return listaProjetos;
    }
}
