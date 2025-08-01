//
//  PokemonView.swift
//  Pokedex
//
//  Created by Kamal Faraj on 31/07/2025.
//

import KMPObservableViewModelSwiftUI
import Shared
import SwiftUI

struct PokemonView: View {

    private let id: Int

    @StateViewModel private var viewModel = PokemonViewModel(
        id: 1,
        pokemonsRepository: PokemonsRepository(
            pokemonsRemoteDataSource: NetworkFactory.shared.createPokemonsRemoteDataSource(
                pokeApiService: NetworkFactory.shared.createPokeApiService(
                    httpClient: NetworkFactory.shared.createHttpClient()
                )
            )
        )
    )

    var body: some View {
        PokemonContentView(uiState: viewModel.uiState)
    }

}

struct PokemonContentView: View {

    let uiState: PokemonUiState

    var body: some View {
        ScrollView {
            VStack(alignment: .center) {
                AsyncImage(url: URL(string: "FIXME: uiState.sprite")) { phase in
                    phase.image?.resizable()
                }
                .frame(width: 72, height: 72)
                Text(String(format: "#%04d", "FIXME: uiState.id"))
                    .font(.caption)
                Text("FIXME: uiState.name")
                    .font(.body)
            }
            .padding()
        }
    }

}

#Preview {
    let uiState = PokemonUiState(id: 1, name: "Bulbasaur", sprite: "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png")
    PokemonContentView(uiState: uiState)
}
