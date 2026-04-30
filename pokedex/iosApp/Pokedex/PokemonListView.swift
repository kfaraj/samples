import Shared
import SwiftUI

struct PokemonListView: View {
    let onItemClick: (Int?) -> Void

    var body: some View {
        PokemonListViewControllerRepresentable(onItemClick: onItemClick)
            .ignoresSafeArea()
    }
}

struct PokemonListViewControllerRepresentable: UIViewControllerRepresentable {
    let onItemClick: (Int?) -> Void

    func makeUIViewController(context: Context) -> UIViewController {
        return PokemonListScreen_iosKt.PokemonListViewController() { itemId in
            onItemClick(itemId?.intValue)
        }
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
        // Do nothing.
    }
}

#Preview {
    PokemonListView() { itemId in
        // Do nothing.
    }
}
