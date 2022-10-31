import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentsProductsComponent } from './students-products.component';

describe('StudentsProductsComponent', () => {
  let component: StudentsProductsComponent;
  let fixture: ComponentFixture<StudentsProductsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StudentsProductsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StudentsProductsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
