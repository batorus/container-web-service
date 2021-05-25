package com.batorus.container.controllers;

import com.batorus.container.DTO.ContainerDto;
import com.batorus.container.DTO.ItemDto;
import com.batorus.container.models.Container;
import com.batorus.container.models.Item;
import com.batorus.container.services.ContainerService;
import com.batorus.container.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class ContainerController {

    @Autowired
    ContainerService containerService;

    @Autowired
    ItemService itemService;

    @GetMapping(path = "/containers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map<String, List<ItemDto>>> listContainersAction() {
        List<Container> containers = containerService.getAllContainers();

        List<Map<String, List<ItemDto>>> list = containers.stream()
                .map(container -> {
                    Map<String, List<ItemDto>>  ldto = new HashMap<>();
                    List<Item> items = itemService.getAllItemsByContainerId(container.getId());
                    String cName = container.getContainerName();
                    if(items.size() > 0)
                        ldto = items.stream().map(item -> ItemDto.from(item)).collect(Collectors.groupingBy(item -> cName));
                    else
                        ldto.put(cName, Collections.emptyList());

                    return ldto;
                })
                .collect(Collectors.toList());

        return list;
    }

    @PostMapping(path = "/containers", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Container createAction(@Valid @RequestBody Container container) {

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
    public List<Container> saveAllAction(@RequestBody List<Container> list) {

        return containerService.saveAll(list);
    }

    @PutMapping("/containers/{id}")
    public Container updateAction(@PathVariable Long id, @Valid @RequestBody Container container) {

        return containerService.update(id, container);
    }

    @GetMapping("/containers/{id}")
    public Container getOneAction(@PathVariable Long id) {

        return containerService.find(id);
    }

    @DeleteMapping("/containers/{id}")
    public ResponseEntity<String> deleteAction(@PathVariable Long id) {
        containerService.delete(id);

        return new ResponseEntity<>("Deleted successfully!", HttpStatus.OK);
    }

}
