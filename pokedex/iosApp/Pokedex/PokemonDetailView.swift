import KMPObservableViewModelSwiftUI
import Shared
import SwiftUI

struct PokemonDetailView: View {
    @StateViewModel var viewModel: PokemonDetailViewModel

    var body: some View {
        PokemonDetailViewSnapshot(uiState: viewModel.uiStateValue)
    }
}

struct PokemonDetailViewSnapshot: View {
    let uiState: PokemonDetailUiState

    var body: some View {
        ScrollView {
            VStack {
                AsyncImage(url: URL(string: uiState.sprite ?? "")) { phase in
                    phase.image?.resizable()
                }
                .frame(width: 194, height: 194)
                Text(uiState.id.map { String(format: "#%04d", $0.intValue) } ?? "")
                    .font(.subheadline)
                Text(uiState.name ?? "")
                    .font(.body)
            }
        }
    }
}

#Preview {
    let uiState = PokemonDetailUiState(
        id: 1,
        name: "Bulbasaur",
        sprite: "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
    )
    PokemonDetailViewSnapshot(uiState: uiState)
}
