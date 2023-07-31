package org.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    // Custom query methods for search
    List<Item> findByIdOrNameOrOtherField(Long id, String name, String otherField);
}
