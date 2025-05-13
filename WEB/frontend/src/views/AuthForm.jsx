import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { handleSignup, handleLogin } from '../controllers/AuthController'; // Nhập đúng tên hàm từ AuthController

const AuthForm = () => {
    const [formData, setFormData] = useState({ username: '', password: '' });
    const [message, setMessage] = useState('');
    const [isSignup, setIsSignup] = useState(true); // Điều khiển form đăng ký hay đăng nhập
    const navigate = useNavigate(); // Để điều hướng sau khi đăng ký hoặc đăng nhập

    const handleSubmit = (e) => {
        e.preventDefault();
        if (isSignup) {
            handleSignup(formData, setMessage, navigate); // Gọi handleSignup
        } else {
            handleLogin(formData, setMessage, navigate); // Gọi handleLogin
        }
    };

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value,
        });
    };

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <h2>{isSignup ? 'Đăng ký' : 'Đăng nhập'}</h2>

                <div>
                    <label>Tên người dùng</label>
                    <input
                        type="text"
                        name="username"
                        value={formData.username}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div>
                    <label>Mật khẩu</label>
                    <input
                        type="password"
                        name="password"
                        value={formData.password}
                        onChange={handleChange}
                        required
                    />
                </div>

                <button type="submit">{isSignup ? 'Đăng ký' : 'Đăng nhập'}</button>

                <div>
                    {message && <p>{message}</p>}
                </div>
            </form>

            <button onClick={() => setIsSignup(!isSignup)}>
                {isSignup ? 'Bạn đã có tài khoản? Đăng nhập' : 'Chưa có tài khoản? Đăng ký'}
            </button>
        </div>
    );
};

export default AuthForm;
