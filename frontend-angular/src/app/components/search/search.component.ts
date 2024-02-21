import { Component } from '@angular/core';
import { Product } from '../../models/product.model';
import { CardProductComponent } from "../card-product/card-product.component";
import { ProductService } from '../../services/product.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-search',
  standalone: true,
  templateUrl: './search.component.html',
  styleUrl: './search.component.css',
  imports: [CardProductComponent, CommonModule]
})
export class SearchComponent {
  products: Product[] = [];
  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.loadAllProducts();
  }

  loadAllProducts() {
    this.productService.getAllProducts().subscribe(data => {
      this.products = data;
    });
  }

  searchProducts(query: string) {
    this.productService.searchProducts(query).subscribe(data => {
      this.products = data;
    });
  }

}