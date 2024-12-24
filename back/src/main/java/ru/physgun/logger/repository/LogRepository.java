package ru.physgun.logger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.physgun.logger.model.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

}
