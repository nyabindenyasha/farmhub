package com.xplug.tech.pojo;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ResponseMessage {

    private String message;

    public ResponseMessage(String message) {
        this.message = message;
    }

}
