package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Vegetable;
import com.example.demo.service.VegetableService;

@RestController
@CrossOrigin()
@RequestMapping("/vegetable")
public class VegetableController {

    @Autowired
    private VegetableService vegetableService;

    @PutMapping("/{id}")
    public ResponseEntity<Vegetable> update(@PathVariable long id, @RequestBody Vegetable vegetable) {
        try {
            Vegetable resultVegetable = this.vegetableService.update(id, vegetable);
            return new ResponseEntity<>(resultVegetable, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable long id) {
        try {
            this.vegetableService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<HttpStatus> deleteAll() {
        try {
            this.vegetableService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Vegetable>> getAll(@RequestParam(required = false) String name) {
        try {
            List<Vegetable> vegetables = this.vegetableService.getAll(name);
            if (vegetables.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(vegetables, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vegetable> getById(@PathVariable("id") long id) {
        Vegetable vegetable = this.vegetableService.getById(id);

        if (vegetable != null)
            return new ResponseEntity<>(vegetable, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/")
    public ResponseEntity<Vegetable> create(@RequestBody Vegetable vegetable) {
        try {
            Vegetable createdVegetable = this.vegetableService.create(vegetable);
            return new ResponseEntity<>(createdVegetable, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
