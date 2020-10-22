package model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class VendasModelEntity implements Serializable {

    public static final long SerialVersionUID = 1L;

    private Integer id;
    private String nome;
    private String email;
    private Date dataNascimento;
    private Double baseSalarial;
    private DepartamentoModelEntity departamentoModelEntity;

    public VendasModelEntity() {
    }

    public VendasModelEntity(Integer id, String nome, String email, Date dataNascimento, Double baseSalarial, DepartamentoModelEntity departamentoModelEntity) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.baseSalarial = baseSalarial;
        this.departamentoModelEntity = departamentoModelEntity;
    }

    public static long getSerialVersionUID() {
        return SerialVersionUID;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Double getBaseSalarial() {
        return baseSalarial;
    }

    public void setBaseSalarial(Double baseSalarial) {
        this.baseSalarial = baseSalarial;
    }

    public DepartamentoModelEntity getDepartamentoModelEntity() {
        return departamentoModelEntity;
    }

    public void setDepartamentoModelEntity(DepartamentoModelEntity departamentoModelEntity) {
        this.departamentoModelEntity = departamentoModelEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VendasModelEntity)) return false;
        VendasModelEntity that = (VendasModelEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "VendasModelEntity{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", baseSalarial=" + baseSalarial +
                ", departamentoModelEntity=" + departamentoModelEntity +
                '}';
    }
}
