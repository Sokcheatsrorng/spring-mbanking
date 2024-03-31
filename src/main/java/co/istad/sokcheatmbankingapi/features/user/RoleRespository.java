package co.istad.sokcheatmbankingapi.features.user;

import co.istad.sokcheatmbankingapi.domain.Role;
import co.istad.sokcheatmbankingapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRespository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(String name);
}
