import Shared
import SwiftUI

struct MainView: View {
    var body: some View {
        MainViewControllerRepresentable(title: "Pokédex")
            .ignoresSafeArea()
    }
}

struct MainViewControllerRepresentable: UIViewControllerRepresentable {
    let title: String

    func makeUIViewController(context: Context) -> UIViewController {
        return MainViewController_iosKt.MainViewController(title: title)
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
        // Do nothing.
    }
}
