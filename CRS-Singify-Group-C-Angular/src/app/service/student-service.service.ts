import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class StudentServiceService {
  headers = new HttpHeaders().set('Content-Type', 'application/json').set('Access-Control-Allow-Origin','*');
  constructor(private httpClient:HttpClient) { }

  viewAvailableCourses(): Observable<any>{
    // let getUsersUrl:string = "http://localhost:7000/users/list";
    let getUsersUrl:string = "http://localhost:8080/viewAvailableCourses";

    return this.httpClient.get(getUsersUrl,{headers: this.headers});
  }
}
