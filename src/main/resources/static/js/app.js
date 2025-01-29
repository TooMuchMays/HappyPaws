// App.js
import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import { AuthProvider } from './context/AuthContext';
import Header from './components/Header';
import Login from './components/Login';
import Register from './components/Register';
import Home from './components/Home';

const App = () => {
    return (
        <AuthProvider>
            <Router>
                <Header />
                <Switch>
                    <Route path="/login" component={Login} />
                    <Route path="/register" component={Register} />
                    <Route path="/" component={Home} />
                </Switch>
            </Router>
        </AuthProvider>
    );
};

export default App;