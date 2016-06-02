package br.pucgoias.os.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.pucgoias.os.entidade.OrdemServicos;
import br.pucgoias.os.persistencia.OrdemServicosDAO;
import br.pucgoias.util.OrdemServicosException;

/**
 * Classe que define as operacoes da camada de negocio de Ordem Servicos
 * @author Edileizer
 *
 */
@Service
@Transactional
public class OrdemServicosServiceImpl implements OrdemServicosService {

	//Interface da persistencia
	private OrdemServicosDAO ordemServicosDAO;
	
	public OrdemServicosDAO getOrdemServicosDAO() {
		return ordemServicosDAO;
	}

	@Autowired
	public void setOrdemServicosDAO(OrdemServicosDAO ordemServicosDAO) {
		this.ordemServicosDAO = ordemServicosDAO;
	}

	/**
	 * Inclui uma ordem de servicos
	 * @param ordemServicos
	 * @return
	 * @throws OrdemServicosException
	 */
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
	public OrdemServicos incluir(OrdemServicos ordemServicos) throws OrdemServicosException {
		return getOrdemServicosDAO().incluir(ordemServicos);
	}

	/**
	 * Altera uma ordem de servicos
	 * @param ordemServicos
	 * @return
	 * @throws OrdemServicosException
	 */
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
	public OrdemServicos alterar(OrdemServicos ordemServicos) throws OrdemServicosException {
		/*
		//exclui os itens da base que foram removidos da tela
		OrdemServicos ordemServicosExistente = this.consultar(ordemServicos.getIdOS());
		for (Telefone telefone : pessoaExistente.getListaTelefone()) {
			if(!pessoa.getListaTelefone().contains(telefone)){
				getTelefoneDAO().excluir(telefone.getIdTelefone());
			}
		}
		*/
		return getOrdemServicosDAO().alterar(ordemServicos);
	}

	/**
	 * Exclui uma ordem de servicos
	 * @param ordemServicos
	 * @throws OrdemServicosException
	 */
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
	public void excluir(Integer id) throws OrdemServicosException {
		
		//exclui todos os itens antes de excluir a ordemServicos
		OrdemServicos ordemServicosExistente = this.consultar(id);
		

		getOrdemServicosDAO().excluir(id);
	}

	/**
	 * Consulta uma ordem de servicos pelo identificador
	 * @param id
	 * @return
	 * @throws OrdemServicosException
	 */
	@Transactional(readOnly=true, propagation = Propagation.SUPPORTS)
	public OrdemServicos consultar(Integer id) throws OrdemServicosException {
		OrdemServicos ordemServicos = getOrdemServicosDAO().consultar(id);

		return ordemServicos;
	}

	/**
	 * Lista todas as ordem de servicos cadastradas
	 * @return
	 * @throws OrdemServicosException
	 */
	@Transactional(readOnly=true, propagation = Propagation.SUPPORTS)
	public List<OrdemServicos> listar() throws OrdemServicosException {
		return getOrdemServicosDAO().listar();
	}

}
