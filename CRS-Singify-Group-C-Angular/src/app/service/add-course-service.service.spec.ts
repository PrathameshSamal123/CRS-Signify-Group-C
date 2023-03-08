import { TestBed } from '@angular/core/testing';

import { AddCourseServiceService } from './add-course-service.service';

describe('AddCourseServiceService', () => {
  let service: AddCourseServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AddCourseServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
