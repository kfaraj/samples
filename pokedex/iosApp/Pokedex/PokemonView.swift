import KMPObservableViewModelSwiftUI
import Shared
import SwiftUI

struct PokemonView: View {

    @StateViewModel var viewModel: PokemonViewModel

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
                    .foregroundStyle(Color.primary)
                Text(uiState.name ?? "")
                    .font(.body)
                    .foregroundStyle(Color.primary)
            }
            .padding()
        }
    }

}

#Preview {
    let uiState = PokemonUiState(id: 1, name: "Bulbasaur", sprite: "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png")
    PokemonContentView(uiState: uiState)
}
