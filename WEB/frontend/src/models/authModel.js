import axios from "axios";

const API_URL = "http://localhost:8080/api/auth";

export const signup = async (userData) => {
    try {
        const response = await axios.post(`${API_URL}/signup`, userData);
        return response.data;
    } catch (error) {
        throw new Error(error.response.data);
    }
};

export const login = async (userData) => {
    try {
        const response = await axios.post(`${API_URL}/login`, userData);
        return response.data;
    } catch (error) {
        throw new Error(error.response.data.error || "Login failed");
    }
};
