import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TicketslistComponent } from './ticketslist.component';

describe('TicketslistComponent', () => {
  let component: TicketslistComponent;
  let fixture: ComponentFixture<TicketslistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TicketslistComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TicketslistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
