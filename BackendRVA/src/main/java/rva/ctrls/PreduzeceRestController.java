package rva.ctrls;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rva.jpa.Obrazovanje;
import rva.jpa.Preduzece;
import rva.reps.ObrazovanjeRepository;
import rva.reps.PreduzeceRepository;

@RestController
@Api(tags = {"Preduzece CRUD operacije"})
public class PreduzeceRestController {
	@Autowired
	private PreduzeceRepository preduzeceRepository;
	@Autowired
	private JdbcTemplate jdbc;
	
	@GetMapping("/preduzece")
	@ApiOperation(value = "Vraæa kolekciju svih preduzece iz baze podataka")
	public Collection<Preduzece> getPreduzece() {
		return preduzeceRepository.findAll();
	}
	
	@GetMapping("/preduzeceId/{id}")
	@ApiOperation(value = "Vraæa preduzece iz baze podataka èiji je id vrednost prosleðena kao path varijabla")
	public Preduzece getPreduzeceId(@PathVariable("id") Integer id) {
		return preduzeceRepository.getOne(id);
	}
	
	@GetMapping("/preduzeceNaziv/{naziv}")
	@ApiOperation(value = "Vraæa kolekciju preduzece iz baze podataka koji u nazivu sadrže string prosleðen kao path varijabla")
	public Collection<Preduzece> getPreduzeceNaziv(@PathVariable("naziv") String naziv) {
		return preduzeceRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@DeleteMapping("/preduzece/{id}")
	@CrossOrigin
	@ApiOperation(value = "Briše preduzece u bazi podataka èiji je id vrednost prosleðena kao path varijabla")
	public ResponseEntity<Preduzece> deletePreduzece(@PathVariable("id") Integer id) {
		if (!preduzeceRepository.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		preduzeceRepository.deleteById(id);
		if (id == -100) {
			jdbc.execute("INSERT INTO \"preduzece\"(\"id\", \"naziv\", \"pib\", \"sediste\", \"opis\")VALUES(-100, 'Naziv test', -100 ,'Sediste test', 'Opis test')");
		}
		return new ResponseEntity<> (HttpStatus.OK);
	}
	
	@PostMapping("/preduzece")
	@CrossOrigin
	@ApiOperation(value = "Upisuje preduzece u bazi podataka")
	public ResponseEntity<Preduzece> insertPreduzece(@RequestBody Preduzece preduzece) {
		if (!preduzeceRepository.existsById(preduzece.getId())) {
			preduzeceRepository.save(preduzece);
			return new ResponseEntity<> (HttpStatus.OK);
		}
		return new ResponseEntity<> (HttpStatus.CONFLICT);
	}
	
	@PutMapping("/preduzece")
	@CrossOrigin
	@ApiOperation(value = "Modifikuje postojeæeg preduzece u bazi podataka")
	public ResponseEntity<Preduzece> updatePreduzece(@RequestBody Preduzece preduzece) {
		if (!preduzeceRepository.existsById(preduzece.getId())) {
			return new ResponseEntity<> (HttpStatus.CONFLICT);
		}
		preduzeceRepository.save(preduzece);
		return new ResponseEntity<> (HttpStatus.OK);
	}
}
