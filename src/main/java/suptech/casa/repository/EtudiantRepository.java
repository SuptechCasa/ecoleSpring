package suptech.casa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import suptech.casa.model.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

}
