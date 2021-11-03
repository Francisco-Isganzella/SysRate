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
            resultSet.next();
            curso.setCursoID(resultSet.getInt(1));
            
            
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao salvar o curso:" + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
    }
    
    public Curso pesquisarPorNome(String nome) throws SQLException{
        Curso curso = null;
        String consulta = "SELECT * FROM Curso c"
                + "WHERE c.cursoID = ?";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setString(1, nome);
            resultSet = preparando.executeQuery();
            
            if (resultSet.next()) {
                curso = new Curso();
                curso.setCursoID(resultSet.getInt("cursoID"));
                curso.setVisivel(resultSet.getBoolean("visivel"));
                curso.setCurso(resultSet.getString("curso"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar curso por nome:"+e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return curso;
    }
    
}
