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
    
    public String buscarMediaDidatica (int professorID) throws SQLException{
        Notas notas = null;
        String mediaDidatica = "";
        String consulta = "SELECT AVG(didatica) FROM Nota n" +
                          " INNER JOIN Professor p ON n.professorID = p.professorID" +
                          " WHERE p.professorID = ?" +
                          " GROUP BY p.nomeProfessor";
        
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setInt(1, professorID);
            resultSet = preparando.executeQuery();
            if (resultSet.next()) {
                mediaDidatica = resultSet.getString(1);
            }
                        
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar professor por nome:"+e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return mediaDidatica;
    }
    public String buscarMediaQualidadeMaterial (int professorID) throws SQLException{
        Notas notas = null;
        String mediaQualidadeMaterial = "";
        String consulta = "SELECT AVG(qualidadeMaterial) FROM Nota n" +
                          " INNER JOIN Professor p ON n.professorID = p.professorID" +
                          " WHERE p.professorID = ?" +
                          " GROUP BY p.nomeProfessor";
        
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setInt(1, professorID);
            resultSet = preparando.executeQuery();
            if (resultSet.next()) {
                mediaQualidadeMaterial = resultSet.getString(1);
            }
                        
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar professor por nome:"+e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return mediaQualidadeMaterial;
    }
    public String buscarMediaQualidadeCorrecao (int professorID) throws SQLException{
        Notas notas = null;
        String mediaQualidadeCorrecao = "";
        String consulta = "SELECT AVG(qualidadeCorrecao) FROM Nota n" +
                          " INNER JOIN Professor p ON n.professorID = p.professorID" +
                          " WHERE p.professorID = ?" +
                          " GROUP BY p.nomeProfessor";
        
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setInt(1, professorID);
            resultSet = preparando.executeQuery();
            if (resultSet.next()) {
                mediaQualidadeCorrecao = resultSet.getString(1);
            }
                        
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar professor por nome:"+e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return mediaQualidadeCorrecao;
    }
    public String buscarMediaReceptividade (int professorID) throws SQLException{
        Notas notas = null;
        String mediaReceptividade = "";
        String consulta = "SELECT AVG(receptividade) FROM Nota n" +
                          " INNER JOIN Professor p ON n.professorID = p.professorID" +
                          " WHERE p.professorID = ?" +
                          " GROUP BY p.nomeProfessor";
        
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setInt(1, professorID);
            resultSet = preparando.executeQuery();
            if (resultSet.next()) {
                mediaReceptividade = resultSet.getString(1);
            }
                        
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar professor por nome:"+e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return mediaReceptividade;
    }
    public String buscarMediaRespeito (int professorID) throws SQLException{
        Notas notas = null;
        String mediaRespeito = "";
        String consulta = "SELECT AVG(respeito) FROM Nota n" +
                          " INNER JOIN Professor p ON n.professorID = p.professorID" +
                          " WHERE p.professorID = ?" +
                          " GROUP BY p.nomeProfessor";
        
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setInt(1, professorID);
            resultSet = preparando.executeQuery();
            if (resultSet.next()) {
                mediaRespeito = resultSet.getString(1);
            }
                        
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar professor por nome:"+e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return mediaRespeito;
    }
}
