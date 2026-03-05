package it.ianus.plugin.prometheus.client.handler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PrometheusRestClientTest {

    @Mock
    private RestClient restClient;

    @Mock
    private RestClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private RestClient.ResponseSpec responseSpec;

    private PrometheusRestClient prometheusRestClient;

    @SuppressWarnings({"unchecked", "rawtypes"})
    @BeforeEach
    void setUp() {
        prometheusRestClient = new PrometheusRestClient(restClient);
        // Stub the fluent API chain: restClient.get() -> uri() -> retrieve() -> body()
        doReturn(requestHeadersUriSpec).when(restClient).get();
        doReturn(requestHeadersUriSpec).when(requestHeadersUriSpec).uri(anyString());
        doReturn(responseSpec).when(requestHeadersUriSpec).retrieve();
    }

    // --- get(uri, Class) ---

    @Test
    void get_returnsBody() {
        String expected = "hello";
        when(responseSpec.body(String.class)).thenReturn(expected);

        String result = prometheusRestClient.get("/test", String.class);

        assertThat(result).isSameAs(expected);
    }

    @Test
    void get_wrapsRestClientResponseExceptionInPrometheusClientException() {
        RestClientResponseException cause = new RestClientResponseException(
                "Not Found", HttpStatusCode.valueOf(404), "Not Found", null, null, null);
        when(responseSpec.body(String.class)).thenThrow(cause);

        assertThatThrownBy(() -> prometheusRestClient.get("/test", String.class))
                .isInstanceOf(PrometheusClientException.class)
                .hasMessageContaining("GET")
                .hasMessageContaining("/test")
                .hasMessageContaining("404")
                .hasCause(cause);
    }
}
