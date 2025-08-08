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

    @StateViewModel private var viewModel: PokemonViewModel

    init(id: Int) {
        let pokemonsRemoteDataSource = NetworkFactory.shared.createPokemonsRemoteDataSource()
        let pokemonsLocalDataSource = NativeDatabaseFactory.shared.createPokemonsLocalDataSource()
        let pokemonsRepository = PokemonsRepository(
            pokemonsRemoteDataSource: pokemonsRemoteDataSource,
            pokemonsLocalDataSource: pokemonsLocalDataSource
        )
        _viewModel = StateViewModel(
            wrappedValue: PokemonViewModel(
                id: Int32(id),
                pokemonsRepository: pokemonsRepository
            )
        )
    }

    var body: some View {
        PokemonContentView(uiState: viewModel.uiState)
    }

}

struct PokemonContentView: View {

    let uiState: PokemonUiState

    var body: some View {
        switch uiState {
        case let loading as PokemonUiState.Loading:
            ProgressView()
        case let success as PokemonUiState.Success:
            ScrollView {
                VStack(alignment: .center) {
                    AsyncImage(url: URL(string: success.sprite)) { phase in
                        phase.image?.resizable()
                    }
                    .frame(width: 72, height: 72)
                    Text(String(format: "#%04d", success.id))
                        .font(.caption)
                    Text(success.name)
                        .font(.body)
                }
                .padding()
            }
        case let error as PokemonUiState.Error:
            Text(error.message ?? "")
        default:
            abort()
        }
    }

}

#Preview {
    let uiState = PokemonUiState.Success(id: 1, name: "Bulbasaur", sprite: "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png")
    PokemonContentView(uiState: uiState)
}
