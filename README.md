# docProc
Desenvolvimento de um sistema em Spring capaz de receber arquivos, persisti-los e retornar um arquivo em planilha, xls, com as incidencias das palavras.


COMO EXECUTAR : 
Realizar a compilação através de uma IDE, e realizar as requisições via POSTMAN para as URL's específicas de cada controller
O projeto possui atualmente 4 componentes para receber requisições.


1 - MainController


    1.1 - /listarArquivos - Onde atraves de um request passando os parametros via Request body ( JSON ), é passado um DTO que possui um objeto do TipoCaptura e TipoArquivo e data para listar todos os arquivos referentes ao DTO.
    1.2 - /enviarArquivo - Requisição feita em Form-Data para ser possivel transmitir o arquivo em si, e mais 3 Strings que são a matricula do usuario, a descricao do tipo de captura e a descricao do forma de captura.

2 - ConfigController ( /config )

    2.1 - /set-permissao-usuario - Requisição composta de um DTO enviado via JSON com dois objetos do tipo Usuario, sendo um Admin e outro User, e duas listas, uma de TipoCaptura e outra TipoArquivo que dirá os acessos permitidos para envio do arquivo para o usuario informado.
 

3 - TipoArquivoController ( /tipo-arquivo )

    3.1 - /salvar - Requisição dada com um JSON enviado com um TipoArquivo a ser persistido no banco de dados.
    3.2 - /listar - Listar todos os tipos de arquivo ativos no banco. 
    
    
4 - TipoCapturaController ( /tipo-captura )
    
    4.1 - /salvar - Requisição dada com um JSON enviado com um TipoCaptura a ser persistida no banco de dados.
    4.2 - /listar - Listar todos os tipos de captura ativos no banco. 
    
5 - UsuarioController ( /usuario )
  
    5.1 - /salvar - Requisição via JSON - RequestBody que irá receber um usuario para ser persistido no banco.
    
    
* DTO - 
    -  FiltroDTO {

      TipoCaptura tipoCaptura;

      TipoArquivo tipoArquivo;

      "yyyy-MM-dd HH:mm:ss"
      Date dataEnvio;
    }
    
   - PermissaoDTO {

     Usuario admin;

     Usuario usuario;

     List<TipoArquivo> tipoArquivos;

     List<TipoCaptura> tipoCapturas;
  }
  
   * Banco de dados - O Application properties e o POM.xml estão configurados para usar o PostgreSQL ou o MYSQL, porém, as dependencias e as configuraçoes do MYSQL estão comentadas. Além disso, o banco está configurado para gerar as tabelas e excluí-las ao fim da aplicação ( create - drop ) 
   
   * CommandLineRunner - Na classe de iniciar a aplicação, está configurado para ao iniciar a aplicação, é inserido ao banco 3 formas de captura, 4 tipos de arquivo, 2 usuários e 2 funções.
    
