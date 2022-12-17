package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Color;
import com.example.demo.repository.ColorRepository;

@Service
public class ColorService {

    @Autowired
    private ColorRepository colorRepository;

    public Color getById(long id) {
        Optional<Color> colorData = this.colorRepository.findById(id);
        if (colorData.isPresent())
            return colorData.get();
        return null;

        // TODO: internal_server_errorのパターン不足
    }

    public Color create(Color color) {
        LocalDateTime now = LocalDateTime.now();
        color.setCreatedAt(now);
        color.setUpdatedAt(now);
        return this.colorRepository.save(color);

    }

}
