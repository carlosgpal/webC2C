import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/product.model';
import { CommonModule, DatePipe } from '@angular/common';

@Component({
  selector: 'app-card-details',
  standalone: true,
  imports: [DatePipe, CommonModule],
  templateUrl: './card-details.component.html',
  styleUrls: ['./card-details.component.css']
})
export class CardDetailsComponent implements OnInit {
  product: Product | null = null;

  constructor(
    private productService: ProductService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const id = params['idproduct'];
      if (id) {
        this.productService.getProductDetails(id).subscribe(
          (data) => {
            this.product = data;
          },
          (error) => {
            console.error('Error fetching product details:', error);
          }
        );
      }
    });
  }
}