package com.xplug.tech.crop.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Embeddable
public class NurserySpacing {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "minimumSpacing", column = @Column(name = "sowing_depth_min_spacing")),
            @AttributeOverride(name = "maximumSpacing", column = @Column(name = "sowing_depth_max_spacing")),
            @AttributeOverride(name = "unit", column = @Column(name = "sowing_depth_unit")),
            @AttributeOverride(name = "description", column = @Column(name = "sowing_depth_description"))
    })
    private Spacing sowingDepth;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "minimumSpacing", column = @Column(name = "in_row_spacing_min_spacing")),
            @AttributeOverride(name = "maximumSpacing", column = @Column(name = "in_row_spacing_max_spacing")),
            @AttributeOverride(name = "unit", column = @Column(name = "in_row_spacing_unit")),
            @AttributeOverride(name = "description", column = @Column(name = "in_row_spacing_description"))
    })
    private Spacing inRowSpacing;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "minimumSpacing", column = @Column(name = "inter_row_spacing_min_spacing")),
            @AttributeOverride(name = "maximumSpacing", column = @Column(name = "inter_row_spacing_max_spacing")),
            @AttributeOverride(name = "unit", column = @Column(name = "inter_row_spacing_unit")),
            @AttributeOverride(name = "description", column = @Column(name = "inter_row_spacing_description"))
    })
    private Spacing interRowSpacing;

}


