package br.com.lucas.usuarios.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;

import br.com.lucas.usuarios.modelo.Situacao;
import br.com.lucas.usuarios.modelo.Usuario;

public class PopulaBanco {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();

		Situacao ativo = geraSituacao("ATIVO");
		em.persist(ativo);

		Situacao inativo = geraSituacao("INATIVO");
		em.persist(inativo);

		Usuario jose = geraUsuario("Lucas", "lucasb4rn", "123456", ativo);
		
		Usuario pedro = geraUsuario("Pedro", "pedroxxx", "4321", inativo);
		
		Usuario joao = geraUsuario("Joao", "joaobbb", "582169", ativo);
		
		Usuario amanda = geraUsuario("Amanda", "amanda2019", "94448", ativo);
		
		

		em.persist(jose);
		em.persist(pedro);
		em.persist(joao);
		em.persist(amanda);


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

	@SuppressWarnings("unused")
	private static Calendar parseData(String data) {
		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar;
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

}
