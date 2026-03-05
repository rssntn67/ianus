package org.opennms.integrations.ianus.client.api;

import org.opennms.integrations.ianus.client.handler.OpenNmsClientException;
import org.opennms.integrations.ianus.client.handler.OpenNmsRestClient;
import org.opennms.integrations.ianus.client.model.ResourceDTO;
import org.opennms.integrations.ianus.client.model.ResourceDTOCollection;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.ResolvableType;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public class ResourcesApi {
  private final OpenNmsRestClient apiClient;

  public ResourcesApi(OpenNmsRestClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * 
   * 
   * @param resourceId  (required)
   * @param depth  (optional, default to -1)
   * @return ResourceDTO
   * @throws OpenNmsClientException if fails to make API call
   */
  public ResourceDTO getResourceById(String resourceId, Integer depth) throws OpenNmsClientException {
    // verify the required parameter 'resourceId' is set
    if (resourceId == null) {
      throw new OpenNmsClientException("Missing the required parameter 'resourceId' when calling getResourceById", new RuntimeException());
    }
    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath("/rest/resources/{resourceId}");
    if (depth != null) uriBuilder.queryParam("depth", depth);
    // create path and map variables



    return apiClient.get(uriBuilder.buildAndExpand(resourceId).toUriString(),ResourceDTO.class);
  }
  /**
   * 
   * 
   * @param nodeCriteria  (required)
   * @param depth  (optional, default to -1)
   * @return ResourceDTO
   * @throws OpenNmsClientException if fails to make API call
   */
  public ResourceDTO getResourceForNode(String nodeCriteria, Integer depth) throws OpenNmsClientException {
    // verify the required parameter 'nodeCriteria' is set
    if (nodeCriteria == null) {
      throw new OpenNmsClientException("Missing the required parameter 'nodeCriteria' when calling getResourceForNode", new RuntimeException());
    }
    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath("/rest/resources/fornode/{nodeCriteria}");
    if (depth != null) uriBuilder.queryParam("depth", depth);

    return apiClient.get(uriBuilder.buildAndExpand(nodeCriteria).toUriString(),ResourceDTO.class);
  }
  /**
   * 
   * 
   * @param depth  (optional, default to 1)
   * @return ResourceDTOCollection
   * @throws OpenNmsClientException if fails to make API call
   */
  public ResourceDTOCollection getResources(Integer depth) throws OpenNmsClientException {
    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath("/rest/resources");
    if (depth != null) uriBuilder.queryParam("depth", depth);

    return apiClient.get(uriBuilder.toUriString(), ResourceDTOCollection.class);
  }

  /**
   * 
   * 
   * @param nodes  (optional)
   * @param filterRules  (optional)
   * @param nodeSubresources  (optional)
   * @param stringProperties  (optional)
   * @return List&lt;ResourceDTO&gt;
   * @throws OpenNmsClientException if fails to make API call
   */
  public List<ResourceDTO> select(String nodes, String filterRules, String nodeSubresources, String stringProperties) throws OpenNmsClientException {
    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath("/rest/resources/select");
    if (nodes != null) uriBuilder.queryParam("nodes", nodes);
    if (filterRules != null) uriBuilder.queryParam("filterRules", filterRules);
    if (nodeSubresources != null) uriBuilder.queryParam("nodeSubresources", nodeSubresources);
    if (stringProperties != null) uriBuilder.queryParam("stringProperties", stringProperties);
    return apiClient.get(uriBuilder.toUriString(), ParameterizedTypeReference.forType(
            ResolvableType.forClassWithGenerics(List.class, ResourceDTOCollection.class).getType()));
  }
}
