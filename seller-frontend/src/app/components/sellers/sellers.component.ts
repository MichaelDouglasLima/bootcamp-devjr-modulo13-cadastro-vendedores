import { Component, OnInit } from '@angular/core';
import { Seller } from '../../interfaces/Seller';
import { SellerService } from '../../services/seller.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-sellers',
  templateUrl: './sellers.component.html',
  styleUrl: './sellers.component.css'
})
export class SellersComponent implements OnInit {

  sellers: Seller[] = [];

  seller: Seller = {} as Seller;

  deleteSeller: Seller = {} as Seller;

  showForm: boolean = false;

  isEditing: boolean = false;

  genders: string[] = ['Masculino', 'Feminino'];

  constructor(private sellerService: SellerService, private modalService: NgbModal) {

  }

  ngOnInit(): void {
    this.loadSellers();
  }

  create() {
    this.showForm = true;
  }

  saveSeller(save: boolean) {
    if (save) {
      if (this.isEditing) {
        this.sellerService.update(this.seller).subscribe();
      }
      else {
        this.sellerService.save(this.seller).subscribe({
          next: data => {
            this.sellers.push(data);
          }
        });
      }
    }

    this.seller = {} as Seller;
    this.showForm = false;
    this.isEditing = false;
  }

  loadSellers() {
    this.sellerService.getSellers().subscribe(
      {
        next: data => { this.sellers = data }
      }
    );
  }

  edit(seller: Seller) {
    this.seller = seller;
    this.showForm = true;
    this.isEditing = true;
  }

  delete(modal: any, seller: Seller) {
    this.deleteSeller = seller;
    this.modalService.open(modal).result.then(
      (confirm) => {
        if (confirm) {
          this.sellerService.delete(seller).subscribe({
            next: () => {
              this.sellers = this.sellers.filter(s => s.id !== seller.id);
            }
          });
        }
      }
    );
  }

}
