// Get references to the navigation links
const homeLink = document.querySelector('nav a[href="#"]'); // Select the first link (Home)
const aboutUsLink = document.querySelector('nav a[href="#about-us"]');
const contactLink = document.querySelector('nav a[href="#contact"]');

// Add event listeners for the navigation links
homeLink.addEventListener('click', function(event) {
    event.preventDefault(); // Prevent default link behavior
    // Add code here to display the home section
    console.log("Home link clicked");
});

aboutUsLink.addEventListener('click', function(event) {
    event.preventDefault();
    // Add code here to display the about us section
    console.log("About Us link clicked");
});

contactLink.addEventListener('click', function(event) {
    event.preventDefault();
    // Add code here to display the contact section
    console.log("Contact link clicked");
});