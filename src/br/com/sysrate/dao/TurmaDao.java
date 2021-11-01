/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysrate.dao;

import br.com.sysrate.entidade.Turma;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author migue
 */
public class TurmaDao {
    protected Connection conexao;
    protected PreparedStatement preparando;
    protected ResultSet resultSet;
    
    public void salvar(Turma turma) throws SQLException{
        String sql = "INSERT INTO Turma(disciplinaID, professorID, cursoID, turma, visivel) VALUES (?,?,?,?,?)";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparando.setInt(1, turma.getDisciplinaID());
            preparando.setInt(2, turma.getProfessorID());
            preparando.setInt(3, turma.getCursoID());
            preparando.setString(4, turma.getTurma());
            preparando.setBoolean(5, turma.getVisivel());
            preparando.executeUpdate();
            resultSet.next();
            turma.setCursoID(resultSet.getInt(1));
            
            
        } catch (Exception e) {
            System.err.println("Ocorreu um erro ao salvar a turma:" + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
    }
    
}
