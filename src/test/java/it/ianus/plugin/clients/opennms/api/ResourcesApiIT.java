package it.ianus.plugin.clients.opennms.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import it.ianus.plugin.clients.opennms.handler.OpenNmsRestClient;
import it.ianus.plugin.clients.opennms.model.ResourceDTO;
import it.ianus.plugin.clients.opennms.model.ResourceDTOCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
 *     -Dopennms.it.resource.id=node[1].nodeSnmp[] \
 *     -Dopennms.it.node.criteria=1
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@EnabledIfSystemProperty(named = "opennms.it.enabled", matches = "true")
class ResourcesApiIT {

    @Autowired
    private OpenNmsRestClient restClient;

    private ResourcesApi api;

    @BeforeEach
    void setUp() {
        api = new ResourcesApi(restClient);
    }

    @Test
    void getResources_returnsNonNullCollection() {
        ResourceDTOCollection result = api.getResources(null);
        assertThat(result).isNotNull();
    }

    @Test
    void getResources_withDepth_returnsNonNullCollection() {
        ResourceDTOCollection result = api.getResources(1);
        assertThat(result).isNotNull();
    }

    @Test
    @EnabledIfSystemProperty(named = "opennms.it.resource.id", matches = ".+")
    void getResourceById_returnsResourceWithMatchingId() {
        String resourceId = System.getProperty("opennms.it.resource.id");

        ResourceDTO result = api.getResourceById(resourceId, null);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(resourceId);
    }

    @Test
    @EnabledIfSystemProperty(named = "opennms.it.node.criteria", matches = ".+")
    void getResourceForNode_returnsNonNullResource() {
        String nodeCriteria = System.getProperty("opennms.it.node.criteria");

        ResourceDTO result = api.getResourceForNode(nodeCriteria, null);

        assertThat(result).isNotNull();
    }

    @Test
    void select_withNoParams_returnsNonNullList() {
        List<ResourceDTO> result = api.select(null, null, null, null);
        assertThat(result).isNotNull();
    }

    @Test
    @EnabledIfSystemProperty(named = "opennms.it.node.criteria", matches = ".+")
    void select_withNodeFilter_returnsMatchingResources() {
        String nodeCriteria = System.getProperty("opennms.it.node.criteria");

        List<ResourceDTO> result = api.select(nodeCriteria, null, null, null);

        assertThat(result).isNotNull();
    }
}