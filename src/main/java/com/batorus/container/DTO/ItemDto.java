package com.batorus.container.DTO;

import com.batorus.container.models.Item;


import java.util.Objects;

public class ItemDto {

    private Long id;
    private String itemCode;
    private String itemDescription;
    private String itemName;

    private PlainContainerDto plainContainerDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public PlainContainerDto getPlainContainerDto() {
        return plainContainerDto;
    }

    public void setPlainContainerDto(PlainContainerDto plainContainerDto) {
        this.plainContainerDto = plainContainerDto;
    }

    public static ItemDto from(Item item){
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setItemCode(item.getItemCode());
        itemDto.setItemName(item.getItemName());
        itemDto.setItemDescription(item.getItemDescription());

        if(Objects.nonNull(item.getContainer())){
            itemDto.setPlainContainerDto(PlainContainerDto.from(item.getContainer()));
        }

        return itemDto;
    }

    @Override
    public String toString() {
        return "ItemDto{" +
                "id=" + id +
                ", itemCode='" + itemCode + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", itemName='" + itemName + '\'' +
                ", plainContainerDto=" + plainContainerDto +
                '}';
    }
}