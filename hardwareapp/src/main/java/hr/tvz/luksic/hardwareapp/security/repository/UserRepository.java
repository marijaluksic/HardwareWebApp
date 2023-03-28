package hr.tvz.luksic.hardwareapp.security.repository;

import hr.tvz.luksic.hardwareapp.security.domain.Appuser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Appuser, Long> {

    Optional<Appuser> findByUsername(String username);

}
