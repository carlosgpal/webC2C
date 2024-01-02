import React from 'react';
import { Link } from 'react-router-dom';
import { AiFillEye, AiFillSignal, AiFillAppstore, AiTwotoneCalendar, AiOutlineStar, AiFillAlipayCircle, AiFillAmazonSquare, AiFillBank } from "react-icons/ai";
import './styles/CardProduct.css'

export default function CardProduct({ product }) {
    const { name, date, place, idproduct, description, price, tags, rating, thumbnailimg } = product;

    const renderTags = (tags) => {
        return tags.map((tag, index) => (
            <div key={index} className="mb-1">
                <AiFillEye /> {tag.tag_name}: {tag.tag_value}<br />
            </div>
        ));
    };

    return (
        <div className="cardProduct">
            <div className="card-image">
                <Link to={`/product/${idproduct}`}>
                    <img src={thumbnailimg} alt={name} />
                </Link>
            </div>
            <div className="card-body">
                <h6 className="card-title">
                    <Link to={`/product/${idproduct}`}>{name}</Link>
                </h6>
                <p className="card-info">
                    <AiFillAmazonSquare /> {description}<br />
                    <AiFillBank /> Price: {price}<br />
                    <AiTwotoneCalendar /> Date: {new Date(date).toLocaleDateString()}<br />
                    <AiFillSignal /> Place: {place}<br />
                    <AiFillSignal /> Rating: {rating}<br />
                </p>
                <div>
                    {renderTags(tags)}
                </div>
            </div>
        </div>
    );
}
