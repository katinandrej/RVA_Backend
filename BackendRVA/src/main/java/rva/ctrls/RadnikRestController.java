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
import rva.jpa.Radnik;
import rva.jpa.Sektor;
import rva.reps.ObrazovanjeRepository;
import rva.reps.RadnikRepository;
import rva.reps.SektorRepository;

@RestController
@Api(tags = {"Radnik CRUD operacije"})
public class RadnikRestController {
	@Autowired
	private RadnikRepository radnikRepository;
	@Autowired
	private SektorRepository sektorRepository;
	@Autowired
	private JdbcTemplate jdbc;
	
	@GetMapping("radnik")
	@ApiOperation(value = "Vraæa kolekciju svih radnika iz baze podataka")
	public Collection<Radnik> getRadnik() {
		return radnikRepository.findAll();
	}
	
	@GetMapping(value = "radnikZaSektorId/{id}")
	public Collection<Radnik> radnikZaSektorId(@PathVariable("id") int id){
		Sektor s = sektorRepository.getOne(id);
		return radnikRepository.findBySektor(s);
	}
	
	@GetMapping("radnikId/{id}")
	@ApiOperation(value = "Vraæa radnika iz baze podataka èiji je id vrednost prosleðena kao path varijabla")
	public Radnik getRadnikId(@PathVariable("id") Integer id) {
		return radnikRepository.getOne(id);
	}
	
	@GetMapping("radnikIme/{ime}")
	@ApiOperation(value = "Vraæa kolekciju radnika iz baze podataka koji u imenu sadrže string prosleðen kao path varijabla")
	public Collection<Radnik> getRadnikIme(@PathVariable("ime") String ime) {
		return radnikRepository.findByImeContainingIgnoreCase(ime);
	}
	
	@CrossOrigin
	@DeleteMapping("radnik/{id}")
	@ApiOperation(value = "Briše radnika u bazi podataka èiji je id vrednost prosleðena kao path varijabla")
	public ResponseEntity<Radnik> deleteRadnik(@PathVariable("id") Integer id) {
		if (!radnikRepository.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		radnikRepository.deleteById(id);
		if (id == -100) {
			jdbc.execute("INSERT INTO \"radnik\"(\"id\", \"ime\", \"prezime\", \"broj_lk\", \"obrazovanje\", \"sektor\")VALUES(-100, 'Ime test', 'Prezime test', -100, 3, 5)");
		}
		return new ResponseEntity<> (HttpStatus.OK);
	}
	
	@CrossOrigin
	@PostMapping("radnik")
	@ApiOperation(value = "Upisuje radnika u bazi podataka")
	public ResponseEntity<Radnik> insertRadnik(@RequestBody Radnik radnik) {
		if (!radnikRepository.existsById(radnik.getId())) {
			radnikRepository.save(radnik);
			return new ResponseEntity<> (HttpStatus.OK);
		}
		return new ResponseEntity<> (HttpStatus.CONFLICT);
	}
	
	@CrossOrigin
	@PutMapping("radnik")
	@ApiOperation(value = "Modifikuje postojeæeg radnika u bazi podataka")
	public ResponseEntity<Radnik> updateRadnik(@RequestBody Radnik radnik) {
		if (!radnikRepository.existsById(radnik.getId())) {
			return new ResponseEntity<> (HttpStatus.CONFLICT);
		}
		radnikRepository.save(radnik);
		return new ResponseEntity<> (HttpStatus.OK);
	}
}
