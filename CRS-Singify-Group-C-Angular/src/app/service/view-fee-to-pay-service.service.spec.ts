import { TestBed } from '@angular/core/testing';

import { ViewFeeToPayServiceService } from './view-fee-to-pay-service.service';

describe('ViewFeeToPayServiceService', () => {
  let service: ViewFeeToPayServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ViewFeeToPayServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
