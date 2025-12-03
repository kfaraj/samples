struct PokemonRoute {
    let id: Int
}

extension PokemonRoute: Hashable {
    static func == (lhs: PokemonRoute, rhs: PokemonRoute) -> Bool {
        return lhs.id == rhs.id
    }

    func hash(into hasher: inout Hasher) {
        hasher.combine(id)
    }
}
