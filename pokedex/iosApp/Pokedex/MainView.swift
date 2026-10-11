import Shared
import SwiftUI

struct MainView: View {
    @State private var path = NavigationPath()

    var body: some View {
        NavigationStack(path: $path) {
            PokemonListView(viewModel: PokemonListViewModelFactory.shared.create()) { itemId in
                if let itemId {
                    let key = PokemonDetailKey(id: Int32(itemId))
                    path.append(key)
                }
            }
            .navigationTitle("Pokédex")
            .navigationDestination(for: PokemonDetailKey.self) { key in
                PokemonDetailView(viewModel: PokemonDetailViewModelFactory.shared.create(key: key))
            }
        }
    }
}
