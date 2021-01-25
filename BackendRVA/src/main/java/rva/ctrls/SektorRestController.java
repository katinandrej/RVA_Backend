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
import rva.jpa.Preduzece;
import rva.jpa.Sektor;
import rva.reps.PreduzeceRepository;
import rva.reps.SektorRepository;

@RestController
@Api(tags = {"Sektor CRUD operacije"})
public class SektorRestController {
	@Autowired
	private SektorRepository sektorRepository;
	@Autowired
	private JdbcTemplate jdbc;
	
	@GetMapping("sektor")
	@ApiOperation(value = "Vraæa kolekciju svih sektora iz baze podataka")
	public Collection<Sektor> getSektor() {
		return sektorRepository.findAll();
	}
	
	@GetMapping("sektorId/{id}")
	@ApiOperation(value = "Vraæa sektor iz baze podataka èiji je id vrednost prosleðena kao path varijabla")
	public Sektor getSektorId(@PathVariable("id") Integer id) {
		return sektorRepository.getOne(id);
	}
	
	@GetMapping("sektorNaziv/{naziv}")
	@ApiOperation(value = "Vraæa kolekciju sektora iz baze podataka koji u nazivu sadrže string prosleðen kao path varijabla")
	public Collection<Sektor> getSektorNaziv(@PathVariable("naziv") String naziv) {
		return sektorRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@CrossOrigin
	@DeleteMapping("sektor/{id}")
	@ApiOperation(value = "Briše sektor u bazi podataka èiji je id vrednost prosleðena kao path varijabla")
	public ResponseEntity<Sektor> deleteSektor(@PathVariable("id") Integer id) {
		if (!sektorRepository.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		sektorRepository.deleteById(id);
		if (id == -100) {
			jdbc.execute("INSERT INTO \"sektor\"(\"id\", \"naziv\", \"oznaka\", \"preduzece\")VALUES(-100, 'Naziv test', 'prvi' , 3)");
		}
		return new ResponseEntity<> (HttpStatus.OK);
	}
	
	@CrossOrigin
	@PostMapping("sektor")
	@ApiOperation(value = "Upisuje sektor u bazi podataka")
	public ResponseEntity<Sektor> insertSektor(@RequestBody Sektor sektor) {
		if (!sektorRepository.existsById(sektor.getId())) {
			sektorRepository.save(sektor);
			return new ResponseEntity<> (HttpStatus.OK);
		}
		return new ResponseEntity<> (HttpStatus.CONFLICT);
	}
	
	@CrossOrigin
	@PutMapping("sektor")
	@ApiOperation(value = "Modifikuje postojeæi sektor u bazi podataka")
	public ResponseEntity<Sektor> updateSektor(@RequestBody Sektor sektor) {
		if (!sektorRepository.existsById(sektor.getId())) {
			return new ResponseEntity<> (HttpStatus.CONFLICT);
		}
		sektorRepository.save(sektor);
		return new ResponseEntity<> (HttpStatus.OK);
	}
}
