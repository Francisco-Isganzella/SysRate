/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysrate.dao;

import br.com.sysrate.entidade.Notas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author migue
 */
public class NotasDao {
    
    protected Connection conexao;
    protected PreparedStatement preparando;
    protected ResultSet resultSet;
    
    public Notas buscarNotas (int professorID) throws SQLException{
        Notas notas = null;
        String consulta = "SELECT * FROM Notas n WHERE n.professorID = ?";
        
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setInt(1, professorID);
            resultSet = preparando.executeQuery();
            
            if (resultSet.next()) {
                notas = new Notas();
                notas.setDidatica(resultSet.getInt("didatica"));
                notas.setQualidadeMaterial(resultSet.getInt("qualidadeMaterial"));
                notas.setQualidadeCorrecao(resultSet.getInt("qualidadeCorrecao"));
                notas.setReceptividade(resultSet.getInt("receptividade"));
                notas.setRespeito(resultSet.getInt("respeito"));
            }
                        
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar professor por nome:"+e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return notas;
    }
    
    public Notas buscarMediaDidatica (int professorID) throws SQLException{
        Notas notas = null;
        String consulta = "SELECT * FROM Notas n WHERE n.professorID = ?";
        
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setInt(1, professorID);
            resultSet = preparando.executeQuery();
            
            if (resultSet.next()) {
                notas = new Notas();
                notas.setDidatica(resultSet.getInt("didatica"));
            }
                        
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar professor por nome:"+e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return notas;
    }
    
}
