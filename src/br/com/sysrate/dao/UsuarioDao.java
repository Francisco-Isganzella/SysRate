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
import java.sql.SQLDataException;
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
    
    public void salvar(Usuario usuario) throws SQLDataException, SQLDataException, SQLException{
        String sql = "INSERT INTO Usuario (cursoID, matricula, nomeUsuario, senha, permissao, ativoOnline) VALUES (?,?,?,?,?,?)";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparando.setInt(1, usuario.getCursoID());
            preparando.setString(2, usuario.getMatricula());
            preparando.setString(3, usuario.getNomeUsuario());
            preparando.setString(4, usuario.getSenha());
            preparando.setBoolean(5, usuario.getPermissao());
            preparando.setBoolean(6, usuario.getAtivoOnline());
            preparando.executeUpdate();
            resultSet = preparando.getGeneratedKeys();
            resultSet.next();
            usuario.setUsuarioID(resultSet.getInt(1));
            
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao salvar o usuario: " + e.getMessage());
        } finally{
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
    }
    
    public Usuario pesquisarPorMatricula (String matricula) throws SQLException {
        Usuario usuario = null;
        String consulta = "SELECT * FROM Usuario u WHERE u.matricula = ?";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setString(1, matricula);
            resultSet = preparando.executeQuery();
             
            if (resultSet.next()) {
                usuario = new Usuario();
                usuario.setUsuarioID(resultSet.getInt("usuarioID"));
                usuario.setCursoID(resultSet.getInt("cursoID"));
                usuario.setMatricula(resultSet.getString("matricula"));
                usuario.setNomeUsuario(resultSet.getString("nomeUsuario"));
                usuario.setSenha(resultSet.getString("senha"));
                usuario.setPermissao(resultSet.getBoolean("permissao"));
                usuario.setAtivoOnline(resultSet.getBoolean("ativoOnline"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar por matricula: " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return usuario;
    }
    
    public void alterar (Usuario usuario) throws SQLException {
        String sql = "UPDATE Usuario SET cursoID=?, matricula=?, nomeUsuario=?, senha=?, permissao=?, ativoOnline=? where usuarioID=?";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(sql);
            preparando.setInt(1, usuario.getCursoID());
            preparando.setString(2, usuario.getMatricula());
            preparando.setString(3, usuario.getNomeUsuario());
            preparando.setString(4, usuario.getSenha());
            preparando.setBoolean(5, usuario.getPermissao());
            preparando.setBoolean(6, usuario.getAtivoOnline());
            preparando.setInt(7, usuario.getUsuarioID());
            preparando.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar: "+ e.getMessage());
        }finally {
            FabricaConexao.fecharConexao(conexao, preparando);
        }
    }
    
}
