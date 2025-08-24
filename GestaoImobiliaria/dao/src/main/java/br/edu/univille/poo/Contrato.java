package br.edu.univille.poo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Contrato {
    private long id;
    private long idCliente;
    private long idImovel;
    private BigDecimal valor;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public long getIdImovel() {
        return idImovel;
    }

    public void setIdImovel(long idImovel) {
        this.idImovel = idImovel;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }
}



