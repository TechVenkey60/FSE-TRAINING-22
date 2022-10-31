import { Component, OnInit } from '@angular/core';
import { ProductDetailsService } from '../product-details.service';

@Component({
  selector: 'app-ng-model-data',
  templateUrl: './ng-model-data.component.html',
  styleUrls: ['./ng-model-data.component.scss']
})
export class NgModelDataComponent implements OnInit {

  message:any="Venkat"
  dbConnection:any
  constructor(productsConnection : ProductDetailsService) {
    this.dbConnection = productsConnection.getDBConnection();
   }

  ngOnInit(): void {
  }

}
