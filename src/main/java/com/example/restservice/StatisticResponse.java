package com.example.restservice;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;
import java.util.OptionalLong;

public class StatisticResponse {
    @JsonProperty
    long totalAmount;
    @JsonProperty
    long countOfIncorrect;
    @JsonProperty
    OptionalLong max;
    @JsonProperty
    OptionalLong min;
    @JsonProperty
    Long mostCommonResult;

    public StatisticResponse(long amount, long incorrect, OptionalLong maximum, OptionalLong minimum,
                             Long commonResult)
    {
        this.totalAmount = amount;
        this.countOfIncorrect = incorrect;
        this.max = maximum;
        this.min = minimum;
        this.mostCommonResult = commonResult;
    }
}
