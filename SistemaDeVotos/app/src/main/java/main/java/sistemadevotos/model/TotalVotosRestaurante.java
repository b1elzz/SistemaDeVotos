package main.java.sistemadevotos.model;

/**
 * Representa a contagem total de votos de um restaurante.
 * 
 * <p>Esta classe é utilizada para exibir os resultados da votação agrupados
 * por restaurante, contendo o nome do restaurante e o número total de votos.</p>
 */
public class TotalVotosRestaurante {

    /**
     * Nome do restaurante.
     */
    private String nomeRestaurante;

    /**
     * Total de votos recebidos pelo restaurante.
     */
    private Long totalVotos;

    /**
     * Construtor da classe {@code TotalVotosRestaurante}.
     * 
     * @param nomeRestaurante O nome do restaurante.
     * @param totalVotos      O total de votos do restaurante.
     */
    public TotalVotosRestaurante(String nomeRestaurante, Long totalVotos) {
        this.nomeRestaurante = nomeRestaurante;
        this.totalVotos = totalVotos;
    }

    /**
     * Obtém o nome do restaurante.
     * 
     * @return O nome do restaurante.
     */
    public String getNomeRestaurante() {
        return nomeRestaurante;
    }

    /**
     * Define o nome do restaurante.
     * 
     * @param nomeRestaurante O nome do restaurante.
     */
    public void setNomeRestaurante(String nomeRestaurante) {
        this.nomeRestaurante = nomeRestaurante;
    }

    /**
     * Obtém o total de votos do restaurante.
     * 
     * @return O total de votos.
     */
    public Long getTotalVotos() {
        return totalVotos;
    }

    /**
     * Define o total de votos do restaurante.
     * 
     * @param totalVotos O total de votos.
     */
    public void setTotalVotos(Long totalVotos) {
        this.totalVotos = totalVotos;
    }

    /**
     * Retorna a representação em string do objeto {@code TotalVotosRestaurante}.
     * 
     * @return Uma string formatada contendo o nome do restaurante e o total de votos.
     */
    @Override
    public String toString() {
        return String.format("\nVotos do Restaurante:\n" +
                             "-------------------------------\n" +
                             "Nome do Restaurante: %s\n" +
                             "Total de Votos:       %d\n" +
                             "-------------------------------",
                             nomeRestaurante, totalVotos);
    }
}
