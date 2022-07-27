package ru.ivanov.transactiondemo;

import lombok.Data;

@Data
public class Customer {
  private long id;
  private String fio;
  private Double money;
}
