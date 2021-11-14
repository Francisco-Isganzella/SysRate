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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author migue
 */
public class UsuarioDao {
    protected Connection conexao;
    protected PreparedStatement preparando;
    protected ResultSet resultSet;
    
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
    public void salvar(Usuario usuario) throws SQLException{
        String sql = "INSERT INTO Usuario (nomeUsuario, matricula, cursoID, permissao, ativoOnline) VALUES (?,?,?,?,?)";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparando.setString(1, usuario.getNomeUsuario());
            preparando.setString(2, usuario.getMatricula());
            preparando.setInt(3, usuario.getCursoID());
            preparando.setBoolean(4, usuario.getPermissao());
            preparando.setBoolean(5, usuario.getAtivoOnline());
            preparando.executeUpdate();
            //resultSet.next();
            //usuario.setUsuarioID(resultSet.getInt(1));
            
            
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao salvar o usuario:" + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando);
        }
    }
    public List<Usuario> listarUsuario() throws SQLException{
        List<Usuario> listaUsuario = new ArrayList<Usuario>();
        String consulta = "SELECT * FROM usuario";
        
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            resultSet = preparando.executeQuery();
            while (resultSet.next()) {                
                Usuario u = new Usuario();
                u.setUsuarioID(resultSet.getInt("usuarioID"));
                u.setCursoID(resultSet.getInt("cursoID"));
                u.setMatricula(resultSet.getString("matricula"));
                u.setNomeUsuario(resultSet.getString("nomeUsuario"));
                u.setSenha(resultSet.getString("senha"));
                u.setPermissao(resultSet.getBoolean("permissao"));
                u.setAtivoOnline(resultSet.getBoolean("ativoOnline"));
                
                listaUsuario.add(u);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar os usuarios" + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return listaUsuario;
    }
    
    public List<Usuario> listarUsuarioInner() throws SQLException{
        List<Usuario> listaUsuario = new ArrayList<Usuario>();
        
        String consulta = "SELECT * FROM usuario u INNER JOIN curso c on c.cursoID = u.cursoID ORDER BY u.nomeUsuario";
        
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            resultSet = preparando.executeQuery();
            while (resultSet.next()) {
                Usuario u = new Usuario();
                
                u.setUsuarioID(resultSet.getInt("usuarioID"));
                u.setNomeUsuario(resultSet.getString("nomeUsuario"));
                u.setMatricula(resultSet.getString("matricula"));
                u.setCurso(resultSet.getString("c.curso"));
                u.setVisivelCurso(resultSet.getBoolean("visivelCurso"));
                
                listaUsuario.add(u);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar os usuarios " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return listaUsuario;
    }
    
    public void excluir(Integer id) throws SQLException {
     try {
         conexao = FabricaConexao.abrirConexao();
         preparando = conexao.prepareStatement("DELETE FROM Usuario WHERE usuarioID = ?");
         preparando.setInt(1, id);
         preparando.executeUpdate();
     } catch (SQLException e) {
         System.err.println("Erro ao excluir usuario\n" + e.getMessage());
     } finally {
         FabricaConexao.fecharConexao(conexao, preparando);
     }
    }
}
