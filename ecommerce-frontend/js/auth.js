// Check if user is logged in
function checkAuth() {
    const isLoggedIn = localStorage.getItem('isLoggedIn') === 'true';
    const userDropdown = document.getElementById('userDropdown');
    const loginBtn = document.getElementById('loginBtn');

    if (isLoggedIn) {
        userDropdown.style.display = 'block';
        loginBtn.style.display = 'none';

        // Set username (in a real app, you would decode the JWT or fetch user info)
        document.getElementById('username').textContent = 'User';
    } else {
        userDropdown.style.display = 'none';
        loginBtn.style.display = 'block';
    }
}

// Logout function
function logout() {
    // Clear authentication data
    localStorage.removeItem('token');
    localStorage.removeItem('isLoggedIn');

    // Clear any other user data
    localStorage.removeItem('cart');

    // Remove Authorization header
    delete axios.defaults.headers.common['Authorization'];

    // Redirect to login page
    window.location.href = 'login.html';
}

// Initialize auth
document.addEventListener('DOMContentLoaded', function() {
    checkAuth();

    // Add logout event listener
    const logoutBtn = document.getElementById('logoutBtn');
    if (logoutBtn) {
        logoutBtn.addEventListener('click', function(e) {
            e.preventDefault();
            logout();
        });
    }
});