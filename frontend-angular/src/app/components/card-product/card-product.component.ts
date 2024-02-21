import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Product } from '../../models/product.model';

@Component({
  selector: 'app-card-product',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './card-product.component.html',
  styleUrl: './card-product.component.css'
})
export class CardProductComponent {
  @Input() product!: Product;

  renderTags(tags: { tag_name: string; tag_value: string }[]) {
    return tags.map((tag, index) => ({
      ...tag,
      icon: 'visibility',
      index
    }));
  }
}
