import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import MenuRoundedIcon from '@mui/icons-material/MenuRounded';
import PersonIcon from '@mui/icons-material/Person';
import ProductList from "./ProductList";
import HomeIcon from '@mui/icons-material/Home';
import ProfileComponent from './ProfileComponent';
import './NavigationComponent.css';

const NavigationComponent = () => {
    const [sidebarCollapsed, setSidebarCollapsed] = useState(false);

    return (
        <div style={{ display: 'flex', height: '100vh' }}>
            <div className={`sidebar ${sidebarCollapsed ? 'sidebar-collapsed' : ''}`}>
                <div className={`menu-icon ${sidebarCollapsed ? 'menu-icon-collapsed' : ''}`} onClick={() => setSidebarCollapsed(!sidebarCollapsed)}>
                    <MenuRoundedIcon />
                </div>
                <Link to="/" className="link">
                    <HomeIcon className="icon" />
                    {!sidebarCollapsed && 'Home'}
                </Link>
                <Link to="/profile" className="link">
                    <PersonIcon className="icon" />
                    {!sidebarCollapsed && 'Profile'}
                </Link>
            </div>
            <div className={`content ${sidebarCollapsed ? 'content-collapsed' : ''}`}>
                <Routes>
                    <Route path="/" element={
                        <ProductList>
                        </ProductList>
                    } />
                    <Route path="/profile" element={
                        <ProfileComponent>
                        </ProfileComponent>
                    } />
                </Routes>
            </div>
        </div>
    );
};

export default NavigationComponent;
