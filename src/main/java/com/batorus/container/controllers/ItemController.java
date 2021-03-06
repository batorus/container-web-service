package com.batorus.container.controllers;

import com.batorus.container.models.Item;
import com.batorus.container.models.Tag;
import com.batorus.container.services.ItemService;
import com.batorus.container.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/v1")
public class ItemController {

//    @Autowired
//    ContainerService containerService;

    @Autowired
    ItemService itemService;

    @Autowired
    TagService tagService;

    @GetMapping("/containers/{containerId}/items")
    public List<Item> getAllItemsByContainerIdAction(@PathVariable(value = "containerId") Long containerId) {

        return itemService.getAllItemsByContainerId(containerId);
    }

    @PostMapping("/containers/{containerId}/items")
    public Item createItemAction(@PathVariable Long containerId,
                                 @Valid @RequestBody Item item) {

        return itemService.save(containerId, item);
    }

    @PutMapping("/containers/{containerId}/items/{itemId}")
    public Item updateItemAction(@PathVariable Long containerId,
                                 @PathVariable Long itemId,
                                 @Valid @RequestBody Item item) {

        return itemService.update(containerId, itemId, item);
    }

    @GetMapping("/items/{itemId}")
    public Item getOneItemAction(@PathVariable Long itemId) {

        return itemService.find(itemId);
    }

    @GetMapping("/items/{itemId}/tags/{tagId}")
    public Item addTagsToItemAction(@PathVariable Long itemId, @PathVariable Long tagId) {

        Item item = itemService.find(itemId);
        Tag tag = tagService.find(tagId);
        item.getTags().add(tag);
        tag.getItems().add(item);

        return itemService.save(item);
    }

    @DeleteMapping("/items/{itemId}/tags/{tagId}")
    public Item removeTagsFromItemAction(@PathVariable Long itemId, @PathVariable Long tagId) {

        Item item = itemService.find(itemId);
        Tag tag = tagService.find(tagId);
        item.getTags().remove(tag);

        return itemService.save(item);
    }

    @GetMapping("/items/{itemId}/tags")
    public Set<Tag> getTagsForItemAction(@PathVariable Long itemId) {
        Item item = itemService.find(itemId);

        return item.getTags();
    }


    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<String> deleteItemAction(@PathVariable Long itemId) {
        itemService.delete(itemId);

        return new ResponseEntity<>("Deleted successfully!", HttpStatus.OK);
    }

}
