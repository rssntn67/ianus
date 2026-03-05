package it.ianus.plugin.client.api;


import it.ianus.plugin.client.handler.OpenNmsClientException;
import it.ianus.plugin.client.handler.OpenNmsRestClient;
import it.ianus.plugin.client.model.FilterMetaData;
import it.ianus.plugin.client.model.QueryRequest;
import it.ianus.plugin.client.model.QueryResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.ResolvableType;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public class MeasumentsApi {

  private final OpenNmsRestClient apiClient;
  public MeasumentsApi(OpenNmsRestClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * 
   * 
   * @return List&lt;FilterMetaData&gt;
   * @throws OpenNmsClientException if fails to make API call
   */
  public List<FilterMetaData> getFilterMetadata() throws OpenNmsClientException {
    return apiClient.get("/rest/measurements/filters", ParameterizedTypeReference.forType(
        ResolvableType.forClassWithGenerics(List.class, FilterMetaData.class).getType()));
  }
  /**
   * 
   * 
   * @param name  (required)
   * @return FilterMetaData
   * @throws OpenNmsClientException if fails to make API call
   */
  public FilterMetaData getFilterMetadata1(String name) throws OpenNmsClientException {
    if (name == null) {
      throw new OpenNmsClientException("Missing the required parameter 'name' when calling getFilterMetadata1", new RuntimeException());
    }
    String localVarPath = UriComponentsBuilder.fromPath("/rest/measurements/filters/{name}")
      .buildAndExpand(name)
      .toUriString();

    return apiClient.get(localVarPath, FilterMetaData.class);
  }
  /**
   * 
   * 
   * @param body  (optional)
   * @return QueryResponse
   * @throws OpenNmsClientException if fails to make API call
   */
  public QueryResponse query(QueryRequest body) throws OpenNmsClientException {
    String localVarPath = "/rest/measurements";

    return apiClient.post(localVarPath, body, QueryResponse.class);
  }
  /**
   * 
   * 
   * @param resourceId  (required)
   * @param attribute  (required)
   * @param start  (optional, default to -14400000)
   * @param end  (optional, default to 0)
   * @param step  (optional, default to 300000)
   * @param maxrows  (optional, default to 0)
   * @param fallbackAttribute  (optional)
   * @param aggregation  (optional, default to AVERAGE)
   * @param relaxed  (optional, default to false)
   * @return QueryResponse
   * @throws OpenNmsClientException if fails to make API call
   */
  public QueryResponse simpleQuery(String resourceId, String attribute, Long start, Long end, Long step, Integer maxrows, String fallbackAttribute, String aggregation, Boolean relaxed) throws OpenNmsClientException {
    // verify the required parameter 'resourceId' is set
    if (resourceId == null) {
      throw new OpenNmsClientException("Missing the required parameter 'resourceId' when calling simpleQuery", new RuntimeException());
    }
    // verify the required parameter 'attribute' is set
    if (attribute == null) {
      throw new OpenNmsClientException("Missing the required parameter 'attribute' when calling simpleQuery", new RuntimeException());
    }
    // query params
    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath("/rest/measurements/{resourceId}/{attribute}");
    if (start != null)             uriBuilder.queryParam("start", start);
    if (end != null)               uriBuilder.queryParam("end", end);
    if (step != null)              uriBuilder.queryParam("step", step);
    if (maxrows != null)           uriBuilder.queryParam("maxrows", maxrows);
    if (fallbackAttribute != null) uriBuilder.queryParam("fallback-attribute", fallbackAttribute);
    if (aggregation != null)       uriBuilder.queryParam("aggregation", aggregation);
    if (relaxed != null)           uriBuilder.queryParam("relaxed", relaxed);

    return apiClient.get(uriBuilder.buildAndExpand(resourceId, attribute).toUriString(), QueryResponse.class);
  }
}
