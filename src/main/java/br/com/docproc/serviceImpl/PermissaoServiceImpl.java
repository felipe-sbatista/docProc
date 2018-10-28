package br.com.docproc.serviceImpl;

import br.com.docproc.base.AbstractEntity;
import br.com.docproc.dto.PermissaoDTO;
import br.com.docproc.entity.TipoArquivo;
import br.com.docproc.entity.TipoCaptura;
import br.com.docproc.entity.Usuario;
import br.com.docproc.exception.ParametroPermissaoInvalidoException;
import br.com.docproc.exception.PermissaoException;
import br.com.docproc.exception.UsuarioInvalidoException;
import br.com.docproc.repository.TipoArquivoRepository;
import br.com.docproc.repository.TipoCapturaRepository;
import br.com.docproc.service.PermissaoService;
import br.com.docproc.service.TipoArquivoService;
import br.com.docproc.service.TipoCapturaService;
import br.com.docproc.util.Constante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissaoServiceImpl extends AbstractEntity<Long> implements PermissaoService {

    @Autowired
    private TipoArquivoServiceImpl tipoArquivoService;

    @Autowired
    private TipoCapturaServiceImpl tipoCapturaService;

    @Autowired
    private UsuarioServiceImpl usuarioService;


    public ResponseEntity setPermissoes(PermissaoDTO permissao) {
        try{
            checkParametros(permissao);
            Usuario admin = usuarioService.getByMatricula(permissao.getUsuario().getMatricula());
            Usuario usuario = usuarioService.getByMatricula(permissao.getUsuario().getMatricula());
            if(!admin.getFuncao().getDescricao().equals("ADMIN")){
                throw new PermissaoException(Constante.USUARIO_SEM_ACESSO);
            }

            if(!permissao.getTipoCapturas().isEmpty()){
                usuario.setPermissoesCaptura(permissao.getTipoCapturas());
            }

            if(!permissao.getTipoArquivos().isEmpty()){
                usuario.setPermissoesArquivo(permissao.getTipoArquivos());
            }
            usuarioService.salvar(usuario);
            return ResponseEntity.ok().build();
        } catch (UsuarioInvalidoException e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.getMessage());
        } catch (ParametroPermissaoInvalidoException e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.getMessage());
        } catch (PermissaoException e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.getMessage());
        }
    }



    private void checkParametros(PermissaoDTO dto) throws UsuarioInvalidoException, ParametroPermissaoInvalidoException {
        if(dto.getUsuario() == null || dto.getUsuario().getMatricula().isEmpty()){
            throw new UsuarioInvalidoException(Constante.USUARIO_NAO_INFORMADO);
        }
        if(dto.getAdmin() == null || dto.getAdmin().getMatricula().isEmpty()){
            throw new UsuarioInvalidoException(Constante.ADMIN_NAO_INFORMADO);
        }
        if( (dto.getTipoArquivos() == null && dto.getTipoCapturas() == null) ||
                (dto.getTipoArquivos().isEmpty() && dto.getTipoCapturas().isEmpty())){
            throw new ParametroPermissaoInvalidoException(Constante.CONFIGURACOES_INVALIDAS);
        }

    }
}
