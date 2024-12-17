package main.java.sistemadevotos.model;

import javax.persistence.*;

/**
 * Representa um restaurante no sistema de votação.
 * 
 * <p>Contém informações básicas, como ID e nome, e é mapeada para a tabela 
 * "restaurante" no banco de dados.</p>
 */
@Entity
@Table(name = "restaurante")
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "restaurante_generator")
    @TableGenerator(name = "restaurante_generator", table = "chave", pkColumnName = "id",
                    valueColumnName = "valor", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    public Restaurante() {
    }

    public Restaurante(Integer id, String nome) {
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
        Restaurante other = (Restaurante) obj;
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
        return String.format("\nRestaurante:\n" +
                             "-------------------------------\n" +
                             "ID:      %d\n" +
                             "Nome:    %s\n" +
                             "-------------------------------", id, nome);
    }
    
}
