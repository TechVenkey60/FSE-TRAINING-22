import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProductDetailsService {

  constructor() { }

  getOrderedProducts(){
    return [
      {
      "orderId": 12,
      "name": "T-Shirt",
      "brand": "Puma",
      "Price": 89099
    },
    {
      "orderId": 13,
      "name": "Watch",
      "brand": "Sonata",
      "Price": 899099
    },
    {
      "orderId": 14,
      "name": "shoes",
      "brand": "Luminous",
      "Price": 100000
    }
  ];
  }

  getDBConnection(){
    return "MySql DB Connection has been established.";
  }
}
