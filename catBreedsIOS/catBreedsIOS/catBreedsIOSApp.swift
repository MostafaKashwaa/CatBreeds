//
//  catBreedsIOSApp.swift
//  catBreedsIOS
//
//  Created by Mostafa Kashwaa on 27/03/2021.
//

import SwiftUI
import shared

@main
struct catBreedsIOSApp: App {
    var body: some Scene {
        WindowGroup {
            ContentView(
                viewModel: ContentView.ViewModel(
                    sdk: BreedSDK(databaseDriverFactory: DatabaseDriverFactory())
                )
            )
        }
    }
}
