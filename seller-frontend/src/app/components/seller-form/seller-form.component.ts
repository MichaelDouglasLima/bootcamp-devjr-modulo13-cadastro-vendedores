import { Component, EventEmitter, Input, OnChanges, Output } from '@angular/core';
import { Seller } from '../../interfaces/Seller';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

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
      id: {value:null, disabled:true},
      name: ['', [Validators.required, Validators.minLength(5)]],
      salary: ['', [Validators.required, Validators.min(0), Validators.pattern("^[0-9]*$")]],
      bonus: ['', [Validators.required, Validators.min(0), Validators.max(100), Validators.pattern("^[0-9]*$")]],
      gender: ['', [Validators.required]]
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

  cancel() {
    this.saveEmitter.emit(false);
  }

  get pfgName() { return this.formGroupSeller.get("name") }

  get pfgSalary() { return this.formGroupSeller.get("salary") }

  get pfgBonus() { return this.formGroupSeller.get("bonus") }

  get pfgGender() { return this.formGroupSeller.get("gender") }

}
