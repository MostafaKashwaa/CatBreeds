//
//  BreedRow.swift
//  catBreedsIOS
//
//  Created by Mostafa Kashwaa on 27/03/2021.
//

import SwiftUI
import shared

struct BreedRow: View {
    var breed: Breed
    
    var body: some View {
        HStack {
            RemoteImage(url: breed.image?.url ?? "")
                .frame(width: 50, height: 50)
                .cornerRadius(5)
            VStack(alignment: .leading) {
                Text(breed.name)
                    .bold()
            }
            Spacer()
        }
        .padding(.vertical, 4)
    }
}

struct BreedRow_Previews: PreviewProvider {
    static var previews: some View {
        BreedRow(breed: TheCatApi.Companion().getSampleBreed().toDomain(unitSystem: BreedDTO.UnitSystemMetric()))
    }
}
