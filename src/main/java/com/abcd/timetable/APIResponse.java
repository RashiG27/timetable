package com.abcd.timetable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse implements Serializable {

    private static final long serialVersionUID = 9022363190793067009L;

    protected LocalDateTime localDateTime;
    protected String status;
    protected Integer code;
    protected String message;
    protected Object data;

    public static APIResponse getSuccessResponse(Object data){
        return new APIResponse(LocalDateTime.now(), "Success", 200, "Success", data);
    }

    public static APIResponse getSuccessResponse(Object data, HttpStatus code){
        return new APIResponse(LocalDateTime.now(), "Success", code.value(), " Success", data);
    }
}
