package com.batorus.container.DTO;

import com.batorus.container.models.Container;

public class PlainContainerDto {
    private Long id;
    private String containerName;
    private String containerCode;

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

    public static PlainContainerDto from(Container container){
        PlainContainerDto plainContainerDto = new PlainContainerDto();

        plainContainerDto.setId(container.getId());
        plainContainerDto.setContainerName(container.getContainerName());
        plainContainerDto.setContainerCode(container.getContainerCode());

        return plainContainerDto;
    }
}