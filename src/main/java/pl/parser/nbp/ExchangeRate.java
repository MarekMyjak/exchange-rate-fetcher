package pl.parser.nbp;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
class ExchangeRate {
    private String code;
    private double bid;
    private double ask;
}
