package co.ratethem.payload;

import lombok.Data;

@Data
public class Feedback{
    private int onTime;
    private int detailization;
    private String comment;
}