package br.pucgoias.af.util;

/**
 * Classe que encapsula as excecoes da aplicacao Ordem de Servicos
 * @author Edileizer
 *
 */
public class OrdemServicosException extends Exception {
	
	private static final long serialVersionUID = 1189188521388183949L;
	private Exception ex;
	private String msg;

	public OrdemServicosException(Exception e){
		ex = e;
		msg = e.getMessage();
	}

	public OrdemServicosException(Exception e, String mensagem){
		e.printStackTrace();
		ex = e;
		msg = mensagem;
	}

	public Exception getEx() {
		return ex;
	}

	public String getMsg() {
		return msg;
	}
	
}
