package com.batorus.container.controllers;

import com.batorus.container.models.Container;
import com.batorus.container.services.ContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ContainerController {

    @Autowired
    ContainerService containerService;

    @GetMapping(path = "/containers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Container> listContainersAction() {

        return containerService.getAllContainers();
    }

    @PostMapping(path = "/containers", consumes = {MediaType.APPLICATION_JSON_VALUE},
                                       produces = {MediaType.APPLICATION_JSON_VALUE})
    public Container createContainerAction(@Valid @RequestBody Container container) {

        return containerService.save(container);
    }



}
