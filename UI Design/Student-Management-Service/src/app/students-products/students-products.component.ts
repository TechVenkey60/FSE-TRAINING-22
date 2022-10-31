import { Component, OnInit } from '@angular/core';
import { ProductDetailsService } from '../product-details.service';

@Component({
  selector: 'app-students-products',
  templateUrl: './students-products.component.html',
  styleUrls: ['./students-products.component.scss']
})
export class StudentsProductsComponent implements OnInit {

  products:any

  constructor(productDetailsService : ProductDetailsService) {
    this.products= productDetailsService.getOrderedProducts();
   }

  ngOnInit(): void {
  }

}
