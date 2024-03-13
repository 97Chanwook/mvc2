package ex.wookis.mvc2.repository.memory;

import ex.wookis.mvc2.domain.Item;
import ex.wookis.mvc2.repository.ItemRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemoryItemRepository implements ItemRepository {
    private static Map<Long, Item> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(),item);
        return item;
    }

    @Override
    public Item findById(Long id) {
        return store.get(id);
    }

    @Override
    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void update(Long id, Item item) {
        Item saveItem = findById(id);
        saveItem.setItemName(item.getItemName());
        saveItem.setPrice(item.getPrice());
        saveItem.setQuantity(item.getQuantity());
        saveItem.setOpen(item.isOpen());
        saveItem.setRegions(item.getRegions());
        saveItem.setItemType(item.getItemType());
        saveItem.setDeliveryCode(item.getDeliveryCode());

        if (item.getAttachFile() != null) {
            saveItem.setAttachFile(item.getAttachFile());
        }

        if (item.getImageFiles().size() > 0) {
            saveItem.setImageFiles(item.getImageFiles());
        }
    }

    @Override
    public void clear() {
        store.clear();
    }
}
