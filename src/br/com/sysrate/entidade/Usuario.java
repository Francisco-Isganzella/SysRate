/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysrate.entidade;

/**
 *
 * @author migue
 */
public class Usuario {
    private int usuarioID;
    private int cursoID;
    private String matricula;
    private String nomeUsuario;
    private String senha;
    private Boolean permissao;
    private Boolean ativoOnline;

    /**
     * @return the usuarioID
     */
    public int getUsuarioID() {
        return usuarioID;
    }

    /**
     * @param usuarioID the usuarioID to set
     */
    public void setUsuarioID(int usuarioID) {
        this.usuarioID = usuarioID;
    }

    /**
     * @return the cursoID
     */
    public int getCursoID() {
        return cursoID;
    }

    /**
     * @param cursoID the cursoID to set
     */
    public void setCursoID(int cursoID) {
        this.cursoID = cursoID;
    }

    /**
     * @return the matricula
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * @param matricula the matricula to set
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     * @return the nomeUsuario
     */
    public String getNomeUsuario() {
        return nomeUsuario;
    }

    /**
     * @param nomeUsuario the nomeUsuario to set
     */
    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    /**
     * @return the permissao
     */
    public Boolean getPermissao() {
        return permissao;
    }

    /**
     * @param permissao the permissao to set
     */
    public void setPermissao(Boolean permissao) {
        this.permissao = permissao;
    }

    /**
     * @return the ativoOnline
     */
    public Boolean getAtivoOnline() {
        return ativoOnline;
    }

    /**
     * @param ativoOnline the ativoOnline to set
     */
    public void setAtivoOnline(Boolean ativoOnline) {
        this.ativoOnline = ativoOnline;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

}
