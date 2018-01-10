package pl.lichon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.lichon.entity.Month;

public interface MonthRepository extends JpaRepository<Month, Long> {

}
