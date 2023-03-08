import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../model/user'
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DropCourseServiceService {

  constructor(private http: HttpClient) { }

  dropCourse(courseCode: string) {
    const user: User = { userId:14790 };
    const params = {courseCode };
    const url = 'http://localhost:8080/dropCourse';
    return this.http.post(url, user, { params });
  }

}
