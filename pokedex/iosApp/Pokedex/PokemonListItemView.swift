import KMPObservableViewModelSwiftUI
import Shared
import SwiftUI

struct PokemonListItemView: View {
    let uiState: PokemonListItemUiState?

    var body: some View {
        VStack {
            AsyncImage(url: URL(string: uiState?.sprite ?? "")) { phase in
                phase.image?.resizable()
            }
            .frame(width: 194, height: 194)
            Text((uiState?.id).map { String(format: "#%04d", $0) } ?? "")
                .font(.subheadline)
            Text(uiState?.name ?? "")
                .font(.body)
        }
    }
}

#Preview {
    let uiState = PokemonListItemUiState(
        id: 1,
        name: "Bulbasaur",
        sprite: "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
    )
    PokemonListItemView(uiState: uiState)
}
