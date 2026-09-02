import Shared
import SwiftUI

@main
struct PokedexApp: App {
    init() {
        MainApplication.shared.startKoin()
    }

    var body: some Scene {
        WindowGroup {
            ContentView(title: "Pokédex")
                .ignoresSafeArea()
        }
    }
}
