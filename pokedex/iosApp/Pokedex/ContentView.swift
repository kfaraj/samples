import Shared
import SwiftUI

struct ContentView: UIViewControllerRepresentable {
    let title: String

    func makeUIViewController(context: Context) -> UIViewController {
        return MainViewController_iosKt.MainViewController(title: title)
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
        // Do nothing.
    }
}
