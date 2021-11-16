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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author migue
 */
public class TurmaDao {
    protected Connection conexao;
    protected PreparedStatement preparando;
    protected ResultSet resultSet;
    
    public void salvar(Turma turma) throws SQLException{
        String sql = "INSERT INTO Turma(disciplinaID, professorID, cursoID, turma, visivelTurma) VALUES (?,?,?,?,?)";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparando.setInt(1, turma.getDisciplinaID());
            preparando.setInt(2, turma.getProfessorID());
            preparando.setInt(3, turma.getCursoID());
            preparando.setString(4, turma.getTurma());
            preparando.setBoolean(5, turma.getVisivelTurma());
            preparando.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao salvar a turma:" + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando);
        }
    }
    
    public List<Turma> listarTurma() throws SQLException{
        List<Turma> listaTurma = new ArrayList<Turma>();
        String consulta = "SELECT * FROM turma";
        
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            resultSet = preparando.executeQuery();
            while (resultSet.next()) {                
                Turma t = new Turma();
                t.setTurmaID(resultSet.getInt("turmaID"));
                t.setDisciplinaID(resultSet.getInt("disciplinaID"));
                t.setProfessorID(resultSet.getInt("professorID"));
                t.setCursoID(resultSet.getInt("cursoID"));
                t.setTurma(resultSet.getString("turma"));
                t.setVisivelTurma(resultSet.getBoolean("visivelTurma"));
                listaTurma.add(t);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar os turmas" + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return listaTurma;
    }
    
    public List<Turma> listarTurmaInner() throws SQLException{
        List<Turma> listaTurma = new ArrayList();
        
        String consulta = "SELECT * FROM turma t INNER JOIN professor p on p.professorID = t.professorID INNER JOIN disciplina d on d.disciplinaID = t.disciplinaID INNER JOIN curso c on c.cursoID = t.cursoID ORDER BY p.nomeProfessor";
        
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            resultSet = preparando.executeQuery();
            while (resultSet.next()) {
                Turma t = new Turma();
                t.setTurmaID(resultSet.getInt("turmaID"));
                t.setNomeProfessor(resultSet.getString("p.nomeProfessor"));
                t.setTurma(resultSet.getString("turma"));
                t.setDisciplina(resultSet.getString("d.disciplina"));
                t.setCurso(resultSet.getString("c.curso"));
                t.setVisivelTurma(resultSet.getBoolean("visivelTurma"));
                
                listaTurma.add(t);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar as turmas " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return listaTurma;
    }
    
    public void alterar(Turma turma) throws SQLException {
        String sql = "UPDATE Turma SET visivelTurma = ?, turma = ?, disciplinaID = ?, cursoID = ? WHERE turmaID = ?";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(sql);
            preparando.setBoolean(1, turma.getVisivelTurma());
            preparando.setString(2, turma.getTurma());
            preparando.setInt(5, turma.getTurmaID());
            preparando.setInt(3, turma.getDisciplinaID());
            preparando.setInt(4, turma.getCursoID());
            preparando.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir turma\n" + e.getMessage());
        } 
        finally {
            FabricaConexao.fecharConexao(conexao, preparando);
        }
    }
      
}
