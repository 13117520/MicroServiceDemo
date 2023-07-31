import org.example.Item;
import org.example.ItemRepository;
import org.example.ItemService;
import org.example.MicroserviceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { MicroserviceApplication.class })
@Transactional
public class ItemServiceIntegrationTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void testCreateItem() {
        // Given
        Item item = new Item();
        item.setName("Test Item");

        // When
        Item createdItem = itemService.createItem(item);

        // Then
        assertNotNull(createdItem);
        assertEquals(item.getName(), createdItem.getName());
    }

    @Test
    public void testUpdateItem() {
        // Given
        Item existingItem = new Item();
        existingItem.setName("Existing Item");

        itemRepository.save(existingItem);

        Item updatedItem = new Item();
        updatedItem.setName("Updated Item");

        // When
        Item resultItem = itemService.updateItem(existingItem.getId(), updatedItem);

        // Then
        assertNotNull(resultItem);
        assertEquals(updatedItem.getName(), resultItem.getName());
    }

}
