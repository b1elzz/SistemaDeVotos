package main.java.sistemadevotos.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import main.java.sistemadevotos.model.Restaurante;

/**
 * Repositório responsável por gerenciar as operações de persistência da entidade {@link Restaurante}.
 * 
 * <p>Inclui métodos para inserir, buscar por ID, buscar por nome e listar todos os restaurantes.</p>
 */
public class RestauranteRepository {

    /**
     * Gerenciador de entidades responsável por realizar as operações no banco de dados.
     */
    private EntityManager em;

    /**
     * Construtor padrão que inicializa o {@link EntityManager}.
     */
    public RestauranteRepository() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("DB");
        em = factory.createEntityManager();
    }

    /**
     * Insere ou atualiza um restaurante no banco de dados.
     * 
     * @param restaurante O objeto {@link Restaurante} a ser persistido.
     */
    public void inserirRestaurante(Restaurante restaurante) {
        this.em.getTransaction().begin();
        if (restaurante.getId() != null) {
            this.em.merge(restaurante); // Atualiza caso o ID já exista
        } else {
            this.em.persist(restaurante); // Persiste um novo restaurante
        }
        this.em.getTransaction().commit();
    }

    /**
     * Busca um restaurante pelo seu ID.
     * 
     * @param id O ID do restaurante.
     * @return O objeto {@link Restaurante} correspondente ao ID ou {@code null} se não encontrado.
     */
    public Restaurante buscarPorId(Long id) {
        return em.find(Restaurante.class, id);
    }

    /**
     * Busca um restaurante pelo seu nome.
     * 
     * @param nome O nome do restaurante.
     * @return O objeto {@link Restaurante} correspondente ao nome ou {@code null} se não encontrado.
     */
    public Restaurante buscarPorNome(String nome) {
        try {
            return em.createQuery("FROM Restaurante r WHERE r.nome = :nome", Restaurante.class)
                     .setParameter("nome", nome.toUpperCase())
                     .getSingleResult();
        } catch (Exception e) {
            return null; 
        }
    }

    /**
     * Busca todos os restaurantes ordenados pelo ID.
     * 
     * @return Uma lista de {@link Restaurante} contendo todos os restaurantes cadastrados.
     */
    public List<Restaurante> buscar() {
        TypedQuery<Restaurante> query = this.em.createQuery(
                "SELECT r FROM Restaurante r ORDER BY r.id", Restaurante.class);
        return query.getResultList();
    }
}
