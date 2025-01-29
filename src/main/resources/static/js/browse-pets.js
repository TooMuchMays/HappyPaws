document.addEventListener('DOMContentLoaded', function() {
    const petsList = document.getElementById('pet-list');

    // Fetch user details (replace with actual user fetching logic)
    const user = {
        name: 'John Doe',
        email: 'john.doe@example.com',
        phone: '123-456-7890'
    };

    fetch('http://localhost:9007/api/pets')
        .then(response => response.json())
        .then(pets => {
            pets.forEach(pet => {
                const petCard = document.createElement('div');
                petCard.className = 'col-md-4';
                petCard.innerHTML = `
                    <div class="card mb-4">
                        <img src="images/${pet.imageUrl}" class="card-img-top" alt="${pet.petName}">
                        <div class="card-body">
                            <h5 class="card-title">${pet.petName}</h5>
                            <p class="card-text">${pet.description}</p>
                            <button class="btn btn-primary adopt-btn" data-pet-id="${pet.id}" data-pet-name="${pet.petName}">Adopt</button>
                        </div>
                    </div>
                `;
                petsList.appendChild(petCard);
            });

            // Add click event listener to adopt buttons
            document.querySelectorAll('.adopt-btn').forEach(button => {
                button.addEventListener('click', function() {
                    const petId = this.getAttribute('data-pet-id');
                    const petName = this.getAttribute('data-pet-name');
                    document.getElementById('petId').value = petId;
                    document.getElementById('petName').value = petName;

                    // Populate adopter's details
                    document.getElementById('adopterName').value = user.name;
                    document.getElementById('adopterEmail').value = user.email;
                    document.getElementById('adopterPhone').value = user.phone;

                    $('#adoptionModal').modal('show');
                });
            });
        })
        .catch(error => console.error('Error fetching pets:', error));

    document.getElementById('adoptionForm').addEventListener('submit', function(event) {
        event.preventDefault();
        const formData = new URLSearchParams(new FormData(this)).toString();
        fetch('http://localhost:9007/api/adoptions', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: formData
        })
        .then(response => {
            if (response.ok) {
                document.getElementById('successMessage').style.display = 'block';
                $('#adoptionModal').modal('hide');
            } else {
                alert('Error submitting adoption application.');
            }
        })
        .catch(error => console.error('Error:', error));
    });
});