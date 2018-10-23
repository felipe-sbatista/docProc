package br.com.docproc.utils;

public enum Roles {
    ADMIN("ROLE_ADMIN"), USUARIO("ROLE_USUARIO");

    private String regra;

    Roles(String regra){
        this.regra = regra;
    }

    public String toString() {
        return this.regra;
    }

}
