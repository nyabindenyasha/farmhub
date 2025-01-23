package com.xplug.tech.cropdata;

import com.xplug.tech.fertilizer.FertilizerRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class FertilizerData {

    private List<FertilizerRequest> fertilizers;

}
