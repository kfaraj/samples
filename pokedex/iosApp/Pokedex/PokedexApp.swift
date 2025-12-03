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
                PokedexView(viewModel: SharedApplication.shared.getPokedexViewModel())
                .navigationDestination(for: PokemonRoute.self) { route in
                    PokemonView(viewModel: SharedApplication.shared.getPokemonViewModel(id: Int32(route.id)))
                }
                .navigationTitle(String(localized: "app_name"))
            }
        }
    }

}
