package com.application.neto.meuspedidosvagas.model;

import java.util.ArrayList;

public class Candidate {

    private String nome;
    private String email;
    private int html;
    private int css;
    private int javaScript;
    private int python;
    private int django;
    private int ios;
    private int android;

    public Candidate() {
        super();
    }

    public Candidate(String nome, String email, int html, int css, int javaScript, int python,
                     int django, int ios, int android) {
        this.nome = nome;
        this.email = email;
        this.html = html;
        this.css = css;
        this.javaScript = javaScript;
        this.python = python;
        this.django = django;
        this.ios = ios;
        this.android = android;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getHtml() {
        return html;
    }

    public void setHtml(int html) {
        this.html = html;
    }

    public int getCss() {
        return css;
    }

    public void setCss(int css) {
        this.css = css;
    }

    public int getJavaScript() {
        return javaScript;
    }

    public void setJavaScript(int javaScript) {
        this.javaScript = javaScript;
    }

    public int getPython() {
        return python;
    }

    public void setPython(int python) {
        this.python = python;
    }

    public int getDjango() {
        return django;
    }

    public void setDjango(int django) {
        this.django = django;
    }

    public int getIos() {
        return ios;
    }

    public void setIos(int ios) {
        this.ios = ios;
    }

    public int getAndroid() {
        return android;
    }

    public void setAndroid(int android) {
        this.android = android;
    }

    public ArrayList<String> analyzeProfile(){
        ArrayList<String> retorno = new ArrayList<String>();
        boolean generico = true;
        if(html > 6 && css > 6 && javaScript > 6) {
            retorno.add("Front-End");
            generico = false;
        }
        if(python > 6 && django > 6) {
            retorno.add("Back-End");
            generico = false;
        }
        if(ios > 6 && android > 6) {
            retorno.add("Mobile");
            generico = false;
        }
        if(generico){
            retorno.add("programador");
        }
        return retorno;
    }
}
