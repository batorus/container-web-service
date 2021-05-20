package com.batorus.container.services;

import com.batorus.container.exceptions.ResourceNotFoundException;
import com.batorus.container.models.Container;
import com.batorus.container.models.Tag;
import com.batorus.container.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    @Autowired
    TagRepository tagRepository;

    public List<Tag> getAllTags() {

        return tagRepository.findAll();
    }

    public Tag save(Tag tag) {

        return tagRepository.save(tag);
    }

    public Tag update(Long id, Tag tag) {

        Optional<Tag> optional = tagRepository.findById(id);

        if (!optional.isPresent())
            throw new ResourceNotFoundException("Tag with id: " + id + " was not found!");

        Tag tagToUpdate = optional.get();
        tagToUpdate.setTagName(tag.getTagName());

        return tagRepository.save(tagToUpdate);
    }

    public Tag find(Long id) {

        Optional<Tag> optional = tagRepository.findById(id);

        if (!optional.isPresent())
            throw new ResourceNotFoundException(Tag.class.getSimpleName() + " with id: " + id + " was not found!");

        return optional.get();
    }

    public void delete(Long id) {

        Optional<Tag> optional = tagRepository.findById(id);

        if (!optional.isPresent())
            throw new ResourceNotFoundException(Tag.class.getSimpleName() + " with categoryId: " + id + " was not found!");

        tagRepository.delete(optional.get());

    }
}
