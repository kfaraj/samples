# Pokédex

This sample demonstrates best practices for
[Kotlin Multiplatform](https://kotlinlang.org/multiplatform/).

## Architecture

It follows the
[guide to app architecture](https://developer.android.com/topic/architecture).

```mermaid
flowchart TB
    subgraph UI Layer
    PokemonListScreen --> PokemonListViewModel
    PokemonDetailScreen --> PokemonDetailViewModel
    end
    PokemonListViewModel --> PokemonRepository
    PokemonDetailViewModel --> PokemonRepository
    subgraph Data Layer
    PokemonRepository --> PokemonRemoteDataSource
    PokemonRepository --> PokemonLocalDataSource
    end
```

## Modularization

It follows the
[guide to app modularization](https://developer.android.com/topic/modularization).

```mermaid
flowchart TB
    :androidApp --> :feature:pokemon
    :androidApp --> :core:ui
    :feature:pokemon --> :data:pokemon
    :feature:pokemon --> :core:ui
```

## Dependency injection

It follows the principles of
[dependency injection](https://developer.android.com/training/dependency-injection)
using
[Koin](https://insert-koin.io/).

## Testing

It follows the principles of
[testing](https://developer.android.com/training/testing)
using
[MockK](https://mockk.io/)
and
[Robolectric](https://robolectric.org/).

## Screenshots

![Pokédex sample](screenshots/pokedex.webp)
