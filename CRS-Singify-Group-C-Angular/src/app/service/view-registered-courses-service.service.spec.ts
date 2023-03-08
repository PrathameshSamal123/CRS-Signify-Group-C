import { TestBed } from '@angular/core/testing';

import { ViewRegisteredCoursesServiceService } from './view-registered-courses-service.service';

describe('ViewRegisteredCoursesServiceService', () => {
  let service: ViewRegisteredCoursesServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ViewRegisteredCoursesServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
