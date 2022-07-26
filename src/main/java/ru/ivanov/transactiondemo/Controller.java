package ru.ivanov.transactiondemo;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class Controller {
  private final Dao dao;

  public Controller(Dao dao) {
    this.dao = dao;
  }

  @GetMapping
  @Transactional
  public void test() {
    Customer customer = new Customer();
    customer.setFio("Дмитрий");

    Purchase purchase = new Purchase();
    purchase.setInfo("Булка хлеба");

    save(customer, purchase);
  }

  public void save(Customer customer, Purchase purchase) {
    dao.createCustomer(customer);
    throwException();
    dao.createPurchase(purchase);
  }

  private void throwException (){
    throw new RuntimeException();
  }
}
