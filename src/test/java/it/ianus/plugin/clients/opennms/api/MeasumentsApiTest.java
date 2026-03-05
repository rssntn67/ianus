package it.ianus.plugin.clients.opennms.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import it.ianus.plugin.clients.opennms.handler.OpenNmsClientException;
import it.ianus.plugin.clients.opennms.handler.OpenNmsRestClient;
import it.ianus.plugin.clients.opennms.model.FilterMetaData;
import it.ianus.plugin.clients.opennms.model.QueryRequest;
import it.ianus.plugin.clients.opennms.model.QueryResponse;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MeasumentsApiTest {

    @Mock
    private OpenNmsRestClient restClient;

    private MeasumentsApi api;

    @BeforeEach
    void setUp() {
        api = new MeasumentsApi(restClient);
    }

    // --- getFilterMetadata ---

    @Test
    void getFilterMetadata_delegatesToGetWithCorrectPath() {
        List<FilterMetaData> expected = List.of(new FilterMetaData().name("foo"));
        when(restClient.get(eq("/rest/measurements/filters"), any(ParameterizedTypeReference.class)))
                .thenReturn(expected);

        List<FilterMetaData> result = api.getFilterMetadata();

        assertThat(result).isSameAs(expected);
        verify(restClient).get(eq("/rest/measurements/filters"), any(ParameterizedTypeReference.class));
    }

    // --- getFilterMetadata1 ---

    @Test
    void getFilterMetadata1_buildsCorrectPath() {
        FilterMetaData expected = new FilterMetaData().name("bar");
        ArgumentCaptor<String> pathCaptor = ArgumentCaptor.forClass(String.class);
        when(restClient.get(pathCaptor.capture(), eq(FilterMetaData.class))).thenReturn(expected);

        FilterMetaData result = api.getFilterMetadata1("bar");

        assertThat(result).isSameAs(expected);
        assertThat(pathCaptor.getValue()).isEqualTo("/rest/measurements/filters/bar");
    }

    @Test
    void getFilterMetadata1_throwsWhenNameIsNull() {
        assertThatThrownBy(() -> api.getFilterMetadata1(null))
                .isInstanceOf(OpenNmsClientException.class)
                .hasMessageContaining("name");
    }

    // --- query ---

    @Test
    void query_delegatesToPostWithCorrectPathAndBody() {
        QueryRequest request = new QueryRequest().start(0L).end(3_600_000L);
        QueryResponse expected = new QueryResponse().step(300L);
        when(restClient.post("/rest/measurements", request, QueryResponse.class)).thenReturn(expected);

        QueryResponse result = api.query(request);

        assertThat(result).isSameAs(expected);
        verify(restClient).post("/rest/measurements", request, QueryResponse.class);
    }

    @Test
    void query_acceptsNullBody() {
        when(restClient.post("/rest/measurements", null, QueryResponse.class)).thenReturn(new QueryResponse());
        assertThatCode(() -> api.query(null)).doesNotThrowAnyException();
    }

    // --- simpleQuery ---

    @Test
    void simpleQuery_minimalRequiredParams_buildsCorrectPath() {
        ArgumentCaptor<String> pathCaptor = ArgumentCaptor.forClass(String.class);
        when(restClient.get(pathCaptor.capture(), eq(QueryResponse.class))).thenReturn(new QueryResponse());

        api.simpleQuery("node1", "ifInOctets", null, null, null, null, null, null, null);

        assertThat(pathCaptor.getValue()).isEqualTo("/rest/measurements/node1/ifInOctets");
    }

    @Test
    void simpleQuery_withAllOptionalParams_includesQueryParams() {
        ArgumentCaptor<String> pathCaptor = ArgumentCaptor.forClass(String.class);
        when(restClient.get(pathCaptor.capture(), eq(QueryResponse.class))).thenReturn(new QueryResponse());

        api.simpleQuery("node1", "ifInOctets", 1000L, 2000L, 300L, 100, "fallback", "AVERAGE", true);

        String uri = pathCaptor.getValue();
        assertThat(uri)
                .contains("/rest/measurements/node1/ifInOctets")
                .contains("start=1000")
                .contains("end=2000")
                .contains("step=300")
                .contains("maxrows=100")
                .contains("fallback-attribute=fallback")
                .contains("aggregation=AVERAGE")
                .contains("relaxed=true");
    }

    @Test
    void simpleQuery_throwsWhenResourceIdIsNull() {
        assertThatThrownBy(() -> api.simpleQuery(null, "ifInOctets", null, null, null, null, null, null, null))
                .isInstanceOf(OpenNmsClientException.class)
                .hasMessageContaining("resourceId");
    }

    @Test
    void simpleQuery_throwsWhenAttributeIsNull() {
        assertThatThrownBy(() -> api.simpleQuery("node1", null, null, null, null, null, null, null, null))
                .isInstanceOf(OpenNmsClientException.class)
                .hasMessageContaining("attribute");
    }
}