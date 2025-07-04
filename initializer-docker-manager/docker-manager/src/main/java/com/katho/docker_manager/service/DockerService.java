package com.katho.docker_manager.service;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Image;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DockerService {

    private final DockerClient dockerClient;

    public DockerService(DockerClient client) {
        this.dockerClient = client;
    }

    public List<Container> listContainers(boolean showAll) {
        return dockerClient.listContainersCmd().withShowAll(true).exec();
    }

    public List<Image> listImages() {
        return dockerClient.listImagesCmd().exec();
    }

    public List<Image> filterImages(String filterName) {
        return dockerClient.listImagesCmd().withImageNameFilter(filterName).exec();
    }

    public void startContainers(String containerId) {
        dockerClient.startContainerCmd(containerId).exec();
    }

    public void stopContainers(String containerId) {
        dockerClient.stopContainerCmd(containerId).exec();
    }

    public void deleteContainers(String containerId) {
        dockerClient.removeContainerCmd(containerId).exec();
    }

    public void createContainer(String imageName) {
        dockerClient.createContainerCmd(imageName).exec();
    }
}
