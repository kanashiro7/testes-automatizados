package org.example.testesautomatizados.model;


import com.google.gson.annotations.Expose;
import lombok.Data;

/* Reduz o boilerplate do código*/
@Data
public class EntregaModel {
    @Expose(serialize = false)
    private int numeroEntrega;
    @Expose
    private int numeroPedido;
    @Expose
    private String nomeEntregador;
    @Expose
    private String statusEntrega;
    @Expose
    private String dataEntrega;
}
