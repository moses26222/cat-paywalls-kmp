import SwiftUI
import RevenueCat

@main
struct iosAppApp: App {

    init() {
        Purchases.logLevel = .debug
        Purchases.configure(withAPIKey: "your_ios_api_key_here")
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
