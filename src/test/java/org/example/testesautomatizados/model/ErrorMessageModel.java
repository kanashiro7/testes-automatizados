package org.example.testesautomatizados.model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class ErrorMessageModel {
    @Expose
    private String message;
}
