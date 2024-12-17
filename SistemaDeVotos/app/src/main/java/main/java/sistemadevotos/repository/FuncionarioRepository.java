package main.java.sistemadevotos.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import main.java.sistemadevotos.model.Funcionario;

/**
 * Repositório responsável por gerenciar as operações de persistência da entidade {@link Funcionario}.
 * 
 * <p>Esta classe utiliza JPA para realizar operações básicas no banco de dados, como inserir, buscar por ID, 
 * buscar por nome e listar todos os funcionários.</p>
 */
public class FuncionarioRepository {

    /**
     * Gerenciador de entidades responsável por realizar as operações no banco de dados.
     */
    private EntityManager em;

    /**
     * Construtor padrão que inicializa o {@link EntityManager}.
     */
    public FuncionarioRepository() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("DB");
        em = factory.createEntityManager();
    }

    /**
     * Insere ou atualiza um funcionário no banco de dados.
     * 
     * @param funcionario O objeto {@link Funcionario} a ser persistido.
     */
    public void inserirFuncionario(Funcionario funcionario) {
        this.em.getTransaction().begin();
        if (funcionario.getId() != null) {
            this.em.merge(funcionario); 
        } else {
            this.em.persist(funcionario); 
        }
        this.em.getTransaction().commit();
    }

    /**
     * Busca um funcionário pelo seu ID.
     * 
     * @param id O ID do funcionário.
     * @return O objeto {@link Funcionario} correspondente ao ID ou {@code null} se não encontrado.
     */
    public Funcionario buscarPorId(Long id) {
        return em.find(Funcionario.class, id);
    }

    /**
     * Busca um funcionário pelo seu nome.
     * 
     * @param nome O nome do funcionário.
     * @return O objeto {@link Funcionario} correspondente ao nome ou {@code null} se não encontrado.
     */
    public Funcionario buscarPorNome(String nome) {
        try {
            return em.createQuery("FROM Funcionario f WHERE f.nome = :nome", Funcionario.class)
                     .setParameter("nome", nome.toUpperCase())
                     .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Busca todos os funcionários ordenados pelo ID em ordem ascendente.
     * 
     * @return Uma lista de {@link Funcionario} contendo todos os funcionários cadastrados.
     */
    public List<Funcionario> buscar() {
        TypedQuery<Funcionario> query = this.em.createQuery(
                "SELECT f FROM Funcionario f ORDER BY f.id ASC", Funcionario.class);
        return query.getResultList();
    }
}
