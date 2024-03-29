import { Injectable } from '@angular/core';
import { Seller } from '../interfaces/Seller';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SellerService {

  constructor(private http: HttpClient) { }

  save(seller:Seller) {
    return this.http.post<Seller>("http://localhost:8080/sellers", seller);
  }

  getSellers(): Observable<Seller[]> {
    return this.http.get<Seller[]>("http://localhost:8080/sellers");
  }

  update(seller:Seller) {
    return this.http.put<Seller>(`http://localhost:8080/sellers/${seller.id}`, seller);
  }

  delete(seller:Seller) {
    return this.http.delete<void>(`http://localhost:8080/sellers/${seller.id}`);
  }

}
