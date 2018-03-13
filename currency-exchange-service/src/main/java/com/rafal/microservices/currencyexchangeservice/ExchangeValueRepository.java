package com.rafal.microservices.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeValueRepository extends JpaRepository<EchangeValue, Long> {

    EchangeValue findByFromAndTo(String from, String to);
}
