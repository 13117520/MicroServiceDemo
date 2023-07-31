package org.example;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> createMultipleItems(List<Item> items) {
        return itemRepository.saveAll(items);
    }

    public boolean deleteItem(Long itemId) {
        if (itemRepository.existsById(itemId)) {
            itemRepository.deleteById(itemId);
            return true;
        }
        return false;
    }

    public Item updateItem(Long itemId, Item updatedItem) {
        Item existingItem = itemRepository.findById(itemId).orElse(null);
        if (existingItem != null) {
            // Update fields of existingItem with fields from updatedItem
            // Assuming that Item class has setters for the fields.
            existingItem.setName(updatedItem.getName());
            // Set other fields accordingly

            return itemRepository.save(existingItem);
        }
        return null;
    }

    public Item getItemById(Long itemId) {
        return itemRepository.findById(itemId).orElse(null);
    }

    public List<Item> searchItems(Long itemId, String name, String otherField) {
        // Implement search logic based on the provided parameters
        // You can use custom query methods in the repository or implement a custom search method in the service.
        // For simplicity, let's assume that you have custom query methods in the ItemRepository for this example.
        return itemRepository.findByIdOrNameOrOtherField(itemId, name, otherField);
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
}
