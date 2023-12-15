import React, { useState } from 'react';
import { InputGroup, Button, Input } from 'reactstrap';
import './SearchComponent.css';

const SearchComponent = ({ onSearch }) => {
    const [query, setQuery] = useState('');

    const handleSearch = () => {
        onSearch(query);
    };

    return (
        <div className="search-bar">
            <InputGroup>
                <Input
                    placeholder="Search for products..."
                    value={query}
                    onChange={(e) => setQuery(e.target.value)}
                />
                <Button color="primary" onClick={handleSearch}>Search</Button>
            </InputGroup>
        </div>
    );
};

export default SearchComponent;
