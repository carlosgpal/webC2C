import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { Container, Row, Col, Badge } from 'reactstrap';
import { getProductDetails } from '../utils/apicalls.js';
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import './styles/CardDetails.css';

export default function CardDetails() {
    const { idproduct } = useParams();
    const [product, setProduct] = useState(null);

    useEffect(() => {
        getProductDetails(idproduct).then(setProduct);
    }, [idproduct]);

    if (!product) {
        return <div>Loading...</div>;
    }

    function CustomNextArrow(props) {
        const { onClick } = props;
        return (
            <div
                className="slick-next custom-arrow"
                onClick={onClick}
            />
        );
    }

    function CustomPrevArrow(props) {
        const { onClick } = props;
        return (
            <div
                className="slick-prev custom-arrow"
                onClick={onClick}
            />
        );
    }

    return (
        <Container className="product-details">
            <Row>
                <Col>
                    <h2>{product.name}</h2>
                    <p>{product.description}</p>
                    <p>Price: ${product.price}</p>
                    <p>Rating: {product.rating} / 5</p>
                    <p>Date: {new Date(product.date).toLocaleDateString()}</p>
                    <p>Place: {product.place}</p>
                </Col>
                <Col>
                    <Slider
                        dots={true}
                        infinite={true}
                        speed={500}
                        slidesToShow={1}
                        slidesToScroll={1}
                        nextArrow={<CustomNextArrow />}
                        prevArrow={<CustomPrevArrow />}
                        arrows={true}
                    >
                        {product.images.map((image) => (
                            <img key={image.idimage} src={image.link} alt={product.name} className="product-image" />
                        ))}
                    </Slider>
                </Col>
            </Row>
            <Row>
                <Col>
                    <div className="tags">
                        {product.tags.map((tag) => (
                            <Badge key={tag.idtag} color="secondary">{tag.name}</Badge>
                        ))}
                    </div>
                </Col>
            </Row>
        </Container>
    );
}