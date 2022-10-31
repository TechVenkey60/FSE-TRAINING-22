import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsermembersComponent } from './usermembers.component';

describe('UsermembersComponent', () => {
  let component: UsermembersComponent;
  let fixture: ComponentFixture<UsermembersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UsermembersComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UsermembersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
