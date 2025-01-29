// header.js
document.addEventListener('DOMContentLoaded', function() {
    loadHeader();
});

async function loadHeader() {
    try {
        // Load header content
        const response = await fetch('header.html');
        const html = await response.text();
        document.body.insertAdjacentHTML('afterbegin', html);

        // Update active navigation item
        updateActiveNavItem();

        // Initialize auth state
        if (typeof updateAuthUI === 'function') {
            updateAuthUI();
        }
    } catch (error) {
        console.error('Error loading header:', error);
    }
}

function updateActiveNavItem() {
    const currentPage = window.location.pathname.split('/').pop() || 'index.html';
    const navLinks = document.querySelectorAll('.navbar-nav .nav-link');

    navLinks.forEach(link => {
        const href = link.getAttribute('href');
        if (href === currentPage) {
            link.parentElement.classList.add('active');
        }
    });
}

// Make header functions globally available
window.loadHeader = loadHeader;
window.updateActiveNavItem = updateActiveNavItem;