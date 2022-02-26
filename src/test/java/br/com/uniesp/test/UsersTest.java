package br.com.uniesp.test;

import br.com.uniesp.constants.ApiConstants;
import br.com.uniesp.constants.UsersConstants;
import br.com.uniesp.entidades.PessoaRequest;
import br.com.uniesp.entidades.PessoaRequestBuilder;
import br.com.uniesp.entidades.PessoaResponse;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;

class UsersTest {

    private PessoaRequest pessoaRequest;

    @BeforeEach
    void setupTests() {

        baseURI = ApiConstants.BASE_URI;

        this.pessoaRequest = PessoaRequestBuilder.builder()
                .job(UsersConstants.JOB)
                .nome(UsersConstants.NOME)
                .build();
    }

    @Test
    @DisplayName(UsersConstants.VALIDATE_CREATE_USER)
    public void testCreateUserSucessfully() {

        var pessoaResponse = this.createUser();

        Assertions.assertNotNull(pessoaResponse);
        Assertions.assertNotNull(pessoaResponse.getId());
        Assertions.assertEquals(pessoaRequest.getJob(), pessoaResponse.getJob());
        Assertions.assertEquals(pessoaRequest.getNome(), pessoaResponse.getNome());
    }

    @Test
    @DisplayName(UsersConstants.UPDATE_USER)
    public void testUpdatePutUser() {

        var pessoaResponse = this.createUser();
        this.pessoaRequest.setJob(UsersConstants.JOB_UPDATE_PUT);
        var pessoaUpdate = this.updateUser(pessoaResponse, this.pessoaRequest);

        Assertions.assertNotNull(pessoaUpdate);
        Assertions.assertNotEquals(pessoaUpdate.getId(), pessoaResponse.getId());
        Assertions.assertNotEquals(pessoaUpdate.getJob(), pessoaResponse.getJob());
        Assertions.assertEquals(pessoaUpdate.getNome(), pessoaResponse.getNome());

    }

    @Test
    @DisplayName(UsersConstants.PATCH_USER)
    public void testPatchUser() {

        var pessoaResponse = this.createUser();

        this.pessoaRequest.setNome(UsersConstants.NOME_UPDATE_PUT);
        this.pessoaRequest.setJob(null);

        var pessoaPatch = this.patchUser(pessoaResponse,this.pessoaRequest);

        Assertions.assertNotNull(pessoaPatch);
        Assertions.assertNull(pessoaPatch.getJob());
        Assertions.assertNotEquals(pessoaPatch.getNome(), pessoaResponse.getNome());
        Assertions.assertNotEquals(pessoaPatch.getId(), pessoaResponse.getId());
    }

    @Test
    @DisplayName(UsersConstants.GET_USER)
    public void testGetUser() {
         this.getAnyUser();
    }

    private PessoaResponse createUser() {

        return given()
                .contentType(ContentType.JSON)
                .body(this.pessoaRequest)
                .when().post(UsersConstants.API_USER)
                .then().statusCode(HttpStatus.SC_CREATED)
                .log().all().extract().response().as(PessoaResponse.class);

    }

    private PessoaResponse updateUser(PessoaResponse originalUser, PessoaRequest destinationUser) {
        return given()
                .contentType(ContentType.JSON)
                .body(destinationUser)
                .when().put(UsersConstants.API_USER+"/{1}", originalUser.getId())
                .then().statusCode(HttpStatus.SC_OK)
                .log().all().extract().response().as(PessoaResponse.class);
    }

    private PessoaResponse patchUser(PessoaResponse originalUser, PessoaRequest destinationUser) {
        return given()
                .contentType(ContentType.JSON)
                .body(destinationUser)
                .when().patch(UsersConstants.API_USER+"/{1}", originalUser.getId())
                .then().statusCode(HttpStatus.SC_OK)
                .log().all().extract().response().as(PessoaResponse.class);
    }

    private PessoaResponse getUser(String id ) {
        return given()
                .contentType(ContentType.JSON)
                .when().get(UsersConstants.API_USER+"/{1}",id)
                .then().statusCode(HttpStatus.SC_OK)
                .log().all().extract().response().as(PessoaResponse.class);
    }

    private void getAnyUser(){
        given()
                .contentType(ContentType.JSON)
                .when().get(UsersConstants.API_USER+"/{1}",UsersConstants.ID_FIXO)
                .then().statusCode(HttpStatus.SC_OK)
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(UsersConstants.PATH_CONTRACT));
    }

}