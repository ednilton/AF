package br.pucgoias.af.controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.pucgoias.af.controle.OrdemServicosBean;
import br.pucgoias.os.entidade.OrdemServicos;
import br.pucgoias.os.negocio.OrdemServicosService;
import br.pucgoias.util.OrdemServicosException;

/**
 * Classe que controla as requisicoes do cliente web
 * @author Edileizer
 *
 */

@ManagedBean(name="ordemServicosController")
@RequestScoped
@Controller
public class OrdemServicosController {
	
	@Autowired
	private OrdemServicosBean ordemServicosBean;
	@Autowired
	private List<OrdemServicosBean> listaOrdemServicosBean;
	@Autowired
	private OrdemServicosService ordemServicosService;

	/**
	 * Construtor da classe de ordem de servicos
	 */
	@SuppressWarnings("unchecked")
	public OrdemServicosController(){
		ordemServicosBean = new OrdemServicosBean();
	}
	
	/**
	 * Inclui uma ordem de servicos na base de dados
	 * @return
	 */
	public String incluir() {
		try {
			
			OrdemServicos ordemServicos = new OrdemServicos();
			
			//preenche os dados da tela no objeto persistente
			ordemServicos.setIdOS(ordemServicosBean.getIdOS());
			ordemServicos.setCliente(ordemServicosBean.getCliente());
			ordemServicos.setEndereco(ordemServicosBean.getEndereco());
			ordemServicos.setTelefone(ordemServicosBean.getTelefone());
			ordemServicos.setDataEntrada(ordemServicosBean.getDataEntrada());
			ordemServicos.setValor(ordemServicosBean.getValor());
		
			getOrdemServicosService().incluir(ordemServicos);
			
			return "sucesso";
		}
		catch (Exception e) {
			String msg = "Inclusão não realizada. Motivo: " + ((e instanceof OrdemServicosException ? ((OrdemServicosException)e).getEx().getMessage():""));
			FacesMessage message = new FacesMessage(msg);
			this.getFacesContext().addMessage("formulario", message);
			return "falha";
		}
	}
	
	/**
	 * Lista as ordem de servicos cadastradas
	 * @return
	 */
	public String listar() {
		try {
			List<OrdemServicos> listaOrdemServicos = getOrdemServicosService().listar();
			
			if(listaOrdemServicos==null || listaOrdemServicos.size()==0){
				FacesMessage message = new FacesMessage("Nenhum registro encontrado.");
				this.getFacesContext().addMessage("formulario", message);
			}

			//preeche a lista de ordem de servicos da tela
			listaOrdemServicosBean = new ArrayList<OrdemServicosBean>();
			for (OrdemServicos ordemServicos : listaOrdemServicos) {
				OrdemServicosBean ordemServicosBean = new OrdemServicosBean();
				ordemServicosBean.setIdOS(ordemServicos.getIdOS());
				ordemServicosBean.setCliente(ordemServicos.getCliente());
				ordemServicosBean.setEndereco(ordemServicos.getEndereco());
				ordemServicosBean.setTelefone(ordemServicos.getTelefone());
				ordemServicosBean.setDataEntrada(ordemServicos.getDataEntrada());
				ordemServicosBean.setValor(ordemServicos.getValor());
				listaOrdemServicosBean.add(ordemServicosBean);
			}

			return "listar";
		}
		catch (Exception e) {
			String msg = "Listagem nao realizada. Motivo: " + ((e instanceof OrdemServicosException ? ((OrdemServicosException)e).getEx().getMessage():""));
			FacesMessage message = new FacesMessage(msg);
			this.getFacesContext().addMessage("formulario", message);
			return "falha";
		}
	}
	
	/**
	 * Consulta uma ordem de servicos cadastrada
	 * @return
	 */
	public String consultar() {
		try{

			String idOS = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idOS");
			
			OrdemServicos ordemServicos = getOrdemServicosService().consultar(Integer.parseInt(idOS));

			if(ordemServicos==null || ordemServicos.getIdOS()==null){
				FacesMessage message = new FacesMessage("Nenhum registro encontrado.");
				this.getFacesContext().addMessage("formulario", message);
				return "listar";
			}

			//preenche os dados do bean da tela
			ordemServicosBean.setIdOS(ordemServicos.getIdOS());
			ordemServicosBean.setCliente(ordemServicos.getCliente());
			ordemServicosBean.setEndereco(ordemServicos.getEndereco());
			ordemServicosBean.setTelefone(ordemServicos.getTelefone());
			ordemServicosBean.setDataEntrada(ordemServicos.getDataEntrada());
			ordemServicosBean.setValor(ordemServicos.getValor());
			
			return "editar";
		}
		catch (Exception e) {
			String msg = "Consulta nao realizada. Motivo: " + ((e instanceof OrdemServicosException ? ((OrdemServicosException)e).getEx().getMessage():""));
			FacesMessage message = new FacesMessage(msg);
			this.getFacesContext().addMessage("formulario", message);
			return "falha";
		}
	}

