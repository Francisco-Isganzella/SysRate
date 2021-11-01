/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysrate.dao;

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
public class UsuarioDao {
    protected Connection conexao;
    protected PreparedStatement preparando;
    protected ResultSet resultSet;
    
    public void salvar(Usuario usuario) throws SQLException{
        String sql = "INSERT INTO Usuario (nomeUsuario, matricula, permissao, ativoOnline) VALUES (?,?,?,?)";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparando.setString(1, usuario.getNomeUsuario());
            preparando.setString(2, usuario.getMatricula());
            //preparando.setInt(3, usuario.getCursoID());
            preparando.setBoolean(3, usuario.getPermissao());
            preparando.setBoolean(4, usuario.getAtivoOnline());
            preparando.executeUpdate();
            resultSet.next();
            usuario.setUsuarioID(resultSet.getInt(1));
            
            
        } catch (Exception e) {
            System.err.println("Ocorreu um erro ao salvar o usuario:" + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
    }
    
}
