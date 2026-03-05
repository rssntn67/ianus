package it.ianus.plugin.client.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import it.ianus.plugin.client.handler.OpenNmsRestClient;
import it.ianus.plugin.client.model.QueryRequest;
import it.ianus.plugin.client.model.QueryResponse;
import it.ianus.plugin.client.model.ResourceDTOCollection;
import it.ianus.plugin.client.model.Source;
import it.ianus.plugin.collector.PerformanceCollector;
import it.ianus.plugin.controller.IanusPerformanceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.List;

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

    @Autowired
    private PerformanceCollector performanceCollector;

    private ResourcesApi rApi;
    private MeasumentsApi mApi;

    @BeforeEach
    void setUp() {
        rApi = new ResourcesApi(restClient);
        mApi = new MeasumentsApi(restClient);
    }

    @Test
    void getResourcesIcmpTest() {
        ResourceDTOCollection result = rApi.getResources(null);
        assertThat(result).isNotNull();
        assertThat(result.getObjects()).isNotNull();
        System.out.println("Count: " + result.getCount());
        System.out.println("TotalCount: " + result.getTotalCount());
        System.out.println("Offset: " + result.getOffset());
        result.getObjects().stream()
                .filter(parentDto -> parentDto.getLabel().equals("10.99.77.103"))
                .forEach(parentDto -> {
                    assertThat(parentDto.getId().equals("node[Home:79]"));
                    assertThat(parentDto.getName().equals("Home:79"));
                    assertThat(parentDto.getLink().equals("element/node.jsp?node=Home:79"));
                    assertThat(parentDto.getTypeLabel().equals("Node"));
                    assertThat(parentDto.getChildren()).isNotNull();
                    assertThat(parentDto.getChildren().getCount().equals(2));
                    assertThat(parentDto.getChildren().getTotalCount().equals(2));
                    assertThat(parentDto.getChildren().getOffset().equals(0));
                    parentDto.getChildren().getObjects().forEach(rDto -> {
                        System.out.println("Id:"+rDto.getId());
                        System.out.println("Label:"+rDto.getLabel());
                        System.out.println("Name:"+rDto.getName());
                        System.out.println("Link:"+rDto.getLink());
                        System.out.println("TypeLabel:"+rDto.getTypeLabel());
                        assertThat(rDto.getParentId().equals(parentDto.getId()));
                        assertThat(rDto.getRrdGraphAttributes()).isNotNull();
                        rDto.getRrdGraphAttributes().values().forEach(System.out::println);
                    });
                });
    }

    @Test
    void getResourcesSnmpTest() {
        ResourceDTOCollection result = rApi.getResources(null);
        assertThat(result).isNotNull();
        assertThat(result.getObjects()).isNotNull();
        System.out.println("Count: " + result.getCount());
        System.out.println("TotalCount: " + result.getTotalCount());
        System.out.println("Offset: " + result.getOffset());
        result.getObjects().stream()
                .filter(parentDto -> parentDto.getLabel().equals("_gateway"))
                .forEach(parentDto -> {
                    assertThat(parentDto.getId().equals("node[Home:2]"));
                    assertThat(parentDto.getName().equals("Home:2"));
                    assertThat(parentDto.getLink().equals("element/node.jsp?node=Home:2"));
                    assertThat(parentDto.getTypeLabel().equals("Node"));
                    assertThat(parentDto.getChildren()).isNotNull();
                    assertThat(parentDto.getChildren().getCount().equals(2));
                    assertThat(parentDto.getChildren().getTotalCount().equals(2));
                    assertThat(parentDto.getChildren().getOffset().equals(0));
                    parentDto.getChildren().getObjects()
                    .stream()
                    .filter(rDto -> rDto.getId().contains("interfaceSnmp"))
                    .forEach(rDto -> {
                        System.out.println("Id:"+rDto.getId());
                        System.out.println("Label:"+rDto.getLabel());
                        System.out.println("Name:"+rDto.getName());
                        System.out.println("Link:"+rDto.getLink());
                        System.out.println("TypeLabel:"+rDto.getTypeLabel());
                        assertThat(rDto.getParentId().equals(parentDto.getId()));
                        assertThat(rDto.getRrdGraphAttributes()).isNotNull();
                        rDto.getRrdGraphAttributes().values().forEach(System.out::println);
                        assertThat(rDto.getStringPropertyAttributes()).isNotNull();
                        System.out.println("StringPropertyAttributes");
                        System.out.println(rDto.getStringPropertyAttributes());
                        assertThat(rDto.getExternalValueAttributes()).isNotNull();
                        System.out.println("ExternalValueAttributes");
                        System.out.println(rDto.getExternalValueAttributes());
                    });
                });
    }

    @Test
    void getResponseTimeMeasurementTest() {
        String resourceId = "node[14].responseTime[10.99.88.96]";
        var end= Instant.now().toEpochMilli();
        var start= end-900000;
        System.out.println(start);
        System.out.println(end);
        System.out.println(-start+end);
        QueryRequest queryRequest = new QueryRequest();

            queryRequest.setStart(start);
            queryRequest.setEnd(end);
            queryRequest.setStep(300L);
            queryRequest.setMaxRows(10);
            Source source = new Source();
            source.setResourceId(resourceId);
            source.setAttribute("icmp");
            source.setLabel("icmp");
            source.setTransient(false);

            queryRequest.addSourcesItem(source);
            QueryResponse queryResponse =mApi.query(queryRequest);
            System.out.println("-----QueryResponse------");
            System.out.println(queryResponse);
    }


    @Test
    void getSnmpMeasurementTest() {
        String resourceId = "node[Home:2].interfaceSnmp[ether5-085531b9f9a7]";
        var end= Instant.now().toEpochMilli();
        var start= end-900000;
        System.out.println(start);
        System.out.println(end);
        System.out.println(-start+end);
        QueryRequest queryRequest = new QueryRequest();

        queryRequest.setStart(start);
        queryRequest.setEnd(end);
        queryRequest.setStep(300L);
        queryRequest.setMaxRows(20);
        Source sourceOut = new Source();
        sourceOut.setResourceId(resourceId);
        sourceOut.setAttribute("ifHCOutOctets");
        sourceOut.setLabel("ifHCOutOctets");
        sourceOut.setTransient(false);
        queryRequest.addSourcesItem(sourceOut);

        Source sourceIn = new Source();
        sourceIn.setResourceId(resourceId);
        sourceIn.setAttribute("ifHCInOctets");
        sourceIn.setLabel("ifHCInOctets");
        sourceIn.setTransient(false);

        queryRequest.addSourcesItem(sourceIn);

        QueryResponse queryResponse = mApi.query(queryRequest);
        System.out.println("-----QueryResponse------");
        System.out.println(queryResponse);
    }

    @Test
    void performanceCollectorTest() {
        performanceCollector.collect();

        assertThat(performanceCollector.getCache()).isNotEmpty();

        System.out.println("Cache entries: " + performanceCollector.getCache().size());
        performanceCollector.getCache().forEach((key, dtos) ->
                System.out.println("  " + key + " -> " + dtos.size() + " measurements"));

        List<IanusPerformanceDto> all = performanceCollector.getAll();
        assertThat(all).isNotEmpty();
        System.out.println("Total measurements: " + all.size());
        all.stream().limit(5).forEach(System.out::println);
    }

}
