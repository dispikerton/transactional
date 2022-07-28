package ru.ivanov.transactiondemo;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class Dao {

  private final NamedParameterJdbcTemplate template;

  public Dao(DataSource dataSource) {
    this.template = new NamedParameterJdbcTemplate(dataSource);
  }

  public void createCustomer(Customer customer) {
    String sql = "INSERT INTO customer (fio, money) "
      + "VALUES (:fio, :money)";
    SqlParameterSource parameterSource = new MapSqlParameterSource("fio", customer.getFio())
      .addValue("money", customer.getMoney());
    template.update(sql, parameterSource);
  }

  public void createPurchase(Purchase purchase) {
    String sql = "INSERT INTO purchase (customer_id, price) "
      + "VALUES (:customerId, :price)";
    SqlParameterSource parameterSource = new MapSqlParameterSource("price", purchase.getPrice())
      .addValue("customerId", purchase.getCustomerId());
    template.update(sql, parameterSource);
  }

  public void writeOffMoney(Purchase purchase) {
    String sql = "update customer "
      + "set \"money\" = (SELECT c.\"money\" FROM customer c where id = :id FOR update) - :price "
      + "where id = :id";
    SqlParameterSource parameterSource = new MapSqlParameterSource("price", purchase.getPrice())
      .addValue("id", purchase.getCustomerId());
    template.update(sql, parameterSource);
  }
}
