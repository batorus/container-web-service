package com.batorus.container.controllers;

import com.batorus.container.models.Tag;
import com.batorus.container.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TagController {

    @Autowired
    TagService tagService;

    @GetMapping(path = "/tags", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Tag> listAction() {

        return tagService.getAllTags();
    }

    @PostMapping(path = "/tags", consumes = {MediaType.APPLICATION_JSON_VALUE},
                                 produces = {MediaType.APPLICATION_JSON_VALUE})
    public Tag createAction(@Valid @RequestBody Tag tag) {

        return tagService.save(tag);
    }

    @PutMapping("/tags/{id}")
    public Tag updateAction(@PathVariable Long id, @Valid @RequestBody Tag tag) {

        return tagService.update(id, tag);
    }

    @GetMapping("/tags/{id}")
    public Tag getOneAction(@PathVariable Long id) {

        return tagService.find(id);
    }

    @DeleteMapping("/tags/{id}")
    public ResponseEntity<String> deleteAction(@PathVariable Long id) {
        tagService.delete(id);

        return new ResponseEntity<>("Deleted successfully!", HttpStatus.OK);
    }
}
