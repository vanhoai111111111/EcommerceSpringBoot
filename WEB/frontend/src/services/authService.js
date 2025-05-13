// src/services/authService.js
import axios from 'axios';

const API_URL = 'http://localhost:8080';

export const signup = async (formData) => {
    // Thực hiện gọi API để đăng ký
    const response = await fetch('/api/auth/signup', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
    });
    const data = await response.json();
    return data.message; // Trả về thông báo
};

export const login = async (formData) => {
    // Thực hiện gọi API để đăng nhập
    const response = await fetch('/api/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
    });
    const data = await response.json();
    return data.message; // Trả về thông báo
};

