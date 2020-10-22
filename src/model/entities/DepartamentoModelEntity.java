package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class DepartamentoModelEntity implements Serializable {

    private static final long SerialVersionUID = 1L;

    private Integer id;
    private String nome;

    public DepartamentoModelEntity() {
    }

    public DepartamentoModelEntity(Integer id, String nome) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DepartamentoModelEntity)) return false;
        DepartamentoModelEntity that = (DepartamentoModelEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }

    @Override
    public String toString() {
        return "DepartamentoModelEntity{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
