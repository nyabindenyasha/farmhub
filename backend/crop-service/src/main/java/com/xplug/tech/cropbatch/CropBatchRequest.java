package com.xplug.tech.cropbatch;

import com.xplug.tech.crop.CropFarmer;
import com.xplug.tech.crop.CropFertilizerScheduleTask;
import com.xplug.tech.crop.CropPesticideScheduleTask;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@ToString
public class CropBatchRequest {

    @NotNull(message = "Crop Farmer Id cannot be null!")
    private Long cropFarmerId;

    @NotNull(message = "Crop Farmer Id cannot be null!")
    private Long cropProgramId;

}
