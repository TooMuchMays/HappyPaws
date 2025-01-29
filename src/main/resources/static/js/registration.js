// registration.js
document.getElementById('userType').addEventListener('change', function() {
  const role = this.value;
  document.getElementById('shelter-fields').style.display = role === 'SHELTER' ? 'block' : 'none';
  document.getElementById('adopter-fields').style.display = role === 'ADOPTER' ? 'block' : 'none';
});

document.getElementById('register-form').addEventListener('submit', async function(event) {
  event.preventDefault();
  const username = document.getElementById('username').value;
  const password = document.getElementById('password').value;
  const email = document.getElementById('email').value;
  const firstName = document.getElementById('firstName').value;
  const lastName = document.getElementById('lastName').value;
  const address = document.getElementById('address').value;
  const phone = document.getElementById('phone').value;
  const userType = document.getElementById('userType').value;

  let body = { username, password, email, firstName, lastName, address, phone, userType };

  if (userType === 'SHELTER') {
    body.shelterName = document.getElementById('shelterName').value;
    body.location = document.getElementById('location').value;
    body.webSite = document.getElementById('webSite').value;
    body.operatingHours = document.getElementById('operatingHours').value;
  } else if (userType === 'ADOPTER') {
    body.housingType = document.getElementById('housingType').value;
  }

  try {
    const response = await fetch('http://localhost:9007/users/register', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(body),
    });

    if (response.ok) {
      const result = await response.json();
      alert('Registration successful: ' + JSON.stringify(result));
    } else {
      const errorData = await response.json();
      alert('Registration failed: ' + JSON.stringify(errorData));
    }
  } catch (error) {
    console.error('Error:', error);
    alert('An error occurred: ' + error.message);
  }
});

async function registerUser() {
  // Prepare the registration data
  const registrationData = {
    username: 'newuser',
    password: 'password123',
    email: 'newuser@example.com',
    userType: 'ADOPTER',
    // Add other required fields as needed
  };

  try {
    // Send the registration request
    const response = await fetch('http://localhost:9007/users/register', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(registrationData)
    });

    // Handle the response
    if (response.ok) {
      const data = await response.json();
      console.log('Registration successful:', data);
    } else {
      const errorData = await response.json();
      console.error('Registration failed:', errorData);
    }
  } catch (error) {
    console.error('Error:', error);
  }
}

// Call the function to register the user
registerUser();