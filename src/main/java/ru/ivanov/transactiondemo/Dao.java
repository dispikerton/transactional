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
    String sql = "INSERT INTO customer (fio) "
      + "VALUES (:fio) RETURNING ID";
    SqlParameterSource parameterSource = new MapSqlParameterSource("fio", customer.getFio());
    return template.queryForObject(sql, parameterSource, Long.class);
  }

  public Long createPurchase(Purchase purchase) {
    String sql = "INSERT INTO purchase (info) "
      + "VALUES (:info) RETURNING ID";
    SqlParameterSource parameterSource = new MapSqlParameterSource("info", purchase.getInfo());
    return template.queryForObject(sql, parameterSource, Long.class);
  }
}
