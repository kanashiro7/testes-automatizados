package org.example.testesautomatizados.steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.apache.groovy.json.internal.IO;
import org.example.testesautomatizados.model.ErrorMessageModel;
import org.example.testesautomatizados.service.CadastroEntregasService;
import org.junit.Assert;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class CadastroEntregaSteps {

    CadastroEntregasService cadastroEntregasService = new CadastroEntregasService();


    @Dado("que eu tenha os seguintes dados da entrega:")
    public void queEuTenhaOsSeguintesDadosDaEntrega(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            cadastroEntregasService.setFieldsDelivery(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de entregas")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeEntregas(String endPoint) {
        cadastroEntregasService.createDelivery(endPoint);
    }

    @Então("o status code da resposta deve ser {int}")
    public void oStatusCodeDaRespostaDeveSer(int statuscode) {
        Assert.assertEquals(statuscode, cadastroEntregasService.response.statusCode());
    }

    @E("o corpo de resposta de erro da api deve retornar a mensagem {string}")
    public void oCorpoDeRespostaDeErroDaApiDeveRetornarAMensagem(String message) {
        ErrorMessageModel errorMessageModel = cadastroEntregasService.gson.fromJson
                (cadastroEntregasService.response.jsonPath().prettify(), ErrorMessageModel.class);
        Assert.assertEquals(message, errorMessageModel, getClass());
    }

    @Dado("que eu recupere o ID da entrega criada no contexto")
    public void queEuRecupereOIDDaEntregaCriadaNoContexto() {
        cadastroEntregasService.retrieveIdDelivery();
    }

    @Quando("eu enviar a requisição com o ID para o endpoint {string} de deleção de entrega")
    public void euEnviarARequisiçãoComOIDParaOEndpointDeDeleçãoDeEntrega(String endPoint) {
        cadastroEntregasService.deleteDelivery(endPoint);
    }

    @E("que o arquivo de contrato esperado é o {string}")
    public void queOArquivoDeContratoEsperadoÉO(String contract) throws IOException {
        cadastroEntregasService.setContract(contract);
    }

    @Então("a resposta da requisição deve estar em conformidade com o contrato selecionado")
    public void aRespostaDaRequisiçãoDeveEstarEmConformidadeComOContratoSelecionado() {
    }
}

