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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author migue
 */
public class ProfessorDao {
    protected Connection conexao;
    protected PreparedStatement preparando;
    protected ResultSet resultSet;
    
    public void salvar(Professor professor) throws SQLException{
        String sql = "INSERT INTO Professor (nomeProfessor, visivelProfessor) VALUES (?,?)";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparando.setString(1, professor.getNomeProfessor());
            preparando.setBoolean(2, professor.getVisivelProfessor());
            preparando.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao salvar o professor:" + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando);
        }
    }
    
    public Professor pesquisarPorNome(String nome) throws SQLException{
        Professor professor = null;
        String consulta = "SELECT * FROM Professor p "
                        + "WHERE nomeProfessor = ?";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setString(1, nome);
            resultSet = preparando.executeQuery();
            
            if (resultSet.next()) {
                professor = new Professor();
                professor.setProfessorID(resultSet.getInt("professorID"));
                professor.setVisivelProfessor(resultSet.getBoolean("visivelProfessor"));
                professor.setNomeProfessor(resultSet.getString("nomeProfessor"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar professor por nome:"+e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return professor;
    }
    
    public void alterar(Professor professor) throws SQLException {
        String sql = "UPDATE Professor SET visivelProfessor = ?, nomeProfessor = ? WHERE professorID = ?";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(sql);
            preparando.setBoolean(1, professor.getVisivelProfessor());
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
    
    public List<Professor> listarProfessor() throws SQLException{
        List<Professor> listaProfessor = new ArrayList<Professor>();
        String consulta = "SELECT * FROM professor GROUP BY nomeProfessor";
        
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            resultSet = preparando.executeQuery();
            while (resultSet.next()) {                
                Professor p = new Professor();
                p.setProfessorID(resultSet.getInt("professorID"));
                listaProfessor.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar os turmas" + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return listaProfessor;
    }
    
    public String buscarNomeProfessor(Integer professorID) throws SQLException{
        Professor professor = null;
        String nomeProfessor = "";
        String consulta = "SELECT nomeProfessor FROM professor "
                        + " WHERE professorID = ?" +
                          " GROUP BY nomeProfessor";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setInt(1, professorID);
            resultSet = preparando.executeQuery();
            if (resultSet.next()) {
                nomeProfessor = resultSet.getString(1);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar prof " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return nomeProfessor;
    }
    
    public Professor pesquisarPorId(Integer id) throws SQLException {
        Professor professor = null;
        String consulta = "SELECT * FROM Professor p WHERE professorID = ?";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setInt(1, id);
            resultSet = preparando.executeQuery();
            if (resultSet.next()) {
                professor = new Professor();
                professor.setProfessorID(resultSet.getInt("professorID"));
                professor.setNomeProfessor(resultSet.getString("nomeProfessor"));
                professor.setVisivelProfessor(resultSet.getBoolean("visivelProfessor"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar professor por id\n" + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando);
        }
        return professor;
    }
    
}
