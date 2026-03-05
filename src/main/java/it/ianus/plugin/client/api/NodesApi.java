package it.ianus.plugin.client.api;

import it.ianus.plugin.client.handler.OpenNmsClientException;
import it.ianus.plugin.client.handler.OpenNmsRestClient;
import it.ianus.plugin.client.model.OnmsNode;
import it.ianus.plugin.client.model.OnmsNodeList;
import org.springframework.web.util.UriComponentsBuilder;

public class NodesApi {
  private OpenNmsRestClient apiClient;


  public NodesApi(OpenNmsRestClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * 
   * 
   * @param nodeCriteria  (required)
   * @return OnmsNode
   * @throws OpenNmsClientException if fails to make API call
   */
  public OnmsNode getNode(String nodeCriteria) throws OpenNmsClientException {
    // verify the required parameter 'nodeCriteria' is set
    if (nodeCriteria == null) {
      throw new OpenNmsClientException("Missing the required parameter 'nodeCriteria' when calling getNode1", new RuntimeException());
    }
    // create path and map variables
    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath("/rest/nodes/{nodeCriteria}");

    return apiClient.get(uriBuilder.buildAndExpand(nodeCriteria).toUriString(), OnmsNode.class);
  }
  /**
   * 
   * 
   * @return OnmsNodeList
   * @throws OpenNmsClientException if fails to make API call
   */
  public OnmsNodeList getNodes(Integer limit, Integer offset ) throws OpenNmsClientException {
    Object localVarPostBody = null;
    // create path and map variables
    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath("/rest/nodes");
    if (limit != null) uriBuilder.queryParam("limit", limit);
    if (offset != null) uriBuilder.queryParam("offset", offset);
    // query params
    return apiClient.get(uriBuilder.toUriString(), OnmsNodeList.class);
  }

  /**
   *
   *
   * @return OnmsNodeList
   * @throws OpenNmsClientException if fails to make API call
   */
  public OnmsNodeList getAllNodes() throws OpenNmsClientException {
    Object localVarPostBody = null;
    // create path and map variables
    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath("/rest/nodes");
    uriBuilder.queryParam("limit", 0);
    // query params
    return apiClient.get(uriBuilder.toUriString(), OnmsNodeList.class);
  }
}
