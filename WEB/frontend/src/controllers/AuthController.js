import { signup, login } from "../models/authModel";

export const handleSignup = async (userData) => {
    try {
        const result = await signup(userData);
        return result;
    } catch (error) {
        throw new Error(error.message);
    }
};

export const handleLogin = async (userData) => {
    try {
        const result = await login(userData);
        return result;
    } catch (error) {
        throw new Error(error.message);
    }
};
