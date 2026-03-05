package it.ianus.plugin.client.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import it.ianus.plugin.client.handler.OpenNmsRestClient;
import it.ianus.plugin.client.model.FilterMetaData;
import it.ianus.plugin.client.model.QueryRequest;
import it.ianus.plugin.client.model.QueryResponse;
import it.ianus.plugin.client.model.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests for MeasumentsApi against a live OpenNMS instance.
 *
 * Run with:
 *   ./mvnw verify \
 *     -Dopennms.it.enabled=true \
 *     -Dopennms.base-url=http://localhost:8980/opennms/rest \
 *     -Dopennms.username=admin \
 *     -Dopennms.password=admin \
t *     -Dopennms.it.resource.id=node[selfmonitor:1].nodeSnmp[] \
 *     -Dopennms.it.attribute=OnmsEventCount \
 *     -Dopennms.it.filter.name=Chomp
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@EnabledIfSystemProperty(named = "opennms.it.enabled", matches = "true")
class MeasumentsApiIT {

    @Autowired
    private OpenNmsRestClient restClient;

    private MeasumentsApi api;

    @BeforeEach
    void setUp() {
        api = new MeasumentsApi(restClient);
    }

    @Test
    void getFilterMetadata_returnsNonNullList() {
        List<FilterMetaData> result = api.getFilterMetadata();
        assertThat(result).isNotNull();
    }

    @Test
    @EnabledIfSystemProperty(named = "opennms.it.filter.name", matches = ".+")
    void getFilterMetadata1_returnsFilterForName() {
        String name = System.getProperty("opennms.it.filter.name");

        FilterMetaData result = api.getFilterMetadata1(name);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(name);
    }

    @Test
    @EnabledIfSystemProperty(named = "opennms.it.resource.id", matches = ".+")
    @EnabledIfSystemProperty(named = "opennms.it.attribute",   matches = ".+")
    void simpleQuery_returnsQueryResponse() {
        String resourceId = System.getProperty("opennms.it.resource.id");
        String attribute  = System.getProperty("opennms.it.attribute");

        QueryResponse result = api.simpleQuery(resourceId, attribute,
                null, null, null, null, null, null, null);

        assertThat(result).isNotNull();
        assertThat(result.getLabels()).isNotNull();
    }

    @Test
    @EnabledIfSystemProperty(named = "opennms.it.resource.id", matches = ".+")
    @EnabledIfSystemProperty(named = "opennms.it.attribute",   matches = ".+")
    void query_withBuiltRequest_returnsQueryResponse() {
        String resourceId = System.getProperty("opennms.it.resource.id");
        String attribute  = System.getProperty("opennms.it.attribute");

        Source source = new Source()
                .resourceId(resourceId)
                .attribute(attribute)
                .label("it-test");

        QueryRequest request = new QueryRequest()
                .step(300_000L)
                .addSourcesItem(source);

        QueryResponse result = api.query(request);

        assertThat(result).isNotNull();
        assertThat(result.getLabels()).contains("it-test");
    }
}