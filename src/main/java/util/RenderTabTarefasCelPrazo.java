/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.awt.Color;
import java.awt.Component;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import model.Tarefas;

/**
 * Personaliza as configurações de renderização da coluna Prazo da tabela de 
 * tarefas
 * 
 */
public class RenderTabTarefasCelPrazo extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, 
            boolean isSelected, boolean hasFocus, int row, int column) {
        
        //Cria um objeto JLabel para ser manipulado
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, 
                value, isSelected, hasFocus, row, column);
        
        //Centraliza a coluna Prazo
        label.setHorizontalAlignment(CENTER);
        
        //Recebe o modelo que será usado na tabela, contendo todas as tarefas
        TabTarefasModel modeloTarefa = (TabTarefasModel) table.getModel();
        
        //Recebe a tarefa da linha que está sendo renderizada
        Tarefas tarefa = modeloTarefa.getListaTarefas().get(row);
        
        //Altera a cor caso a tarefa esteja em atraso
        if(!tarefa.getPrazo().after(new Date()) && !tarefa.getConcluido()){
            label.setBackground(new Color(222,60,75));//Rusty Red
            label.setForeground(Color.white);
        } else {
            label.setBackground(Color.white);
            label.setForeground(new Color(0,153,102));//Green Cyan
        }
        
        //Retorna o rótulo para renderização
        return label;
    }
    
}
