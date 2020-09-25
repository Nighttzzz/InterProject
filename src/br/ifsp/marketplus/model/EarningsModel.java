package br.ifsp.marketplus.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class EarningsModel {

    private double daily;
    private double weekly;
    private double monthly;

    public void addDaily(double amount) {
        this.daily += amount;
    }

    public void addWeekly(double amount) {
        this.weekly += amount;
    }

    public void addMonthly(double amount) {
        this.monthly += amount;
    }

}
