//
//  ContentView.swift
//  Pokedex
//
//  Created by Kamal Faraj on 09/07/2025.
//

import Shared
import SwiftUI

struct PokedexView: View {

    let pagingData: [Pokemon]

    var body: some View {
        List(pagingData, id: \.id) { pokemon in
            PokemonView(pokemon: pokemon)
        }
    }

}

#Preview {
    let pagingData = [
        Pokemon(id: 1, name: "Bulbasaur", sprite: "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png")
    ]
    PokedexView(pagingData: pagingData)
}
