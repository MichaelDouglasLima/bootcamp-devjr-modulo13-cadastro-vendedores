import { Component, EventEmitter, Input, OnChanges, Output } from '@angular/core';
import { Seller } from '../../interfaces/Seller';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-seller-form',
  templateUrl: './seller-form.component.html',
  styleUrl: './seller-form.component.css'
})
export class SellerFormComponent implements OnChanges{

  @Input()
  seller: Seller = {} as Seller;

  @Output()
  saveEmitter = new EventEmitter();

  formGroupSeller: FormGroup;

  constructor(private formBuilder: FormBuilder) {
    this.formGroupSeller = this.formBuilder.group({
      id: [''],
      name: [''],
      salary: [''],
      bonus: [''],
      gender: ['']
    })
  }

  ngOnChanges(): void {
    if (this.seller.id) {
      this.formGroupSeller.setValue(this.seller);
    }
  }

  save() {
    if (this.formGroupSeller.valid) {
      Object.assign(this.seller, this.formGroupSeller.value);
      this.saveEmitter.emit(true);
    }
  }

}
