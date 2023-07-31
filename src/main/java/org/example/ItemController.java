package org.example;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public Item createItem(@RequestBody Item item) {
        return itemService.createItem(item);
    }

    @PostMapping("/bulk")
    public List<Item> createMultipleItems(@RequestBody List<Item> items) {
        return itemService.createMultipleItems(items);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long itemId) {
        boolean deleted = itemService.deleteItem(itemId);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PutMapping("/{itemId}")
    public Item updateItem(@PathVariable Long itemId, @RequestBody Item item) {
        return itemService.updateItem(itemId, item);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<Item> getItemById(@PathVariable Long itemId) {
        Item item = itemService.getItemById(itemId);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public List<Item> searchItems(
            @RequestParam(required = false) Long itemId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String otherField
    ) {
        return itemService.searchItems(itemId, name, otherField);
    }

    @GetMapping
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }
}
