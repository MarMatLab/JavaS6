package vod.web.rest.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FigureDTO {
    private String name;
    private String material;
    @NotNull
    private int designerId;
    private float price;
}
