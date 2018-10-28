package br.com.docproc.util;

public interface Constante {

    String USUARIO_SEM_ACESSO = "Usuario não é admin para executar essa ação";
    String USUARIO_NAO_INFORMADO = "Usuario não informado";
    String ADMIN_NAO_INFORMADO = "Administrador não informado";
    String CONFIGURACOES_INVALIDAS = "Configurações não informadas";
    String REGISTRO_JA_EXISTENTE = "O registro já existe no banco de dados";
    String ERRO_PERSISTENCIA = "Falha ao salvar o registro";
    String USUARIO_NAO_CADASTRADO = "Usuario inexistente";

    String [] caracteresExtras = {",", ".", "!", "?", ";", "\n", " "};


    String CAPTURA_INVALIDA = "Forma de captura do arquivo invalido!";
    String TIPO_ARQUIVO_INVALIDO = "Tipo de arquivo invalido!";
}
