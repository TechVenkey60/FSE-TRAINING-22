import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-pipes-demo',
  templateUrl: './pipes-demo.component.html',
  styleUrls: ['./pipes-demo.component.css']
})
export class PipesDemoComponent implements OnInit {

  employee = {
    "name": "Venkatesh Kokkanti",
    "baseSalary": 25000000000,
    "qualification": "MCA",
    "marks": 95.90/100,
    "joinedOn": Date.now(),
    "about": "Venkatesh Venkatesh Venkatesh Venkatesh Venkatesh Venkatesh Venkatesh Venkatesh "
  }

  constructor() { }

  ngOnInit(): void {
  }

}
