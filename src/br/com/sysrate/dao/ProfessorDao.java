/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysrate.dao;

import br.com.sysrate.entidade.Professor;
import br.com.sysrate.entidade.Turma;
import br.com.sysrate.tela.Cadastro;
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
            resultSet.next();
            professor.setProfessorID(resultSet.getInt(1));
            
        } catch (Exception e) {
            System.err.println("Ocorreu um erro ao salvar o professor:" + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
    }
    
}
