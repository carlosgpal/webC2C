import React, { useState, useEffect } from "react";
import { Row, Col, Container, Badge, CardTitle } from "reactstrap";
import { getAllProducts } from "../utils/apicalls.js";

import Header from "./Header.jsx";
import CardProduct from "./CardProduct.jsx";

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
            <Row>
                <Col>
                    <Header />
                </Col>
            </Row>
            <Row>
                <h1 className="loading-text">Loading...</h1>
            </Row>
        </div>
    ) : (
        <div>
            <Row>
                <Col>
                    <Header />
                </Col>
            </Row>
            <Container>
                <CardTitle tag="center">
                    <Badge pill color="dark">
                        Total products found: {products.length}
                    </Badge>
                </CardTitle>
                <Row>
                    {products.map((product) => {
                        return (
                            <Col key={product.idproduct} xs="12" sm="6" md="4" lg="3">
                                <CardProduct product={product} />
                            </Col>
                        );
                    })}
                </Row>
            </Container>
        </div>
    );
}