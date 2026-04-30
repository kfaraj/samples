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
                PokemonListView() { itemId in
                    selectedId = itemId
                }
                .navigationDestination(item: $selectedId) { id in
                    PokemonDetailView(id: id) {
                        selectedId = nil
                    }
                    .navigationBarBackButtonHidden()
                }
            }
        }
    }
}
