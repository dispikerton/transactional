package ru.ivanov.transactiondemo;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class Controller {
  private final Dao dao;

  public Controller(Dao dao) {
    this.dao = dao;
  }

  @PostMapping("/order")
  @Transactional
  public void makeOrder(@RequestBody Purchase purchase) {
    dao.createPurchase(purchase);
    method();
    dao.writeOffMoney(purchase);
  }

  @PostMapping("/customer")
  public void createCustomer(@RequestBody Customer customer) {
    dao.createCustomer(customer);
  }


  @SneakyThrows
  private void method() {
    Thread.sleep(60000000);
  }

  // private void method() {
  //   throw new RuntimeException();
  // }
}
