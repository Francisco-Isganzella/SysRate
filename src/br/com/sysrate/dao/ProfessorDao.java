/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysrate.dao;

import br.com.sysrate.entidade.Professor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author migue
 */
public class ProfessorDao {
    protected Connection conexao;
    protected PreparedStatement preparando;
    protected ResultSet resultSet;
    
    public void salvar(Professor professor) throws SQLException{
        String sql = "INSERT INTO Professor (nomeProfessor, visivel) VALUES (?,?)";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparando.setString(1, professor.getNomeProfessor());
            preparando.setBoolean(2, professor.getVisivel());
            preparando.executeUpdate();
            //resultSet.next();
            //professor.setProfessorID(resultSet.getInt(1));
            
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao salvar o professor:" + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando);
        }
    }
    
    public Professor pesquisarPorNome(String nome) throws SQLException{
        Professor professor = null;
        //String consulta = "SELECT * FROM Professor p WHERE p.nomeProfessor like ?";
        String consulta = "SELECT * FROM Professor p "
                + "WHERE p.nomeProfessor = ?";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            //preparando.setString(1, "%" + nome + "%");
            preparando.setString(1, nome);
            resultSet = preparando.executeQuery();
            
            if (resultSet.next()) {
                professor = new Professor();
                professor.setProfessorID(resultSet.getInt("professorID"));
                professor.setVisivel(resultSet.getBoolean("visivel"));
                professor.setNomeProfessor(resultSet.getString("nomeProfessor"));
            }
            
            //System.out.println(professor.getNomeProfessor());
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar professor por nome:"+e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return professor;
    }
    
    public void alterar(Professor professor) throws SQLException {
        String sql = "UPDATE Professor SET visivel = ?, nomeProfessor = ? WHERE professorID = ?";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(sql);
            preparando.setBoolean(1, professor.getVisivel());
            preparando.setString(2, professor.getNomeProfessor());
            preparando.setInt(3, professor.getProfessorID());
            preparando.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir professor\n" + e.getMessage());
        } 
        finally {
            FabricaConexao.fecharConexao(conexao, preparando);
        }
    }
    
}
