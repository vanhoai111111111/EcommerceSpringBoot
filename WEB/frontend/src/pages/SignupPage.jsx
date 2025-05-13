import React, { useState } from 'react';
import SignupForm from '../views/SignupForm';
import { handleSignup } from '../controllers/AuthController';
import SignupRequest from '../models/SignupRequest';

const SignupPage = () => {
    const [formData, setFormData] = useState({ name: '', email: '', password: '' });
    const [message, setMessage] = useState('');

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const request = new SignupRequest(formData.name, formData.email, formData.password);
        const res = await handleSignup(request);
        setMessage(res.message);
    };

    return (
        <div>
            <h2>Signup</h2>
            <SignupForm formData={formData} onChange={handleChange} onSubmit={handleSubmit} />
            {message && <p>{message}</p>}
        </div>
    );
};

export default SignupPage;
