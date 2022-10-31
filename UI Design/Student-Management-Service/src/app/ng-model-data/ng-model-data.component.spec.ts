import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NgModelDataComponent } from './ng-model-data.component';

describe('NgModelDataComponent', () => {
  let component: NgModelDataComponent;
  let fixture: ComponentFixture<NgModelDataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NgModelDataComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NgModelDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
