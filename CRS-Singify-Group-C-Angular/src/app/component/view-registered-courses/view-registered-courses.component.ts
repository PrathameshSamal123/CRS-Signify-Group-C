import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';
import { ViewRegisteredCoursesServiceService } from 'src/app/service/view-registered-courses-service.service';

@Component({
  selector: 'app-view-registered-courses',
  templateUrl: './view-registered-courses.component.html',
  styleUrls: ['./view-registered-courses.component.css']
})
export class ViewRegisteredCoursesComponent implements OnInit {
  getData: any[] = [];
  constructor(private myService: ViewRegisteredCoursesServiceService) {}

  ngOnInit(): void {
    this.onSubmit();
  }

  onSubmit() {
    const user: User = { userId:14790 };
    this.myService.viewRegisteredCourses(user).subscribe((res : any[]) => {
      console.log(res);
      this.getData = res;
    });



}
}
