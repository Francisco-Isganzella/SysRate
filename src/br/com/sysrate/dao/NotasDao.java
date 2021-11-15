/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysrate.dao;

import br.com.sysrate.entidade.Notas;
import br.com.sysrate.entidade.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author migue
 */
public class NotasDao {
    
    protected Connection conexao;
    protected PreparedStatement preparando;
    protected ResultSet resultSet;
    
    public void salvar(Notas notas) throws SQLException{
        String sql = "INSERT INTO Nota (professorID, didatica, qualidadeMaterial, qualidadeCorrecao, receptividade, respeito) VALUES (?,?,?,?,?,?)";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparando.setInt(1, notas.getProfessorID());
            preparando.setInt(2, notas.getDidatica());
            preparando.setInt(3, notas.getQualidadeMaterial());
            preparando.setInt(4, notas.getQualidadeCorrecao());
            preparando.setInt(5, notas.getReceptividade());
            preparando.setInt(6, notas.getRespeito());
            preparando.executeUpdate();
            
            
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao salvar as notas:" + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando);
        }
    }
}
