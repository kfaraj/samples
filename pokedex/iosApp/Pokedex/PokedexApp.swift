//
//  PokedexApp.swift
//  Pokedex
//
//  Created by Kamal Faraj on 31/07/2025.
//

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
                PokedexView()
                .navigationDestination(for: PokemonRoute.self) { route in
                    PokemonView(id: route.id)
                }
                .navigationTitle("Pokédex")
            }
        }
    }

}
