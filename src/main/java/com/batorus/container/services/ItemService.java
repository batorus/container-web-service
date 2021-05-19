package com.batorus.container.services;

import com.batorus.container.exceptions.ResourceNotFoundException;
import com.batorus.container.models.Container;
import com.batorus.container.models.Item;
import com.batorus.container.repositories.ContainerRepository;
import com.batorus.container.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    ContainerRepository containerRepository;

    @Autowired
    ItemRepository itemRepository;

    public List<Item> getAllItemsByContainerId(Long id) {

        return itemRepository.findByContainerId(id);
    }

    public Item save(Long id, Item item) {

        Optional<Container> optional = containerRepository.findById(id);

        if (!optional.isPresent())
            throw new ResourceNotFoundException(Container.class.getName()+" with id: " + id + " was not found!");

        item.setContainer(optional.get());

        return itemRepository.save(item);
    }

    public Item save(Item item){
        return itemRepository.save(item);
    }

    public Item update(Long containerId, Long itemId, Item item) {

        Optional<Container> containerOptional = containerRepository.findById(containerId);

        if (!containerOptional.isPresent())
            throw new ResourceNotFoundException("Container with containerId: " + containerId + " was not found!");

        Optional<Item> itemOptional = itemRepository.findById(itemId);

        if (!itemOptional.isPresent())
            throw new ResourceNotFoundException("Item with itemId: " + itemId + " was not found!");

        itemOptional.get().setItemName(item.getItemName());
        itemOptional.get().setItemDescription(item.getItemDescription());
        itemOptional.get().setItemPrice(item.getItemPrice());
        itemOptional.get().setItemCode(item.getItemCode());

        return itemRepository.save(itemOptional.get());
    }

    public Item find(Long id) {
        Optional<Item> optional = itemRepository.findById(id);

        if (!optional.isPresent())
            throw new ResourceNotFoundException("Item with id: " + id + " was not found!");

        return optional.get();
    }

    public void delete(Long id) {
        Optional<Item> optional = itemRepository.findById(id);

        if (!optional.isPresent())
            throw new ResourceNotFoundException("Item with id: " + id + " was not found!");

        itemRepository.delete(optional.get());
    }

}
