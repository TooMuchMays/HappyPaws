/* Auth Pages Common Styles */
.auth-container {
    max-width: 800px;
    margin: 2rem auto;
    padding: 0 1rem;
}

/* Card Styling */
.auth-container .card {
    border: none;
    border-radius: 10px;
    box-shadow: 0 2px 10px rgba(0,0,0,0.1);
}

.auth-container .card-header {
    background: #4CAF50;
    border-radius: 10px 10px 0 0;
    padding: 1.5rem;
}

.auth-container .card-header h2 {
    font-size: 1.5rem;
    color: white;
    margin: 0;
}

.auth-container .card-body {
    padding: 2rem;
}

.auth-container .card-footer {
    background: #f8f9fa;
    border-top: 1px solid #eee;
    padding: 1rem;
}

/* Form Sections */
.form-section {
    margin-bottom: 2rem;
    padding-bottom: 1rem;
    border-bottom: 1px solid #eee;
}

.section-title {
    color: #4CAF50;
    font-size: 1.1rem;
    margin-bottom: 1.5rem;
}

/* Form Groups */
.form-group {
    margin-bottom: 1.5rem;
}

.form-group label {
    font-weight: 500;
    color: #333;
    margin-bottom: 0.5rem;
    display: block;
}

.form-control {
    border-radius: 6px;
    border: 1px solid #ddd;
    padding: 0.75rem 1rem;
    transition: all 0.3s ease;
}

.form-control:focus {
    border-color: #4CAF50;
    box-shadow: 0 0 0 0.2rem rgba(76, 175, 80, 0.25);
}

/* Custom Select Styling */
select.form-control {
    -webkit-appearance: none;
    -moz-appearance: none;
    appearance: none;
    background-image: url("data:image/svg+xml;charset=utf8,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 4 5'%3E%3Cpath fill='%23333' d='M2 0L0 2h4zm0 5L0 3h4z'/%3E%3C/svg%3E");
    background-repeat: no-repeat;
    background-position: right 0.75rem center;
    background-size: 8px 10px;
    padding-right: 2rem;
}

/* Button Styling */
.btn-primary {
    background-color: #4CAF50;
    border-color: #4CAF50;
    padding: 0.75rem 1.5rem;
    font-weight: 500;
    transition: all 0.3s ease;
}

.btn-primary:hover {
    background-color: #388E3C;
    border-color: #388E3C;
    transform: translateY(-1px);
}

/* Password Toggle Button */
.input-group-append .btn {
    border-color: #ddd;
    background: #f8f9fa;
}

.input-group-append .btn:hover {
    background: #e9ecef;
}

/* Remember Me Checkbox */
.custom-control-label {
    font-weight: normal;
    color: #666;
}

.custom-control-input:checked ~ .custom-control-label::before {
    background-color: #4CAF50;
    border-color: #4CAF50;
}

/* Error Alert */
.alert {
    border-radius: 6px;
    padding: 1rem;
    margin-bottom: 1.5rem;
}

.alert-danger {
    background-color: #fff2f2;
    border-color: #ffcdd2;
    color: #d32f2f;
}

/* Links */
.card-footer a {
    color: #4CAF50;
    text-decoration: none;
    transition: color 0.3s ease;
}

.card-footer a:hover {
    color: #388E3C;
    text-decoration: underline;
}

/* Responsive Adjustments */
@media (max-width: 768px) {
    .auth-container {
        margin: 1rem auto;
    }

    .auth-container .card-body {
        padding: 1.5rem;
    }

    .form-group {
        margin-bottom: 1rem;
    }

    .btn {
        padding: 0.5rem 1rem;
    }
}

/* Loading State */
.form-loading {
    position: relative;
    pointer-events: none;
    opacity: 0.7;
}

.form-loading::after {
    content: '';
    position: absolute;
    top: 50%;
    left: 50%;
    width: 1.5rem;
    height: 1.5rem;
    border: 2px solid #f3f3f3;
    border-top: 2px solid #4CAF50;
    border-radius: 50%;
    animation: spin 1s linear infinite;
    transform: translate(-50%, -50%);
}

@keyframes spin {
    0% { transform: translate(-50%, -50%) rotate(0deg); }
    100% { transform: translate(-50%, -50%) rotate(360deg); }
}

/* Field Validation States */
.form-control.is-invalid {
    border-color: #dc3545;
    background-image: url("data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' width='12' height='12' fill='none' stroke='%23dc3545' viewBox='0 0 12 12'%3e%3ccircle cx='6' cy='6' r='4.5'/%3e%3cpath stroke-linejoin='round' d='M5.8 3.6h.4L6 6.5z'/%3e%3ccircle cx='6' cy='8.2' r='.6' fill='%23dc3545' stroke='none'/%3e%3c/svg%3e");
    background-repeat: no-repeat;
    background-position: right calc(0.375em + 0.1875rem) center;
    background-size: calc(0.75em + 0.375rem) calc(0.75em + 0.375rem);
}

.form-control.is-valid {
    border-color: #28a745;
    background-image: url("data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' width='8' height='8' viewBox='0 0 8 8'%3e%3cpath fill='%2328a745' d='M2.3 6.73L.6 4.53c-.4-1.04.46-1.4 1.1-.8l1.1 1.4 3.4-3.8c.6-.63 1.6-.27 1.2.7l-4 4.6c-.43.5-.8.4-1.1.1z'/%3e%3c/svg%3e");
    background-repeat: no-repeat;
    background-position: right calc(0.375em + 0.1875rem) center;
    background-size: calc(0.75em + 0.375rem) calc(0.75em + 0.375rem);
}

.invalid-feedback {
    display: none;
    color: #dc3545;
    font-size: 0.875rem;
    margin-top: 0.25rem;
}

.was-validated .form-control:invalid ~ .invalid-feedback,
.form-control.is-invalid ~ .invalid-feedback {
    display: block;
}