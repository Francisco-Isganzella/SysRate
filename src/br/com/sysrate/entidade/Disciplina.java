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
public class Disciplina extends Professor{
    private Integer disciplinaID;
    private String disciplina;
    private Boolean visivelDisciplina;

    /**
     * @return the disciplinaID
     */
    public Integer getDisciplinaID() {
        return disciplinaID;
    }

    /**
     * @param disciplinaID the disciplinaID to set
     */
    public void setDisciplinaID(Integer disciplinaID) {
        this.disciplinaID = disciplinaID;
    }

    /**
     * @return the disciplina
     */
    public String getDisciplina() {
        return disciplina;
    }

    /**
     * @param disciplina the disciplina to set
     */
    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    /**
     * @return the visivelDisciplina
     */
    public Boolean getVisivelDisciplina() {
        return visivelDisciplina;
    }

    /**
     * @param visivelDisciplina the visivelDisciplina to set
     */
    public void setVisivelDisciplina(Boolean visivelDisciplina) {
        this.visivelDisciplina = visivelDisciplina;
    }

}
