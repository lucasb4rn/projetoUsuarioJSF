package br.com.lucas.usuarios.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.lucas.usuarios.dao.DAO;
import br.com.lucas.usuarios.modelo.Situacao;

@ManagedBean
@RequestScoped
public class SituacaoBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Situacao situacao = new Situacao();

	public Situacao getSituacao() {
		
		return situacao;
		
	}

	public void gravar() {
		
		System.out.println("Gravando situacao " + this.situacao.getDescricao());

		new DAO<Situacao>(Situacao.class).adiciona(this.situacao);

		this.situacao = new Situacao();
	}


}
