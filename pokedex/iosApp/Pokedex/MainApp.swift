import Shared
import SwiftUI

@main
struct MainApp: App {
    init() {
        MainApplication.shared.startKoin()
    }

    var body: some Scene {
        WindowGroup {
            MainView()
        }
    }
}
