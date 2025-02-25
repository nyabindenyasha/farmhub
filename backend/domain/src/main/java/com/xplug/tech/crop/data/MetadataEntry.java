package com.xplug.tech.crop.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class MetadataEntry {

    private String key;
    private String value;

}
