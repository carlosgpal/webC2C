import React, { useState, useEffect } from "react";
import { Row, Col, Container, Badge, CardTitle } from "reactstrap";
import { getAllProducts } from "../utils/apicalls.js";

import Header from "./Header.jsx";
import CardProduct from "./CardProduct.jsx";
import "./ProductList.css";

export default function ProductList() {
    const [products, setProducts] = useState(null);

    const getProducts = () => {
        getAllProducts().then((products) => {
            setProducts(products);
        });
    };

    useEffect(() => {
        getProducts();
    }, []);

    return products === null ? (
        <div>
            <Header />
            <h1 className="loading-text">Loading...</h1>
        </div>
    ) : (
        <div>
            <Header />
            <Container>
                <CardTitle tag="h1" className="text-center">
                    <Badge pill color="dark">
                        Total products found: {products.length}
                    </Badge>
                </CardTitle>
                {/* Use the CSS class for the scrollable container */}
                <div className="cards-container">
                    <div style={{ display: "flex", flexWrap: "wrap", justifyContent: "center" }}>
                        {products.map((product) => (
                            <CardProduct key={product.idproduct} product={product} />
                        ))}
                    </div>
                </div>
            </Container>
        </div>
    );
}