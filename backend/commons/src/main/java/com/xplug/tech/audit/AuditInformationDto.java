package com.xplug.tech.audit;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class AuditInformationDto {

    private Action action;

    private Resource resource;

}
