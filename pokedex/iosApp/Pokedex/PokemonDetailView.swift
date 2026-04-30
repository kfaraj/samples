import Shared
import SwiftUI

struct PokemonDetailView: View {
    let id: Int
    let onNavigateUp: () -> Void

    var body: some View {
        PokemonDetailViewControllerRepresentable(id: id, onNavigateUp: onNavigateUp)
            .ignoresSafeArea()
    }
}

struct PokemonDetailViewControllerRepresentable: UIViewControllerRepresentable {
    let id: Int
    let onNavigateUp: () -> Void

    func makeUIViewController(context: Context) -> UIViewController {
        return PokemonDetailScreen_iosKt.PokemonDetailViewController(id: Int32(id)) {
            onNavigateUp()
        }
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
        // Do nothing.
    }
}

#Preview {
    PokemonDetailView(id: 1) {
        // Do nothing.
    }
}
