package com.xplug.tech.crop.data;

import com.xplug.tech.crop.Pesticide;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Set;

public class PestsAndDiseases {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String alias;

    private String description;

    private String fileUrl;

    private Set<Pesticide> control;



}
