package com.itqtest.numbersgenerateservice.repository;

import com.itqtest.numbersgenerateservice.entity.GeneratedNumber;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для хранения сгенерированных номеров заказов в базе данных.
 *
 * @author Egor Nazarev
 */
@Repository
public interface GeneratedNumberRepository extends MongoRepository<GeneratedNumber, String> {
}
