import Shared
import SwiftUI

@main
struct PokedexApp: App {

    init() {
        SharedApplication.shared.startKoin()
    }

    var body: some Scene {
        WindowGroup {
            NavigationStack {
                PokemonListView(viewModel: SharedApplication.shared.getPokemonListViewModel())
                .navigationDestination(for: PokemonDetailRoute.self) { route in
                    PokemonDetailView(viewModel: SharedApplication.shared.getPokemonDetailViewModel(id: Int32(route.id)))
                }
                .navigationTitle(String(localized: "app_name"))
            }
        }
    }

}
