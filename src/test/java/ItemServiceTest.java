import org.example.Item;
import org.example.ItemRepository;
import org.example.ItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {

    @InjectMocks
    private ItemService itemService;

    @Mock
    private ItemRepository itemRepository;

    @Test
    public void testCreateItem() {
        // Given
        Item item = new Item();
        item.setName("Test Item");

        // When
        when(itemRepository.save(item)).thenReturn(item);
        Item createdItem = itemService.createItem(item);

        // Then
        assertNotNull(createdItem);
        assertEquals(item.getName(), createdItem.getName());
    }

    @Test
    public void testUpdateItem() {
        // Given
        Item existingItem = new Item();
        existingItem.setId(1L);
        existingItem.setName("Existing Item");

        Item updatedItem = new Item();
        updatedItem.setName("Updated Item");

        // When
        when(itemRepository.findById(1L)).thenReturn(Optional.of(existingItem));
        when(itemRepository.save(existingItem)).thenReturn(existingItem);

        Item resultItem = itemService.updateItem(1L, updatedItem);

        // Then
        assertNotNull(resultItem);
        assertEquals(updatedItem.getName(), resultItem.getName());
    }

}