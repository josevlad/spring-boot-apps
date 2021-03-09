package ar.com.ada.online.second.yourpiratemovies.model.repository.security;


import ar.com.ada.online.second.yourpiratemovies.model.entity.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
