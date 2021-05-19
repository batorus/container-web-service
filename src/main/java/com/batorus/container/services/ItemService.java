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


}
