package suptech.casa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import suptech.casa.model.Etudiant;
import suptech.casa.service.EtudiantService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@CrossOrigin(origins = "*")
public class EtudiantController {
@Autowired EtudiantService etudiantService;
@GetMapping("etudiants")
public List<Etudiant> getAllEtudiants() {
    return etudiantService.getAllEtudiants();
}

@GetMapping("etudiants/{id}")
public Etudiant getEtudiantById(@PathVariable Long id) {
    return etudiantService.getEtudiantById(id);
}

@PostMapping("etudiants")
public Etudiant addEtudiant(@RequestBody Etudiant etudiant) {
    return etudiantService.addEtudiant(etudiant);
}

@DeleteMapping("etudiants/{id}")
public boolean deleteEtudiant(@PathVariable Long id) {
	return etudiantService.deleteEtudiantById(id);
}

@PutMapping("etudiants")
public Etudiant updateEtudiant(@RequestBody Etudiant etudiant) {
    return etudiantService.updateEtudiant(etudiant);
}
}
