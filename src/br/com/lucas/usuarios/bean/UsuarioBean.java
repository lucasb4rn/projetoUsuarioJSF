package br.com.lucas.usuarios.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import br.com.lucas.usuarios.dao.DAO;
import br.com.lucas.usuarios.modelo.Situacao;
import br.com.lucas.usuarios.modelo.Usuario;

@ManagedBean
@RequestScoped
public class UsuarioBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario = new Usuario();
	
	private List<Usuario> listaUsuarios;
	
	private Integer situacaoId;
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void gravar() {
		
		System.out.println("Gravando Usuario " + this.usuario.getNome());
		
		DAO<Usuario> dao = new DAO<Usuario>(Usuario.class);
		DAO<Situacao> daoSituacao = new DAO<Situacao>(Situacao.class);
		
			
			this.usuario.setSituacao(daoSituacao.buscaPorId(this.situacaoId));
			
			dao.adiciona(this.usuario);
			
			 FacesContext.getCurrentInstance().addMessage("cadastroUsuarios:feedback", new javax.faces.application.FacesMessage("Usuario cadastrado com Sucesso!!! "));
			
			this.listaUsuarios = dao.listaTodos();
			
			this.usuario = new Usuario();
			
	}
	
	
	public String altera() {
		
		System.out.println("Alterando Usuario " + this.usuario.getNome());
		
		DAO<Usuario> dao = new DAO<Usuario>(Usuario.class);
		
		DAO<Situacao> daoSituacao = new DAO<Situacao>(Situacao.class);
		
		this.usuario.setSituacao(daoSituacao.buscaPorId(this.situacaoId));
		
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("param1", "Usuario " + this.usuario.getNome() + " alterado com Sucesso!!!");
		
		/*FacesContext.getCurrentInstance().addMessage("cadastroUsuarios:feedback", new javax.faces.application.FacesMessage("Usuario alterado com Sucesso!!! "));*/
		
		dao.atualiza(this.usuario);
		
		return "listaUsuario?faces-redirect=true";
		
	}
	
	
	public void remove(Usuario usuario) {
		
		DAO<Usuario> dao = new DAO<Usuario>(Usuario.class);
		
		dao.remove(usuario);
		
		this.listaUsuarios = dao.listaTodos();
		
	}
	
	public String altera(Usuario usuario) {
		
		this.usuario = usuario;
		this.situacaoId = usuario.getSituacao().getId();
		
		return "alteraUsuario";
		
		
	}
	
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		
		DAO<Usuario> dao = new DAO<Usuario>(Usuario.class);
			
			if(this.listaUsuarios == null) {
				this.listaUsuarios = dao.listaTodos();			
			}
			
			return listaUsuarios;
		}
	
	
		public List<Situacao> getSituacao() {
			return new DAO<Situacao>(Situacao.class).listaTodos();
		}

		public Integer getSituacaoId() {
			return situacaoId;
		}

		public void setSituacaoId(Integer situacaoId) {
			this.situacaoId = situacaoId;
		}
		
		
		
		
		
		
    }


	



