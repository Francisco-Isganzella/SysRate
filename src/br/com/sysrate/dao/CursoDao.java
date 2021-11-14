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
        String sql = "INSERT INTO Curso (curso, visivel) VALUES (?,?)";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparando.setString(1, curso.getCurso());
            preparando.setBoolean(2, curso.getVisivel());
            preparando.executeUpdate();
            preparando.getGeneratedKeys();
            //resultSet.next();
            //curso.setCursoID(resultSet.getInt(1));
            
            
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao salvar o curso:" + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando);
        }
    }
    
    public Curso pesquisarPorNome(String nome) throws SQLException{
        Curso curso = null;
        //String consulta = "SELECT * FROM Curso c WHERE c.curso like ?";
        String consulta = "SELECT * FROM curso c "
                + "WHERE c.curso = ?";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            //preparando.setString(1, "%" + nome + "%");
            preparando.setString(1, nome);
            resultSet = preparando.executeQuery();
            
            if (resultSet.next()) {
                curso = new Curso();
                curso.setCursoID(resultSet.getInt("cursoID"));
                curso.setVisivel(resultSet.getBoolean("visivel"));
                curso.setCurso(resultSet.getString("curso"));
            }
            
            //System.out.println(curso.getCurso());
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar curso por nome:"+e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return curso;
    }
    
    public List<Curso> listarCurso() throws SQLException{
        List<Curso> listaCurso = new ArrayList<Curso>();
        String consulta = "SELECT * FROM Curso";
        
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            resultSet = preparando.executeQuery();
            while (resultSet.next()) {                
                Curso c = new Curso();
                c.setCursoID(resultSet.getInt("cursoID"));
                c.setVisivel(resultSet.getBoolean("visivel"));
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
        String sql = "UPDATE Curso SET visivel = ?, curso = ? WHERE cursoID = ?";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(sql);
            preparando.setBoolean(1, curso.getVisivel());
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
        String consulta = "SELECT * FROM Curso c WHERE c.cursoID = ?";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setInt(1, id);
            resultSet = preparando.executeQuery();
            if (resultSet.next()) {
                curso = new Curso();
                curso.setCursoID(resultSet.getInt("cursoID"));
                curso.setCurso(resultSet.getString("curso"));
                curso.setVisivel(resultSet.getBoolean("visivel"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar curso por id\n" + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando);
        }
        return curso;
    }
}
