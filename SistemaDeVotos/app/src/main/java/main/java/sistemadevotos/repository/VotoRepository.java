package main.java.sistemadevotos.repository;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import main.java.sistemadevotos.model.TotalVotosRestaurante;
import main.java.sistemadevotos.model.Voto;

/**
 * Repositório responsável por gerenciar as operações de persistência da entidade {@link Voto}.
 * 
 * <p>Inclui métodos para votar, buscar votos e verificar votos por data.</p>
 */
public class VotoRepository {

    /**
     * Gerenciador de entidades responsável por realizar as operações no banco de dados.
     */
    private EntityManager em;

    /**
     * Construtor padrão que inicializa o {@link EntityManager}.
     */
    public VotoRepository() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("DB");
        em = factory.createEntityManager();
    }

    /**
     * Registra um voto no banco de dados.
     * 
     * @param voto O objeto {@link Voto} a ser persistido.
     */
    public void vota(Voto voto) {
        this.em.getTransaction().begin();
        this.em.merge(voto);
        this.em.getTransaction().commit();
    }

    /**
 * Busca a contagem total de votos agrupados por restaurante para o dia atual.
 * 
 * <p>O método utiliza uma consulta JPQL para filtrar votos apenas da data atual,
 * agrupá-los pelo nome do restaurante e ordená-los pela quantidade de votos em ordem
 * decrescente.</p>
 * 
 * @return Uma lista de {@link TotalVotosRestaurante} contendo:
 *         <ul>
 *           <li>O nome do restaurante.</li>
 *           <li>O total de votos registrados para o restaurante no dia atual.</li>
 *         </ul>
 *         Retorna {@code null} caso nenhum resultado seja encontrado.
 *         
 * @throws NoResultException Se a consulta não encontrar nenhum registro (capturado e tratado internamente).
 */
public List<TotalVotosRestaurante> buscar() {
    StringBuffer sb = new StringBuffer(100);
    sb.append("SELECT new main.java.sistemadevotos.model.TotalVotosRestaurante(e.nome, COUNT(a.id)) ");
    sb.append("FROM Voto a ");
    sb.append("INNER JOIN a.restaurante e ");
    sb.append("WHERE a.data = CURRENT_DATE "); 
    sb.append("GROUP BY e.nome ");
    sb.append("ORDER BY COUNT(a.id) DESC");

    TypedQuery<TotalVotosRestaurante> query = this.em.createQuery(sb.toString(), TotalVotosRestaurante.class);

    try {
        return query.getResultList();
    } catch (NoResultException e) {
        return null;
    }
}


    /**
     * Busca votos por uma data específica.
     * 
     * @param data A data para filtrar os votos.
     * @return Uma lista de {@link Voto} correspondente à data.
     */
    public List<Voto> buscarPorData(Calendar data) {
        return em.createQuery("FROM Voto v WHERE v.data = :data", Voto.class)
                 .setParameter("data", data, TemporalType.DATE)
                 .getResultList();
    }

    /**
     * Verifica se um funcionário votou em uma data específica.
     * 
     * @param funcionarioId O ID do funcionário.
     * @param data          A data a ser verificada.
     * @return {@code true} se o funcionário já votou na data, {@code false} caso contrário.
     */
    public boolean votoPorData(Integer funcionarioId, Calendar data) {
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(v) FROM Voto v WHERE v.funcionario.id = :id AND v.data = :data", Long.class);
        query.setParameter("id", funcionarioId);
        query.setParameter("data", data, TemporalType.DATE);
        return query.getSingleResult() > 0;
    }
}
