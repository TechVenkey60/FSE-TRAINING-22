import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReaderSubscribedBooksComponent } from './reader-subscribed-books.component';

describe('ReaderSubscribedBooksComponent', () => {
  let component: ReaderSubscribedBooksComponent;
  let fixture: ComponentFixture<ReaderSubscribedBooksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReaderSubscribedBooksComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReaderSubscribedBooksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
