import KMPObservableViewModelSwiftUI
import Shared
import SwiftUI

struct PokemonListView: View {
    @StateViewModel var viewModel: PokemonListViewModel
    let onItemClick: (Int?) -> Void

    var body: some View {
        ScrollView {
            LazyVGrid(columns: [GridItem(.flexible()), GridItem(.flexible())]) {
                ForEach(viewModel.pagingDataSnapshotValue.indices, id: \.self) { index in
                    let item = viewModel.getItem(index: Int32(index))
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
