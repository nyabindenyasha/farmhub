package com.xplug.tech.cropdata;

import com.xplug.tech.crop.CropRequest;
import com.xplug.tech.cropfertilizerschedule.CropFertilizerScheduleRequest;
import com.xplug.tech.cropguide.CropGuideRequest;
import com.xplug.tech.croppesticideschedule.CropPesticideScheduleRequest;
import com.xplug.tech.cropstagesofgrowth.CropStagesOfGrowthRequest;
import com.xplug.tech.cropvariety.CropVarietyRequest;
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

    private List<CropStagesOfGrowthRequest> stagesOfGrowth;

    private List<CropVarietyRequest> varieties;

    private CropGuideRequest cropData;

}

@Getter
@Setter
@ToString
class CropDataFertilizerScheduleRequest extends CropFertilizerScheduleRequest {

    private String fertilizerName;

}

@Getter
@Setter
@ToString
class CropDataPesticideScheduleRequest extends CropPesticideScheduleRequest {

    private String pesticideName;

}

