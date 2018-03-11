package com.aws.alexa.service.client.config;

import com.aws.alexa.service.client.TestRestClient;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.validation.constraints.NotNull;

//@Configuration
public class ClientConfig {

    @NotNull
    @Value("${integration.test-client.url}")
    private String clientUrl;

    @Bean
    public TestRestClient geocodeRestClient() {
        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(clientUrl)
                        .addConverterFactory(GsonConverterFactory.create());

        return builder.client(okHttpClient())
                .build().create(TestRestClient.class);
    }

    private OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder().build();
    }
}
