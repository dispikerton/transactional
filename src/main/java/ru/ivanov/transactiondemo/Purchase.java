package ru.ivanov.transactiondemo;

import lombok.Data;

@Data
public class Purchase {
  private long id;
  private long customerId;
  private Double price;
}
