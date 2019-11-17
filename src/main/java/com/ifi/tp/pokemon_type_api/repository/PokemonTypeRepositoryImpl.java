package com.ifi.tp.pokemon_type_api.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifi.tp.pokemon_type_api.bo.PokemonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class PokemonTypeRepositoryImpl implements PokemonTypeRepository {

    private List<PokemonType> pokemons;

    public PokemonTypeRepositoryImpl() {
        try {
            var pokemonsStream = new ClassPathResource("pokemons.json").getInputStream();

            var objectMapper = new ObjectMapper();
            var pokemonsArray = objectMapper.readValue(pokemonsStream, PokemonType[].class);
            this.pokemons = Arrays.asList(pokemonsArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PokemonType findPokemonTypeById(int id) {
        System.out.println("Loading Pokemon information for Pokemon id " + id);

        for ( PokemonType p : this.pokemons) {
            if (p.getId() == id){
                return p;
            }
        }
        return null;
    }

    @Override
    public PokemonType findPokemonTypeByName(String name) {
        System.out.println("Loading Pokemon information for Pokemon name " + name);

        for ( PokemonType p : this.pokemons) {
            if (p.getName().equals(name)){
                return p;
            }
        }
        return null;
    }

    @Override
    public List<PokemonType> findAllPokemonType() {
        return this.pokemons;
    }

    @Override
    public List<PokemonType> findPokemonsByTypes(String types) {
        List<String> typesList = Arrays.asList(types.split(","));
        List<PokemonType> res = new ArrayList<>();
        for ( PokemonType p : this.pokemons) {
            if (this.pokemonHaveTypes(p, typesList)){
                res.add(p);
            }
        }
        return res;
    }

    public  boolean pokemonHaveTypes(PokemonType p, List<String> types) {
        List<String> pTypes = p.getTypes();
        for (int i = 0; i < types.size(); i++) {
            if (!pTypes.contains(types.get(i))){
                return false;
            }
        }
        return  true;
    }
}