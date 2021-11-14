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
public class Turma extends Curso{
    private Integer turmaID;
    private Integer disciplinaID;
    private Integer professorID;
    private Integer cursoID;
    private String turma;
    private Boolean visivel;
    
    

    /**
     * @return the turmaID
     */
    public Integer getTurmaID() {
        return turmaID;
    }

    /**
     * @param turmaID the turmaID to set
     */
    public void setTurmaID(Integer turmaID) {
        this.turmaID = turmaID;
    }

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
     * @return the cursoID
     */
    public Integer getCursoID() {
        return cursoID;
    }

    /**
     * @param cursoID the cursoID to set
     */
    public void setCursoID(Integer cursoID) {
        this.cursoID = cursoID;
    }

    /**
     * @return the turma
     */
    public String getTurma() {
        return turma;
    }

    /**
     * @param turma the turma to set
     */
    public void setTurma(String turma) {
        this.turma = turma;
    }

    /**
     * @return the visivel
     */
    public Boolean getVisivel() {
        return visivel;
    }

    /**
     * @param visivel the visivel to set
     */
    public void setVisivel(Boolean visivel) {
        this.visivel = visivel;
    }

    
}
