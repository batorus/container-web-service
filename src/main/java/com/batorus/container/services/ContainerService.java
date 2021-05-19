package com.batorus.container.services;

import com.batorus.container.exceptions.ResourceNotFoundException;
import com.batorus.container.models.Container;
import com.batorus.container.repositories.ContainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContainerService {

    @Autowired
    ContainerRepository containerRepository;


    public List<Container> getAllContainers() {
        return containerRepository.findAll();
    }

    public Container save(Container container) {
        return containerRepository.save(container);
    }

    public Container update(Long id, Container container) {

        Optional<Container> containerOptional = containerRepository.findById(id);

        if (!containerOptional.isPresent())
            throw new ResourceNotFoundException("Container with id: " + id + " was not found!");

        Container containerToUpdate = containerOptional.get();
        containerToUpdate.setContainerName(container.getContainerName());
        containerToUpdate.setContainerCode(container.getContainerCode());

        return containerRepository.save(containerToUpdate);
    }

    public Container find(Long id) {

        Optional<Container> optional = containerRepository.findById(id);

        if (!optional.isPresent())
            throw new ResourceNotFoundException(Container.class.getSimpleName() + " with categoryId: " + id + " was not found!");

        return optional.get();
    }


    public void delete(Long id) {

        Optional<Container> optional = containerRepository.findById(id);

        if (!optional.isPresent())
            throw new ResourceNotFoundException(Container.class.getSimpleName() + " with categoryId: " + id + " was not found!");

        containerRepository.delete(optional.get());

    }

    public List<Container> saveAll(List<Container> list) {
        return containerRepository.saveAll(list);
    }

}
