import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReadContentComponent } from './read-content.component';

describe('ReadContentComponent', () => {
  let component: ReadContentComponent;
  let fixture: ComponentFixture<ReadContentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReadContentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReadContentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
