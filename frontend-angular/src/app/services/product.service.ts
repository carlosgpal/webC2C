import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  getAllProducts(): Observable<any> {
    return this.http.get(`${this.baseUrl}/api/elastic`)
  }

  searchProducts(query: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/api/elastic/search?query=${query}`)
  }

  getProductDetails(id: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/api/products/${id}`)
  }
}
