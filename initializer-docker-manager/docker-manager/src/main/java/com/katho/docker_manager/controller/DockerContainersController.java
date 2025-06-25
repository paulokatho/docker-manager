package com.katho.docker_manager.controller;

import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Image;
import com.katho.docker_manager.service.DockerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/containers")
public class DockerContainersController {

    private final DockerService dockerService;

    public DockerContainersController(DockerService dockerService) {

        this.dockerService = dockerService;
    }

    @GetMapping("")
    public List<Container> listContainers(@RequestParam(required = false, defaultValue = "true") boolean showAll) {
        return dockerService.listContainers(showAll);

    }

    @PostMapping("/{id}/start")
    public void startContainer(@PathVariable("id") String containerId) {
        dockerService.startContainers(containerId);
    }

    @PostMapping("/{id}/stop")
    public void stopContainer(@PathVariable("id") String containerId) {
        dockerService.stopContainers(containerId);
    }

    @DeleteMapping("/{id}")
    public void deleteContainer(@PathVariable("id") String containerId) {
        dockerService.deleteContainers(containerId);
    }

    @PostMapping("")
    public void createContainer(@RequestParam String imageName) {
        dockerService.createContainer(imageName );
    }
}
