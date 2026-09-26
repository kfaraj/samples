import KMPObservableViewModelSwiftUI
import Shared
import SwiftUI

struct PokemonListView: View {
    @StateViewModel var viewModel: PokemonListViewModel
    let onItemClick: (Int?) -> Void

    var body: some View {
        PokemonListViewSnapshot(
            itemSnapshotList: viewModel.itemSnapshotList as! [PokemonListItemUiState?],
            onItemAccess: { index in
                viewModel.itemAccessed(index: Int32(index))
            },
            onItemClick: onItemClick
        )
    }
}

struct PokemonListViewSnapshot: View {
    let itemSnapshotList: [PokemonListItemUiState?]
    let onItemAccess: (Int) -> Void
    let onItemClick: (Int?) -> Void

    var body: some View {
        ScrollView {
            LazyVGrid(columns: [GridItem(.flexible()), GridItem(.flexible())]) {
                ForEach(itemSnapshotList.indices, id: \.self) { index in
                    let _ = onItemAccess(index)
                    let item = itemSnapshotList[index]
                    PokemonListItemView(uiState: item)
                        .onTapGesture {
                            onItemClick((item?.id).map { Int($0) })
                        }
                        .padding()
                }
            }
            .padding()
        }
    }
}

#Preview {
    PokemonListViewSnapshot(
        itemSnapshotList: [
            PokemonListItemUiState(
                id: 1,
                name: "Bulbasaur",
                sprite: "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
            )
        ],
        onItemAccess: { index in
        },
        onItemClick: { itemId in
        }
    )
}
