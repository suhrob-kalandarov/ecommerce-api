// API URL Configuration
const API_URL = 'http://localhost:8080/api';

// Default headers for API requests
axios.defaults.headers.common['Content-Type'] = 'application/json';

// Add token to requests if available
const token = localStorage.getItem('token');
if (token) {
    // The token already includes "Bearer " prefix from the backend
    axios.defaults.headers.common['Authorization'] = token;
}

// Add axios interceptor to handle 401 Unauthorized errors
axios.interceptors.response.use(
    response => response,
    error => {
        if (error.response && error.response.status === 401) {
            // Clear token and redirect to login
            localStorage.removeItem('token');
            localStorage.removeItem('isLoggedIn');
            window.location.href = '/login.html';
        }
        return Promise.reject(error);
    }
);