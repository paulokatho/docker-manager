package com.katho.docker_manager.config;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DockerClientConfig {

    @Value(value = "${docker.socket.path}")
    private String dockerSocketPath;

    @Bean
    public DockerClient buildDockerClient() {

        DefaultDockerClientConfig.Builder dockerClientConfigBuilder = DefaultDockerClientConfig
                .createDefaultConfigBuilder();

        if(this.dockerSocketPath != null && this.dockerSocketPath.startsWith("unix://")) {
            dockerClientConfigBuilder.withDockerHost(dockerSocketPath)
                    .withDockerHost(dockerSocketPath);
        }

        DefaultDockerClientConfig dockerClientConfig = dockerClientConfigBuilder
                .build();

        ApacheDockerHttpClient dockerHttpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(dockerClientConfig.getDockerHost()).build();

        return DockerClientBuilder.getInstance(dockerClientConfig)
                .withDockerHttpClient(dockerHttpClient)
                .build();
    }
}

/*
    // CODIGO QUE CONFIGURA ALGUMAS PROPRIEDADES PARA ESTABILIZAR O DOCKER COMO:
    - .withApiVersion(RemoteApiVersion.VERSION_1_24
    - .connectionTimeout(Duration.ofMillis(300)
    - dentre outras propriedades -> Fazer o DE-PARA com o codigo acima e ver as diferenças

@Value("${docker.socket.path}")
    private String dockerSocketPath;

    @Bean
    @Lazy(false)
    public DockerClient buildDockerClient() {
        DefaultDockerClientConfig.Builder dockerClientConfigBuilder = DefaultDockerClientConfig
                .createDefaultConfigBuilder();

        if(this.dockerSocketPath != null && this.dockerSocketPath.startsWith("unix://")) {
            dockerClientConfigBuilder.withDockerHost(dockerSocketPath)
                    .withApiVersion(RemoteApiVersion.VERSION_1_24)
                    .withDockerTlsVerify(false);
        }

        DefaultDockerClientConfig dockerClientConfig = dockerClientConfigBuilder
                .build();

        ApacheDockerHttpClient dockerHttpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(dockerClientConfig.getDockerHost())
                .maxConnections(5)
                .connectionTimeout(Duration.ofMillis(300))
                .responseTimeout(Duration.ofSeconds(3))
                .build();

        DockerClient client = DockerClientBuilder.getInstance(dockerClientConfig)
                .withDockerHttpClient(dockerHttpClient)
                .build();

        client.pingCmd().exec();

        return client;
    }
 */


