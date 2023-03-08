import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewRegisteredCoursesComponent } from './view-registered-courses.component';

describe('ViewRegisteredCoursesComponent', () => {
  let component: ViewRegisteredCoursesComponent;
  let fixture: ComponentFixture<ViewRegisteredCoursesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewRegisteredCoursesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewRegisteredCoursesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
