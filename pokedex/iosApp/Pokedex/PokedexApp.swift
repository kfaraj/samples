import Shared
import SwiftUI

@main
struct PokedexApp: App {
    @State private var selectedId: Int?

    init() {
        SharedApplication.shared.startKoin()
    }

    var body: some Scene {
        WindowGroup {
            NavigationStack {
                PokemonListView(viewModel: PokemonListViewModelFactory.shared.create()) { itemId in
                    selectedId = itemId
                }
                .navigationTitle("Pokédex")
                .navigationDestination(item: $selectedId) { id in
                    PokemonDetailView(viewModel: PokemonDetailViewModelFactory.shared.create(id: Int32(id)))
                }
            }
        }
    }
}
