import { Component, OnInit } from '@angular/core';
import { Seller } from '../../interfaces/Seller';
import { SellerService } from '../../services/seller.service';

@Component({
  selector: 'app-sellers',
  templateUrl: './sellers.component.html',
  styleUrl: './sellers.component.css'
})
export class SellersComponent implements OnInit{

  sellers: Seller[] = [];

  seller: Seller = {} as Seller;

  constructor(private sellerService: SellerService) {

  }

  ngOnInit(): void {
    this.loadSellers();
  }

  saveSeller() {
    this.sellerService.save(this.seller).subscribe({
      next: data => {
        this.sellers.push(data);
        this.seller = {} as Seller;
      }
    });

  }

  loadSellers() {
    this.sellerService.getSellers().subscribe(
      {
        next: data => { this.sellers = data }
      }
    );
  }

}
