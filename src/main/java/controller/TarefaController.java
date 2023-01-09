package controller;

import model.Tarefas;
import util.ConexaoBD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TarefaController {
    public void incluir(Tarefas tarefa){
        //Comando SQL
        String sql = "INSERT INTO tarefas " +
                "(nome, descricao, concluido, observacoes, prazo, dataCriacao, dataAtualizacao, proj_id, etiquetas_id)" +
                "VALUES" +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?);";

        Connection conexao = null;//Conexão com o BD
        PreparedStatement declaracao = null;//Declaração SQL

        try{
            conexao = ConexaoBD.conectar();//Conecta com o BD
            declaracao = conexao.prepareStatement(sql);//Prepara a declaração SQL

            //Prepara os valores para envio ao BD
            declaracao.setString(1, tarefa.getNome());
            declaracao.setString(2, tarefa.getDescricao());
            declaracao.setBoolean(3, tarefa.getConcluido());
            declaracao.setString(4, tarefa.getObservacoes());
            declaracao.setDate(5, new Date(tarefa.getPrazo().getTime()));
            declaracao.setDate(6, new Date(tarefa.getDataCriacao().getTime()));
            declaracao.setDate(7, new Date(tarefa.getDataAtualizacao().getTime()));
            declaracao.setInt(8, tarefa.getProj_id());
            declaracao.setInt(9, tarefa.getEtiquetas_id());

            //Envia dados ao BD
            declaracao.execute();

            //Confirmação
            System.out.println("Tarefa salva com sucesso!");
        }catch (Exception e){
            throw new RuntimeException("Erro ao salvar a tarefa.\n" + e);
        }finally {
            ConexaoBD.desconectar(conexao, declaracao);
        }
    }

    public void excluir(int id){
        String sql = "DELETE FROM tarefas WHERE id = ?;";

        Connection conexao = null;//Conexão com o BD
        PreparedStatement declaracao = null;//Declaração SQL

        try{
            conexao = ConexaoBD.conectar();//Conecta com o BD
            declaracao = conexao.prepareStatement(sql);//Prepara declaração SQL
            declaracao.setInt(1, id);//Prepara os dados para envio
            declaracao.execute();//Executa o comando SQL
            System.out.println("Tarefa excluída com sucesso");//Confirmação
        }catch (Exception e){
            throw new RuntimeException("Erro ao excluir a tarefa.\n" + e.getMessage(), e);
        }finally {
            ConexaoBD.desconectar(conexao, declaracao);
        }
    }

    public void atualizar(Tarefas tarefa){
        String sql = "UPDATE tarefas SET " +
                "nome = ?, descricao = ?, concluido = ?, observacoes = ?, prazo = ?, dataAtualizacao = ?, etiquetas_id = ? " +
                "WHERE id = ?;";

        Connection conexao = null;//Conexão com o BD
        PreparedStatement declaracao = null;//Declaração SQL

        try{
            conexao = ConexaoBD.conectar();//Conecta com o BD
            declaracao = conexao.prepareStatement(sql);//Prepara a declara��o SQL

            //Atribuir valores � declara��o SQL
            declaracao.setString(1, tarefa.getNome());
            declaracao.setString(2, tarefa.getDescricao());
            declaracao.setBoolean(3, tarefa.getConcluido());
            declaracao.setString(4, tarefa.getObservacoes());
            declaracao.setDate(5, new Date(tarefa.getPrazo().getTime()));
            declaracao.setDate(6, new Date(tarefa.getDataAtualizacao().getTime()));
            declaracao.setInt(7, tarefa.getEtiquetas_id());
            declaracao.setInt(8, tarefa.getId());
            

            //Executa comando SQL
            declaracao.execute();

            //Confimação
            System.out.println("Tarefa atualizada com sucesso!");
        }catch (Exception e){
            throw new RuntimeException("Erro ao atualizar a tarefa\n" + e.getMessage(), e);
        }finally {
            ConexaoBD.desconectar(conexao, declaracao);
        }
    }

    public List<Tarefas> consultar(int proj_id){
        String sql = "SELECT * FROM tarefas WHERE proj_id = ?;";
        List<Tarefas> listaTarefas = new ArrayList<Tarefas>();//Cria uma lista de tarefas

        Connection conexao = null;//Conexão com o BD
        PreparedStatement declaracao = null;//Declaração SQL
        ResultSet consulta = null;//Resultado da consulta SQL

        try{
            conexao = ConexaoBD.conectar();//Conecta com o BD
            declaracao = conexao.prepareStatement(sql);//Prepara a declaração SQL
            declaracao.setInt(1, proj_id);//Insere o id do Projeto na declaração
            consulta = declaracao.executeQuery();//Executa a consulta SQL

            while (consulta.next()){//Enquanto houver tarefa no projeto...
                Tarefas tarefa = new Tarefas();//Cria um objeto tarefa

                //Insere o resultado da consulta no objeto
                tarefa.setId(consulta.getInt("id"));
                tarefa.setNome(consulta.getString("nome"));
                tarefa.setDescricao(consulta.getString("descricao"));
                tarefa.setConcluido(consulta.getBoolean("concluido"));
                tarefa.setObservacoes(consulta.getString("observacoes"));
                tarefa.setPrazo(consulta.getDate("prazo"));
                tarefa.setDataCriacao(consulta.getDate("dataCriacao"));
                tarefa.setDataAtualizacao(consulta.getDate("dataAtualizacao"));
                tarefa.setProj_id(consulta.getInt("proj_id"));
                tarefa.setEtiquetas_id(consulta.getInt("etiquetas_id"));

                //Insere a tarefa na lista de tarefas
                listaTarefas.add(tarefa);
            }

            //Confirma��o
            System.out.println("Consulta realizada com sucesso!");
        }catch (Exception e){
            throw new RuntimeException("Erro ao consultar o banco de dados.\n" + e.getMessage(), e);
        }finally {
            ConexaoBD.desconectar(conexao, declaracao, consulta);
        }
        return listaTarefas;
    }
}
