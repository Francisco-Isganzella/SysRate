/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysrate.dao;

import br.com.sysrate.entidade.Disciplina;
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
public class DisciplinaDao {
    
    protected Connection conexao;
    protected PreparedStatement preparando;
    protected ResultSet resultSet;
    
    public void salvar(Disciplina disciplina) throws SQLException{
        String sql = "INSERT INTO Disciplina (disciplina, visivelDisciplina) VALUES (?,?)";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparando.setString(1, disciplina.getDisciplina());
            preparando.setBoolean(2, disciplina.getVisivelDisciplina());
            preparando.executeUpdate();
            
        } catch (Exception e) {
            System.err.println("Ocorreu um erro ao salvar a disciplina:" + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando);
        }
    }
    
    public Disciplina pesquisaPorNome(String nome) throws SQLException {
        Disciplina disciplina = null;
        String consulta = "SELECT * FROM disciplina d "
                + "WHERE d.disciplina= ?";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setString(1, nome);
            resultSet = preparando.executeQuery();
            
            if (resultSet.next()) {
                disciplina = new Disciplina();
                disciplina.setDisciplinaID(resultSet.getInt("disciplinaID"));
                disciplina.setVisivelDisciplina(resultSet.getBoolean("visivelDisciplina"));
                disciplina.setDisciplina(resultSet.getString("disciplina"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar disciplina por nome:"+e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return disciplina;
    }
    
    public List<Disciplina> listarDisciplina()throws SQLException{
        List<Disciplina> listaDisciplina = new ArrayList<Disciplina>();
        String consulta = "SELECT * FROM Disciplina d ORDER BY d.disciplina";
        
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            resultSet = preparando.executeQuery();
            while (resultSet.next()) {                
                Disciplina d = new Disciplina();
                d.setDisciplinaID(resultSet.getInt("disciplinaID"));
                d.setVisivelDisciplina(resultSet.getBoolean("visivelDisciplina"));
                d.setDisciplina(resultSet.getString("disciplina"));
                listaDisciplina.add(d);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar disciplinas" + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return listaDisciplina;
    }
    
    public void alterar(Disciplina disciplina) throws SQLException {
        String sql = "UPDATE Disciplina SET visivelDisciplina = ?, disciplina = ? WHERE disciplinaID = ?";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(sql);
            preparando.setBoolean(1, disciplina.getVisivelDisciplina());
            preparando.setString(2, disciplina.getDisciplina());
            preparando.setInt(3, disciplina.getDisciplinaID());
            preparando.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir disciplina\n" + e.getMessage());
        } 
        finally {
            FabricaConexao.fecharConexao(conexao, preparando);
        }
    }
    
    public Disciplina pesquisarPorId(Integer id) throws SQLException {
        Disciplina disciplina = null;
        String consulta = "SELECT * FROM Disciplina d WHERE d.disciplinaID = ?";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setInt(1, id);
            resultSet = preparando.executeQuery();
            if (resultSet.next()) {
                disciplina = new Disciplina();
                disciplina.setDisciplinaID(resultSet.getInt("disciplinaID"));
                disciplina.setDisciplina(resultSet.getString("disciplina"));
                disciplina.setVisivelDisciplina(resultSet.getBoolean("visivelDisciplina"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar disciplina por id\n" + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando);
        }
        return disciplina;
    }
    
    public String buscarDisciplina (Integer professorID) throws SQLException{
        Disciplina disciplina = null;
        //Professor professor = null;
        String nomeDisciplina = "";
        String consulta = "SELECT d.disciplina FROM turma t INNER JOIN disciplina d on d.disciplinaID = t.disciplinaID WHERE t.professorID = ?";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setInt(1, professorID);
            resultSet = preparando.executeQuery();
            if (resultSet.next()) {
                nomeDisciplina = resultSet.getString(1);
                
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar disciplina " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return nomeDisciplina;      
    }
    
    public List<Disciplina> buscarDisciplinaProf (Integer professorID) throws SQLException{
        List<Disciplina> listaDisciplinaProf = new ArrayList<Disciplina>();
        String consulta = "SELECT d.disciplina FROM turma t INNER JOIN disciplina d on d.disciplinaID = t.disciplinaID WHERE t.professorID = ?";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setInt(1, professorID);
            resultSet = preparando.executeQuery();
            while (resultSet.next()) {
                Disciplina d = new Disciplina();
                d.setDisciplina(resultSet.getString("disciplina"));
                listaDisciplinaProf.add(d);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar disciplina " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return listaDisciplinaProf;
    }
}
