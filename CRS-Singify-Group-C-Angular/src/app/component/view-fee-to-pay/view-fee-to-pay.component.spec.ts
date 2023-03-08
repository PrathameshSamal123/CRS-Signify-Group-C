import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewFeeToPayComponent } from './view-fee-to-pay.component';

describe('ViewFeeToPayComponent', () => {
  let component: ViewFeeToPayComponent;
  let fixture: ComponentFixture<ViewFeeToPayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewFeeToPayComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewFeeToPayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
