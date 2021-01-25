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
import rva.reps.ObrazovanjeRepository;

@RestController
@Api(tags = {"Obrazovanje CRUD operacije"})
public class ObrazovanjeRestController {
	@Autowired
	private ObrazovanjeRepository obrazovanjeRepository;
	@Autowired
	private JdbcTemplate jdbc;
	
	@GetMapping("obrazovanje")
	@ApiOperation(value = "Vraæa kolekciju svih obrazovanja iz baze podataka")
	public Collection<Obrazovanje> getObrazovanje() {
		return obrazovanjeRepository.findAll();
	}
	
	@GetMapping("obrazovanjeId/{id}")
	@ApiOperation(value = "Vraæa obrazovanja iz baze podataka èiji je id vrednost prosleðena kao path varijabla")
	public Obrazovanje getObrazovanjeId(@PathVariable("id") Integer id) {
		return obrazovanjeRepository.getOne(id);
	}
	
	@GetMapping("obrazovanjeNaziv/{naziv}")
	@ApiOperation(value = "Vraæa kolekciju obrazovanja iz baze podataka koji u nazivu sadrže string prosleðen kao path varijabla")
	public Collection<Obrazovanje> getObrazovanjeNaziv(@PathVariable("naziv") String naziv) {
		return obrazovanjeRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@CrossOrigin
	@DeleteMapping("obrazovanje/{id}")
	@ApiOperation(value = "Briše obrazovanje u bazi podataka èiji je id vrednost prosleðena kao path varijabla")
	public ResponseEntity<Obrazovanje> deleteObrazovanje(@PathVariable("id") Integer id) {
		if (!obrazovanjeRepository.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		obrazovanjeRepository.deleteById(id);
		if (id == -100) {
			jdbc.execute("INSERT INTO \"obrazovanje\"(\"id\", \"naziv\", \"stepen_strucne_spreme\", \"opis\")VALUES(-100, 'Naziv test', 'Test', 'Opis test')");
		}
		return new ResponseEntity<> (HttpStatus.OK);
	}
	
	@CrossOrigin
	@PostMapping("obrazovanje")
	@ApiOperation(value = "Upisuje obrazovanje u bazi podataka")
	public ResponseEntity<Obrazovanje> insertObrazovanje(@RequestBody Obrazovanje obrazovanje) {
		if (!obrazovanjeRepository.existsById(obrazovanje.getId())) {
			obrazovanjeRepository.save(obrazovanje);
			return new ResponseEntity<> (HttpStatus.OK);
		}
		return new ResponseEntity<> (HttpStatus.CONFLICT);
	}
	
	@CrossOrigin
	@PutMapping("obrazovanje")
	@ApiOperation(value = "Modifikuje postojeæeg obrazovanje u bazi podataka")
	public ResponseEntity<Obrazovanje> updateObrazovanje(@RequestBody Obrazovanje obrazovanje) {
		if (!obrazovanjeRepository.existsById(obrazovanje.getId())) {
			return new ResponseEntity<> (HttpStatus.CONFLICT);
		}
		obrazovanjeRepository.save(obrazovanje);
		return new ResponseEntity<> (HttpStatus.OK);
	}
}
