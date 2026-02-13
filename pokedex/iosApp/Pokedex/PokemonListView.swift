import KMPObservableViewModelSwiftUI
import Shared
import SwiftUI

struct PokemonListView: View {

    @StateViewModel var viewModel: PokemonListViewModel

    var body: some View {
        let lazyPagingItems = viewModel.lazyPagingItems
        ScrollView {
            LazyVGrid(columns: [GridItem(.flexible()), GridItem(.flexible())]) {
                ForEach(0..<lazyPagingItems.itemCount, id: \.self) { index in
                    if let item = lazyPagingItems.get(index: Int32(index)) {
                        NavigationLink(value: PokemonDetailRoute(id: Int(item.id))) {
                            PokemonListItemView(item: item)
                        }
                    }
                }
            }
            .padding()
        }
    }

}

struct PokemonListContentView: View {

    let items: [PokemonListItemUiState]

    var body: some View {
        ScrollView {
            LazyVGrid(columns: [GridItem(.flexible()), GridItem(.flexible())]) {
                ForEach(items, id: \.id) { item in
                    NavigationLink(value: PokemonDetailRoute(id: Int(item.id))) {
                        PokemonListItemView(item: item)
                    }
                }
            }
            .padding()
        }
    }

}

#Preview {
    let items = [
        PokemonListItemUiState(id: 1, name: "Bulbasaur", sprite: "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png")
    ]
    PokemonListContentView(items: items)
}
