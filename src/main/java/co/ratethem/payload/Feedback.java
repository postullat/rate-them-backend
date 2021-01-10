package co.ratethem.payload;

import lombok.Data;

@Data
public class Feedback{
    private double onTime;
    private double detailization;
    private String comment;
}