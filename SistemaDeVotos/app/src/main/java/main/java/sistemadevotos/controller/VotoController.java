package main.java.sistemadevotos.controller;

import java.util.Calendar;
import java.util.List;

import main.java.sistemadevotos.exceptions.FuncionarioJaVotouHojeException;
import main.java.sistemadevotos.model.Funcionario;
import main.java.sistemadevotos.model.Restaurante;
import main.java.sistemadevotos.model.TotalVotosRestaurante;
import main.java.sistemadevotos.model.Voto;
import main.java.sistemadevotos.repository.FuncionarioRepository;
import main.java.sistemadevotos.repository.RestauranteRepository;
import main.java.sistemadevotos.repository.VotoRepository;

/**
 * Controlador responsável por gerenciar as operações relacionadas aos votos.
 * 
 * <p>Inclui funcionalidades como cadastro de votos, apuração de votação,
 * e listagem de dados relacionados a funcionários e restaurantes.</p>
 */
public class VotoController {

    private VotoRepository votoRepository;
    private FuncionarioRepository funcionarioRepository;
    private RestauranteRepository restauranteRepository;

    /**
     * Construtor padrão que inicializa os repositórios necessários.
     */
    public VotoController() {
        this.votoRepository = new VotoRepository();
        this.funcionarioRepository = new FuncionarioRepository();
        this.restauranteRepository = new RestauranteRepository();
    }

    /**
     * Cadastra um voto associando um funcionário e um restaurante.
     * 
     * @param nomeFuncionario O nome do funcionário que votou.
     * @param nomeRestaurante O nome do restaurante votado.
     * @throws FuncionarioJaVotouHojeException Caso o funcionário já tenha votado no dia.
     * @throws Exception Em caso de outros erros inesperados.
     */
    public void cadastrarVoto(String nomeFuncionario, String nomeRestaurante) throws Exception {
        Restaurante restaurante = restauranteRepository.buscarPorNome(nomeRestaurante);
        if (restaurante == null) {
            restaurante = new Restaurante();
            restaurante.setNome(nomeRestaurante);
            restauranteRepository.inserirRestaurante(restaurante);
        }

        Funcionario funcionario = funcionarioRepository.buscarPorNome(nomeFuncionario);
        if (funcionario == null) {
            funcionario = new Funcionario();
            funcionario.setNome(nomeFuncionario);
            funcionarioRepository.inserirFuncionario(funcionario);
        }

        Calendar data = Calendar.getInstance();
        if (votoRepository.votoPorData(funcionario.getId(), data)) {
            throw new FuncionarioJaVotouHojeException("Funcionário já votou hoje.");
        }

        Voto voto = new Voto();
        voto.setData(data);
        voto.setFuncionario(funcionario);
        voto.setRestaurante(restaurante);
        votoRepository.vota(voto);
    }

    /**
     * Apura os votos registrados no sistema e retorna a contagem total.
     * 
     * @return Uma lista de {@link TotalVotosRestaurante} com os votos apurados.
     */
    public List<TotalVotosRestaurante> apurarVotos() {
        return votoRepository.buscar();
    }

    /**
     * Lista todos os restaurantes cadastrados no sistema.
     */
    public void listarRestaurantes() {
        List<Restaurante> restaurantes = restauranteRepository.buscar();
        if (restaurantes.isEmpty()) {
            System.out.println("Nenhum restaurante cadastrado.");
        } else {
            restaurantes.forEach(System.out::println);
        }
    }

    /**
     * Lista todos os funcionários cadastrados no sistema.
     */
    public void listarFuncionarios() {
        List<Funcionario> funcionarios = funcionarioRepository.buscar();
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
        } else {
            funcionarios.forEach(System.out::println);
        }
    }
}
