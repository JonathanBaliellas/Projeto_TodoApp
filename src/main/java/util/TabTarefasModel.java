/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Tarefas;

/**
 *
 * @author jbali
 */
public class TabTarefasModel extends AbstractTableModel{
    //Define as colunas da tabela
    String[] colunas = {"Nome","Descrição","Prazo","Concluída", "Editar", "Excluir"};
    
    //Cria uma lista de tarefas
    List<Tarefas> listaTarefas = new ArrayList();

    @Override
    public int getRowCount() {
        return listaTarefas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch(coluna){
            case 0: return listaTarefas.get(linha).getNome();
            case 1: return listaTarefas.get(linha).getDescricao();
            case 2: 
                SimpleDateFormat prazo = new SimpleDateFormat("dd/MM/yyyy");
                return prazo.format(listaTarefas.get(linha).getPrazo());
            case 3: return listaTarefas.get(linha).getConcluido();
            case 4: return "";
            case 5: return "";
            default: return "Dados não encontrados";
        }
    }

    @Override
    public String getColumnName(int coluna) {
        return colunas[coluna];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(listaTarefas.isEmpty()) return Object.class;
        return this.getValueAt(0, columnIndex).getClass();
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        listaTarefas.get(rowIndex).setConcluido((boolean) aValue);
    }

    public String[] getColunas() {
        return colunas;
    }

    public List<Tarefas> getListaTarefas() {
        return listaTarefas;
    }

    public void setListaTarefas(List<Tarefas> listaTarefas) {
        this.listaTarefas = listaTarefas;
    }

    //@Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 3;//Retorna true na coluna 3 (concluído)
    }
    
    
}
