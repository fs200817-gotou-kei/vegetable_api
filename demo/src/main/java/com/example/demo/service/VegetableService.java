package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Vegetable;
import com.example.demo.repository.VegetableRepository;

@Service
public class VegetableService {

    @Autowired
    private VegetableRepository vegetableRepository;

    // public List<Vegetable> getAll() {
    // return this.vegetableRepository.findAll();
    // }

    public List<Vegetable> getAll(String name) {
        if (name == null)
            return this.vegetableRepository.findAll();

        return this.vegetableRepository.findByNameContaining(name);
    }

    public Vegetable update(long id, Vegetable vegetable) throws Exception {
        Optional<Vegetable> vegetableData = this.vegetableRepository.findById(id);

        if (vegetableData.isPresent()) {
            Vegetable resultVegetable = vegetableData.get();
            LocalDateTime now = LocalDateTime.now();
            resultVegetable.setUpdatedAt(now);
            resultVegetable.setName(vegetable.getName());
            resultVegetable.setColor(vegetable.getColor());
            resultVegetable.setPrice(vegetable.getPrice());
            return this.vegetableRepository.save(resultVegetable);
        }
        throw new Exception();
    }

    public void deleteById(long id) throws Exception {
        Optional<Vegetable> vegetableData = this.vegetableRepository.findById(id);

        if (vegetableData.isPresent()) {
            this.vegetableRepository.deleteById(id);
            return;
        }
        throw new Exception();
    }

    public void deleteAll() throws Exception {
        this.vegetableRepository.deleteAll();
    }

    public Vegetable getById(long id) {
        Optional<Vegetable> vegetableData = this.vegetableRepository.findById(id);

        if (vegetableData.isPresent())
            return vegetableData.get();

        return null;
    }

    public Vegetable create(Vegetable vegetable) {
        LocalDateTime now = LocalDateTime.now();
        vegetable.setCreatedAt(now);
        vegetable.setUpdatedAt(now);

        return this.vegetableRepository.save(vegetable);
    }

}
