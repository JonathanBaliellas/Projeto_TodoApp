package util;

import controller.ProjetoController;
import model.Projetos;
import java.sql.Connection;
import java.util.List;

public class Testes {
    public void conexao(){
        //Testando conexão
        Connection conexao = ConexaoBD.conectar();
        ConexaoBD.desconectar(conexao);
    }

    public void inserirProjeto(){
        //Inserindo novo projeto
        ProjetoController projetoController = new ProjetoController();
        Projetos projeto = new Projetos();
        projeto.setNome("Teste IntelliJ 2");
        projeto.setDescricao("Teste usando IntelliJ IDEA, novamente");
        projetoController.incluir(projeto);
    }

    public void excluirProjeto(){
        //Excluindo projeto
        ProjetoController projetoController = new ProjetoController();
        projetoController.excluir(9);
    }

    public void atualizarProjeto(){
        //Atualizando o projeto
        ProjetoController projetoController = new ProjetoController();
        Projetos projeto = new Projetos();
        projeto.setNome("Teste IntelliJ 4 - novo nome");
        projeto.setDescricao("Teste de atualização do projeto");
        projeto.setId(3);
        projetoController.atualizar(projeto);
    }

    public void listarProjetos(){
        //Listando o projeto
        ProjetoController projetoController = new ProjetoController();
        List<Projetos> listaProjetos = projetoController.consultar();
        System.out.println("Número de projetos: " + listaProjetos.size());
    }
}
