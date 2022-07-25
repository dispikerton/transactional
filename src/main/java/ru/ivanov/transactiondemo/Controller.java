package ru.ivanov.transactiondemo;

import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class Controller {
  private final TransactionTemplate template;
  private final Dao dao;

  public Controller(TransactionTemplate template, Dao dao) {
    this.template = template;
    this.dao = dao;
  }

  @GetMapping
  public void test() {
    Customer customer = new Customer();
    customer.setFio("Дмитрий");

    Purchase purchase = new Purchase();
    purchase.setProduct("Хлеб");

    String execute = template.execute(status -> {
      Long customerId = dao.createCustomer(customer);
      Long purchaseId = dao.createPurchase(purchase);
      return "cId " + customerId + " , pId " + purchaseId;
    });
    log.info("number {}", execute);
  }
}
