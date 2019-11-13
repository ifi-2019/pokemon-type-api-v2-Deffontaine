package com.ifi.tp.pokemon_type_api.controller;

import com.ifi.tp.pokemon_type_api.bo.PokemonType;
import com.ifi.tp.pokemon_type_api.service.PokemonTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokemon-types")
class PokemonTypeController {

    private PokemonTypeService pokemonTypeService;

    public PokemonTypeController(PokemonTypeService pokemonTypeService) {
        this.pokemonTypeService = pokemonTypeService;
    }

    @GetMapping("/{id}")
    PokemonType getPokemonTypeFromId(@PathVariable int id){
        return this.pokemonTypeService.getPokemonType(id);
    }

    @GetMapping(value = "/", params = "name")
    PokemonType getPokemonNameFromId(@RequestParam String name){
        return this.pokemonTypeService.getPokemonName(name);
    }

    @GetMapping("/")
    public List<PokemonType> getAllPokemonTypes() {
        return this.pokemonTypeService.getAllPokemonTypes();
    }
}