package com.jpql.sistemausuarios.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import java.util.Objects;

@Entity
public class Configuracao {

    @Id
    private Integer id;

    @MapsId
    @OneToOne
    private Usuario usuario;

    private boolean receberNotificacoes;

    private boolean encerrarNotificacoes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isReceberNotificacoes() {
        return receberNotificacoes;
    }

    public void setReceberNotificacoes(boolean receberNotificacoes) {
        this.receberNotificacoes = receberNotificacoes;
    }

    public boolean isEncerrarNotificacoes() {
        return encerrarNotificacoes;
    }

    public void setEncerrarNotificacoes(boolean encerrarNotificacoes) {
        this.encerrarNotificacoes = encerrarNotificacoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Configuracao that = (Configuracao) o;
        return receberNotificacoes == that.receberNotificacoes && encerrarNotificacoes == that.encerrarNotificacoes && Objects.equals(id, that.id) && Objects.equals(usuario, that.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usuario, receberNotificacoes, encerrarNotificacoes);
    }
}
