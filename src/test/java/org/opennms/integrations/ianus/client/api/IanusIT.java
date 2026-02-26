package org.opennms.integrations.ianus.client.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.opennms.integrations.ianus.client.handler.OpenNmsRestClient;
import org.opennms.integrations.ianus.client.model.QueryRequest;
import org.opennms.integrations.ianus.client.model.QueryResponse;
import org.opennms.integrations.ianus.client.model.ResourceDTOCollection;
import org.opennms.integrations.ianus.client.model.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests for ResourcesApi against a live OpenNMS instance.
 * Run with:
 *   ./mvnw verify \
 *     -Dopennms.it.enabled=true \
 *     -Dopennms.base-url=<a href="http://localhost:8980/opennms/rest">...</a> \
 *     -Dopennms.username=admin \
 *     -Dopennms.password=admin \
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@EnabledIfSystemProperty(named = "opennms.it.enabled", matches = "true")
class IanusIT {

    @Autowired
    private OpenNmsRestClient restClient;

    private ResourcesApi rApi;
    private MeasumentsApi mApi;

    @BeforeEach
    void setUp() {
        rApi = new ResourcesApi(restClient);
        mApi = new MeasumentsApi(restClient);
    }

    @Test
    void getResources_returnsNonNullCollection() {
        ResourceDTOCollection result = rApi.getResources(null);
        assertThat(result).isNotNull();
        assertThat(result.getObjects()).isNotNull();
        result.getObjects().forEach( o -> o.getChildren().getObjects().forEach( c -> {
            System.out.println("----------------");
            System.out.println(c.getId());
            System.out.println(c.getLabel());
            System.out.println(c.getName());
            System.out.println(c.getParentId());
            System.out.println(c.getLink());
            System.out.println(c.getTypeLabel());
        }));
    }

    @Test
    void getMeasurement() {
        String resourceId = "node[Home:55].responseTime[10.99.77.42]";
        var end= Instant.now().toEpochMilli();
        var start= end-600000;
        System.out.println(start);
        System.out.println(end);
        System.out.println(-start+end);
        /*
        QueryRequest queryRequest = new QueryRequest();

            queryRequest.setStart(start);
            queryRequest.setEnd(end);
            queryRequest.setStep(300L);
            queryRequest.setMaxRows(100);
            Source source = new Source();
            source.setResourceId(resourceId);
            source.setAggregation("AVERAGE");
            source.setAttribute("icmp");
            source.setLabel("icmp");
            source.setTransient(true);

            queryRequest.addSourcesItem(source);
            QueryResponse queryResponse =mApi.query(queryRequest);
            System.out.println("-----QueryResponse------");
            System.out.println(queryResponse);
*/
        QueryResponse    queryResponse = mApi.simpleQuery(resourceId, "icmp", null, null, null, null,null, null, null);
        System.out.println("-----QueryResponse------");
        System.out.println(queryResponse);

    }



}