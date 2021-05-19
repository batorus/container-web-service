package com.batorus.container.repositories;

import com.batorus.container.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    public List<Item> findByContainerId(Long id);
}
