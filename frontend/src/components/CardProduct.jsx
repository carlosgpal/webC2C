import React from 'react';
import { AiFillEye, AiFillSignal, AiFillAppstore, AiTwotoneCalendar, AiOutlineStar, AiFillAlipayCircle, AiFillAmazonSquare, AiFillBank } from "react-icons/ai";

export default function CardProduct({ product }) {
    // Destructure the props for easier access
    const { name, date, place, idproduct, description, price, tags } = product;

    const renderTags = (tags) => {
        return tags.map((tag, index) => (
            <div key={index} className="mb-1">
                <AiFillEye /> {tag.tag_name}: {tag.tag_value}<br />
            </div>
        ));
    };

    return (
        <div className="cardProduct" style={{ width: '18rem', backgroundColor: 'cyan' }}>
            <div className="card-body">
                <h6 className="card-title">{name}</h6>
                <p className="card-info">
                    <AiFillAppstore /> Id: {idproduct}<br />
                    <AiFillAlipayCircle /> Name: {name}<br />
                    <AiFillAmazonSquare /> Description: {description}<br />
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
