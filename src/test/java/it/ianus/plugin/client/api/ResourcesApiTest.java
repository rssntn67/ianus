package it.ianus.plugin.client.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import it.ianus.plugin.client.handler.OpenNmsClientException;
import it.ianus.plugin.client.handler.OpenNmsRestClient;
import it.ianus.plugin.client.model.ResourceDTO;
import it.ianus.plugin.client.model.ResourceDTOCollection;
import org.springframework.core.ParameterizedTypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ResourcesApiTest {

    @Mock
    private OpenNmsRestClient restClient;

    private ResourcesApi api;

    @BeforeEach
    void setUp() {
        api = new ResourcesApi(restClient);
    }

    // --- ResourceDTOCollection JSON mapping ---


    @Test
    void resourceDTOCollection_deserializesResourceField() throws Exception {
        String json = "{\"resource\":[{\"id\":\"node1\"}],\"totalCount\":1,\"count\":1,\"offset\":0}";
        ResourceDTOCollection result = new ObjectMapper().readValue(json, ResourceDTOCollection.class);
        assertThat(result.getObjects()).hasSize(1);
        assertThat(result.getObjects().get(0).getId()).isEqualTo("node1");
        assertThat(result.getTotalCount()).isEqualTo(1);
    }

    @Test
    void resourceDTOCollection_objectsIsNullWhenResourceFieldAbsent() throws Exception {
        String json = "{\"totalCount\":0,\"count\":0,\"offset\":0}";
        ResourceDTOCollection result = new ObjectMapper().readValue(json, ResourceDTOCollection.class);
        assertThat(result.getObjects()).isNull();
    }

    // --- getResourceById ---

    @Test
    void getResourceById_buildsCorrectPath() {
        ResourceDTO expected = new ResourceDTO().id("node1");
        ArgumentCaptor<String> pathCaptor = ArgumentCaptor.forClass(String.class);
        when(restClient.get(pathCaptor.capture(), eq(ResourceDTO.class))).thenReturn(expected);

        ResourceDTO result = api.getResourceById("node1", null);

        assertThat(result).isSameAs(expected);
        assertThat(pathCaptor.getValue()).isEqualTo("/rest/resources/node1");
    }

    @Test
    void getResourceById_withDepth_addsQueryParam() {
        ArgumentCaptor<String> pathCaptor = ArgumentCaptor.forClass(String.class);
        when(restClient.get(pathCaptor.capture(), eq(ResourceDTO.class))).thenReturn(new ResourceDTO());

        api.getResourceById("node1", 2);

        assertThat(pathCaptor.getValue()).contains("depth=2");
    }

    @Test
    void getResourceById_throwsWhenResourceIdIsNull() {
        assertThatThrownBy(() -> api.getResourceById(null, null))
                .isInstanceOf(OpenNmsClientException.class)
                .hasMessageContaining("resourceId");
    }

    // --- getResourceForNode ---

    @Test
    void getResourceForNode_buildsCorrectPath() {
        ArgumentCaptor<String> pathCaptor = ArgumentCaptor.forClass(String.class);
        when(restClient.get(pathCaptor.capture(), eq(ResourceDTO.class))).thenReturn(new ResourceDTO());

        api.getResourceForNode("node1", null);

        assertThat(pathCaptor.getValue()).isEqualTo("/rest/resources/fornode/node1");
    }

    @Test
    void getResourceForNode_withDepth_addsQueryParam() {
        ArgumentCaptor<String> pathCaptor = ArgumentCaptor.forClass(String.class);
        when(restClient.get(pathCaptor.capture(), eq(ResourceDTO.class))).thenReturn(new ResourceDTO());

        api.getResourceForNode("node1", 3);

        assertThat(pathCaptor.getValue()).contains("depth=3");
    }

    @Test
    void getResourceForNode_throwsWhenNodeCriteriaIsNull() {
        assertThatThrownBy(() -> api.getResourceForNode(null, null))
                .isInstanceOf(OpenNmsClientException.class)
                .hasMessageContaining("nodeCriteria");
    }

    // --- getResources ---

    @Test
    void getResources_withoutDepth_callsBaseUrl() {
        ResourceDTOCollection expected = new ResourceDTOCollection().totalCount(5);
        ArgumentCaptor<String> pathCaptor = ArgumentCaptor.forClass(String.class);
        when(restClient.get(pathCaptor.capture(), eq(ResourceDTOCollection.class))).thenReturn(expected);

        ResourceDTOCollection result = api.getResources(null);

        assertThat(result).isSameAs(expected);
        assertThat(pathCaptor.getValue()).isEqualTo("/rest/resources");
    }

    @Test
    void getResources_withDepth_addsQueryParam() {
        ArgumentCaptor<String> pathCaptor = ArgumentCaptor.forClass(String.class);
        when(restClient.get(pathCaptor.capture(), eq(ResourceDTOCollection.class))).thenReturn(new ResourceDTOCollection());

        api.getResources(1);

        assertThat(pathCaptor.getValue()).contains("depth=1");
    }

    // --- select ---

    @Test
    void select_withNoParams_callsBaseUrl() {
        ArgumentCaptor<String> pathCaptor = ArgumentCaptor.forClass(String.class);
        when(restClient.get(pathCaptor.capture(), any(ParameterizedTypeReference.class))).thenReturn(List.of());

        api.select(null, null, null, null);

        assertThat(pathCaptor.getValue()).isEqualTo("/rest/resources/select");
    }

    @Test
    void select_withAllParams_includesQueryParams() {
        ArgumentCaptor<String> pathCaptor = ArgumentCaptor.forClass(String.class);
        when(restClient.get(pathCaptor.capture(), any(ParameterizedTypeReference.class))).thenReturn(List.of());

        api.select("nodeA", "ruleB", "subC", "propD");

        String uri = pathCaptor.getValue();
        assertThat(uri)
                .contains("nodes=nodeA")
                .contains("filterRules=ruleB")
                .contains("nodeSubresources=subC")
                .contains("stringProperties=propD");
    }

    @Test
    void select_withPartialParams_omitsNullParams() {
        ArgumentCaptor<String> pathCaptor = ArgumentCaptor.forClass(String.class);
        when(restClient.get(pathCaptor.capture(), any(ParameterizedTypeReference.class))).thenReturn(List.of());

        api.select("nodeA", null, null, null);

        String uri = pathCaptor.getValue();
        assertThat(uri)
                .contains("nodes=nodeA")
                .doesNotContain("filterRules")
                .doesNotContain("nodeSubresources")
                .doesNotContain("stringProperties");
    }
}