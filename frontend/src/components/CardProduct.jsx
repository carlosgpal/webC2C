import React from 'react';
import { AiFillEye, AiFillSignal, AiFillAppstore, AiTwotoneCalendar, AiOutlineStar, AiFillAlipayCircle, AiFillAmazonSquare, AiFillBank } from "react-icons/ai";
import './CardProduct.css'

export default function CardProduct({ product }) {
    // Destructure the props for easier access
    const { name, date, place, idproduct, description, price, tags, thumbnailimg, image } = product;

    // Function to render the tags
    const renderTags = (tags) => {
        return tags.map((tag, index) => (
            <div key={index} className="mb-1">
                <AiFillEye /> {tag.tag_name}: {tag.tag_value}<br />
            </div>
        ));
    };

    // Render the card product component
    return (
        <div className="cardProduct">
            <div className="card-image">
                <img src={thumbnailimg} alt={name} />
            </div>
            <div className="card-body">
                <h6 className="card-title">{name}</h6>
                <p className="card-info">
                    <AiFillAmazonSquare /> {description}<br />
                    <AiFillBank /> Price: {price}<br />
                    <AiTwotoneCalendar /> Date: {new Date(date).toLocaleDateString()}<br />
                    <AiFillSignal /> Place: {place}<br />
                </p>
                <div>
                    {renderTags(tags)}
                </div>
            </div>
        </div>
    );
}
