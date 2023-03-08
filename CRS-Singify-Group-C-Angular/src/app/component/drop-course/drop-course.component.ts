import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';
import { DropCourseServiceService } from 'src/app/service/drop-course-service.service';

@Component({
  selector: 'app-drop-course',
  templateUrl: './drop-course.component.html',
  styleUrls: ['./drop-course.component.css']
})
export class DropCourseComponent implements OnInit {
  courseCode: string='';
  getData: any[] = [];
  constructor(private myService: DropCourseServiceService) {

  }

  ngOnInit(): void {
  }

  dropCourse() {
    const user: User = { userId: 14790 };
    const params = { courseCode: this.courseCode };
    this.myService.dropCourse(this.courseCode).subscribe(
      () => alert('Course Dropped Successfully'),
      (error: HttpErrorResponse) => {
        if (error.status !== 200) {
          alert('Course Could Not Be Dropped...Please Enter Valid Course Code');
        }
        else{
          alert('Course Dropped Successfully');
        }
      }
    );
  }

}
