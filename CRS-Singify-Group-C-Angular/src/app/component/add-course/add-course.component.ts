import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';
import { AddCourseServiceService } from 'src/app/service/add-course-service.service';

@Component({
  selector: 'app-add-course',
  templateUrl: './add-course.component.html',
  styleUrls: ['./add-course.component.css']
})
export class AddCourseComponent implements OnInit {
  semester: number =0;
  courseCode: string='';
getData: any[] = [];
  constructor(private myService: AddCourseServiceService) {

  }


  ngOnInit(): void {
    // this.addCourse();
  }

  addCourse() {
    const user: User = { userId: 14790 };
    const params = { semester: this.semester, courseCode: this.courseCode };
    this.myService.addCourse(this.semester, this.courseCode).subscribe(
      () => alert('Course Added Successfully'),
      (error: HttpErrorResponse) => {
        if (error.status !== 201) {
          alert('Course Could Not Be Added');
        }
        else{
          alert('Course Added Successfully');
        }
      }
    );
  }
  

}
// error => alert(`Course Could Not Be Added': ${error}`)