//
//  cat_adoptioniOSApp.swift
//  cat_adoptioniOS
//
//  Created by Mostafa Kashwaa on 23/03/2021.
//

import SwiftUI
import shared

@main
struct cat_adoptioniOSApp: App {
    @StateObject var repository = ContentView.Repo()
    var body: some Scene {
        WindowGroup {
            ContentView()
                .environmentObject(repository)
        }
    }
}
