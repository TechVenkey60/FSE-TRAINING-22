import { TestBed } from '@angular/core/testing';

import { LibrarybookactionsService } from './librarybookactions.service';

describe('LibrarybookactionsService', () => {
  let service: LibrarybookactionsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LibrarybookactionsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
