package suptech.casa.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
public Etudiant addEtudiant(
		@RequestParam Long id,
		@RequestParam String nom,
		@RequestParam int age,
		@RequestParam MultipartFile photo
		) throws IllegalStateException, IOException {
	String path="src/main/resources/photos/"+id+".png";
	photo.transferTo(Path.of(path));
	String photoURL="http://localhost:8080/photos/"+id;
	Etudiant etudiant=new Etudiant(id, nom, age, photoURL);
    return etudiantService.addEtudiant(etudiant);
}

@DeleteMapping("etudiants/{id}")
public boolean deleteEtudiant(@PathVariable Long id) {
	File photo=new File("src/main/resources/photos/"+id+".png");
	if (photo.exists()) photo.delete();
	return etudiantService.deleteEtudiantById(id);
}

@PutMapping("etudiants")
public Etudiant updateEtudiant(@RequestBody Etudiant etudiant) {
    return etudiantService.updateEtudiant(etudiant);
}

@GetMapping("/photos/{id}")
public ResponseEntity<Resource> getPhoto(@PathVariable String id) {
	String path="src/main/resources/photos/"+id+".png";
	FileSystemResource photo=new FileSystemResource(path);
	if (!photo.exists()) {
		return ResponseEntity.notFound().build();
	}
    return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(photo);
}

}
