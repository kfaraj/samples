import KMPObservableViewModelSwiftUI
import Shared
import SwiftUI

struct PokedexView: View {

    @StateViewModel var viewModel: PokedexViewModel

    var body: some View {
        ScrollView {
            LazyVGrid(columns: [GridItem(.flexible()), GridItem(.flexible())]) {
                ForEach(viewModel.pagingDataSnapshot.indices, id: \.self) { index in
                    if let item = viewModel.get(index: Int32(index)) {
                        NavigationLink(value: PokemonRoute(id: Int(item.id))) {
                            PokedexItemView(item: item)
                        }
                    }
                }
            }
            .padding()
        }
    }

}

struct PokedexContentView: View {

    let items: [PokedexItemUiState]

    var body: some View {
        ScrollView {
            LazyVGrid(columns: [GridItem(.flexible()), GridItem(.flexible())]) {
                ForEach(items, id: \.id) { item in
                    NavigationLink(value: PokemonRoute(id: Int(item.id))) {
                        PokedexItemView(item: item)
                    }
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
