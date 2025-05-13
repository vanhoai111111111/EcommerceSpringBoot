import React, { useState } from "react";
import Signup from "./components/Signup";
import Login from "./components/Login";

const App = () => {
    const [isLogin, setIsLogin] = useState(true);

    return (
        <div>
            <button onClick={() => setIsLogin(!isLogin)}>
                {isLogin ? "Switch to Sign Up" : "Switch to Login"}
            </button>

            {isLogin ? <Login /> : <Signup />}
        </div>
    );
};

export default App;
