package ru.ivanov.transactiondemo;

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

  @GetMapping("/order")
  public void makeOrder() {
    Purchase purchase = new Purchase();
    purchase.setInfo("Не кладите в пакеты");
    purchase.setPrice(200.00);
    purchase.setCustomerId(1);

    dao.createPurchase(purchase);
    dao.writeOffMoney(purchase);
  }

  @GetMapping("/customer")
  public void createCustomer() {
    Customer customer = new Customer();
    customer.setFio("Дмитрий");
    customer.setMoney(5000.50);
    dao.createCustomer(customer);
  }
}
