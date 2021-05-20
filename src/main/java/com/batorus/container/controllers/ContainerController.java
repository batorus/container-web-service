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
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class ContainerController {

    @Autowired
    ContainerService containerService;

    @Autowired
    ItemService itemService;

    @GetMapping(path = "/containers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Container> listContainersAction() {
        List<Container> containers = containerService.getAllContainers();
        List<List<Item>> list = containers.stream().map(container->{

            List<Item> items = itemService.getAllItemsByContainerId(container.getId());

            if(items.size()>0){

                List<ItemDto> ldto = items.stream().map(ItemDto::from).collect(Collectors.toList());
                System.out.println(ldto);
            }


            return items;

        }).collect(Collectors.toList());
        //System.out.println(list);
       // List<ContainerDto> containerDto = containers.stream().map(ContainerDto::from).collect(Collectors.toList());

        return containerService.getAllContainers();
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
