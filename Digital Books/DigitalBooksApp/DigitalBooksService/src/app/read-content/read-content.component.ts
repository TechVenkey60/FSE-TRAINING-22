import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DigitalBooksOperationsService } from 'src/services/digital-books-operations.service';

@Component({
  selector: 'app-read-content',
  templateUrl: './read-content.component.html',
  styleUrls: ['./read-content.component.css']
})
export class ReadContentComponent implements OnInit {

  readerIdValue:number=0;
  readerSubScribedInfo:any;
  showData:any;

  constructor(private route: ActivatedRoute, 
              private digitalService : DigitalBooksOperationsService) { }

  ngOnInit(): void {
   this.readerIdValue = this.route.snapshot.params['subscriptionId'];
   this.digitalService.getReaderSubscribedContent(this.readerIdValue)
      .subscribe(data => {
       this.readerSubScribedInfo = data;
       this.showData = JSON.parse(this.readerSubScribedInfo);
       console.log(this.readerSubScribedInfo);
      }, error => {
        console.log(error.error);
      });
    
  }

}
