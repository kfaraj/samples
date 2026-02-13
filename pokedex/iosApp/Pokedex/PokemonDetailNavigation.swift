struct PokemonDetailRoute {
    let id: Int
}

extension PokemonDetailRoute: Hashable {
    static func == (lhs: PokemonDetailRoute, rhs: PokemonDetailRoute) -> Bool {
        return lhs.id == rhs.id
    }

    func hash(into hasher: inout Hasher) {
        hasher.combine(id)
    }
}
