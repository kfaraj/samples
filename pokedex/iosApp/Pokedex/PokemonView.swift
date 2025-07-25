//
//  PokemonView.swift
//  Pokedex
//
//  Created by Kamal Faraj on 17/07/2025.
//

import Shared
import SwiftUI

struct PokemonView: View {

    let pokemon: Pokemon

    var body: some View {
        HStack(alignment: .center) {
            AsyncImage(url: URL(string: pokemon.sprite)) { phase in
                phase.image?.resizable()
            }
            .frame(width: 72, height: 72)
            VStack(alignment: .leading) {
                Text(String(format: "#%04d", pokemon.id))
                    .font(.caption)
                Text(pokemon.name)
                    .font(.body)
            }
        }
    }

}

#Preview {
    let pokemon = Pokemon(id: 1, name: "Bulbasaur", sprite: "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png")
    PokemonView(pokemon: pokemon)
}
