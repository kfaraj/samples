import Shared
import SwiftUI

struct ContentView: View {
    let title: String
    @State private var backStack = NavigationPath()

    var body: some View {
        NavigationStack(path: $backStack) {
            PokemonListView(viewModel: PokemonListViewModelFactory.shared.create()) { itemId in
                if let itemId {
                    let key = PokemonDetailKey(id: Int32(itemId))
                    backStack.append(key)
                }
            }
            .navigationTitle(title)
            .navigationDestination(for: PokemonDetailKey.self) { key in
                PokemonDetailView(viewModel: PokemonDetailViewModelFactory.shared.create(key: key))
            }
        }
    }
}

#Preview {
    ContentView(title: "Pokédex")
}
