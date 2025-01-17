package com.xplug.tech.crop.management.cropdata;

import com.xplug.tech.crop.management.service.crop.CropRequest;
import com.xplug.tech.crop.management.service.cropfertilizerschedule.CropFertilizerScheduleRequest;
import com.xplug.tech.crop.management.service.croppesticideschedule.CropPesticideScheduleRequest;
import com.xplug.tech.crop.management.service.cropstagesofgrowth.CropStagesOfGrowthRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CropData {

    private CropRequest crop;

    private List<CropDataFertilizerScheduleRequest> fertilizerSchedule;

    private List<CropDataPesticideScheduleRequest> pesticideSchedule;

    private List<CropDataCropStagesOfGrowthRequest> stagesOfGrowth;

}

@Getter
@Setter
@ToString
class CropDataFertilizerScheduleRequest extends CropFertilizerScheduleRequest {

    private String cropName;
    private String fertilizerName;

}

@Getter
@Setter
@ToString
class CropDataPesticideScheduleRequest extends CropPesticideScheduleRequest {

    private String cropName;
    private String pesticideName;

}

@Getter
@Setter
@ToString
class CropDataCropStagesOfGrowthRequest extends CropStagesOfGrowthRequest {

    private String cropName;

}

