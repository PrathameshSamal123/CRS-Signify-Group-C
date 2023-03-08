import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../model/user'
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ViewRegisteredCoursesServiceService {

  constructor(private http: HttpClient) { }

  viewRegisteredCourses(user: User): Observable<any> {
    const url = 'http://localhost:8080/viewRegisteredCourses'; // replace with your API endpoint
    return this.http.post(url, user);
  }

}
