package com.aws.alexa.service.client;

import com.aws.alexa.service.client.domain.ClientResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface TestRestClient {

    @GET("/test-api")
    Call<ClientResponse> getClientResponse(@Header("access_token") String accessToken);
}
