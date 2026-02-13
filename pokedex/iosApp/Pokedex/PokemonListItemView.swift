import Shared
import SwiftUI

struct PokemonListItemView: View {

    let item: PokemonListItemUiState

    var body: some View {
        VStack(alignment: .center) {
            AsyncImage(url: URL(string: item.sprite)) { phase in
                phase.image?.resizable()
            }
            .frame(width: 72, height: 72)
            Text(String(format: "#%04d", item.id))
                .font(.caption)
                .foregroundStyle(Color.primary)
            Text(item.name)
                .font(.body)
                .foregroundStyle(Color.primary)
        }
        .padding()
    }

}

#Preview {
    let item = PokemonListItemUiState(id: 1, name: "Bulbasaur", sprite: "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png")
    PokemonListItemView(item: item)
}
