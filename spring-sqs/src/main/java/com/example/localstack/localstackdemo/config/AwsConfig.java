package com.example.localstack.localstackdemo.config;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.CreateQueueRequest;
import software.amazon.awssdk.services.sqs.model.CreateQueueResponse;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlResponse;

import java.net.URI;
import java.util.concurrent.ExecutionException;

@Configuration
public class AwsConfig {

    @Value("${cloud.aws.sqs.queue-name}")
    private String queueName;

    @Bean
    public String queueUrl(SqsAsyncClient sqsAsyncClient) {
        try {
            CreateQueueResponse response = sqsAsyncClient
                    .createQueue(CreateQueueRequest.builder().queueName(queueName).build())
                    .get();
            return response.queueUrl();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Не удалось создать очередь SQS: " + queueName, e);
        } catch (ExecutionException e) {
            throw new RuntimeException("Не удалось создать очередь SQS: " + queueName, e);
        }
    }

    @Bean
    @Primary
    public SqsAsyncClient sqsAsyncClient() {
        return SqsAsyncClient.builder()
                .endpointOverride(URI.create("http://localstack:4566"))
                .region(Region.US_EAST_1)
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create("test", "test")))
                .build();
    }

    @Bean
    public SqsTemplate sqsTemplate(SqsAsyncClient sqsAsyncClient) {
        return SqsTemplate.builder()
                .sqsAsyncClient(sqsAsyncClient)
                .build();
    }
}
