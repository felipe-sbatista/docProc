package br.com.docproc.util;

public interface Constante {
    Long ADMIN = 1L;
    Long USUARIO = 2L;

    String USUARIO_SEM_ACESSO = "Usuario não é admin para executar essa ação";
    String USUARIO_NAO_INFORMADO = "Usuario não informado";
    String ADMIN_NAO_INFORMADO = "Administrador não informado";
    String CONFIGURACOES_INVALIDAS = "Configurações não informadas";

    String [] caracteresExtras = {",", ".", "!", "?", ";"};


}
