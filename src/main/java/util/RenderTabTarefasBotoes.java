/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author jbali
 */
public class RenderTabTarefasBotoes extends DefaultTableCellRenderer{
    //ATRIBUTOS
    String tipoBotao;
    
    //CONSTRUTOR
    public RenderTabTarefasBotoes(String tipoBotao) {
        this.tipoBotao = tipoBotao;
    }
    
    //MÉTODOS

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, 
            boolean isSelected, boolean hasFocus, int row, int column) {
        
        //Cria o objeto JLabel para que possa ser manipulado
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, 
                value, isSelected, hasFocus, row, column);
        
        label.setHorizontalAlignment(CENTER);//Centraliza o elemento
        
        //Inclui o ícone com base no tipo de botão (editar/exluir)
        label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/" + tipoBotao + ".png")));
        
        return label;//Renderiza o rótulo
    }
    
    
    //MÉTODOS ACESSORES
    public String getTipoBotao() {
        return tipoBotao;
    }

    public void setTipoBotao(String tipoBotao) {
        this.tipoBotao = tipoBotao;
    }
}
