import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../model/user'
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class AddCourseServiceService {

  constructor(private http: HttpClient) { }

  addCourse(semester: number, courseCode: string) {
    const user: User = { userId:14790 };
    const params = { semester: semester.toString(), courseCode };
    const url = 'http://localhost:8080/addCourse';
    return this.http.post(url, user, { params });
  }


}
