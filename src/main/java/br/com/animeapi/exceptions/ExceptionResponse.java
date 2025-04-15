package br.com.animeapi.exceptions;

import java.util.Date;
import java.util.Map;

public class ExceptionResponse {
    private Date data;
    private String message;
    private String details;
    public Map<String ,String> error;

    public ExceptionResponse() {
    }



}
