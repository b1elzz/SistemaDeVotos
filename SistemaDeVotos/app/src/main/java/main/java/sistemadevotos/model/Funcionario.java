package main.java.sistemadevotos.model;

import javax.persistence.*;

/**
 * Representa um funcionário no sistema de votação.
 * 
 * <p>Possui informações básicas do funcionário, como ID e nome. A entidade é 
 * persistida no banco de dados usando JPA.</p>
 */
@Entity
@Table(name = "funcionario")
public class Funcionario {

    /**
     * Identificador único do funcionário.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "restaurante_generator")
    @TableGenerator(name = "restaurante_generator", table = "chave", pkColumnName = "id",
                    valueColumnName = "valor", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    /**
     * Nome do funcionário.
     */
    @Column(name = "nome")
    private String nome;

    /**
     * Construtor padrão.
     */
    public Funcionario() {
    }

    /**
     * Construtor com parâmetros.
     * 
     * @param id   O ID do funcionário.
     * @param nome O nome do funcionário.
     */
    public Funcionario(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Funcionario other = (Funcionario) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("\nFuncionário:\n" +
                             "-------------------------------\n" +
                             "ID:      %d\n" +
                             "Nome:    %s\n" +
                             "-------------------------------", id, nome);
    }
}
