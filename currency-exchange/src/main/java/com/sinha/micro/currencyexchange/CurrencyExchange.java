package com.sinha.micro.currencyexchange;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "currency_exchange")
public class CurrencyExchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "currency_from")
    @Enumerated(EnumType.STRING)
    private Currency from;
    @Column(name = "currency_to")
    @Enumerated(EnumType.STRING)
    private Currency to;
    private BigDecimal conversionMultiple;
    private String environment;

}
