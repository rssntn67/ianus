package org.opennms.integrations.ianus.client.api;

import org.opennms.integrations.ianus.client.handler.OpenNmsClientException;
import org.opennms.integrations.ianus.client.handler.OpenNmsRestClient;
import org.opennms.integrations.ianus.client.model.OnmsNode;
import org.opennms.integrations.ianus.client.model.OnmsNodeList;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
