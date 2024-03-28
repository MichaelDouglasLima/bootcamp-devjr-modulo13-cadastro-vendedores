import { Injectable } from '@angular/core';
import { Seller } from '../interfaces/Seller';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SellerService {

  constructor(private http: HttpClient) { }

  getSellers(): Observable<Seller[]> {
    return this.http.get<Seller[]>("http://localhost:8080/sellers");
  }

  save(seller:Seller) {
    return this.http.post<Seller>("http://localhost:8080/sellers", seller);
  }

}
