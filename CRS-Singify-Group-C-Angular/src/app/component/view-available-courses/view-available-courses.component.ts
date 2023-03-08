import { Component, OnInit } from '@angular/core';
import { StudentServiceService } from 'src/app/service/student-service.service';

@Component({
  selector: 'app-view-available-courses',
  templateUrl: './view-available-courses.component.html',
  styleUrls: ['./view-available-courses.component.css']
})
export class ViewAvailableCoursesComponent implements OnInit {

  getData: any[] = [];
  constructor(private _httpService:StudentServiceService) { }

  ngOnInit(): void {
    this.viewAvailableCourses();
  }

  viewAvailableCourses(){
    this._httpService.viewAvailableCourses( ).subscribe((res : any[])=>{
             console.log(res);
       this.getData = res;
    });
}
}
