package main.java.sistemadevotos.model;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Representa um voto no sistema de votação.
 * 
 * <p>Esta entidade associa um funcionário a um restaurante em uma determinada data.</p>
 */
@Entity
@Table(name = "voto")
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "restaurante_generator")
    @TableGenerator(name = "restaurante_generator", table = "chave", pkColumnName = "id",
                    valueColumnName = "valor", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "data")
    private Calendar data;

    @ManyToOne
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "id_restaurante")
    private Restaurante restaurante;

    public Voto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }


    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
            this.funcionario = funcionario;
    }


    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        result = prime * result + ((funcionario == null) ? 0 : funcionario.hashCode());
        result = prime * result + ((restaurante == null) ? 0 : restaurante.hashCode());
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
        Voto other = (Voto) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (data == null) {
            if (other.data != null)
                return false;
        } else if (!data.equals(other.data))
            return false;
        if (funcionario == null) {
            if (other.funcionario != null)
                return false;
        } else if (!funcionario.equals(other.funcionario))
            return false;
        if (restaurante == null) {
            if (other.restaurante != null)
                return false;
        } else if (!restaurante.equals(other.restaurante))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Voto: " + " ID > " + id + " Funcionario > " + funcionario + " Restaurante > " + restaurante + " Data > " + data;
    }
}
