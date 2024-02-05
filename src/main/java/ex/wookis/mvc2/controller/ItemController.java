package ex.wookis.mvc2.controller;

import ex.wookis.mvc2.domain.DeliveryCode;
import ex.wookis.mvc2.domain.Item;
import ex.wookis.mvc2.domain.ItemType;
import ex.wookis.mvc2.form.ItemSaveForm;
import ex.wookis.mvc2.form.ItemUpdateForm;
import ex.wookis.mvc2.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemRepository repository;

    @ModelAttribute("regions")
    public Map<String, String> regions() {
        return Map.of("SEOUL","서울", "BUSAN", "부산", "JEJU","제주");
    }

    @ModelAttribute("itemTypes")
    public ItemType[] itemTypes() {
        return ItemType.values();
    }

    @ModelAttribute("deliveryCodes")
    public List<DeliveryCode> deliveryCodes() {
        return List.of(
                new DeliveryCode("FAST","빠른 배송"),
                new DeliveryCode("NORMAL","일반 배송"),
                new DeliveryCode("SLOW","느린 배송")
        );
    }

    @GetMapping
    public String items(Model model) {
        List<Item> items = repository.findAll();
        model.addAttribute("items",items);
        return "item/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable("itemId") Long itemId, Model model) {
        Item item = repository.findById(itemId);
        model.addAttribute("item",item);
        return "item/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item",new Item());
        return "item/addForm";
    }

    @PostMapping("/add")
    public String addItem(@Validated @ModelAttribute("item") ItemSaveForm form,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        log.info("Form Data = {}",form);
        //1. price * quantity 가 10000 보다 작으면 안됀다.
        if (form.getPrice() != null && form.getQuantity() != null) {
            int resultPrice = form.getPrice() * form.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin",new Object[]{10000, resultPrice}, null);
            }
        }

        if (bindingResult.hasErrors()) {
            log.info("Add Item Error = {}", bindingResult);
            return "item/addForm";
        }

        Item saveItem = repository.save(Item.builder()
                .itemName(form.getItemName())
                .price(form.getPrice())
                .quantity(form.getQuantity())
                .open(form.isOpen())
                .regions(form.getRegions())
                .itemType(form.getItemType())
                .deliveryCode(form.getDeliveryCode())
                .build());

        redirectAttributes.addAttribute("itemId",saveItem.getId());
        return "redirect:/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable("itemId") Long itemId,
                           Model model) {
        Item item = repository.findById(itemId);
        model.addAttribute("item",item);
        return "item/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable("itemId") Long itemId,
                       @Validated @ModelAttribute("item")ItemUpdateForm form,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {
        log.info("ID = {}, Form Data = {}",itemId, form);

        //1. price * quantity 가 10000 보다 작으면 안됀다.
        if (form.getPrice() != null && form.getQuantity() != null) {
            int resultPrice = form.getPrice() * form.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin",new Object[]{10000, resultPrice}, null);
            }
        }

        if (bindingResult.hasErrors()) {
            log.info("Add Item Error = {}", bindingResult);
            return "item/editForm";
        }

        repository.update(itemId, Item.builder()
                .itemName(form.getItemName())
                .price(form.getPrice())
                .quantity(form.getQuantity())
                .open(form.isOpen())
                .regions(form.getRegions())
                .itemType(form.getItemType())
                .deliveryCode(form.getDeliveryCode())
                .build());

        return "redirect:/items/{itemId}";
    }


}
