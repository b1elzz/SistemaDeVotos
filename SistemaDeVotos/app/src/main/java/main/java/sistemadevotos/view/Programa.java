package main.java.sistemadevotos.view;

import main.java.sistemadevotos.controller.ProgramaController;

/**
 * Classe principal que serve como ponto de entrada do sistema de votação.
 * 
 * <p>Esta classe inicializa o controlador principal {@link ProgramaController}
 * e chama o método responsável por executar o programa.</p>
 */
public class Programa {

    /**
     * Método principal (main) que inicializa o programa.
     * 
     * @param args Argumentos passados via linha de comando (não utilizados).
     */
    public static void main(String[] args) {
       
        ProgramaController programaController = new ProgramaController();

       
        programaController.executar();
    }
}
