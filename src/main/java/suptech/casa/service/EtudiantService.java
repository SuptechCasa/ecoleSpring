package suptech.casa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import suptech.casa.model.Etudiant;
import suptech.casa.repository.EtudiantRepository;

@Service
public class EtudiantService {
@Autowired EtudiantRepository etudiantRepository;
	public List<Etudiant> getAllEtudiants() {
		return etudiantRepository.findAll();
	}
	public Etudiant addEtudiant(Etudiant etudiant) {
		return etudiantRepository.save(etudiant);
	}
	public Etudiant getEtudiantById(Long id) {
		return etudiantRepository.findById(id).orElse(null);
	}

}
