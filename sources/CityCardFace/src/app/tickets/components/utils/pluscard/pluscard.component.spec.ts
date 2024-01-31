import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PluscardComponent } from './pluscard.component';

describe('PluscardComponent', () => {
  let component: PluscardComponent;
  let fixture: ComponentFixture<PluscardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PluscardComponent]
    });
    fixture = TestBed.createComponent(PluscardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
