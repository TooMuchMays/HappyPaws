document.addEventListener('DOMContentLoaded', function() {
    const urlParams = new URLSearchParams(window.location.search);
    const petId = urlParams.get('id');

    if (petId) {
        fetch(`http://localhost:9007/pets/${petId}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(pet => {
                const petDetails = document.getElementById('pet-details');
                if (!petDetails) {
                    console.error('Pet details container not found!');
                    return;
                }

                const petCard = document.createElement('div');
                petCard.classList.add('pet-card');

                const petImage = document.createElement('img');
                petImage.src = `img/${pet.imageUrl}`;
                petImage.alt = pet.petName;
                petImage.onerror = function() {
                    this.src = 'img/112.jpg';
                };
                petCard.appendChild(petImage);

                const petName = document.createElement('h3');
                petName.textContent = pet.petName;
                petCard.appendChild(petName);

                const petSpecies = document.createElement('p');
                petSpecies.textContent = `Species: ${pet.species}`;
                petCard.appendChild(petSpecies);

                const petBreed = document.createElement('p');
                petBreed.textContent = `Breed: ${pet.breed}`;
                petCard.appendChild(petBreed);

                const petDescription = document.createElement('p');
                petDescription.textContent = pet.description;
                petCard.appendChild(petDescription);

                const adoptButton = document.createElement('button');
                adoptButton.classList.add('btn', 'btn-primary', 'mt-3');
                adoptButton.textContent = 'Adopt Me';
                adoptButton.onclick = function() {
                    window.location.href = `adoption-application.html?petId=${pet.id}`;
                };
                petCard.appendChild(adoptButton);

                petDetails.appendChild(petCard);
            })
            .catch(error => console.error('Error fetching pet data:', error));
    }
});