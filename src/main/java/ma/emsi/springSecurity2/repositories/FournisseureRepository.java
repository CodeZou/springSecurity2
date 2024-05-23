package ma.emsi.springSecurity2.repositories;


import jakarta.transaction.Transactional;
import ma.emsi.springSecurity2.entities.Fournisseure;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface FournisseureRepository extends JpaRepository<Fournisseure, Long> {
    Page<Fournisseure> findByNameContains(String Keyword, Pageable pageable);
}

