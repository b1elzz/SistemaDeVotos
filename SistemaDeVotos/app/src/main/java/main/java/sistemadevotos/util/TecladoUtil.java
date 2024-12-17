package main.java.sistemadevotos.util;

import java.util.Scanner;

/**
 * Classe utilitária responsável por facilitar a entrada de dados via teclado.
 * 
 * <p>Fornece métodos para leitura de diferentes tipos de entrada, como strings e inteiros, 
 * além de métodos auxiliares como limpeza de buffer.</p>
 */
public class TecladoUtil {

    /**
     * Scanner utilizado para capturar entrada do teclado.
     */
    private Scanner teclado = new Scanner(System.in);

    /**
     * Lê uma string digitada pelo usuário.
     * 
     * @param mensagem Mensagem exibida ao usuário solicitando a entrada.
     * @return A string digitada pelo usuário.
     */
    public String lerString(String mensagem) {
        System.out.println(mensagem);
        return teclado.nextLine();
    }

    /**
     * Lê um número inteiro digitado pelo usuário.
     * 
     * @param mensagem Mensagem exibida ao usuário solicitando a entrada.
     * @return O número inteiro digitado pelo usuário.
     */
    public Integer lerInteiro(String mensagem) {
        System.out.println(mensagem);
        return teclado.nextInt();
    }

    /**
     * Limpa o buffer do teclado.
     * 
     * <p>Este método é útil para evitar problemas com entradas subsequentes após
     * a leitura de números.</p>
     * 
     * @return Uma string vazia, representando o buffer limpo.
     */
    public String limparBuffer() {
        return teclado.nextLine();
    }
}
