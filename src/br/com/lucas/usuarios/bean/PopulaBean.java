package br.com.lucas.usuarios.bean;

import java.util.GregorianCalendar;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;

import br.com.lucas.usuarios.dao.DAO;
import br.com.lucas.usuarios.dao.JPAUtil;
import br.com.lucas.usuarios.modelo.Situacao;
import br.com.lucas.usuarios.modelo.Usuario;

@ManagedBean
public class PopulaBean {
	
	public void criaDados() {
		
		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();

		DAO<Situacao> dao = new DAO<Situacao>(Situacao.class);
		
		
		Situacao buscaPorIdAtivo = dao.buscaPorId(1);
		Situacao buscaPorIdInativo = dao.buscaPorId(2);
		
		Situacao ativo = geraSituacao("ATIVO");
		Situacao inativo = geraSituacao("INATIVO");
		
		
		if(buscaPorIdAtivo == null && buscaPorIdInativo == null) {
			
			em.persist(inativo);
			em.persist(ativo);
			
			Usuario jose = geraUsuario("Lucas", "lucasb4rn", "123456", ativo);
			Usuario matheus = geraUsuario("matheus", "matheusx1", "123456", inativo);
			Usuario gabriel = geraUsuario("gabriel", "gabrielx1", "aaaaaa", ativo);
			Usuario marcos = geraUsuario("marcos", "marcosx1", "aaaaaaa", inativo);
			Usuario rafael = geraUsuario("rafael", "rafaelx1", "bbbbbbbb", ativo);
			Usuario pedro = geraUsuario("Pedro", "pedroxxx", "4321", inativo);
			Usuario joao = geraUsuario("Joao", "joaobbb", "582169", ativo);
			Usuario amanda = geraUsuario("Amanda", "amanda2019", "94448", ativo);
			Usuario pedro1 = geraUsuario("Pedro", "pedroxxx", "4321", inativo);
			Usuario joao1 = geraUsuario("Joao", "joaobbb", "582169", ativo);
			Usuario amand1 = geraUsuario("Amanda", "amanda2019", "94448", ativo);
			Usuario gabrie3 = geraUsuario("gabrie3", "gabrielx1", "aaaaaa", ativo);
			Usuario marcos3 = geraUsuario("marcos3", "marcosx1", "aaaaaaa", inativo);
			Usuario marcos2 = geraUsuario("marcos3", "marcosx1", "aaaaaaa", inativo);
			
			em.persist(jose);
			em.persist(pedro);
			em.persist(joao);
			em.persist(amanda);
			em.persist(matheus);
			em.persist(gabriel);
			em.persist(rafael);
			em.persist(marcos);
			em.persist(pedro1);
			em.persist(joao1);
			em.persist(amand1);
			em.persist(gabrie3);
			em.persist(marcos3);
			em.persist(marcos2);
			
			
		} else {
			
			Usuario jose = geraUsuario("Lucas", "lucasb4rn", "123456", buscaPorIdAtivo);
			Usuario matheus = geraUsuario("matheus", "matheusx1", "123456", buscaPorIdAtivo);
			Usuario gabriel = geraUsuario("gabriel", "gabrielx1", "aaaaaa", buscaPorIdAtivo);
			Usuario marcos = geraUsuario("marcos", "marcosx1", "aaaaaaa", buscaPorIdInativo);
			Usuario rafael = geraUsuario("rafael", "rafaelx1", "bbbbbbbb", buscaPorIdInativo);
			Usuario pedro = geraUsuario("Pedro", "pedroxxx", "4321", buscaPorIdInativo);
			Usuario joao = geraUsuario("Joao", "joaobbb", "582169", buscaPorIdInativo);
			Usuario amanda = geraUsuario("Amanda", "amanda2019", "94448", buscaPorIdInativo);
			Usuario pedro1 = geraUsuario("Pedro", "pedroxxx", "4321", buscaPorIdInativo);
			Usuario joao1 = geraUsuario("Joao", "joaobbb", "582169", buscaPorIdAtivo);
			Usuario amand1 = geraUsuario("Amanda", "amanda2019", "94448", buscaPorIdAtivo);
			Usuario gabrie3 = geraUsuario("gabrie3", "gabrielx1", "aaaaaa", buscaPorIdAtivo);
			Usuario marcos3 = geraUsuario("marcos3", "marcosx1", "aaaaaaa", buscaPorIdInativo);
			Usuario marcos2 = geraUsuario("marcos3", "marcosx1", "aaaaaaa", buscaPorIdInativo);
			
			
			em.persist(jose);
			em.persist(pedro);
			em.persist(joao);
			em.persist(amanda);
			em.persist(matheus);
			em.persist(gabriel);
			em.persist(rafael);
			em.persist(marcos);
			em.persist(pedro1);
			em.persist(joao1);
			em.persist(amand1);
			em.persist(gabrie3);
			em.persist(marcos3);
			em.persist(marcos2);
			
			
		}
		

		em.getTransaction().commit();
		em.close();
		
	}
	

	private static Situacao geraSituacao(String descricao) {
		Situacao situacao = new Situacao();
		situacao.setDescricao(descricao);
		return situacao;
	}

	private static Usuario geraUsuario(String nome, String login, String senha, Situacao situacao) {
		
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setLogin(login);
		usuario.setSenha(senha);
		usuario.setDataCadastro(new GregorianCalendar().getInstance());
		usuario.setSituacao(situacao);
		return usuario;
	}



}