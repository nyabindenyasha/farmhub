package com.xplug.tech.cropbatch;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CropBatchRequest {

    @NotNull(message = "Farmer id cannot be null!")
    private Long farmerId;

    @NotNull(message = "Crop Program id cannot be null!")
    private Long cropProgramId;

    //todo
    //add optional cropVarietyId
    //data collection

    @NotNull(message = "Date Of Transplant cannot be null!")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateOfTransplant;

    @NotNull(message = "Location cannot be null!")
    private String location;

    private String remarks;

}
