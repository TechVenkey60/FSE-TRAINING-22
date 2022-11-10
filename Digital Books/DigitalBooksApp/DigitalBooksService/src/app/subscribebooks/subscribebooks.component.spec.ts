import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubscribebooksComponent } from './subscribebooks.component';

describe('SubscribebooksComponent', () => {
  let component: SubscribebooksComponent;
  let fixture: ComponentFixture<SubscribebooksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SubscribebooksComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SubscribebooksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
