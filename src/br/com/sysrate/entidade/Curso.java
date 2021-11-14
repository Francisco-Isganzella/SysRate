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
public class Curso extends Disciplina{
    private Integer cursoID;
    private String curso;
    private Boolean visivelCurso;
 
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
     * @return the curso
     */
    public String getCurso() {
        return curso;
    }

    /**
     * @param curso the curso to set
     */
    public void setCurso(String curso) {
        this.curso = curso;
    }

    /**
     * @return the visivelCurso
     */
    public Boolean getVisivelCurso() {
        return visivelCurso;
    }

    /**
     * @param visivelCurso the visivelCurso to set
     */
    public void setVisivelCurso(Boolean visivelCurso) {
        this.visivelCurso = visivelCurso;
    }
}
