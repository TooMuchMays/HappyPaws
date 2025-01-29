document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const shelterId = document.getElementById('shelterId').value;

    // Simulate login process
    if (shelterId) {
        // Store shelter ID in localStorage
        localStorage.setItem('shelterId', shelterId);
        // Redirect to the main page after successful login
        window.location.href = 'index.html';
    } else {
        alert('Invalid Shelter ID');
    }
});

document.getElementById('listPetForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const petData = {
        name: document.getElementById('name').value,
        species: document.getElementById('species').value,
        breed: document.getElementById('breed').value,
        age: document.getElementById('age').value,
        description: document.getElementById('description').value,
        photos: document.getElementById('photos').value,
        status: document.getElementById('status').value,
        shelterId: document.getElementById('shelterId').value
    };

    fetch('http://localhost:9007/pets/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(petData)
    })
    .then(response => response.json())
    .then(data => {
        alert('Pet listed successfully!');
        document.getElementById('listPetForm').reset();
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Failed to list pet.');
    });
});