//
//  BreedDetails.swift
//  catBreedsIOS
//
//  Created by Mostafa Kashwaa on 28/03/2021.
//

import SwiftUI
import shared

struct BreedDetails: View {
    var breed: Breed
    
    var body: some View {
        VStack {
            RemoteImage(url: breed.image?.url ?? "")
                .scaledToFill()
                .frame(height: 300)
                .clipShape(Circle())
                .overlay(Circle().stroke(Color.gray, lineWidth: 4))
                .shadow(radius: 4)
            Text(breed.name)
                .bold()
            Text(breed.description_)
            Spacer()
        }
    }
}

struct BreedDetails_Previews: PreviewProvider {
    static var previews: some View {
        BreedDetails(breed: TheCatApi.Companion().getSampleBreed().toDomain(unitSystem: BreedDTO.UnitSystemMetric()) )
    }
}
