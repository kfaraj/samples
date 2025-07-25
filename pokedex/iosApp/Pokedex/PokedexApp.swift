//
//  PokedexApp.swift
//  Pokedex
//
//  Created by Kamal Faraj on 09/07/2025.
//

import KMPNativeCoroutinesAsync
import KMPNativeCoroutinesCore
import Shared
import SwiftUI

@main
struct PokedexApp: App {

    private let viewModel = PokedexViewModel(
        pokemonsRepository: PokemonsRepository(
            pokemonsRemoteDataSource: NetworkFactory.shared.createPokemonsRemoteDataSource(
                pokeApiService: NetworkFactory.shared.createPokeApiService(
                    httpClient: NetworkFactory.shared.createHttpClient()
                )
            )
        )
    )

    @State private var pagingData: [Pokemon] = []

    var body: some Scene {
        WindowGroup {
            PokedexView(pagingData: pagingData)
                .task {
                    do {
                        for try await pagingData in asyncSequence(for: viewModel.pagingData) {
                            self.pagingData = pagingData
                        }
                    } catch {
                        print((error))
                    }
                }
        }
    }

}
