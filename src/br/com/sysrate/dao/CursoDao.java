/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysrate.dao;

import br.com.sysrate.entidade.Curso;
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
public class CursoDao {
    protected Connection conexao;
    protected PreparedStatement preparando;
    protected ResultSet resultSet;
    
    public void salvar(Curso curso) throws SQLException{
        String sql = "INSERT INTO Curso (curso, visivelCurso) VALUES (?,?)";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparando.setString(1, curso.getCurso());
            preparando.setBoolean(2, curso.getVisivelCurso());
            preparando.executeUpdate();
            preparando.getGeneratedKeys();
            
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao salvar o curso:" + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando);
        }
    }
    
    public Curso pesquisarPorNome(String nome) throws SQLException{
        Curso curso = null;
        String consulta = "SELECT * FROM curso c "
                        + "WHERE curso = ?";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setString(1, nome);
            resultSet = preparando.executeQuery();
            
            if (resultSet.next()) {
                curso = new Curso();
                curso.setCursoID(resultSet.getInt("cursoID"));
                curso.setVisivelCurso(resultSet.getBoolean("visivelCurso"));
                curso.setCurso(resultSet.getString("curso"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar curso por nome:"+e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return curso;
    }
    
    public List<Curso> listarCurso() throws SQLException{
        List<Curso> listaCurso = new ArrayList<Curso>();
        String consulta = "SELECT * FROM Curso c ORDER BY curso";
        
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            resultSet = preparando.executeQuery();
            while (resultSet.next()) {                
                Curso c = new Curso();
                c.setCursoID(resultSet.getInt("cursoID"));
                c.setVisivelCurso(resultSet.getBoolean("visivelCurso"));
                c.setCurso(resultSet.getString("curso"));
                listaCurso.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar os cursos" + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return listaCurso;
    }
    
    public void alterar(Curso curso) throws SQLException {
        String sql = "UPDATE Curso SET visivelCurso = ?, curso = ? WHERE cursoID = ?";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(sql);
            preparando.setBoolean(1, curso.getVisivelCurso());
            preparando.setString(2, curso.getCurso());
            preparando.setInt(3, curso.getCursoID());
            preparando.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir curso " + e.getMessage());
        } 
        finally {
            FabricaConexao.fecharConexao(conexao, preparando);
        }
    }
    
    public Curso pesquisarPorId(Integer id) throws SQLException {
        Curso curso = null;
        String consulta = "SELECT * FROM Curso c WHERE cursoID = ?";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setInt(1, id);
            resultSet = preparando.executeQuery();
            if (resultSet.next()) {
                curso = new Curso();
                curso.setCursoID(resultSet.getInt("cursoID"));
                curso.setCurso(resultSet.getString("curso"));
                curso.setVisivelCurso(resultSet.getBoolean("visivelCurso"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar curso por id\n" + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando);
        }
        return curso;
    }
    
    public String buscarNomeCurso(String nomeProfessor) throws SQLException{
        Curso curso = null;
        String nomeCurso = "";
        String consulta = "SELECT curso" +
                          " FROM (Turma t INNER JOIN Curso c ON t.cursoID = c.cursoID)" +
                          " INNER JOIN Professor p ON t.professorID = p.professorID" +
                          " WHERE p.nomeProfessor = ?" +
                          " GROUP BY curso";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setString(1, nomeProfessor);
            resultSet = preparando.executeQuery();
            if (resultSet.next()) {
                nomeCurso = resultSet.getString(1);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar curso " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return nomeCurso;
    }
}
