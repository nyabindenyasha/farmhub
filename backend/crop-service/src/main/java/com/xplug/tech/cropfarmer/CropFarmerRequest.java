package com.xplug.tech.cropfarmer;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@ToString
public class CropFarmerRequest {

    @NotNull(message = "Crop id cannot be null!")
    private Long cropId;

    @NotNull(message = "Farmer id cannot be null!")
    private Long farmerId;

    @NotNull(message = "Crop Program id cannot be null!")
    private Long cropProgramId;

    @NotNull(message = "Date Of Transplant cannot be null!")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateOfTransplant;

    @NotNull(message = "Location cannot be null!")
    private String location;

    private String remarks;

}
