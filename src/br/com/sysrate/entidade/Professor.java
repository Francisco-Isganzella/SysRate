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
public class Professor {
    private Integer professorID;
    private String nomeProfessor;
    private Boolean visivelProfessor;

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
     * @return the nomeProfessor
     */
    public String getNomeProfessor() {
        return nomeProfessor;
    }

    /**
     * @param nomeProfessor the nomeProfessor to set
     */
    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }

    /**
     * @return the visivelProfessor
     */
    public Boolean getVisivelProfessor() {
        return visivelProfessor;
    }

    /**
     * @param visivelProfessor the visivelProfessor to set
     */
    public void setVisivelProfessor(Boolean visivelProfessor) {
        this.visivelProfessor = visivelProfessor;
    }
}
