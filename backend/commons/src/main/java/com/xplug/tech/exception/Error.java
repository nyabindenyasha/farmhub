package com.xplug.tech.exception;

import lombok.*;


@Data
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Error {

    private int httpStatusCode;

    private String message;

    private String exception;

   public static Error createException(int code, String message, String exception){
       return new Error(code,message,exception);
   }
}
