//
//  ContentView.swift
//  cat_adoptioniOS
//
//  Created by Mostafa Kashwaa on 23/03/2021.
//

import SwiftUI
import shared

struct ContentView: View {
    @EnvironmentObject var repository : Repo
    var body: some View {
        NavigationView {
            List {
                ForEach(repository.dummyRepository.getAllBreeds(), id: \.id) { breed in
                    BreedRow(breed: breed)
                }
            }
            .navigationTitle("Breeds")
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
            .environmentObject(ContentView.Repo())
    }
}

extension ContentView {
    class Repo: ObservableObject {
        @Published var dummyRepository = PetDummyRepository()
    }
}
