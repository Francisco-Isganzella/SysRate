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
public class Notas {

    /**
     * @return the notasID
     */
    public Integer getNotasID() {
        return notasID;
    }

    /**
     * @param notasID the notasID to set
     */
    public void setNotasID(Integer notasID) {
        this.notasID = notasID;
    }

    /**
     * @return the professorID
     */
    public Integer getProfessorID() {
        return professorID;
    }

    /**
     * @param professorID the professorID to set
     */
    public void setProfessorID(Integer professorID) {
        this.professorID = professorID;
    }

    /**
     * @return the didatica
     */
    public Integer getDidatica() {
        return didatica;
    }

    /**
     * @param didatica the didatica to set
     */
    public void setDidatica(Integer didatica) {
        this.didatica = didatica;
    }

    /**
     * @return the qualidadeMaterial
     */
    public Integer getQualidadeMaterial() {
        return qualidadeMaterial;
    }

    /**
     * @param qualidadeMaterial the qualidadeMaterial to set
     */
    public void setQualidadeMaterial(Integer qualidadeMaterial) {
        this.qualidadeMaterial = qualidadeMaterial;
    }

    /**
     * @return the qualidadeCorrecao
     */
    public Integer getQualidadeCorrecao() {
        return qualidadeCorrecao;
    }

    /**
     * @param qualidadeCorrecao the qualidadeCorrecao to set
     */
    public void setQualidadeCorrecao(Integer qualidadeCorrecao) {
        this.qualidadeCorrecao = qualidadeCorrecao;
    }

    /**
     * @return the receptividade
     */
    public Integer getReceptividade() {
        return receptividade;
    }

    /**
     * @param receptividade the receptividade to set
     */
    public void setReceptividade(Integer receptividade) {
        this.receptividade = receptividade;
    }

    /**
     * @return the respeito
     */
    public Integer getRespeito() {
        return respeito;
    }

    /**
     * @param respeito the respeito to set
     */
    public void setRespeito(Integer respeito) {
        this.respeito = respeito;
    }
    private Integer notasID;
    private Integer professorID;
    private Integer didatica;
    private Integer qualidadeMaterial;
    private Integer qualidadeCorrecao;
    private Integer receptividade;
    private Integer respeito;
}
