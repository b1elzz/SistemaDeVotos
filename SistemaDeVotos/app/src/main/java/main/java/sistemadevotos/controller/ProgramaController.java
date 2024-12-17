package main.java.sistemadevotos.controller;

import java.util.List;

import main.java.sistemadevotos.exceptions.FuncionarioJaVotouHojeException;
import main.java.sistemadevotos.model.TotalVotosRestaurante;
import main.java.sistemadevotos.repository.FuncionarioRepository;
import main.java.sistemadevotos.repository.RestauranteRepository;
import main.java.sistemadevotos.util.TecladoUtil;

/**
 * Controlador principal responsável por gerenciar o fluxo do sistema de votação.
 * 
 * <p>Esta classe implementa o menu principal, permitindo que o usuário interaja
 * com o sistema, cadastrando votos, apurando votações do dia, e listando
 * os dados de restaurantes e funcionários.</p>
 */
public class ProgramaController {

    /**
     * Controlador responsável pelas operações relacionadas aos votos.
     */
    private VotoController votoController;

    /**
     * Repositório responsável pela manipulação dos dados dos funcionários.
     */
    private FuncionarioRepository funcionarioRepository;

    /**
     * Repositório responsável pela manipulação dos dados dos restaurantes.
     */
    private RestauranteRepository restauranteRepository;

    /**
     * Utilitário responsável por capturar entradas do teclado.
     */
    private TecladoUtil tecladoUtil;

    /**
     * Construtor padrão que inicializa os controladores, repositórios e utilitários.
     */
    public ProgramaController() {
        this.votoController = new VotoController();
        this.funcionarioRepository = new FuncionarioRepository();
        this.restauranteRepository = new RestauranteRepository();
        this.tecladoUtil = new TecladoUtil();
    }

    /**
     * Executa o menu principal do sistema, processando as opções escolhidas pelo usuário.
     */
    public void executar() {
        int opcao;

        do {
            opcao = exibirMenu();
            switch (opcao) {
                case 1:
                    cadastrarVoto();
                    break;
                case 2:
                    apurarVotacao();
                    break;
                case 3:
                    System.out.println(restauranteRepository.buscar());
                    break;
                case 4:
                    System.out.println(funcionarioRepository.buscar());
                    break;
                case 5:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } while (opcao != 5);
    }

    /**
     * Exibe o menu principal e captura a opção escolhida pelo usuário.
     * 
     * @return A opção escolhida pelo usuário.
     */
    private int exibirMenu() {
        System.out.println("Menu:");
        System.out.println("1. Cadastrar Voto");
        System.out.println("2. Apurar Votação do Dia");
        System.out.println("3. Listar Restaurantes");
        System.out.println("4. Listar Funcionarios");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");
        return tecladoUtil.lerInteiro("");
    }

    /**
     * Solicita ao usuário os dados necessários e realiza o cadastro de um voto.
     * 
     * <p>Captura o nome do funcionário e o nome do restaurante, chamando o
     * controlador de votos para registrar a votação.</p>
     */
    private void cadastrarVoto() {
        tecladoUtil.limparBuffer();
        String nomeFuncionario = tecladoUtil.lerString("Digite o nome do funcionário: ").toUpperCase();
        String nomeRestaurante = tecladoUtil.lerString("Digite o nome do restaurante: ").toUpperCase();

        try {
            votoController.cadastrarVoto(nomeFuncionario, nomeRestaurante);
            System.out.println("Voto registrado com sucesso.");
        } catch (FuncionarioJaVotouHojeException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    /**
     * Apura os votos registrados na data atual e exibe o resultado da votação.
     * 
     * <p>Se nenhum voto foi registrado no dia, uma mensagem apropriada é exibida.</p>
     */
    private void apurarVotacao() {
        List<TotalVotosRestaurante> votos = votoController.apurarVotos();
        if (votos.isEmpty()) {
            System.out.println("Nenhum voto registrado hoje.");
        } else {
            System.out.println("Resultado da votação de hoje:");
            System.out.println(votoController.apurarVotos());
        }
    }
}
