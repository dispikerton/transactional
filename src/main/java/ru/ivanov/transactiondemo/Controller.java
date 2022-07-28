package ru.ivanov.transactiondemo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class Controller {
  private final Dao dao;

  public Controller(Dao dao) {
    this.dao = dao;
  }

  @PostMapping("/order")
  public void makeOrder(@RequestBody Purchase purchase) {
    dao.createPurchase(purchase);
    dao.writeOffMoney(purchase);
  }

  @PostMapping("/customer")
  public void createCustomer(@RequestBody Customer customer) {
    dao.createCustomer(customer);
  }
}
