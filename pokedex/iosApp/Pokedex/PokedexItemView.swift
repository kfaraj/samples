//
//  PokedexItemView.swift
//  Pokedex
//
//  Created by Kamal Faraj on 31/07/2025.
//

import Shared
import SwiftUI

struct PokedexItemView: View {

    let item: PokedexItemUiState

    var body: some View {
        VStack(alignment: .center) {
            AsyncImage(url: URL(string: item.sprite)) { phase in
                phase.image?.resizable()
            }
            .frame(width: 72, height: 72)
            Text(String(format: "#%04d", item.id))
                .font(.caption)
            Text(item.name)
                .font(.body)
        }
        .padding()
    }

}

#Preview {
    let item = PokedexItemUiState(id: 1, name: "Bulbasaur", sprite: "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png")
    PokedexItemView(item: item)
}
