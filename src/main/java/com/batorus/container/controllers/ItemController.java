package com.batorus.container.controllers;

import com.batorus.container.models.Item;
import com.batorus.container.services.ContainerService;
import com.batorus.container.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ItemController {

    @Autowired
    ContainerService containerService;

    @Autowired
    ItemService itemService;

    @GetMapping("/containers/{containerId}/items")
    public List<Item> getAllItemsByContainerIdAction(@PathVariable(value = "containerId") Long containerId) {

        return itemService.getAllItemsByContainerId(containerId);
    }

    @PostMapping("/containers/{containerId}/items")
    public Item createItemAction(@PathVariable Long containerId,
                                 @Valid @RequestBody Item item) {

        return itemService.save(containerId, item);
    }
}
