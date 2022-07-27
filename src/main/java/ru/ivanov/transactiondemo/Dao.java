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

  public Long createCustomer(Customer customer) {
    String sql = "INSERT INTO customer (fio, money) "
      + "VALUES (:fio, :money) RETURNING ID";
    SqlParameterSource parameterSource = new MapSqlParameterSource("fio", customer.getFio())
      .addValue("money", customer.getMoney());
    return template.queryForObject(sql, parameterSource, Long.class);
  }

  public Long createPurchase(Purchase purchase) {
    String sql = "INSERT INTO purchase (info, price) "
      + "VALUES (:info, :price) RETURNING ID";
    SqlParameterSource parameterSource = new MapSqlParameterSource("info", purchase.getInfo())
      .addValue("price", purchase.getPrice());
    return template.queryForObject(sql, parameterSource, Long.class);
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
