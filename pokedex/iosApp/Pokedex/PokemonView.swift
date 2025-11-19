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
        let pokemonsRepository = SharedComponent.shared.getPokemonsRepository()
        _viewModel = StateViewModel(
            wrappedValue: PokemonViewModel(
                savedStateHandle: Lifecycle_viewmodel_savedstateSavedStateHandle(initialState: ["id": id]),
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
        ScrollView {
            VStack(alignment: .center) {
                AsyncImage(url: URL(string: uiState.sprite ?? "")) { phase in
                    phase.image?.resizable()
                }
                .frame(width: 72, height: 72)
                Text(uiState.id.map { String(format: "#%04d", $0.intValue) } ?? "")
                    .font(.caption)
                Text(uiState.name ?? "")
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
