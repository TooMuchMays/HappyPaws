// login.js
async function login(event) {
    event.preventDefault();
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    const response = await fetch('/users/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username, password }),
    });

    if (response.ok) {
        const data = await response.json();
        localStorage.setItem('token', data.token);
        window.location.href = 'index.html'; // Redirect to home page after login
    } else {
        const errorData = await response.json();
        document.getElementById('login-error').textContent = errorData.message || 'Invalid username or password';
        document.getElementById('login-error').style.display = 'block';
    }
}

document.getElementById('login-form').addEventListener('submit', login);