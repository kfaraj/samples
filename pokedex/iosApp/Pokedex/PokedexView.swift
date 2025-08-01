//
//  PokedexView.swift
//  Pokedex
//
//  Created by Kamal Faraj on 31/07/2025.
//

import KMPObservableViewModelSwiftUI
import Shared
import SwiftUI

struct PokedexView: View {

    @StateViewModel private var viewModel = PokedexViewModel(
        pokemonsRepository: PokemonsRepository(
            pokemonsRemoteDataSource: NetworkFactory.shared.createPokemonsRemoteDataSource(
                pokeApiService: NetworkFactory.shared.createPokeApiService(
                    httpClient: NetworkFactory.shared.createHttpClient()
                )
            )
        )
    )

    var body: some View {
        PokedexContentView(items: viewModel.pagingData)
    }

}

struct PokedexContentView: View {

    let items: [PokedexItemUiState]

    var body: some View {
        ScrollView {
            LazyVGrid(columns: [GridItem(.flexible()), GridItem(.flexible())]) {
                ForEach(items, id: \.id) { item in
                    PokedexItemView(item: item)
                }
            }
            .padding()
        }
    }

}

#Preview {
    let items = [
        PokedexItemUiState(id: 1, name: "Bulbasaur", sprite: "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png")
    ]
    PokedexContentView(items: items)
}
