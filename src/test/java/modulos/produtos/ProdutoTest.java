package modulos.produtos;

import dataFactory.ProdutoDataFactory;
import dataFactory.UsuarioDataFactory;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.ComponentePojo;
import pojo.ProdutoPojo;
import pojo.UsuarioPojo;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Testes de API Rest do modulo de produto")
public class ProdutoTest {
    private String token;

    @BeforeEach
    public void beforeEach(){
        //Configurando os dados da API rest da lojinha
        baseURI = "http://165.227.93.41";
        //port =8080
        basePath = "/lojinha-bugada";


        //obter o token do usuario admin
        //given: dado que eu envie tais dados
        this.token = given()
                    .contentType(ContentType.JSON)
                    //.body("{\n" +
                    //        "  \"usuarioLogin\": \"admin\",\n" +
                    //        "  \"usuarioSenha\": \"admin\"\n" +
                    //        "}")
                    .body(UsuarioDataFactory.criarUsuarioAdmin())
                //quando eu fizer um post
                .when()
                    .post("/v2/login")
                //entao extraia o que esta dentro de data, detro de token
                .then()
                    .extract()
                        .path("data.token");

        //System.out.println(token);

    }

    @Test
    @DisplayName("validar os valores proibidos do limites do produto 0.00 nao é permitido")
    public void testValidarLimitesProibidosValorProdutozerado() {

        //testar inserir um produto com valor 0.00 e validar que a mensagem de erro foi apresentada
        //e o status code retornado foi 422



        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutoDataFactory.criarProdutoComumComValorIgualA(0.00))
                .when()
                .post("/v2/produtos")
                .then()
                .assertThat()
                .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                .statusCode(422);
    }
    @Test
    @DisplayName("validar os valores proibidos do limites do produto 7000.01 nao eh permitido")
    public void testValidarLimitesProibidosValorProdutosetemileum(){

            //testar inserir um produto com valor 0.00 e validar que a mensagem de erro foi apresentada
            //e o status code retornado foi 422
            given()
                    .contentType( ContentType.JSON)
                    .header("token",this.token)
                    .body(ProdutoDataFactory.criarProdutoComumComValorIgualA(7000.01))
            .when()
                    .post("/v2/produtos")
            .then()
                    .assertThat()
                        .body("error",equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                        .statusCode(422);

    }
}
