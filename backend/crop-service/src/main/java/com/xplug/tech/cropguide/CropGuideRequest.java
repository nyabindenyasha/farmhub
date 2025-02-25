package com.xplug.tech.cropguide;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CropGuideRequest {

    private Long cropId;

    private NurseryManagementRequest nurseryManagement;

    private FieldManagementRequest fieldManagement;

}
