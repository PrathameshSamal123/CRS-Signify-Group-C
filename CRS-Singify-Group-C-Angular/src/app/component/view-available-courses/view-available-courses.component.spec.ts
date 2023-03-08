import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAvailableCoursesComponent } from './view-available-courses.component';

describe('ViewAvailableCoursesComponent', () => {
  let component: ViewAvailableCoursesComponent;
  let fixture: ComponentFixture<ViewAvailableCoursesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewAvailableCoursesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewAvailableCoursesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
