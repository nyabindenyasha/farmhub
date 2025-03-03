package com.xplug.tech.cropguide;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CropGuideRequest {

    private Long cropId;

    private String cropName;

    private NurseryManagementRequest nurseryManagement;

    private FieldManagementRequest fieldManagement;

}
