package ex.wookis.mvc2.form;

import ex.wookis.mvc2.domain.ItemType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ItemUpdateForm {

    @NotNull
    private Long id;

    @NotBlank
    private String itemName;

    @NotNull
    @Range(min=1000, max = 1000000)
    private Integer price;

    @NotNull
    @Max(value = 9999)
    private Integer quantity;

    private boolean open;

    @NotNull
    @Size(min = 1)
    private List<String> regions;

    @NotNull
    private ItemType itemType;

    @NotBlank
    private String deliveryCode;

    private MultipartFile attachFile;

    private List<MultipartFile> imageFiles;

}
