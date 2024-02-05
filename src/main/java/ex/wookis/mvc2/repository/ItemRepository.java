package ex.wookis.mvc2.repository;

import ex.wookis.mvc2.domain.Item;

import java.util.List;

public interface ItemRepository {

    Item save(Item item);

    Item findById(Long id);

    List<Item> findAll();

    void update(Long id, Item item);

    void clear();
}
