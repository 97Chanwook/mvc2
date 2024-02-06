package ex.wookis.mvc2.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Item 관리 모델
 */
@Data
@NoArgsConstructor
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;
    private boolean open;
    private List<String> regions;
    private ItemType itemType;
    private String deliveryCode;

    @Builder
    public Item(String itemName, Integer price, Integer quantity, boolean open, List<String> regions, ItemType itemType, String deliveryCode) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.open = open;
        this.regions = regions;
        this.itemType = itemType;
        this.deliveryCode = deliveryCode;
    }
}
