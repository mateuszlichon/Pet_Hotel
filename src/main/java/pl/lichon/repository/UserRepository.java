package pl.lichon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.lichon.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findOneByEmail(String email);
}
