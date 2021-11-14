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
<<<<<<< HEAD
public class Usuario {
    private int usuarioID;
    private int cursoID;
=======
public class Usuario extends Curso{
    private Integer usuarioID;
    private Integer cursoID;
>>>>>>> adminCadastroV2
    private String matricula;
    private String nomeUsuario;
    private String senha;
    private Boolean permissao;
    private Boolean ativoOnline;
<<<<<<< HEAD
=======
    
    
>>>>>>> adminCadastroV2

    /**
     * @return the usuarioID
     */
<<<<<<< HEAD
    public int getUsuarioID() {
=======
    public Integer getUsuarioID() {
>>>>>>> adminCadastroV2
        return usuarioID;
    }

    /**
     * @param usuarioID the usuarioID to set
     */
<<<<<<< HEAD
    public void setUsuarioID(int usuarioID) {
=======
    public void setUsuarioID(Integer usuarioID) {
>>>>>>> adminCadastroV2
        this.usuarioID = usuarioID;
    }

    /**
     * @return the cursoID
     */
<<<<<<< HEAD
    public int getCursoID() {
=======
    public Integer getCursoID() {
>>>>>>> adminCadastroV2
        return cursoID;
    }

    /**
     * @param cursoID the cursoID to set
     */
<<<<<<< HEAD
    public void setCursoID(int cursoID) {
=======
    public void setCursoID(Integer cursoID) {
>>>>>>> adminCadastroV2
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
<<<<<<< HEAD
=======
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

    /**
>>>>>>> adminCadastroV2
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

<<<<<<< HEAD
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

=======
    
>>>>>>> adminCadastroV2
}
