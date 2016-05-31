package br.pucgoias.af.negocio;

import java.util.List;

import br.pucgoias.os.entidade.OrdemServicos;
import br.pucgoias.util.OrdemServicosException;

/**
 * Interface que define as operacoes da camada de negocio de Ordem de Servicos
 * @author Edileizer
 *
 */
public interface OrdemServicosService {
	
	/**
	 * Inclui uma ordemServicos
	 * @param ordemServicos
	 * @return
	 * @throws OrdemServicosException
	 */
	public OrdemServicos incluir(OrdemServicos ordemServicos) throws OrdemServicosException;
	
	/**
	 * Altera uma ordemServicos
	 * @param ordemServicos
	 * @return
	 * @throws OrdemServicosException
	 */
	public OrdemServicos alterar(OrdemServicos ordemServicos) throws OrdemServicosException;
	
	/**
	 * Exclui uma ordemServicos
	 * @param id
	 * @throws OrdemServicosException
	 */
	public void excluir(Integer id) throws OrdemServicosException;
	
	/**
	 * Consulta uma ordemServicos pelo identificador
	 * @param id
	 * @return
	 * @throws OrdemServicosException
	 */
	public OrdemServicos consultar(Integer id) throws OrdemServicosException;
	
	/**
	 * Lista todas as OrdemServicos cadastradas
	 * @return
	 * @throws OrdemServicosException
	 */
	public List<OrdemServicos> listar() throws OrdemServicosException;

}
