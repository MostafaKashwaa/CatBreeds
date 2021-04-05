//
//  ContentView.swift
//  catBreedsIOS
//
//  Created by Mostafa Kashwaa on 27/03/2021.
//

import SwiftUI
import shared

struct ContentView: View {
    @ObservedObject private(set) var viewModel: ViewModel
//    @EnvironmentObject var repository : Repo
    var body: some View {
        NavigationView {
            switch viewModel.status {
            case .loading:
                Text("Loading")
            case .result(let breeds):
                List {
                    ForEach(breeds, id: \.id) { breed in
                        NavigationLink(
                            destination: BreedDetails(breed: breed)) {
                            BreedRow(breed: breed)
                        }
                    }
                    .onDelete(perform: { indexSet in
                        
                    })
                }
                .navigationTitle("Breeds")
            case .error(let description):
                Text(description)
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView(viewModel: .init(sdk: BreedSDK(databaseDriverFactory: DatabaseDriverFactory())))
    }
}

extension ContentView {
    enum Status {
        case loading
        case result([Breed])
        case error(String)
    }
    
    
    class ViewModel: ObservableObject {
        let sdk: BreedSDK
        @Published var status = Status.loading
        
        init(sdk: BreedSDK) {
            self.sdk = sdk
            self.laodBreeds(forceReload: false)
        }
        
        func laodBreeds(forceReload: Bool) {
            self.status = .loading
            sdk.getBreeds(forceReload: forceReload) { breeds, error in
                if let breeds = breeds {
                    self.status = .result(breeds)
                } else {
                    self.status = .error(error?.localizedDescription ?? "error")
                }
            }
        }
    }
}
