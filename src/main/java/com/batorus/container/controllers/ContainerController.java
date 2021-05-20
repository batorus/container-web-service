package com.batorus.container.controllers;

import com.batorus.container.models.Container;
import com.batorus.container.services.ContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    //Send request like this:
    //    [{"containerName":"container 1",
    //            "containerCode":"#123"},
    //    {"containerName":"container 2",
    //            "containerCode":"#456"},
    //    {"containerName":"container 3",
    //            "containerCode":"#789"}]
    @PostMapping("/containers/savebatch")
    public List<Container> saveAllCategoriesAction(@RequestBody List<Container> list) {

        return containerService.saveAll(list);
    }

    @PutMapping("/containers/{id}")
    public Container updateContainersAction(@PathVariable Long id, @Valid @RequestBody Container container) {

        return containerService.update(id, container);
    }

    @GetMapping("/containers/{id}")
    public Container getOneContainerAction(@PathVariable Long id) {

        return containerService.find(id);
    }

    @DeleteMapping("/containers/{id}")
    public ResponseEntity<String> deleteAction(@PathVariable Long id) {
        containerService.delete(id);

        return new ResponseEntity<>("Deleted successfully!", HttpStatus.OK);
    }

}
