package com.somesoftwareteam.graphql.utility;

import com.somesoftwareteam.graphql.datasources.mysql.entities.Fixture;
import com.somesoftwareteam.graphql.utility.IntegrationTestBase;
import com.vladmihalcea.hibernate.type.json.internal.JacksonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static com.somesoftwareteam.graphql.utility.TestTokenProvider.getToken;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://docs.spring.io/spring/docs/current/spring-framework-reference/pdf/testing-webtestclient.pdf
 */
public class RestResourceBase<T> extends IntegrationTestBase {

    public void createReadUpdateDelete(T entity, Class<T> entityClass, String resourceCollectionName) {

        WebTestClient webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:" + port).build();
        String token = getToken();

        T resultFromPost = webTestClient
                .post()
                .uri("/rest/" + resourceCollectionName)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .bodyValue(entity)
                .exchange()
                .expectStatus()
                .isCreated()
                .returnResult(entityClass)
                .getResponseBody()
                .blockFirst();
        assertThat(resultFromPost).isNotNull();

        List<T> resultFromGetAll = webTestClient
                .get()
                .uri("/rest/" + resourceCollectionName)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(entityClass)
                .returnResult()
                .getResponseBody();
        assertThat(resultFromGetAll).isNotNull();
        assertThat(resultFromGetAll.size()).isGreaterThan(0);

//        String fixtureUri = "http://localhost:" + port + "/rest/fixtures/" + resultFromPost.getId();
//        String verificationUri = "http://localhost:" + port + "/rest/verifications/" + resultFromVerificationPost.getId();
//        String propertyUri = "http://localhost:" + port + "/rest/properties/" + resultFromPropertyPost.getId();

//        webTestClient
//                .put()
//                .uri(propertyUri + "/fixtures")
//                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
//                .header(HttpHeaders.CONTENT_TYPE, "text/uri-list")
//                .bodyValue(fixtureUri)
//                .exchange()
//                .expectStatus()
//                .isNoContent();
    }
}