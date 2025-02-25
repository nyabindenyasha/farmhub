package com.xplug.tech.cropguide;

import com.xplug.tech.crop.data.MetadataEntry;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NurseryBedPreparationRequest {

    private List<FertilizerApplicationRateRequest> fertilizerApplications;

    private List<MetadataEntry> metadataList;

}
