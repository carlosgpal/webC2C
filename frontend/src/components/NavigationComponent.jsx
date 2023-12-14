import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import MenuRoundedIcon from '@mui/icons-material/MenuRounded';
import LocalMall from '@mui/icons-material/LocalMall'
import ProductList from "./ProductList";
import './NavigationComponent.css';

const NavigationComponent = () => {
    const [sidebarCollapsed, setSidebarCollapsed] = useState(false);

    return (
        <div style={{ display: 'flex', height: '100vh' }}>
            <div className={`sidebar ${sidebarCollapsed ? 'sidebar-collapsed' : ''}`}>
                <div className={`menu-icon ${sidebarCollapsed ? 'menu-icon-collapsed' : ''}`} onClick={() => setSidebarCollapsed(!sidebarCollapsed)}>
                    <MenuRoundedIcon />
                </div>
                <Link to="/products" className="link">
                    <LocalMall className="icon" />
                    {!sidebarCollapsed && 'Productos'}
                </Link>
            </div>
            <div className={`content ${sidebarCollapsed ? 'content-collapsed' : ''}`}>
                <Routes>
                    <Route path="/products" element={
                        <ProductList>
                        </ProductList>
                    } />
                </Routes>
            </div>
        </div>
    );
};

export default NavigationComponent;