	/**
	 * Cria uma nova ordem de servicos
	 * @return
	 */
	public String criar() {
		try{

			ordemServicosBean = new OrdemServicosBean();
			
			return "criar";
		}
		catch (Exception e) {
			String msg = "Cria��o n�o realizada. Motivo: " + ((e instanceof OrdemServicosException ? ((OrdemServicosException)e).getEx().getMessage():""));
			FacesMessage message = new FacesMessage(msg);
			this.getFacesContext().addMessage("formulario", message);
			return "falha";
		}
	}
	
	/**
	 * Exclui uma ordem de servicos cadastrada
	 * @return
	 */
	public String excluir() {
		try{

			HtmlInputHidden idOS = (HtmlInputHidden) this.getFacesContext().getViewRoot().findComponent("formulario:idOS");
			
			OrdemServicos ordemServicos = getOrdemServicosService().consultar((Integer)idOS.getValue());

			if(ordemServicos==null || ordemServicos.getIdOS()==null){
				FacesMessage message = new FacesMessage("Nenhum registro encontrado.");
				this.getFacesContext().addMessage("formulario", message);
				return "listar";
			}

			getOrdemServicosService().excluir(ordemServicos.getIdOS());
			
			return "sucesso";
		}
		catch (Exception e) {
			String msg = "Exclusao nao realizada. Motivo: " + ((e instanceof OrdemServicosException ? ((OrdemServicosException)e).getEx().getMessage():""));
			FacesMessage message = new FacesMessage(msg);
			this.getFacesContext().addMessage("formulario", message);
			return "falha";
		}
	}
	
	/**
	 * Altera uma ordem de servicos cadastrada
	 * @return
	 */
	public String alterar() {
		try{
			
			OrdemServicos ordemServicos = getOrdemServicosService().consultar(ordemServicosBean.getIdOS());
			
			if(ordemServicos==null || ordemServicos.getIdOS()==null){
				FacesMessage message = new FacesMessage("Nenhum registro encontrado.");
				this.getFacesContext().addMessage("formulario", message);
				return "listar";
			}

			//preenche os dados da tela no objeto persistente
			ordemServicos.setCliente(ordemServicosBean.getCliente());
			ordemServicos.setEndereco(ordemServicosBean.getEndereco());
			ordemServicos.setTelefone(ordemServicosBean.getTelefone());
			ordemServicos.setDataEntrada(ordemServicosBean.getDataEntrada());
			ordemServicos.setValor(ordemServicosBean.getValor());
			
			getOrdemServicosService().alterar(ordemServicos);
			return "sucesso";
			
		}
		catch (Exception e) {
			String msg = "Altera��o n�o realizada. Motivo: " + ((e instanceof OrdemServicosException ? ((OrdemServicosException)e).getEx().getMessage():""));
			FacesMessage message = new FacesMessage(msg);
			this.getFacesContext().addMessage("formulario", message);
			return "falha";
		}
	}
	
	public OrdemServicosService getOrdemServicosService() {
		return ordemServicosService;
	}

	public void setOrdemServicosService(OrdemServicosService ordemServicosService) {
		this.ordemServicosService = ordemServicosService;
	}
	
	private FacesContext getFacesContext(){
		return FacesContext.getCurrentInstance();
	}
	
	private Object getSession(String variavel){
		return this.getFacesContext().getExternalContext().getSessionMap().get(variavel);
	}
	
	private void setSession(String variavel, Object objeto){
		this.getFacesContext().getExternalContext().getSessionMap().put(variavel, objeto);
	}
	
	public OrdemServicosBean getOrdemServicosBean() {
		return ordemServicosBean;
	}

	public void setOrdemServicosBean(OrdemServicosBean ordemServicosBean) {
		this.ordemServicosBean = ordemServicosBean;
	}

	public List<OrdemServicosBean> getListaOrdemServicosBean() {
		return listaOrdemServicosBean;
	}

	public void setListaOrdemServicosBean(List<OrdemServicosBean> listaOrdemServicosBean) {
		this.listaOrdemServicosBean = listaOrdemServicosBean;
	}
}
