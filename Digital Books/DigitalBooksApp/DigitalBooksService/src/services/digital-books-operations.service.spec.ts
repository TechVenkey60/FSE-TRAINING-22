import { TestBed } from '@angular/core/testing';

import { DigitalBooksOperationsService } from './digital-books-operations.service';

describe('DigitalBooksOperationsService', () => {
  let service: DigitalBooksOperationsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DigitalBooksOperationsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
