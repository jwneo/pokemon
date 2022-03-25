package com.jwneo.pokemon.controller;

import com.jwneo.pokemon.model.Pokedex;
import com.jwneo.pokemon.service.PokedexService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PokedexController {

    private final PokedexService pokedexService;

    @GetMapping("/pokedex")
    public List<Pokedex> findAllPokedex() {
        return pokedexService.findAll();
    }

    @GetMapping("/pokedex/{code}")
    public List<Pokedex> findPokedex(@PathVariable("code") String code) {
        return pokedexService.findByCode(code);
    }

    @GetMapping(value = "/pokedex/image/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> userSearch(@PathVariable("name") String name) throws IOException {
        InputStream imageStream = new FileInputStream("C://images/" + name);
        byte[] imageByteArray = imageStream.readAllBytes();
        imageStream.close();
        return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
    }
}
