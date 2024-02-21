export interface Product {
    idproduct: string;
    name: string;
    date: string;
    place: string;
    description: string;
    price: number;
    tags: Tag[];
    rating: number;
    thumbnailimg: string;
    images?: Image[];
}

export interface Tag {
    tag_name: string;
    tag_value: string;
}

export interface Image {
    idimage: string;
    link: string;
}