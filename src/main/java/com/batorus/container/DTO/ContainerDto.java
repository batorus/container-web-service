package com.batorus.container.DTO;

import com.batorus.container.models.Container;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ContainerDto {
    private Long id;
    private String containerName;
    private String containerCode;

    private List<ItemDto> itemsDto = new ArrayList<>();

    public ContainerDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public String getContainerCode() {
        return containerCode;
    }

    public void setContainerCode(String containerCode) {
        this.containerCode = containerCode;
    }

    public List<ItemDto> getItemsDto() {
        return itemsDto;
    }

    public void setItemsDto(List<ItemDto> itemsDto) {
        this.itemsDto = itemsDto;
    }

    public static ContainerDto from(Container container){
        ContainerDto containerDto = new ContainerDto();
        containerDto.setId(container.getId());
        containerDto.setContainerName(container.getContainerName());
        containerDto.setContainerCode(container.getContainerCode());
       // containerDto.setItemsDto(containerDto.getItems().stream().map(ItemDto::from).collect(Collectors.toList()));
        return containerDto;
    }
}