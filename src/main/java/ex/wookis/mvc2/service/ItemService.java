package ex.wookis.mvc2.service;

import ex.wookis.mvc2.domain.Item;
import ex.wookis.mvc2.domain.UploadFile;
import ex.wookis.mvc2.form.ItemSaveForm;
import ex.wookis.mvc2.form.ItemUpdateForm;
import ex.wookis.mvc2.repository.FileStore;
import ex.wookis.mvc2.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final FileStore store;

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findItem(Long itemId) {
        return itemRepository.findById(itemId);
    }

    public Item saveItem(ItemSaveForm form) throws IOException {

        UploadFile attachFile = store.storeFile(form.getAttachFile());
        List<UploadFile> imageFiles = store.storeFileList(form.getImageFiles());

        return itemRepository.save(Item.builder()
                .itemName(form.getItemName())
                .price(form.getPrice())
                .quantity(form.getQuantity())
                .open(form.isOpen())
                .regions(form.getRegions())
                .itemType(form.getItemType())
                .deliveryCode(form.getDeliveryCode())
                .attachFile(attachFile)
                .imageFiles(imageFiles)
                .build());
    }

    public void updateItem(Long itemId, ItemUpdateForm form) throws IOException {
        UploadFile attachFile = store.storeFile(form.getAttachFile());
        List<UploadFile> imageFiles = store.storeFileList(form.getImageFiles());

        itemRepository.update(itemId, Item.builder()
                .itemName(form.getItemName())
                .price(form.getPrice())
                .quantity(form.getQuantity())
                .open(form.isOpen())
                .regions(form.getRegions())
                .itemType(form.getItemType())
                .deliveryCode(form.getDeliveryCode())
                .attachFile(attachFile)
                .imageFiles(imageFiles)
                .build());
    }

    public ResponseEntity<Resource> download(Long itemId) throws MalformedURLException {
        Item item = findItem(itemId);
        String storeFileName = item.getAttachFile().getStoreFileName();
        String uploadFileName = item.getAttachFile().getUploadFileName();

        UrlResource urlResource = new UrlResource("file:" + store.getFullPath(storeFileName));

        log.info("Upload File Name = {}", uploadFileName);
        String encodedUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedUploadFileName +"\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(urlResource);
    }

    public Resource image(String fileName) throws MalformedURLException {
        log.info("File Name is {}",fileName);
        return new UrlResource("file:"+store.getFullPath(fileName));
    }
}
